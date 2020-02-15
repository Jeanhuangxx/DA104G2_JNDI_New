package com.equip_odlist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equip_odlist.model.*;
import com.equip_order.model.*;

public class Equip_odlistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//　後台 & 前台 取得訂單明細
		if ("getOneOdlist".equals(action) || "getOneOdlist_front".equals(action)) {
			
			String equip_order_no = req.getParameter("equip_order_no");
			
			EquipOdlistService odlistSvc = new EquipOdlistService();
			List<EquipOdlistVO> list = odlistSvc.getOneOdlist(equip_order_no);
			
			EquipOrderService orderSvc = new EquipOrderService();
			EquipOrderVO equiporderVO = orderSvc.getOneOrder(equip_order_no);
			
			
			String url = null;
			if ("getOneOdlist".equals(action)) 
				url = "/back-end/equip_odlist/get_one_odlist.jsp";
			else if ("getOneOdlist_front".equals(action))
				url = "/front-end/memberCenter/member_myOrder.jsp";
			
			req.setAttribute("list", list);         
			req.setAttribute("equiporderVO", equiporderVO);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			
		}
		
		
	}

}
