package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.util.MailService;
import com.util.MyUtil;

//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		// 取得name = actoin的hiddenInput參數
		String action = req.getParameter("action");

//=================================登出=======================================================
		if ("logout".equals(action)) {

			HttpSession session = req.getSession();
			session.invalidate();
//			req.getSession().removeAttribute("memVO");

			String url = req.getContextPath() + "/front-end/member/login.jsp";
			res.sendRedirect(url);
			return;

		}

//============================================== 登入Action ===============================================
		if ("login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);// <--------

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String inputAccount = req.getParameter("inputAccount");// <----取客戶端參數1
				String inputPassword = req.getParameter("inputPassword");// <----取客戶端參數2
				// ----------------帳號驗證---------------
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				// -----------------密碼驗證----------------
				if (inputPassword == null || (inputPassword.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// <-------------------如果有任何錯誤進入此頁面
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/loginDeny.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String account = null;
				String frontEndPassword = null;

				// ----------------帳號驗證---------------
				try {
					account = inputAccount.trim();// <----客戶端參數放入JAVA變數中，準備放入VO
				} catch (Exception e) {
					errorMsgs.add("帳號編號格式不正確");// <----錯誤訊息取值
				}

				// -----------------密碼驗證---------------
				try {
					frontEndPassword = inputPassword.trim();// <----客戶端參數放入JAVA變數中，準備放入VO
				} catch (Exception e) {
					errorMsgs.add("密碼編號格式不正確");// <----錯誤訊息取值
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// <-------------------如果有任何錯誤進入此頁面
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/loginDeny.jsp");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemService MemSvc = new MemService();

				// -------------------確認資料庫中是否有此帳號---------------
				MemVO memVO = MemSvc.getOneMemByAccount(account);// <--------查詢資料庫by主鍵(JAVA變數放入VO)
				if (memVO == null) {
					errorMsgs.add("查無此帳號");
				} else if (!(frontEndPassword.equals(memVO.getMem_psw()) && account.equals(memVO.getMem_id()))) {
					errorMsgs.add("密碼不正確");
				} else if (memVO.getMem_per() != 0) {
					errorMsgs.add("帳號未開通");
				} else {
					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					// --------------------帳號密碼正確--------------------------------------------------

					// *工作1: 才在session內做已經登入過的標識
					HttpSession session = req.getSession();

					//
					session.setAttribute("account", account);
					session.setAttribute("mem_no", memVO.getMem_no());
					session.setAttribute("memVO", memVO);
					session.setAttribute("mem_type", memVO.getMem_type());
					session.setAttribute("mem_per", memVO.getMem_per());
					// *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					try {

						String location = (String) session.getAttribute("location");
						String URI = (String) session.getAttribute("URI");
						if (location != null) {
							// 受保護用重導向
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
//						} else if (URI != null) {
//							// 未受保護用重導向
//							res.sendRedirect(URI);
//							return;
						} else {
							// *工作3: (-->如無來源網頁:則重導至login_success.jsp)
							String url = req.getContextPath() + "/index.jsp";
							res.sendRedirect(url);
							return;
						}
					} catch (Exception ignored) {
					}

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// <-------------------如果有任何錯誤進入此頁面

					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");// <--------------如果有任何錯誤，跳轉回首頁
				failureView.forward(req, res);
			}
		}

// =============================================================================================================

//========================================  註冊(signUp) Action For 一般會員 =========================================================

		if ("signUpForGeneral".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				/* 必填資料 */
				// 帳號
				MemService MS1 = new MemService();
				String enameReg = "^[(a-zA-Z0-9)]{0,18}$";
				String mem_id = req.getParameter("mem_id");
				MemVO memVO1 = MS1.getOneMemByAccount(mem_id);

				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("請填入會員帳號.");
				} else if (!mem_id.trim().matches(enameReg)) {
					errorMsgs.add("會員帳號只能是英文字母大小寫、數字 , 且長度必需在6到18之間");
				} else if (memVO1 != null) {
					errorMsgs.add("重複的會員帳號.");
				}

				// 密碼
//				String mem_psw = req.getParameter("mem_psw");
//				if (mem_psw == null || mem_psw.trim().length() == 0) {
//					errorMsgs.add("請填入會員密碼.");
//				}
				String pswReg = "^[(a-zA-Z0-9)]{6,20}$";
				String psw = req.getParameter("pw");
				String mem_psw = req.getParameter("mem_psw");
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼不能留白");
				} else if (!mem_psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是英文、數字,且長度必須在6到20之間!!");
				} else if (!mem_psw.equals(psw)) {
					errorMsgs.add("密碼不一致");
				}

				// 信箱
				String mem_email;// 預設值
				mem_email = req.getParameter("mem_email");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("信箱不能留白");
				}

				/* 選填資料 */
				// 名稱
				String mem_name;// 預設值
				mem_name = req.getParameter("mem_name");
				if (mem_name == null || mem_name.trim().length() == 0) {
					mem_name = mem_id;
				} else if (mem_name.trim().length() > 6) {
					errorMsgs.add("字數過長");
				}

				// 性別(0:不透漏 1:男生 2:女生)
				Integer mem_general_gen;
				mem_general_gen = new Integer(req.getParameter("mem_general_gen"));

				// 生日日期
				String birthday = req.getParameter("mem_general_bd");
				java.sql.Date mem_general_bd = null;
				if (birthday == null || birthday.trim().length() == 0) {
					mem_general_bd = new java.sql.Date(System.currentTimeMillis());
				} else {
					mem_general_bd = java.sql.Date.valueOf(birthday);
				}

				// 電話
				String telReg = "^[(0-9)]{6,10}$";
				String tel = req.getParameter("mem_tel");
				Integer mem_tel = null;
				if (tel == null || tel.trim().length() == 0) {
					mem_tel = 0;// 預設值
				} else if (!tel.trim().matches(telReg)) {
					errorMsgs.add("請輸入正確電話");
				} else if (tel.trim().length() > 10) {
					errorMsgs.add("電話長度過長");
				} else {
					mem_tel = new Integer(tel);
				}

				// 地址
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				String mem_add;
				mem_add = req.getParameter("mem_add");
				if (mem_add == null || mem_add.trim().length() == 0) {
					mem_add = "";// 預設值
				} else {
					mem_add = county + district + mem_add;
				}

				// 大頭照
				Part myPart = req.getPart("mem_img");
				byte[] mem_img = MyUtil.pathToByteArray(myPart);

				// ---------以下資料不在註冊表格上----------------
				// 會員編號(primarKey)
				// 會員權限(0:正常, 1:停權)
				int mem_per = 1;
				// 會員類型(0:一般會員, 1:店家)
				int mem_type = 0;
				// 店家營業登記證
				byte[] mem_store_business = null;
				// 店家負責人
				String mem_store_owner = null;
				// 店家統一編號
				String mem_store_taxid = null;

				// 當輸入有錯誤時，記錄下當前輸入狀態的的VO，讓使用者只須重新填寫錯誤的表格
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_id(mem_id);
//				memVO.setMem_psw(mem_psw);
				memVO.setMem_general_gen(mem_general_gen);
				memVO.setMem_general_bd(mem_general_bd);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_email(mem_email);
				memVO.setMem_add(mem_add);
				memVO.setMem_img(mem_img);
				memVO.setMem_per(1);// 剛創建為停權
				memVO.setMem_type(0);
				memVO.setMem_store_business(mem_store_business);
				memVO.setMem_store_owner(mem_store_owner);
				memVO.setMem_store_taxid(mem_store_taxid);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMem.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始新增資料到資料庫
				 ***************************************/
				MemService MS = new MemService();
				MS.addMem(mem_name, mem_id, mem_psw, mem_general_gen, mem_general_bd, mem_tel, mem_email, mem_add,
						mem_img, mem_per, mem_type, mem_store_business, mem_store_owner, mem_store_taxid);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

