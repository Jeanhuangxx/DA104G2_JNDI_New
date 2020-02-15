package com.backmem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.adm.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.util.MailService;
import com.util.MyUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class BackMemServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println(action);	

//查詢單個		
		if ("getOne_For_Display".equals(action)) { // 來自member_select.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				System.out.println(mem_no);
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/member_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
//				try {
//					mem_no = new String(mem_no);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/member_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
				System.out.println(memVO.getMem_name());
//				if (memVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/member_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/back-end/manage/member_listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/member_select.jsp");
				failureView.forward(req, res);
			}
		}
		
//查詢後台正常/停權潛店---------------依權限分類---------------------------------------------
		if ("getAllStoresPER".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer mem_per = new Integer(req.getParameter("mem_per"));
				Integer	mem_type=1;
				MemService memSvc = new MemService();
				//int mem_type=1;
				List<MemVO> list = memSvc.getAllByStatus(mem_per, mem_type);
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/manage/storeBRC.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/storeBRC.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//getOne_For_Update	
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdm.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = req.getParameter("mem_no");
				System.out.println(mem_no);
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/manage/member_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_adm_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/member_listall.jsp");
				failureView.forward(req, res);
			}
		}
		
//修改/停權
		
		if ("update".equals(action)) { // 來自update_adm_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = new String(req.getParameter("mem_no").trim());
//編號
	

//名稱				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("姓名 : 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、和數字 , 且長度必需在2到10之間");
	            }
//ID帳號				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("ID : 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("ID : 只能是大小寫英文字母、和數字, 且長度必需為6");
	            }
//密碼			
				String mem_psw = req.getParameter("mem_psw");
				String mem_pswReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼 : 請勿空白");
				} else if(!mem_psw.trim().matches(mem_pswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼 : 只能是大小寫英文字母、和數字 , 且長度必需為6");
	            }

//電話				
				Integer mem_tel = null;
				try {
					mem_tel = new Integer(req.getParameter("mem_tel").trim());
				} catch (NumberFormatException e) {
					mem_tel = 0;
					errorMsgs.add("電話請填數字.");
				}
//EMAIL				
				String mem_email = req.getParameter("mem_email");
				String mem_emailReg = "^[(a-zA-Z0-9@.)]{16,16}$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("管理員EMAIL : 請勿空白");
				} else if(!mem_email.trim().matches(mem_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員EMAIL : 只能是大小寫英文字母、數字、@和 .  , 且長度必需為16");
	            }
				
//地址
				
				
//照片				
//				Part part = req.getPart("adm_img");
//				byte[] adm_img = MyUtil.pathToByteArray(part);
				
//				MyUtil a = new MyUtil();
//				a.pathToByteArray(adm_img);
				
//				Part part = req.getPart("adm_img");
//				InputStream in = part.getInputStream();
//				byte[] adm_img = new byte[in.available()];
//				in.read(adm_img);
//				in.close();
				
//會員權限
				
//會員類型
				
//營業登記證
				Part part = req.getPart("mem_img");
				byte[] adm_img = MyUtil.pathToByteArray(part);
				
//店家負責人
				
//統一編號
				
				
				
				AdmVO admVO = new AdmVO();
				admVO.setAdm_no(mem_no);
				admVO.setAdm_id(mem_id);
				admVO.setAdm_psw(mem_psw);
				admVO.setAdm_name(mem_name);
				admVO.setAdm_tel(mem_tel);
				admVO.setAdm_email(mem_email);
				admVO.setAdm_img(adm_img);
//
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的admVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adm/update_adm_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
//				
				/***************************2.開始修改資料*****************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(mem_no, mem_id, mem_psw, mem_name, mem_tel, mem_email, adm_img);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的admVO物件,存入req
				String url = "/adm/listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdm.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adm/update_adm_input.jsp");
				failureView.forward(req, res);
			}
		}
		
//後台修改潛店權限 ============================== 店家修改 ====================================== 
		if ("updateStoreBRC".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			MemService MS = new MemService();

			String mem_no = req.getParameter("mem_no");
System.out.println("AJAX mem = " + mem_no);
			MemVO mem_update = MS.getOneMem(mem_no);
			String mem_name = mem_update.getMem_name();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_status=req.getParameter("mem_status");
System.out.println(mem_status+"權限");
				// 會員權限(0:正常, 1:停權)
				int mem_per = new Integer(mem_status);
				
				// ---------以下資料不在店家註冊表格上----------------
				// 會員權限(0:正常, 1:停權)
				//int mem_per = 1;
				// 會員類型(0:一般會員, 1:店家)
				//int mem_type = 1;
				// 性別(0:不透漏 1:男生 2:女生)
				Integer mem_general_gen = 0;
				// 生日日期
				java.sql.Date mem_general_bd = null;
				// 大頭貼
				byte[] mem_img = null;
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", mem_update); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/manage/storeBRC.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/******************2.開始新增資料到資料庫**************/
				
				MS.updateMem(mem_update.getMem_no(),mem_update.getMem_name(), mem_update.getMem_id(),
						mem_update.getMem_psw(), mem_update.getMem_general_gen(), mem_update.getMem_general_bd(), mem_update.getMem_tel(), mem_update.getMem_email(),
						mem_update.getMem_add(), mem_update.getMem_img(), mem_per, mem_update.getMem_type(), mem_update.getMem_store_business(), 
						mem_update.getMem_store_owner(), mem_update.getMem_store_taxid());
				
				res.setContentType("text/html; charset=utf-8");
				
				PrintWriter out = res.getWriter();
				out.println("\n" + "\n" + "\n" + "\t" + "\t" + "\t" + "\t" +"  * *  權   限   修   改   成   功  !  * * "
						  + "\n" + "\n" + "\n" + "\t" + "\t" + "\t" + "\t" +"  * *  通   知   信   已   寄   出   ~ * * ");

			/*寄送通知信給潛店會員----------(Send the Success email)----------------*/
				MailService mailService = new MailService();
				String mailURL=req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
				+ "/front-end/member/login.jsp";
				if (mem_per == 0) {
			    String messageText = "  Hello! 潛店會員  " + mem_name + 
			    		 "\n" + "\n" +" 您的會員註冊已經通過。"
			    		+ "\n" + "\n" + "現在您可以在潛進台灣網站上販賣您的行程。"
			    		 + "\n" + "\n" +	"會員登入網址   : "+mailURL; 
			    mailService.sendMail(mem_update.getMem_email(), "潛店會員開通通知", messageText);
				} else {
					String messageText = "  Hello! 潛店會員  " + mem_name + 
				    		 "\n" + "\n" +" 很抱歉，您的會員權限已經關閉。"
				    		+ "\n" + "\n" + "現在您無法在潛進台灣網站上販賣您的行程。"
				    		 + "\n" + "\n" ; 
				    mailService.sendMail(mem_update.getMem_email(), "潛店會員權限關閉通知", messageText);
				}
				
			 /*------------------------其他可能的錯誤處理-----------------------*/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/manage/storeBRC.jsp");
				failureView.forward(req, res);
			}
		}
		
