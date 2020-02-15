package com.group_join.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.model.*;
import com.group_join.model.*;
import com.mem.model.*;
import com.util.MailService;

public class Group_joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("insert".equals(action)) { // 來自addGroup.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				Integer group_jo_status = 0;// 0為未審核

				/*************************** 2.開始新增資料 ***************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				group_joinSvc.addGroup_join(group_no, mem_no, group_jo_status);

				PrintWriter out = res.getWriter();
				out.write("ok");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("getAll_For_Mem".equals(action))

		{ // 來自XXX.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_no = null;
				try {
					mem_no = str;
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				List<Group_joinVO> group_joinVO = group_joinSvc.getAllForMem(mem_no);
				if (group_joinVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				req.setAttribute("group_joinVO", group_joinVO); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listAllMemGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("check".equals(action)) {// 來自listOneGroup.jsp的"審核揪團"請求(Ajax)
			String mem_no = req.getParameter("mem_no");
			String mem_no1 = req.getParameter("mem_no1");
			String group_no = req.getParameter("group_no");
			Group_joinService group_joinSvc = new Group_joinService();
			if (mem_no != null) {
				group_joinSvc.updateGroup_join(group_no, mem_no, 1, 0);
			} else if (mem_no1 != null) {
				group_joinSvc.updateGroup_join(group_no, mem_no1, 2, 0);
			}
			PrintWriter out = res.getWriter();
			out.write("ok");
		}

		if ("okemail".equals(action) || "noemail".equals(action)) {
			String mem_no = req.getParameter("mem_no");
			String mem_no1 = req.getParameter("mem_no1");
			MemService memSvc = new MemService();
			MailService mailSvc = new MailService();
			if (mem_no != null) {
				MemVO memVO = memSvc.getOneMem(mem_no);
				mailSvc.sendMail(memVO.getMem_email(), "潛進台灣-報名揪團審核信", "審核通過，請等待開團");
			} else if (mem_no1 != null) {
				MemVO memVO = memSvc.getOneMem(mem_no1);
				mailSvc.sendMail(memVO.getMem_email(), "潛進台灣-報名揪團審核信", "審核不通過，謝謝您的報名。");
			}
			PrintWriter out = res.getWriter();
			out.write("ok");
		}

		if ("delete".equals(action)) { // 來自listOneGroup.jsp的"退出揪團"請求(Ajax)
			String mem_no = req.getParameter("mem_no");
			String group_no = req.getParameter("group_no");
			Group_joinService group_joinSvc = new Group_joinService();
			group_joinSvc.delete(group_no, mem_no);

			PrintWriter out = res.getWriter();
			out.write("ok");
		}

		if ("deletequit".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroup(group_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.getSession().setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				group_joinSvc.delete(group_no, mem_no);
				List<Group_joinVO> group_joinList = group_joinSvc.getAllForMem(mem_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("group_joinList", group_joinList); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listAllMemGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("quit".equals(action) || "cancel".equals(action)) {// 來自listAllMemGroup.jsp的"退出揪團"請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				/*************************** 2.開始新增資料 ***************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				GroupService groupSvc = new GroupService();
				group_joinSvc.delete(group_no, mem_no);
				List<Group_joinVO> group_joinList = null;
				// 接收進行中的"退出揪團"
				if ("quit".equals(action)) {
					group_joinList = group_joinSvc.getAllChecking(mem_no);
				}
				if ("cancel".equals(action))// 接收審核中的"取消揪團"
					group_joinList = group_joinSvc.getAllChecking(mem_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("group_joinList", group_joinList); // 資料庫取出的groupVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