//				MemVO memVO_1 = MS.getOneMemByAccount(mem_id);
				String url = "/front-end/member/mailVerification.jsp";
//				HttpSession session = req.getSession();
//				session.setAttribute("account", mem_id);
//				session.setAttribute("mem_no", memVO_1.getMem_no());
//				session.setAttribute("memVO", memVO_1);
//				session.setAttribute("mem_type", memVO_1.getMem_type());
//				session.setAttribute("mem_per", memVO_1.getMem_per());
				MailService mailService = new MailService();
				mailService.sendMail(mem_email, "密碼通知",
						req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
								+ "/member/MemberServlet.do?action=openAccount&mem_id=" + mem_id);

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMem.jsp");
				failureView.forward(req, res);
			}
		}
// =====================================================================================================================

//========================================  註冊(signUp) Action For 店家 =========================================================

		if ("signUpForStore".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 店家名稱
				String mem_name = "";// 預設值
				mem_name = req.getParameter("mem_name");
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("請填入會員帳號.");
				} else if (mem_name.trim().length() > 6) {
					errorMsgs.add("店家名稱必需在6字之內");
				}

				// 帳號
				MemService memSvc = new MemService();
				String enameReg = "^[(a-zA-Z0-9)]{0,18}$";
				String mem_id = req.getParameter("mem_id");
				MemVO memVO = memSvc.getOneMemByAccount(mem_id);
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("請填入店家帳號.");
				} else if (!mem_id.trim().matches(enameReg)) {
					errorMsgs.add("店家帳號只能是英文字母大小寫、數字 , 且長度必需在6到18之間");
				} else if (memVO != null) {
					errorMsgs.add("帳號已使用，請重新輸入");
				}

				// 密碼
