package com.room_image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room_image.model.RoomImageService;

public class RoomImageServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("deleteRoomImg".equals(action)) {
			try {
				String room_img_no = req.getParameter("room_img_no");
				System.out.println(room_img_no);
				
				RoomImageService roomImageSvc = new RoomImageService();
				roomImageSvc.deleteRoomImage(room_img_no);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
