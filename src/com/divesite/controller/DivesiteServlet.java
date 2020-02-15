package com.divesite.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.dive.model.*;
import com.dive.model.DiveService;
import com.dive.model.DiveVO;
import com.dive.model.DiveService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.util.MyUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, 
maxRequestSize = 5 * 5 * 1024 * 1024)

public class DivesiteServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println(action);	

//查詢未審核潛點(依狀態分)---------------------------------------------------------------

		if ("getAllByStatus".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer dive_status = new Integer(req.getParameter("dive_status"));
System.out.println(dive_status);
				DiveService diveSvc = new DiveService();
		
				List<DiveVO> list = diveSvc.getAllByStatus(dive_status);
System.out.println(list);
				
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/manage/dive_status.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/dive_status.jsp");
				failureView.forward(req, res);
			}
		}


//修改潛點狀態------------------------------------------------------------------
		if ("updateDiveStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			DiveService diveSvc = new DiveService();
			
			String dive_no = req.getParameter("dive_no");
			System.out.println("AJAX dive = " + dive_no);
			DiveVO dive_update = diveSvc.getOneDive(dive_no);

				/*-------------- 1.接收請求參數 - 輸入格式的錯誤處理 -----------------*/
			try {
				String divestatus=req.getParameter("dive_status");
				System.out.println(divestatus+"狀態");
				// 會員權限(0:開放, 1:關閉)
				int dive_status = new Integer(divestatus);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diveVO", dive_update); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/manage/dive_status.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
/*----------------- 2.開始新增資料到資料庫-----------------------*/
				diveSvc.updateDive(dive_update.getDive_no(), dive_update.getWeather_area_no(), dive_update.getDive_name(), dive_update.getDive_des(), 
						dive_update.getDive_img(), dive_status, dive_update.getDive_lat(), dive_update.getDive_lang());
				
res.setContentType("text/html; charset=utf-8");
				
				PrintWriter out = res.getWriter();
				out.println("\n" + "\n" + "\n" + "\n" + "\t" + "\t" + "\t" + "\t" +"* *  狀   態   修   改   成   功   !  * *");

				/*------------------------其他可能的錯誤處理-----------------------*/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/manage/dive_status.jsp");
				failureView.forward(req, res);
			}
			
//新增潛點----------------------------------------------------------------------



	}
}
}