//				String mem_psw = req.getParameter("mem_psw");
//				if (mem_psw == null || mem_psw.trim().length() == 0) {
//					errorMsgs.add("請填入店家密碼.");
//				}
//				
				String pswReg = "^[(a-zA-Z0-9)]{6,20}$";
				String psw = req.getParameter("pw");
				String mem_psw = req.getParameter("mem_psw");
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼不能留白");
				} else if (!mem_psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是英文、數字,且長度必須在6到20之間!!");
				} else if (!mem_psw.equals(psw)) {
					errorMsgs.add("密碼不一致");
				}

				// 電話
				String tel = req.getParameter("mem_tel");
				Integer mem_tel = null;// 預設值
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				} else if (tel.trim().length() > 10) {
					errorMsgs.add("請輸入正確電話號碼");
				} else {
					mem_tel = new Integer(tel);
				}

				// 信箱
				String mem_email = req.getParameter("mem_email");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("請輸入信箱");
				} else if (mem_email.trim().length() > 50) {
					errorMsgs.add("請輸入正確信箱");
				}
				// 地址
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				String mem_add = req.getParameter("mem_add");
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				} else if (mem_email.trim().length() > 100) {
					errorMsgs.add("請輸入正確地址");
				} else {
					mem_add = county + district + mem_add;
				}

				// 店家負責人
				String mem_store_owner = "";
				mem_store_owner = req.getParameter("mem_store_owner");
				if (mem_store_owner == null || mem_store_owner.trim().length() == 0) {
					errorMsgs.add("請填入店家負責人.");
				} else if (mem_store_owner.trim().length() > 20) {
					errorMsgs.add("店家負責人需在20字之內");
				}
				// 店家營業登記證
				Part myPart = req.getPart("mem_store_business");
				byte[] mem_store_business = MyUtil.pathToByteArray(myPart);
				String type = myPart.getHeader("Content-type").substring(0, 4);
				if (type == null || type.trim().trim().length() == 0) {
					errorMsgs.add("請放封面");
				} else if ("image".indexOf(type) == -1) {
					errorMsgs.add("檔案格式錯誤");
				}

				// 店家統一編號
				String mem_store_taxid = req.getParameter("mem_store_taxid");
				if (mem_store_taxid == null || mem_store_taxid.trim().length() == 0) {
					errorMsgs.add("請填入店家統一編號.");
				} else if (mem_store_taxid.trim().length() > 8) {
					errorMsgs.add("店家負責人需在8字之內");
				}

				// ---------以下資料不在店家註冊表格上----------------
				// 會員權限(0:正常, 1:停權)
				int mem_per = 1;
				// 會員類型(0:一般會員, 1:店家)
				int mem_type = 1;
				// 性別(0:不透漏 1:男生 2:女生)
				Integer mem_general_gen = 0;
				// 生日日期
				java.sql.Date mem_general_bd = null;
				// 大頭貼
				byte[] mem_img = null;

				// 當輸入有錯誤時，記錄下當前輸入狀態的的VO，讓使用者只須重新填寫錯誤的表格
				memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_id(mem_id);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_general_gen(mem_general_gen);
				memVO.setMem_general_bd(mem_general_bd);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_email(mem_email);
				memVO.setMem_add(mem_add);
				memVO.setMem_img(mem_img);
				memVO.setMem_per(mem_per);
				memVO.setMem_type(mem_type);
				memVO.setMem_store_business(mem_store_business);
				memVO.setMem_store_owner(mem_store_owner);
				memVO.setMem_store_taxid(mem_store_taxid);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addStore.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始新增資料到資料庫
				 ***************************************/
				MemService MS = new MemService();
				MS.addMem(mem_name, mem_id, mem_psw, mem_general_gen, mem_general_bd, mem_tel, mem_email, mem_add,
						mem_img, mem_per, mem_type, mem_store_business, mem_store_owner, mem_store_taxid);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				memVO = MS.getOneMemByAccount(mem_id);
