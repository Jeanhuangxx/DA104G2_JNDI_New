package com.equipment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.equip_odlist.model.EquipOdlistVO;
import com.equipment.model.*;
import com.util.MyUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EquipmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String action = req.getParameter("action");
		List<EquipOdlistVO> buylist = (Vector<EquipOdlistVO>) session.getAttribute("shoppingcart");

		//　後台 查詢單筆裝備明細
		if ("getOne_For_Detail".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); 
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			Integer equip_status = null;
			try {
				equip_status = new Integer(req.getParameter("equip_status"));
				req.setAttribute("equip_status", equip_status);
				
			} catch (NumberFormatException e) {
				
			}
			
			
			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理
				String equip_no = req.getParameter("equip_no");
				if (equip_no == null || (equip_no.trim()).length() == 0)
					errorMsgs.add("請輸入裝備編號");

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// 2.開始查詢資料
				EquipmentService equipSvc = new EquipmentService();
				EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);
				if (equipmentVO == null)
					errorMsgs.add("查無資料");

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// 3.查詢完成,準備轉交
				req.setAttribute("equipmentVO", equipmentVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equipment/listOneDetail.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/select_page.jsp");
				failureView.forward(req, res);
			}

		}
		
		
		

		//　後台 從查詢單筆裝備明細頁面 去修改裝備資料
		if ("getOne_For_Update".equals(action)) { // 來自listOneDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); 
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			Integer equip_status = null;
			try {
				equip_status = new Integer(req.getParameter("equip_status"));
				req.setAttribute("equip_status", equip_status);
				
			} catch (NumberFormatException e) {
				
			}
			
			
			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理
				String equip_no = req.getParameter("equip_no");

				// 2.開始查詢資料
				EquipmentService equipSvc = new EquipmentService();
				EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);

				// 3.查詢完成,準備轉交
				req.setAttribute("equipmentVO", equipmentVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equipment/update_equip_input.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/listAllEquip.jsp");
				failureView.forward(req, res);
			}

		}

		//　後台 送出修改
		if ("update".equals(action)) { // 來自update_equip_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); 
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			Integer status = null;
			try {
				status = new Integer(req.getParameter("status"));
				req.setAttribute("status", status);
				
			} catch (NumberFormatException e) {
				
			}
			
			
			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理
				String equip_no = req.getParameter("equip_no");

				//　裝備名稱
				String equip_name = req.getParameter("equip_name");
				String equip_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{2,50}$";
				if (equip_name == null || equip_name.trim().length() == 0) {
					errorMsgs.add("裝備名稱請勿空白");
				} else if (!equip_name.trim().matches(equip_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("裝備名稱：只能是中、英文字母、數字和_和空白, 且長度必需在2到50之間");
				}

				//　裝備描述
				String equip_des = req.getParameter("equip_des");
				if (equip_des == null || equip_des.trim().length() == 0)
					errorMsgs.add("裝備描述請勿空白");

				// equip_img1
				Part part1 = req.getPart("equip_img1");

				byte[] buf1 = null;
				byte[] equip_img1 = null;

				InputStream in1 = part1.getInputStream();
				buf1 = new byte[in1.available()];
				if (buf1.length != 0) {
					String type = part1.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) 
						errorMsgs.add("檔案格式錯誤");
					
					in1.read(buf1);
					equip_img1 = buf1;
					in1.close();
				} else if (buf1.length == 0) {
					equip_no = req.getParameter("equip_no");
					EquipmentService equipSvc = new EquipmentService();
					EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);
					buf1 = equipmentVO.getEquip_img1();
					equip_img1 = buf1;
				}

				// equip_img2
				Part part2 = req.getPart("equip_img2");
				
				byte[] buf2 = null;
				byte[] equip_img2 = null;

				InputStream in2 = part2.getInputStream();
				buf2 = new byte[in2.available()];
				if (buf2.length != 0) {
					String type = part2.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) 
						errorMsgs.add("檔案格式錯誤");
					
					in2.read(buf2);
					equip_img2 = buf2;
					in2.close();
				} else if (buf2.length == 0) {
					equip_no = req.getParameter("equip_no");
					EquipmentService equipSvc = new EquipmentService();
					EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);
					buf2 = equipmentVO.getEquip_img2();
					equip_img2 = buf2;
				}
				
				

				// equip_img3
				Part part3 = req.getPart("equip_img3");

				byte[] buf3 = null;
				byte[] equip_img3 = null;

				InputStream in3 = part3.getInputStream();
				buf3 = new byte[in3.available()];
				if (buf3.length != 0) {
					String type = part3.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) 
						errorMsgs.add("檔案格式錯誤");
					
					in3.read(buf3);
					equip_img3 = buf3;
					in3.close();
				} else if (buf3.length == 0) {
					equip_no = req.getParameter("equip_no");
					EquipmentService equipSvc = new EquipmentService();
					EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);
					buf3 = equipmentVO.getEquip_img3();
					equip_img3 = buf3;
				}
				
				
				//　狀態
				Integer equip_status = new Integer(req.getParameter("equip_status").trim());

				//　價格
				Integer equip_price = null;
				try {
					equip_price = new Integer(req.getParameter("equip_price").trim());
					if (equip_price <= 0) {
						equip_price = 100;
						errorMsgs.add("價格請填正整數");
					}
				} catch (NumberFormatException e) {
					equip_price = 100;
					errorMsgs.add("價格請填正整數");
				}

				//　更新日期
				java.sql.Date equip_update = new java.sql.Date(System.currentTimeMillis());

				EquipmentVO equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_no(equip_no);
				equipmentVO.setEquip_name(equip_name);
				equipmentVO.setEquip_des(equip_des);
				equipmentVO.setEquip_img1(equip_img1);
				equipmentVO.setEquip_img2(equip_img2);
				equipmentVO.setEquip_img3(equip_img3);
				equipmentVO.setEquip_status(equip_status);
				equipmentVO.setEquip_price(equip_price);
				equipmentVO.setEquip_update(equip_update);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equipmentVO", equipmentVO); // 含有輸入格式錯誤的equipmentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/equipment/update_equip_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 2.開始修改資料
				EquipmentService equipSvc = new EquipmentService();
				equipmentVO = equipSvc.updateEquip(equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3,
						equip_status, equip_price, equip_update);
				
				
				try {
					EquipmentService equipSvc1 = new EquipmentService();
					List<EquipmentVO> list = equipSvc1.getStatus(status);
					req.setAttribute("list", list);
					
				} catch (NullPointerException e) {
					
				}


				// 3.修改完成,準備轉交
				
				String url = requestURL+"?whichPage="+whichPage+"&equip_no="+equip_no;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				
				req.setAttribute("equipmentVO", equipmentVO); // 資料庫update成功後,正確的的equipmentVO物件,存入req
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/update_equip_input.jsp");
				failureView.forward(req, res);
			}

		}

		//　後台 新增裝備
		if ("insert".equals(action)) { // 來自addEquip.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理

				//　裝備名稱
				String equip_name = req.getParameter("equip_name");
				String equip_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{2,50}$";
				if (equip_name == null || equip_name.trim().length() == 0) {
					errorMsgs.add("裝備名稱請勿空白");
				} else if (!equip_name.trim().matches(equip_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("裝備名稱：只能是中、英文字母、數字和_和空白, 且長度必需在2到50之間");
				}

				//　裝備描述
				String equip_des = req.getParameter("equip_des");
				if (equip_des == null || equip_des.trim().length() == 0)
					errorMsgs.add("裝備描述請勿空白");

				// equip_img1
				Part part1 = req.getPart("equip_img1");
				byte[] equip_img1 = MyUtil.pathToByteArray(part1);

				// equip_img2
				Part part2 = req.getPart("equip_img2");
				byte[] equip_img2 = MyUtil.pathToByteArray(part2);

				// equip_img3
				Part part3 = req.getPart("equip_img3");
				byte[] equip_img3 = MyUtil.pathToByteArray(part3);

				//　狀態
				Integer equip_status = new Integer(req.getParameter("equip_status"));

				//　價格
				Integer equip_price = null;
				try {
					equip_price = new Integer(req.getParameter("equip_price").trim());
					if (equip_price <= 0) {
						equip_price = 100;
						errorMsgs.add("價格請填正整數");
					}
				} catch (NumberFormatException e) {
					equip_price = 100;
					errorMsgs.add("價格請填正整數");
				}

				//　更新日期
				java.sql.Date equip_update = new java.sql.Date(System.currentTimeMillis());

				EquipmentVO equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_name(equip_name);
				equipmentVO.setEquip_des(equip_des);
				equipmentVO.setEquip_img1(equip_img1);
				equipmentVO.setEquip_img2(equip_img2);
				equipmentVO.setEquip_img3(equip_img3);
				equipmentVO.setEquip_status(equip_status);
				equipmentVO.setEquip_price(equip_price);
				equipmentVO.setEquip_update(equip_update);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equipmentVO", equipmentVO); // 含有輸入格式錯誤的equipmentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/addEquip.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.開始新增資料
				EquipmentService equipSvc = new EquipmentService();
				equipmentVO = equipSvc.addEquip(equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, equip_update);

				// 3.新增完成,準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equipment/listAllEquip.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/addEquip.jsp");
				failureView.forward(req, res);
			}

		}

		//　後台 查詢上架/下架
		if ("getStatus".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理
				Integer equip_status = new Integer(req.getParameter("equip_status"));

				// 2.開始查詢資料
				EquipmentService equipSvc = new EquipmentService();
				List<EquipmentVO> list = equipSvc.getStatus(equip_status);

				// 3.查詢完成,準備轉交
				req.setAttribute("list", list);
				req.setAttribute("status", equip_status);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equipment/listStatus.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equipment/listAllEquip.jsp");
				failureView.forward(req, res);
			}

		}
		
		

		// 前台 呈現單筆裝備明細
		if ("getOne_For_Detail_Front".equals(action)) { // 來自equip_shop.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 
				String equip_no = req.getParameter("equip_no");

				// 2.開始查詢資料
				EquipmentService equipSvc = new EquipmentService();
				EquipmentVO equipmentVO = equipSvc.getOneEquip(equip_no);

				// 3.查詢完成,準備轉交
				req.setAttribute("equipmentVO", equipmentVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/equipment/equip_detail.jsp");
				successView.forward(req, res);

				// 其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/equipment/equip_shop.jsp");
				failureView.forward(req, res);
			}

		}
		
		

		//　前台 加入購物車
		if ("add".equals(action)) { // 來自equip_detail.jsp的請求
			
			//　接收前面ajax傳來的參數
			String equip_no = req.getParameter("equip_no");
			String equip_name = req.getParameter("equip_name");
			Integer buy_amt = new Integer(req.getParameter("buy_amt"));
			Integer equip_price = new Integer(req.getParameter("equip_price"));
			
			
			//　將這些參數設定進訂單明細VO
			EquipOdlistVO equipodlistVO = new EquipOdlistVO();
			equipodlistVO.setEquip_no(equip_no);
			equipodlistVO.setEquip_name(equip_name);
			equipodlistVO.setBuy_amt(buy_amt);
			equipodlistVO.setEquip_price(equip_price);
			

			//　判斷購物車內是否已存在該裝備
			if (buylist == null) {
				buylist = new Vector<EquipOdlistVO>();
				buylist.add(equipodlistVO);
			} else {
				if (buylist.contains(equipodlistVO)) {
					EquipOdlistVO innerEquipOdlistVO = buylist.get(buylist.indexOf(equipodlistVO));
					innerEquipOdlistVO.setBuy_amt(innerEquipOdlistVO.getBuy_amt() + equipodlistVO.getBuy_amt());
				} else {
					buylist.add(equipodlistVO);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			
			
			// 計算總金額
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				EquipOdlistVO equipodlistVO1 = buylist.get(i);

				Integer price = equipodlistVO1.getEquip_price();
				Integer quantity = equipodlistVO1.getBuy_amt();

				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			
		}

		
		
		// 前台 從購物車刪除
		if (("delete").equals(action)) { // 來自cart.jsp的請求

			String num = req.getParameter("num");
			int del = Integer.parseInt(num);
			buylist.remove(del);
			
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				EquipOdlistVO equipodlistVO1 = buylist.get(i);

				Integer price = equipodlistVO1.getEquip_price();
				Integer quantity = equipodlistVO1.getBuy_amt();

				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);	
			
			// 刪除後一樣轉交回cart.jsp
			session.setAttribute("shoppingcart", buylist);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/equipment/cart.jsp");
			rd.forward(req, res);

		}

		
		

		//　前台　透過ajax傳欲變更之第幾個裝備的數量
		if (("calculate").equals(action)) {
			
			PrintWriter out = res.getWriter();
			JSONObject jsonObj = new JSONObject();
			
			Integer num= new Integer(req.getParameter("num"));
			Integer buy_amt = new Integer(req.getParameter("buy_amt"+num));
			
			EquipOdlistVO equipodlistVO = buylist.get(num);
			equipodlistVO.setBuy_amt(buy_amt);
			
			// 計算小計
			int subtotal = equipodlistVO.getEquip_price() * buy_amt;
			String s = String.valueOf(subtotal);
			try {
				jsonObj.put("subtotal", s);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
			// 計算總金額
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				EquipOdlistVO equipodlistVO1 = buylist.get(i);

				Integer price = equipodlistVO1.getEquip_price();
				Integer quantity = equipodlistVO1.getBuy_amt();

				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);	
			
			try {
				jsonObj.put("amount", amount);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(jsonObj);
			
			
		}
		
		
		
		// 前台 關鍵字搜尋
		if (("get_keyword").equals(action)) { // 來自equip_shop.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 1.接收請求參數 - 輸入格式的錯誤處理
			String keyword = req.getParameter("keyword");
			
			if ((keyword.trim()).length() == 0) 
				errorMsgs.add("請輸入關鍵字");
			
			
			// 2.開始查詢資料
			EquipmentService equipSvc = new EquipmentService();
			List<EquipmentVO> list = equipSvc.get_keyword(keyword);
			
			if (list.isEmpty()) 
				errorMsgs.add("查無資料");
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/equipment/equip_shop.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			
			// 3.查詢完成,準備轉交
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/equipment/equip_keyword.jsp");
			successView.forward(req, res);

		}
		
		
		
		

	}

}
