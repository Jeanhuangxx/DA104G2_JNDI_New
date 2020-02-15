package com.tour_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.tour_order.model.TourOrderService;
import com.tour_order.model.TourOrderVO;

public class TourOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		String action = req.getParameter("action");

		
		
		if("sendReview".equals(action)) {
			
			try {
				//取得評分
				String str_rating = req.getParameter("tour_rev");
				Integer tour_rev = null;
				if(str_rating != null) {
					tour_rev = new Integer(str_rating);
				}
				
				//取得評論
				String tour_rev_note = req.getParameter("tour_rev_note").trim();
				
				
				//取得訂單編號
				String tour_no = req.getParameter("tour_no");
				
				//取得登入的會員編號
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				
				TourOrderVO tourOrderVO = new TourOrderVO();
				tourOrderVO.setTour_no(tour_no);
				tourOrderVO.setMem_no(mem_no);
				tourOrderVO.setTour_rev(tour_rev);
				tourOrderVO.setTour_rev_note(tour_rev_note);

				//update review
				TourOrderService tourOrderSvc = new TourOrderService();
				tourOrderVO = tourOrderSvc.updateTourReview(tour_rev_note, tour_rev, tour_no, mem_no);
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		if("bookTour".equals(action)) {
			try {
				
				String mem_no = req.getParameter("mem_no");
				String tour_no = req.getParameter("tour_no");
				Integer tour_price = new Integer(req.getParameter("tour_price"));
				
				TourOrderVO tourOrderVO = new TourOrderVO();
				tourOrderVO.setMem_no(mem_no);
				tourOrderVO.setTour_no(tour_no);
				tourOrderVO.setTtl_price(tour_price);
				
				//以下修改
				HttpSession session = req.getSession();
				session.removeAttribute("tourVO");
				//以上修改
				
				
				TourOrderService tourOrderSvc = new TourOrderService();
				tourOrderVO = tourOrderSvc.addTourOrder(tour_no, mem_no, tour_price);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
