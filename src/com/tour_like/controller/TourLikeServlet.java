package com.tour_like.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.tour.model.TourService;
import com.tour.model.TourVO;
import com.tour_like.model.TourLikeService;
import com.tour_like.model.TourLikeVO;

public class TourLikeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		String action = req.getParameter("action");
		System.out.println(action);

		if ("cancelFollow".equals(action)) {
			try {

				String tour_no = req.getParameter("tour_no");

				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);
				req.setAttribute("tourVO", tourVO);
				
				TourLikeService tourLikeSvc = new TourLikeService();
				tourLikeSvc.deleteTourLike(tour_no, mem_no);
				String url = "/front-end/travel-page/travel-detail.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		if ("cancelFollowMem".equals(action)) {
			try {

				String tour_no = req.getParameter("tour_no");

				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);
				req.setAttribute("tourVO", tourVO);
				
				TourLikeService tourLikeSvc = new TourLikeService();
				
				tourLikeSvc.deleteTourLike(tour_no, mem_no);
				String url = req.getHeader("referer");
				res.sendRedirect(url);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("followTrip".equals(action)) {
			try {
				String tour_no = req.getParameter("tour_no");

				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				
//				if (memVO == null) {
//					session.setAttribute("location", req.getContextPath() + "/front-end/travel-page/travel-detail.jsp");
//					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
//					return;
//				} else {
//					mem_no = memVO.getMem_no();
//				}
				
				TourLikeVO tourLikeVO = new TourLikeVO();
				tourLikeVO.setMem_no(mem_no);
				tourLikeVO.setTour_no(tour_no);
				
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);
				req.setAttribute("tourVO", tourVO);
				
				TourLikeService tourLikeSvc = new TourLikeService();
				tourLikeSvc.addTourLike(tour_no, mem_no);
//				String url = req.getHeader("referer");
				
				String url = "/front-end/travel-page/travel-detail.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}

	}
}