//				String url = "/index.jsp";
//				HttpSession session = req.getSession();
//				session.setAttribute("account", mem_id);
//				session.setAttribute("mem_no", memVO.getMem_no());
//				session.setAttribute("memVO", memVO);
//				session.setAttribute("mem_type", memVO.getMem_type());
//				session.setAttribute("mem_per", memVO.getMem_per());

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/login.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addStore.jsp");
				failureView.forward(req, res);
			}
		}
// ==========================================================================================================================

//========================================  修改(update) Action For 一般會員 =========================================================

		if ("updaetForGeneral".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				/* 必填資料 */
				// 帳號
				MemService MS1 = new MemService();
				String enameReg = "^[(a-zA-Z0-9)]{6,18}$";
				String mem_no = req.getParameter("mem_no");
				MemVO mem_update = MS1.getOneMem(mem_no);
				String mem_id = mem_update.getMem_id();

				// 密碼
				String mem_psw = req.getParameter("mem_psw");
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("請填入會員密碼.");
				}

				// 信箱
				String mem_email;// 預設值
				mem_email = req.getParameter("mem_email");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白.");
				}

				/* 選填資料 */
				// 名稱
				String mem_name;// 預設值
				mem_name = req.getParameter("mem_name");
				if (mem_name == null || mem_name.trim().length() == 0) {
					mem_name = mem_id;
				} else if (mem_name.trim().length() > 6) {
					errorMsgs.add("字數過長");
				}

				// 性別(0:不透漏 1:男生 2:女生)
				Integer mem_general_gen;
				mem_general_gen = new Integer(req.getParameter("mem_general_gen"));

				// 生日日期
				String birthday = req.getParameter("mem_general_bd");
				java.sql.Date mem_general_bd = null;
				if (birthday == null || birthday.trim().length() == 0) {
					mem_general_bd = new java.sql.Date(System.currentTimeMillis());
				} else {
					mem_general_bd = java.sql.Date.valueOf(birthday);
				}

				// 電話
				String tel = req.getParameter("mem_tel");
				Integer mem_tel = null;
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請填入電話");
				} else if (tel.trim().length() > 10) {
					errorMsgs.add("電話長度過長");
				} else {
					mem_tel = new Integer(tel);
				}

				// 地址
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				String mem_add = req.getParameter("mem_add");
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				} else if (mem_email.trim().length() > 100) {
					errorMsgs.add("請輸入正確地址");
				} else {
					//*********************************************
					mem_add = county + district + mem_add;
				}

				// 大頭照

				byte[] buf = null;
				byte[] mem_img = null;
				Part myPart = req.getPart("mem_img");
				InputStream in = myPart.getInputStream();
				buf = new byte[in.available()];
				if (buf.length != 0) {
					String type = myPart.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) {
						errorMsgs.add("檔案格式錯誤");
					}
					in.read(buf);
					mem_img = buf;
					in.close();
				} else if (buf.length == 0) {
					mem_no = req.getParameter("mem_no");
					MemService memSvc = new MemService();
					MemVO memVO = memSvc.getOneMem(mem_no);
					buf = memVO.getMem_img();
					mem_img = buf;
				}

				// ---------以下資料不在註冊表格上----------------
				// 會員編號(primarKey)
				// 會員權限(0:正常, 1:停權)
				int mem_per = 0;
				// 會員類型(0:一般會員, 1:店家)
				int mem_type = 0;
				// 店家營業登記證
				byte[] mem_store_business = null;
				// 店家負責人
				String mem_store_owner = null;
				// 店家統一編號
				String mem_store_taxid = null;

				// 當輸入有錯誤時，記錄下當前輸入狀態的的VO，讓使用者只須重新填寫錯誤的表格
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_id(mem_id);
//				memVO.setMem_psw(mem_psw);
				memVO.setMem_general_gen(mem_general_gen);
				memVO.setMem_general_bd(mem_general_bd);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_email(mem_email);
				memVO.setMem_add(mem_add);
				memVO.setMem_img(mem_img);
				memVO.setMem_per(0);
				memVO.setMem_type(0);
				memVO.setMem_store_business(mem_store_business);
				memVO.setMem_store_owner(mem_store_owner);
				memVO.setMem_store_taxid(mem_store_taxid);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memberCenter/updateMem.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始新增資料到資料庫
				 ***************************************/
				MemService MS = new MemService();
				MS.updateMem(mem_no, mem_name, mem_id, mem_psw, mem_general_gen, mem_general_bd, mem_tel, mem_email,
						mem_add, mem_img, mem_per, mem_type, mem_store_business, mem_store_owner, mem_store_taxid);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/memberCenter/memDetail.jsp";
				memVO = MS.getOneMem(mem_no);
				req.getSession().setAttribute("memVO", memVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memberCenter/memDetail.jsp");
				failureView.forward(req, res);
			}

			// InputStream in =
			// getServletContext().getResourceAsStream("/NoData/no.png");//為了要讓整個war檔匯出後，讓別的使用者不會有路徑上的問題
			// String realPath = getServletContext().getRealPath("/images_uploaded");
			// //為了要讓整個war檔匯出後，讓別的使用者不會有路徑上的問題
			// myPart.write(realPath+"/"+fileName);//寫入本地硬碟
		}
		// =====================================================================================================================

		/* ============================== 店家修改 ====================================== */
		if ("updateForStore".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			MemService memSvc = new MemService();
//			String enameReg = "^[(a-zA-Z0-9)]{6,18}$";
			String mem_no = req.getParameter("mem_no");

			MemVO mem_update = memSvc.getOneMem(mem_no);
			String mem_id = mem_update.getMem_id();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 店家名稱
				String mem_name = "";// 預設值
				mem_name = req.getParameter("mem_name");

				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("請填入會員帳號.");
				} else if (mem_name.trim().length() > 6) {
					errorMsgs.add("店家名稱必需在6字之內");
				}

				// 帳號

				// 密碼
