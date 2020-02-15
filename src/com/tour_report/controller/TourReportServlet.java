package com.tour_report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.tour.model.TourService;
import com.tour.model.TourVO;
import com.tour_report.model.TourReportService;
import com.tour_report.model.TourReportVO;

public class TourReportServlet extends HttpServlet{
	
	public void deGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("sendReport".equals(action)) {
			String errorMsgs = "";
			req.setAttribute("errorMsgs", errorMsgs);
			//問題描述是否為空
			try {
				String tour_re_note = req.getParameter("tour_re_note");
				if(tour_re_note == null || tour_re_note.trim().length() == 0) {
					errorMsgs = "請描述問題";
				}
				
				String tour_no = req.getParameter("tour_no");
				
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				
				TourReportVO tourReportVO = new TourReportVO();
				tourReportVO.setTour_no(tour_no);
				tourReportVO.setMem_no(mem_no);
				tourReportVO.setTour_re_note(tour_re_note);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("tourReportVO", tourReportVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travel-page/travel-detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TourReportService tourReportSvc = new TourReportService();
				tourReportVO = tourReportSvc.addTourReport(tour_no, mem_no, tour_re_note);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("passReport".equals(action) || "rejectReport".equals(action)) {
			
			try {
				
				Integer tour_re_status = null;
				String str_tour_status = req.getParameter("tour_status");
				Integer tour_status = null;
				if("passReport".equals(action)) {
					tour_re_status = 1;
					tour_status = 0;
				} else if("rejectReport".equals(action)) {
					tour_re_status = 2;
					tour_status = 1;
				}
			
				String mem_no = req.getParameter("mem_no");
				String tour_no = req.getParameter("tour_no");
				
				TourVO tourVO = new TourVO();
				tourVO.setTour_no(tour_no);
				tourVO.setTour_status(tour_status);
				
				TourService tourSvc = new TourService();
				tourSvc.updateTourStatus(tour_no, tour_status);
				
				TourReportVO tourReportVO = new TourReportVO();
				tourReportVO.setTour_no(tour_no);
				tourReportVO.setTour_re_status(tour_re_status);
				tourReportVO.setMem_no(mem_no);
				
				TourReportService tourReportSvc = new TourReportService();
				tourReportSvc.updateTourReportStatus(tour_no, tour_re_status, mem_no);
			
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
