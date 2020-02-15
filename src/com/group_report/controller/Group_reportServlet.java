package com.group_report.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.group.model.*;
import com.group_message.model.*;
import com.group_report.model.*;

@MultipartConfig
public class Group_reportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("insert".equals(action)) { // 來自addGroup.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			Map<String, String> word = new HashMap<>();
//			req.setAttribute("word", word);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String group_no = req.getParameter("group_no").trim();
				String mem_no = req.getParameter("mem_no");
				String group_re_note = req.getParameter("group_re_note");
//				word.put(group_re_note, group_re_note);

				Group_reportService group_reSvc = new Group_reportService();

				/*************************** 2.開始新增資料 ***************************************/
				group_reSvc.addGroup_report(group_no, mem_no, group_re_note);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("oncheck".equals(action)) { // 來自group_report.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer group_re_status = Integer.valueOf(req.getParameter("group_re_status"));

				/*************************** 2.開始查詢資料 *****************************************/
				Group_reportService group_reSvc = new Group_reportService();
				List<Group_reportVO> list = group_reSvc.getAll();
				for (int i = 0; i < list.size(); i++) {
					Group_reportVO g = list.get(i);
					if (g.getGroup_re_status() != group_re_status) {
						list.remove(i);
						i--;
					}
				}

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的groupVO物件,存入req
				String url = "/back-end/group/group_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/group/group_report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("checked".equals(action)) { // 來自group_report.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer group_re_status = Integer.valueOf(req.getParameter("group_re_status"));

				/*************************** 2.開始查詢資料 *****************************************/
				Group_reportService group_reSvc = new Group_reportService();
				List<Group_reportVO> list = group_reSvc.getAll();
				for (int i = 0; i < list.size(); i++) {
					Group_reportVO g = list.get(i);
					if (g.getGroup_re_status() != group_re_status && g.getGroup_re_status() != group_re_status + 1) {
						list.remove(i);
						i--;
					}
				}

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的groupVO物件,存入req
				String url = "/back-end/group/group_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/group/group_report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("check".equals(action)) {
			String requestURL = req.getParameter("requestURL");// 送出請求的來源網頁路徑
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				String group_re_no = req.getParameter("group_re_no");
				Integer group_re_status = new Integer(req.getParameter("group_re_status"));

				/***************************2.開始修改資料***************************************/
				Group_reportService group_reSvc = new Group_reportService();
				Group_reportVO group_reVO = group_reSvc.getOneGroup_report(group_re_no);
				group_reSvc.updateGroup_report(group_re_no, group_reVO.getGroup_no(), group_reVO.getMem_no(),
						group_reVO.getGroup_re_note(), group_re_status);
				String group_no = (group_reVO.getGroup_no());
				if (group_re_status == 1) {
					List<Group_reportVO> list = group_reSvc.getAll();
					for (Group_reportVO gr : list) {
						if (gr.getGroup_no().equals(group_no) && gr.getGroup_re_status() == 0) {
							group_reSvc.updateGroup_report(gr.getGroup_re_no(), gr.getGroup_no(), gr.getMem_no(),
									gr.getGroup_re_note(), 1);
						}
					}
					GroupService groupSvc = new GroupService();
					GroupVO groupVO = groupSvc.getOneGroup(group_no);
					groupSvc.updateStatus(group_no, 3, groupVO.getGroup_tour_num());

				}
				List<Group_reportVO> list = new ArrayList<Group_reportVO>();
			
					group_reSvc = new Group_reportService();
					list = group_reSvc.getAll();
					for (int i = 0; i < list.size(); i++) {
						Group_reportVO g = list.get(i);
						if (g.getGroup_re_status() != 0) {
							list.remove(i);
							i--;
						}
					}
				
				
				if(group_re_status==2) {
					group_reSvc = new Group_reportService();
					list = group_reSvc.getAll();
					for (int i = 0; i < list.size(); i++) {
						Group_reportVO g = list.get(i);
						if (g.getGroup_re_status() != group_re_status) {
							list.remove(i);
							i--;
						}
					}
				}
					
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				req.setAttribute("list", list); // 資料庫取出的groupVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}

		}

	}

}
