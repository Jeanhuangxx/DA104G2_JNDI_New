package com.tour_image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tour_image.model.TourImageService;
import com.tour_image.model.TourImageVO;

public class TourImageServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("deleteTourImg".equals(action)) {
			try {
				String tour_img_no = req.getParameter("tour_img_no");
				
				TourImageService tourImageSvc = new TourImageService();
				tourImageSvc.deleteTourImage(tour_img_no);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
