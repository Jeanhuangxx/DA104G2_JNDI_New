package com.admlogin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.adm.model.AdmService;
import com.adm.model.AdmVO;

import javax.servlet.annotation.WebServlet;

public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 錯誤顯示方法
		Map<String,String> errorMsgs = new HashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		
		if("logOut".equals(action)) {
			AdmVO admVOall = (AdmVO) session.getAttribute("admVO");
			if (admVOall != null) {
				session.removeAttribute("admVO");
				session.removeAttribute("admAccount");
				session.removeAttribute("password");
				res.sendRedirect(req.getContextPath() + "/back-end/admlogin/admlogin.jsp");
				return;
			}
		}	
		
		
		
		if("login".equals(action)) {
				// 【取得使用者 帳號(account) 密碼(password)】
				String admAccount = req.getParameter("admAccount");
				String password = req.getParameter("password");
		
				// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
				// 【至資料庫搜尋比對】
				try {
					// String accountCK = req.getParameter("account");
					AdmService admSvc = new AdmService();
					AdmVO admVO = admSvc.getOneAdmId(admAccount);
					if (admVO == null) {
						errorMsgs.put("admAccount", "查無此帳號，請重新輸入");
		//				return;
					} else if (password.trim().length() == 0) {
						errorMsgs.put("password","密碼不正確，請重新輸入");
		//				return;
					}else if (!allowUser(admAccount, password)) { // 【帳號 , 密碼無效時】
						// 【檢查該帳號 , 密碼是否有效】
						errorMsgs.put("password","密碼不正確，請重新輸入");
					}else { // 【帳號 , 密碼有效時, 才做以下工作】
		
						session.setAttribute("admAccount", admAccount);
						session.setAttribute("admVO", admVO);
		
						// *工作1: 才在session內做已經登入過的標識
		
		//--------------------帳密沒問題成功登入--------------------//
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							res.sendRedirect(location);
							return;
						}
					}
					
					if (!errorMsgs.isEmpty()) {// <-------------------如果有任何錯誤進入此頁面
						System.out.println(123);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admlogin/admlogin.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
				} catch (Exception ignored) {
		
				}
				res.sendRedirect(req.getContextPath() + "/back-end/manage/manage.jsp"); // *工作3:
			}//login

	}//doPost
	

	protected boolean allowUser(String account, String password) {

		AdmService admSvc = new AdmService();
		AdmVO admVO = admSvc.getOneAdmId(account);
		String adm_psw = admVO.getAdm_psw();
		if (password.equals(adm_psw)) {
			return true;
		} else {
			return false;
		}
	}
}