//			String mem_psw = req.getParameter("mem_psw");
//			if (mem_psw == null || mem_psw.trim().length() == 0) {
//				errorMsgs.add("請填入店家密碼.");
//			}
//			
				String pswReg = "^[(a-zA-Z0-9)]{6,20}$";
				String psw = req.getParameter("pw");
				String mem_psw = req.getParameter("mem_psw");
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼不能留白");
				} else if (!mem_psw.trim().matches(pswReg)) {
					errorMsgs.add("密碼只能是英文、數字,且長度必須在6到20之間!!");
				} else if (!mem_psw.equals(psw)) {
					errorMsgs.add("密碼不一致");
				}

				// 電話
				String tel = req.getParameter("mem_tel");
				Integer mem_tel = null;// 預設值
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				} else if (tel.trim().length() > 10) {
					errorMsgs.add("請輸入正確電話號碼");
				} else {
					mem_tel = new Integer(tel);
				}

				// 信箱
				String mem_email = req.getParameter("mem_email");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("請輸入信箱");
				} else if (mem_email.trim().length() > 50) {
					errorMsgs.add("請輸入正確信箱");
				}
				// 地址
				String mem_add = req.getParameter("mem_add");
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				} else if (mem_add.trim().length() > 100) {
					errorMsgs.add("請輸入正確地址");
				}

				// 店家負責人
				String mem_store_owner = "";
				mem_store_owner = req.getParameter("mem_store_owner");
				if (mem_store_owner == null || mem_store_owner.trim().length() == 0) {
					errorMsgs.add("請填入店家負責人.");
				} else if (mem_store_owner.trim().length() > 20) {
					errorMsgs.add("店家負責人需在20字之內");
				}
				// 店家營業登記證
				byte[] mem_store_business = null;
				byte[] buf = null;
				Part part = req.getPart("mem_store_business");
				InputStream in = part.getInputStream();
				buf = new byte[in.available()];
				if (buf.length != 0) {
					String type = part.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) {
						errorMsgs.add("檔案格式錯誤");
					}
					in.read(buf);
					mem_store_business = buf;
					in.close();
				} else if (buf.length == 0) {
//					mem_no = req.getParameter("album_no");
					MemService memSvc2 = new MemService();
					MemVO memVO = memSvc2.getOneMem(mem_no);
					buf = memVO.getMem_store_business();
					mem_store_business = buf;
				}

				// 店家統一編號
				String mem_store_taxid = req.getParameter("mem_store_taxid");
				if (mem_store_taxid == null || mem_store_taxid.trim().length() == 0) {
					errorMsgs.add("請填入店家統一編號.");
				} else if (mem_store_taxid.trim().length() > 8) {
					errorMsgs.add("店家負責人需在8字之內");
				}

				// ---------以下資料不在店家註冊表格上----------------
				// 會員權限(0:正常, 1:停權)
				int mem_per = mem_update.getMem_per();
				// 會員類型(0:一般會員, 1:店家)
				int mem_type = mem_update.getMem_type();
				// 性別(0:不透漏 1:男生 2:女生)
				Integer mem_general_gen = mem_update.getMem_general_gen();
				// 生日日期
				java.sql.Date mem_general_bd = null;
				// 大頭貼
				byte[] mem_img = null;

				// 當輸入有錯誤時，記錄下當前輸入狀態的的VO，讓使用者只須重新填寫錯誤的表格
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_id(mem_id);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_general_gen(mem_general_gen);
				memVO.setMem_general_bd(mem_general_bd);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_email(mem_email);
				memVO.setMem_add(mem_add);
				memVO.setMem_img(mem_img);
				memVO.setMem_per(mem_per);
				memVO.setMem_type(mem_type);
				memVO.setMem_store_business(mem_store_business);
				memVO.setMem_store_owner(mem_store_owner);
				memVO.setMem_store_taxid(mem_store_taxid);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_info.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始新增資料到資料庫
				 ***************************************/
				MemService MS = new MemService();
