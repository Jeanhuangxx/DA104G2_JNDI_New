package com.equip_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.equip_odlist.model.EquipOdlistVO;
import com.equip_order.model.*;
import com.equipment.model.EquipmentVO;


public class Equip_orderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		HttpSession session = req.getSession();
		
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理
				String equip_order_no = req.getParameter("equip_order_no");
				if (equip_order_no == null || (equip_order_no.trim()).length() == 0) 
					errorMsgs.add("請輸入訂單編號");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equip_order/select_page_order.jsp");
					failureView.forward(req,res);
					return;
				}
				
				// 2.開始查詢資料
				EquipOrderService equipOrderSvc = new EquipOrderService();
				EquipOrderVO equiporderVO = equipOrderSvc.getOneOrder(equip_order_no);
				if (equiporderVO == null) 
					errorMsgs.add("查無資料");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equip_order/select_page_order.jsp");
					failureView.forward(req,res);
					return;
				}
				
				// 3.查詢完成,準備轉交
				req.setAttribute("equiporderVO", equiporderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equip_order/listOneOrder.jsp");
				successView.forward(req,res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equip_order/select_page_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		//　後台　改訂單狀態　處理中->處理完成
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1.接收請求參數
				String equip_order_no = req.getParameter("equip_order_no");

				//　至資料庫撈出該筆訂單資料
				EquipOrderService orderSvc = new EquipOrderService();
				EquipOrderVO orderVO = orderSvc.getOneOrder(equip_order_no);
				
				String mem_no = orderVO.getMem_no();
				Timestamp equip_order_time = orderVO.getEquip_order_time();
				Integer equip_order_price = orderVO.getEquip_order_price();
				
				//　出貨日期　指定為系統當天日期
				java.sql.Date equip_shipping_date = new java.sql.Date(System.currentTimeMillis());
				//　處理狀態
				Integer equip_order_status = new Integer(req.getParameter("equip_order_status"));
				
				String equip_note = orderVO.getEquip_note();
				String cus_name = orderVO.getCus_name();
				Integer cus_tel = orderVO.getCus_tel();
				String cus_add = orderVO.getCus_add();
				
				
				// 2.開始修改資料
				EquipOrderVO equiporderVO = orderSvc.updateOrder(equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, equip_note, cus_name, cus_tel, cus_add);
				
				// 3.修改完成,準備轉交
				req.setAttribute("equiporderVO", equiporderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equip_order/newOrder.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equip_order/newOrder.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		
		//　後台　依會員編號查訂單
		if ("getMem".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1.接收請求參數
				String mem_no = req.getParameter("mem_no");
				
				// 2.開始查詢資料
				EquipOrderService equipOrderSvc = new EquipOrderService();
				List<EquipOrderVO> list = equipOrderSvc.getMem(mem_no);
								
				// 3.查詢完成,準備轉交
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/equip_order/get_mem.jsp");
				successView.forward(req, res);

				//　其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/equip_order/select_page_order.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		// 前台 付款頁面 確認購買
		if (("pay").equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			
			String mem_no = req.getParameter("mem_no");
			Integer equip_order_price = new Integer(req.getParameter("equip_order_price"));
			
			// 1.接收請求參數 - 輸入格式的錯誤處理
			
			//　收件人姓名
			String cus_name = req.getParameter("cus_name");
			if (cus_name == null || cus_name.trim().length() == 0) 
				errorMsgs.add("收件人請勿空白");
			
			
			//　收件人電話
			Integer cus_tel = null;
			try {
				cus_tel = new Integer(req.getParameter("cus_tel").trim());
				
				if (cus_tel.toString().length() > 10) {
					cus_tel = null;
					errorMsgs.add("請確認電話");
				}
				
			} catch (NumberFormatException e) {
				errorMsgs.add("請確認電話");
			}
					
			
			//　收件人住址
			String county = req.getParameter("county");
			String district = req.getParameter("district");
			String cus_add = req.getParameter("cus_add");
			
			if (county == null || county.trim().length() == 0 || district == null || district.trim().length() == 0) {
				errorMsgs.add("請確認地址");
			} else if (cus_add == null || cus_add.trim().length() == 0) {
				errorMsgs.add("請確認地址");
			}
			
			cus_add = county + district + cus_add;
			
			
		
			
			String equip_note = req.getParameter("equip_note");
			
			if (equip_note.length()==0) 
				equip_note = "無";
			
			
			//　將上方訂單資料存入訂單VO
			EquipOrderVO equiporderVO = new EquipOrderVO();

			equiporderVO.setMem_no(mem_no);
			equiporderVO.setEquip_order_price(equip_order_price);
			equiporderVO.setCus_name(cus_name);
			equiporderVO.setCus_tel(cus_tel);
			equiporderVO.setCus_add(cus_add);
			equiporderVO.setEquip_note(equip_note);
			
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("equiporderVO", equiporderVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/equip_order/buy_info.jsp");
				failureView.forward(req,res);
				return;
			}
			
			
			List<EquipOdlistVO> buylist = (Vector<EquipOdlistVO>) session.getAttribute("shoppingcart");
			
			
			//　透過參數傳入訂單VO及訂單明細(buylist)
			EquipOrderService equipSvc = new EquipOrderService();
			equipSvc.addOrder(equiporderVO, buylist);
			
			
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/equip_order/cus_order.jsp");
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/equip_order/buy_info.jsp");
				failureView.forward(req, res);
			}
			
		}
		
	}

}