//新增
        if ("insert".equals(action)) { // 來自addAdm.jsp的請求  
			System.out.println("1111111");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String adm_id = req.getParameter("adm_id");
				String adm_idReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (adm_id == null || adm_id.trim().length() == 0) {
					errorMsgs.add("管理員ID : 請勿空白");
				} else if(!adm_id.trim().matches(adm_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員ID : 只能是大小寫英文字母、和數字 , 且長度必需為6");
	            }
				
				String adm_psw = "5555";
//				String adm_pswReg = "^[(a-zA-Z0-9)]{6,6}$";
//				if (adm_psw == null || adm_psw.trim().length() == 0) {
//					errorMsgs.add("管理員密碼  : 請勿空白");
//				} else if(!adm_psw.trim().matches(adm_pswReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("管理員密碼 : 只能是大小寫英文字母、和數字 , 且長度必需為6");
//	            }
				
				String adm_name = req.getParameter("adm_name");
				String adm_nameReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (adm_name == null || adm_name.trim().length() == 0) {
					errorMsgs.add("管理員姓名 : 請勿空白");
				} else if(!adm_name.trim().matches(adm_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是大小寫英文字母、和數字 , 且長度必需為6");
	            }
				
//				String adm_tel = req.getParameter("adm_tel").trim();
//				String adm_telReg = "^[(0-9)]{9,9}$";
//				if (adm_tel == null || adm_tel.trim().length() == 0) {
//					errorMsgs.add("管理員電話 : 請勿空白");
//				} else if(!adm_tel.trim().matches(adm_telReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("管理員電話 : 只能是數字 , 且長度必需為9");
//	            }
				
				Integer adm_tel = null;
				try {
					adm_tel = new Integer(req.getParameter("adm_tel").trim());
				} catch (NumberFormatException e) {
					adm_tel = 0;
					errorMsgs.add("電話請填數字.");
				}
				
				String adm_email = req.getParameter("adm_email");
				String adm_emailReg = "^[(a-zA-Z0-9@.)]{16,16}$";
				if (adm_email == null || adm_email.trim().length() == 0) {
					errorMsgs.add("管理員EMAIL : 請勿空白");
				} else if(!adm_email.trim().matches(adm_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員EMAIL : 只能是大小寫英文字母、數字、@和 .  , 且長度必需為16");
	            }
				
				Part part=req.getPart("adm_img");
				byte[] adm_img=MyUtil.pathToByteArray(part);
				
//				try {
//					adm_img = new byte(req.getParameter("adm_img"));
//				} catch (NumberFormatException e) {
//					adm_img = 0;
//					errorMsgs.add("請附上圖片檔案");
//				}
//				
//
				AdmVO admVO = new AdmVO();
				admVO.setAdm_id(adm_id);
				admVO.setAdm_psw(adm_psw);
				admVO.setAdm_name(adm_name);
				admVO.setAdm_tel(adm_tel);
				admVO.setAdm_email(adm_email);
				admVO.setAdm_img(adm_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adm/addAdm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(adm_id, adm_psw, adm_name, adm_tel, adm_email, adm_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "adm/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAdm.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adm/addAdm.jsp");
				failureView.forward(req, res);
			}
		}
		
//刪除		
		if ("delete".equals(action)) { // 來自listAllAdm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adm_no = new String(req.getParameter("adm_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdmService admSvc = new AdmService();
				admSvc.deleteAdm(adm_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/adm/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adm/listAllAdm.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