//				MS.updateMem(mem_name, mem_id, mem_psw, mem_general_gen, mem_general_bd, mem_tel, mem_email, mem_add,
//						mem_img, mem_per, mem_type, mem_store_business, mem_store_owner, mem_store_taxid);
				MS.updateMem(mem_no, mem_name, mem_id, mem_psw, mem_general_gen, mem_general_bd, mem_tel, mem_email,
						mem_add, mem_img, mem_per, mem_type, mem_store_business, mem_store_owner, mem_store_taxid);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				MemVO memVO_1 = MS.getOneMemByAccount(mem_id);
				String url = "/front-end/member/memDetail.jsp";
				HttpSession session = req.getSession();
				session.setAttribute("account", mem_id);
				session.setAttribute("mem_no", memVO_1.getMem_no());
				session.setAttribute("memVO", memVO_1);
				session.setAttribute("mem_type", memVO_1.getMem_type());
				session.setAttribute("mem_per", memVO_1.getMem_per());

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_info.jsp");
				failureView.forward(req, res);
			}
		}
		/*------------------------------------------------------------*/

		/*---------------------------Ajax驗證帳號是否使用-------------------*/
		if ("ckaccount".equals(action)) {
			String mem_id = req.getParameter("account");
			MemService memSvc = new MemService();
			JSONObject object = new JSONObject();

			PrintWriter out = res.getWriter();
			if (memSvc.getOneMemByAccount(mem_id) != null) {
				try {
					object.put("data", "帳號已使用");
					out.print(object);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					object.put("data", "");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print(object);
			}
		}
		/*------------------------------------------------------------*/

		/*--------------------------開通帳號----------------------------*/
		if ("openAccount".equals(action)) {
			String mem_id = req.getParameter("mem_id");
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMemByAccount(mem_id);
			memVO.setMem_per(0);
			memVO = memSvc.updateMem(memVO.getMem_no(), memVO.getMem_name(), memVO.getMem_id(), memVO.getMem_psw(),
					memVO.getMem_general_gen(), memVO.getMem_general_bd(), memVO.getMem_tel(), memVO.getMem_email(),
					memVO.getMem_add(), memVO.getMem_img(), memVO.getMem_per(), memVO.getMem_type(),
					memVO.getMem_store_business(), memVO.getMem_store_owner(), memVO.getMem_store_taxid());

			HttpSession session = req.getSession();
			session.setAttribute("account", mem_id);
			session.setAttribute("mem_no", memVO.getMem_no());
			session.setAttribute("memVO", memVO);
			session.setAttribute("mem_type", memVO.getMem_type());
			session.setAttribute("mem_per", memVO.getMem_per());

			String url = "/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}

	// 取得檔案名稱(存到資料庫沒用到，存到本地端使用)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		// 為了IE所以要把檔名塞到File內
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	// 隨機函數產生密碼
	public String genAuthCode() { // 0~9：30~39。A~Z：41~5A。a~z：61~7A。
		String verificationCode = ""; // 創建驗證碼字串，初始值給""。
		int randomNum; // 創建一個存放亂數的變數
		for (int i = 1; i <= 8; i++) { // i=1~8，分別代表第i個驗證碼
			randomNum = (int) (Math.random() * 62); // 產生0~61亂數，共有62個亂數(數字10+大寫26+小寫26)
			if (randomNum <= 9) { // (數字)進入此條件的亂數範圍為randomNum=0~9，共10個數字，分別代表驗證碼的0~9
				verificationCode += randomNum; // 數字無須轉換為字元，直接寫入驗證碼
			} else if (randomNum <= 35) { // (大寫字母)進入此條件的亂數範圍為randomNum=10~35，共26個數字，分別代表驗證碼的A~Z
				verificationCode += (char) ('\u0041' + (randomNum - 10)); // 大寫A為'\u0041'，當+(randomNum-10)=0時，為A，而當+(randomNum-10)=+25時，為Z。
			} else { // (小寫字母)進入此條件的亂數範圍為randomNum=36~61，共26個數字，分別代表驗證碼的a~z
				verificationCode += (char) ('\u0061' + (randomNum - 36)); // 小寫a為'\u0061'，當+(randomNum-36)=0時，為a，而當+(randomNum-36)=+25時，為z。
			}
		}
		System.out.println("本次隨機產生驗證碼為：" + "\n" + verificationCode); // 印出驗證碼
		return verificationCode;
	}

}