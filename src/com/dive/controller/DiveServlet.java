package com.dive.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dive.model.DiveService;
import com.dive.model.DiveVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.util.MyUtil;
import org.json.JSONArray;
import org.json.JSONObject;



//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
//		System.out.println("進入DoPOST");

		String action = req.getParameter("action");
		//================== Search by Name ==============================
		if("search".equals(action)) {
			DiveService divSvc22 = new DiveService();
			List<DiveVO> searchedDive= divSvc22.getListByDiveName(req.getParameter("dive_name"));
			Gson gson = new Gson();
			String jsonStr1 = gson.toJson(searchedDive);
			PrintWriter pw = res.getWriter();
			pw.print(jsonStr1);
		}
		
		//================== Search by Area ==============================		
		if("searchByArea".equals(action)) {
			System.out.println("進入Search by Area action");
			System.out.println("weather_area_no:"+req.getParameter("weather_area_no"));
			String weather_area_no = req.getParameter("weather_area_no");
			DiveService divSvc22 = new DiveService();
			
			//"請選擇"選項顯示所有區域
			if("all".equals(weather_area_no)) {
				List<DiveVO> diveArray = divSvc22.getAll();
				List<DiveVO> allowedDiveArray = new ArrayList<DiveVO>();
				//過濾不可顯示的潛點
				for(DiveVO allowedDive :diveArray) {
					if(allowedDive.getDive_status()==0) {
						allowedDiveArray.add(allowedDive);
					}
				}				
				//顯示所有潛點 
				Gson gson = new Gson();
				String jsonstr = gson.toJson(allowedDiveArray);
				PrintWriter pw = res.getWriter();
				pw.print(jsonstr);								
			}else {
				//搜尋其他潛水區域
				List<DiveVO> searchedDiveArray = divSvc22.getAllByWeatherArea(req.getParameter("weather_area_no"));
				List<DiveVO> allowedDiveArray = new ArrayList<DiveVO>();
				for(DiveVO allowedDive :searchedDiveArray) {
					if(allowedDive.getDive_status()==0) {
						allowedDiveArray.add(allowedDive);
					}
				}

				Gson gson = new Gson();
				String jsonStr1 = gson.toJson(allowedDiveArray);
				PrintWriter pw = res.getWriter();
				pw.print(jsonStr1);				
			}

		}		
		
		
		//================= 地圖初始化 ===============================
		
		if("test".equals(action)) {
			
			DiveService divSvc22 = new DiveService();
			List<DiveVO> diveArray = divSvc22.getAll();
			List<DiveVO> allowedDiveArray = new ArrayList<DiveVO>();
			//過濾不可顯示的潛點
			for(DiveVO allowedDive :diveArray) {
				if(allowedDive.getDive_status()==0) {
					allowedDiveArray.add(allowedDive);
				}
			}

			
//			顯示所有潛點 
			Gson gson = new Gson();
			String jsonstr = gson.toJson(allowedDiveArray);
			PrintWriter pw = res.getWriter();
			pw.print(jsonstr);
			
		}

		//================================== insert ===========================================

        if ("insert".equals(action)) { 

			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsForAddDive", errorMsgs);

			try {
				/***********************1.接收前端參數&驗證*************************/
				//所在區域(氣象區域)
				String weather_area_no = req.getParameter("weather_area_no");
				if (weather_area_no == null || weather_area_no.trim().length() == 0) {
					errorMsgs.add("請選擇潛點區域");
				} 
				
				//潛點名稱
				String dive_name = req.getParameter("dive_name");

				if (dive_name == null || dive_name.trim().length() == 0) {
					errorMsgs.add("請輸入名稱");
				} 
				
				//潛點描述
				String dive_des = req.getParameter("dive_des").trim();
				if (dive_des == null || dive_des.trim().length() == 0) {
					errorMsgs.add("請輸入描述");
				}

				//潛點圖片
				Part myPart = req.getPart("dive_img");
				byte[] dive_img = MyUtil.pathToByteArray(myPart);

				//潛點狀態(0:顯示 1:隱藏)		
				Integer dive_status = 1;
				
				//潛點經度
				String dive_lat = req.getParameter("dive_lang").trim();
				if (dive_lat == null || dive_lat.trim().length() == 0) {
					errorMsgs.add("請輸入經度");
				}

				//潛點緯度
				String dive_lang = req.getParameter("dive_lat").trim();
				if (dive_lang == null || dive_lang.trim().length() == 0) {
					errorMsgs.add("請輸入緯度");
				}

				DiveVO diveVO = new DiveVO();
				diveVO.setWeather_area_no(weather_area_no);
				diveVO.setDive_name(dive_name);
				diveVO.setDive_des(dive_des);
				diveVO.setDive_img(dive_img);
				diveVO.setDive_status(dive_status);
				diveVO.setDive_lat(dive_lat);
				diveVO.setDive_lang(dive_lang);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diveVO", diveVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/dive/addDiveMap.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.將資料傳入資料庫***************************************/
				DiveService divSvc = new DiveService();
				diveVO = divSvc.addDive(weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang);
				
				/***************************3.將處理完的資料回傳前端(Send the Success view)***********/
				String url = "/front-end/dive/GoogleMapTest2.jsp";
				req.setAttribute("isInserted", 1);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************任何其他錯誤**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/dive/addDiveMap.jsp");
				failureView.forward(req, res);
			}
		}
        		

		
	}//doPost()

}
