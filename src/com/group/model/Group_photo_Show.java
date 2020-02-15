package com.group.model;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.dive.model.DiveService;
import com.mem.model.MemService;

public class Group_photo_Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Group_photo_Show() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		res.setContentType("image/gif"); // 必要!
		ServletOutputStream out;
		// 從參數判斷是哪個會員
		String group_no = req.getParameter("group_no");
		String mem_no = req.getParameter("mem_no");
		String dive_no = req.getParameter("dive_no");
		// 從參數判斷抓哪張圖片
//		String ask = req.getParameter("ask").trim();
		if (group_no != null) {
			try {
//			if ("group_photo".equals(ask)) {
				out = res.getOutputStream();
				GroupService groupSvc = new GroupService();
				byte[] group_photo = groupSvc.getOneGroup(group_no).getGroup_photo();
				out.write(group_photo);
				out.flush();

//			}		
//錯誤處理,我還沒用XD
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("無圖片");
			}
		}
		
		if(mem_no != null&& !mem_no.equals("undefined")) {
			try {
//				if ("group_photo".equals(ask)) {
					out = res.getOutputStream();
					MemService groupSvc = new MemService();
					byte[] mem_img = groupSvc.getOneMem(mem_no).getMem_img();
					out.write(mem_img);
					out.flush();

//				}		
	//錯誤處理,我還沒用XD
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("無圖片");
				}
		}
		if (dive_no != null) {
			try {
//			if ("group_photo".equals(ask)) {
				out = res.getOutputStream();
				DiveService diveSvc = new DiveService();
				byte[] dive_img = diveSvc.getOneDive(dive_no).getDive_img();
				out.write(dive_img);
				out.flush();

//			}		
//錯誤處理,我還沒用XD
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("無圖片");
			}
		}
		
	}

}
