package com.adm.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adm.model.*;
import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.pe.model.PeService;
import com.pe.model.PeVO;
import com.util.GetAuthCode;
import com.util.MailService;
import com.util.MyUtil;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class AdmServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
//查詢單個 =====================================================================================	
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*1.接收請求參數 - 輸入格式的錯誤處理*/
				String str = req.getParameter("adm_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/manage_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String adm_no = null;
				try {
					adm_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/manage_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*2.開始查詢資料*/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(adm_no);
				if (admVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/manage_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*3.查詢完成,準備轉交(Send the Success view)*/
				req.setAttribute("admVO", admVO); // 資料庫取出的admVO物件,存入req
				String url = "/back-end/manage/manage_listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdm.jsp
				successView.forward(req, res);
				
				/*其他可能的錯誤處理*/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/manage_select.jsp");
				failureView.forward(req, res);
			}
		}
//getOne_For_Update ===========================================================================	
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdm.jsp的請求
			try {
				/*1.接收請求參數*/
				String adm_no = new String(req.getParameter("adm_no"));
				
				/*2.開始查詢資料*/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(adm_no);				
				/*3.查詢完成,準備轉交(Send the Success view)*/
				req.setAttribute("admVO", admVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/manage/manage_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_adm_input.jsp
				successView.forward(req, res);

				/*其他可能的錯誤處理*/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/manage_listall.jsp");
				failureView.forward(req, res);
			}
		}
		
//修改 / 停權 =================================================================================		
		if ("update".equals(action)) { // 來自update_adm_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*1.接收請求參數 - 輸入格式的錯誤處理*/
				String adm_no = new String(req.getParameter("adm_no").trim());
				String adm_id = req.getParameter("adm_id");
                //管理員密碼			
				String adm_psw = req.getParameter("adm_psw");
                //管理員姓名				
				String adm_name = req.getParameter("adm_name");
                //管理員電話				

				Integer adm_tel = new Integer(req.getParameter("adm_tel").trim());
                //管理員EMAIL				
				String adm_email = req.getParameter("adm_email");
                //管理員照片				
				
				
				byte[] buf = null;
				byte[] adm_img = null;
				Part part = req.getPart("adm_img");
				InputStream in = part.getInputStream();
				buf = new byte[in.available()];
				if (buf.length != 0) {
					
					in.read(buf);
					adm_img = buf;
					in.close();
				} else if (buf.length == 0) {
					adm_no = req.getParameter("adm_no");
					AdmService admSvc = new AdmService();
					buf = admSvc.getOneAdm(adm_no).getAdm_img();
					adm_img = buf;
				}
				
//				Part part = req.getPart("adm_img");
//				byte[] adm_img = MyUtil.pathToByteArray(part);
				AdmVO admVO = new AdmVO();
				admVO.setAdm_no(adm_no);
				admVO.setAdm_id(adm_id);
				admVO.setAdm_psw(adm_psw);
				admVO.setAdm_name(adm_name);
				admVO.setAdm_tel(adm_tel);
				admVO.setAdm_email(adm_email);
				admVO.setAdm_img(adm_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的admVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/manage_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/*2.開始修改資料*/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(adm_no, adm_id, adm_psw, adm_name, adm_tel, adm_email, adm_img);
				PeService peSvc = new PeService();
				peSvc.deletePe(adm_no);
				PeVO peVO=new PeVO();
				String[] funno = req.getParameterValues("funno");
				if (funno!= null) {
					for (int i = 0; i < funno.length; i++) {
						peVO = peSvc.addPe(adm_no, funno[i]);
					}
				}
				/*3.修改完成,準備轉交(Send the Success view)*/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的admVO物件,存入req
				String url = "/back-end/manage/manage_listall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdm.jsp
				successView.forward(req, res);

				/*其他可能的錯誤處理*/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/manage_update.jsp");
				failureView.forward(req, res);
			}
		}
		
//新增=========================================================================================
        if ("insert".equals(action)) { // 來自addAdm.jsp的請求  
			System.out.println("1111111");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*1.接收請求參數 - 輸入格式的錯誤處理*/
				/*88888888888888888888888888888888888888888888888888888888*/
				AdmService admSer1 = new AdmService();
				String adm_id = req.getParameter("adm_id");
				AdmVO admVO1 = admSer1.getOneAdmId(adm_id);
				String adm_idReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (adm_id == null || adm_id.trim().length() == 0) {
					errorMsgs.add("管理員ID : 請勿空白");
				} else if(!adm_id.trim().matches(adm_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員ID : 只能是大小寫英文字母，和數字 , 且長度必需為6");
	            } else if (admVO1 != null) {
					errorMsgs.add("重複的管理員ID.");
				}
				/*88888888888888888888888888888888888888888888888888888888*/
	

				
				String adm_psw = GetAuthCode.getAuthCode();
				
				String adm_name = req.getParameter("adm_name");
				String adm_nameReg = "^[(a-zA-Z0-9)]{6,6}$";
				if (adm_name == null || adm_name.trim().length() == 0) {
					errorMsgs.add("管理員姓名 : 請勿空白");
				} else if(adm_name.trim().length()>=6) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 且長度必需小於6");
	            }
				String tel = req.getParameter("adm_tel");
				Integer adm_tel=null;
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("管理員電話 : 請勿空白");
				}else {
					adm_tel=new Integer(tel);
					if(!((adm_tel.toString().length()) == 9)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("管理員電話 : 只能是數字 , 且長度必需為9");
		            }else {
		            	adm_tel = new Integer(tel.trim());
		            }
				} 
				
				
				String adm_email = req.getParameter("adm_email");
				String adm_emailReg = "^[(a-zA-Z0-9@.)]{16,40}$";
				if (adm_email == null || adm_email.trim().length() == 0) {
					errorMsgs.add("管理員EMAIL : 請勿空白");
				} else if(!adm_email.trim().matches(adm_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員EMAIL : 只能是大小寫英文字母、數字，@和 .  , 且長度必需為16");
	            }
				
                Part part=req.getPart("adm_img");
				byte[] adm_img=MyUtil.pathToByteArray(part);
				if (adm_img == null) {
					errorMsgs.add("請選擇圖片檔案");
				}

				/*新增權限---------------------------------------------------------------------*/
				List<String> pelist = new LinkedList<String>();

				String[] fun = req.getParameterValues("funno");

				if (fun != null) {
					for (int i = 0; i < fun.length; i++) {
						pelist.add(fun[i]);
					}
				}
				
				HashMap<String,String> AdmValied  = new HashMap<>();
				
				AdmVO admVO = new AdmVO();
				admVO.setAdm_id(adm_id);
				admVO.setAdm_psw(adm_psw);
				admVO.setAdm_name(adm_name);
				admVO.setAdm_tel(adm_tel);
				admVO.setAdm_email(adm_email);
				admVO.setAdm_img(adm_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(500);
					req.setAttribute("admVO", admVO);// 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("pelist", pelist);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manage/manage_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*2.開始新增資料*/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(adm_id,adm_psw,adm_name,adm_tel,adm_email,adm_img);
				String adm_no=admSvc.getOneAdmId(adm_id).getAdm_no();
				/*3.新增完成,準備轉交(Send the Success view)*/
				
				PeService peSvc = new PeService();
				PeVO peVO = null;
				
				String[] funno = req.getParameterValues("funno");
				if (funno!= null) {
					for (int i = 0; i < funno.length; i++) {
						peVO = peSvc.addPe(adm_no, funno[i]);
					}
				}
System.out.println(adm_email);
				MailService mailService = new MailService();
			    String messageText = "Hello! 管理員" + adm_name + " 請謹記此密碼: " + adm_psw + "\n" +" (已經啟用)"
			    		 + "\b" +	"登入以下網址   :   "+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/back-end/admlogin/admlogin.jsp"; 
			    mailService.sendMail(adm_email, "密碼通知", messageText);
				
				String url = "/back-end/manage/manage_listall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAdm.jsp
				successView.forward(req, res);				
				
				/*其他可能的錯誤處理*/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/manage_add.jsp");
				failureView.forward(req, res);
			}
		}
		
//刪除==========================================================================================		
		if ("delete".equals(action)) { // 來自listAllAdm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/*1.接收請求參數*/
				String adm_no = new String(req.getParameter("adm_no"));
				
				/*2.開始刪除資料*/
				AdmService admSvc = new AdmService();
				admSvc.deleteAdm(adm_no);
				
				/*3.刪除完成,準備轉交(Send the Success view)*/								
				String url = "/back-end/manage/manage_listall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/*其他可能的錯誤處理*/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manage/manage_listall.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
