package com.photo_report.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photo.model.PhotoService;
import com.photo.model.PhotoVO;
import com.photo_report.model.PhotoReportService;
import com.photo_report.model.PhotoReportVO;

//@WebServlet("/PhotoReportServlet")
public class PhotoReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoReportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		/* ===============================新增檢舉========================== */

		if ("insert_report".equals(action)) {

			List<String> errorMsgs_re = new LinkedList();
			request.setAttribute("errorMsgs_re", errorMsgs_re);
			try {
				String photo_no = request.getParameter("photo_no");

				if (photo_no == null || photo_no.trim().length() == 0) {
					errorMsgs_re.add("請選擇相片");
				}

				PhotoService photoSvc = new PhotoService();
				PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);

				String photo_re_note = request.getParameter("photo_re_note");
				String photo_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,66}$";
				if (photo_re_note == null || photo_re_note.trim().length() == 0) {
					errorMsgs_re.add("事由請勿空白");
				} else if (!photo_re_note.trim().matches(photo_noteReg)) {
					errorMsgs_re.add("字數過長");
				}

				PhotoReportVO photoReportVO = new PhotoReportVO();
				photoReportVO.setMem_no("M000001");
				photoReportVO.setPhoto_no(photo_no);
				photoReportVO.setPhoto_re_note(photo_re_note);
				photoReportVO.setPhoto_re_status(0);// 被檢舉

				if (!errorMsgs_re.isEmpty()) {
					request.setAttribute("photoVO", photoVO);
					request.setAttribute("photoReportVO", photoReportVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
					failureView.forward(request, response);
					return;
				}

				/**************** 新增相片檢舉 **********************/
				PhotoReportService photoReportSvc = new PhotoReportService();
				request.setAttribute("photoVO", photoVO);
				request.setAttribute("photoReportVO", photoReportVO);
				photoReportSvc.addPhotoReport(photo_no, "M000001", photo_re_note, 0);

				/***************** 新增完成 *********************/
				String url = "/front-end/photo/photoList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs_re.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
				failureView.forward(request, response);
			}
		}
		/* ============================================================== */

		/* ============================圖片檢舉查詢ALL============================== */
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String photo_re_no = request.getParameter("photo_re_no");
				String photo_no = request.getParameter("photo_no");

				/*************************** 2.開始查詢資料 ****************************************/
				PhotoReportService photoReportSvc = new PhotoReportService();
				PhotoReportVO photoReportVO = photoReportSvc.getOnePhotoReport(photo_re_no);

				PhotoService photoSvc = new PhotoService();
				PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("photoReportVO", photoReportVO);
				request.setAttribute("photoVO", photoVO);
				String from = request.getParameter("from");
				request.getSession().setAttribute("from", from);
				String url = "/back-end/photo_report/update_photo_report.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/photo_report/update_photo_report.jsp");
				failureView.forward(request, response);
			}
		}

		/* ============================================================= */

		/* ==================================修改照片審核狀態========================= */
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				PhotoReportService photoReportSvc = new PhotoReportService();
				PhotoService photoSvc = new PhotoService();

				String photo_re_no = request.getParameter("photo_re_no");
				String photo_no = request.getParameter("photo_no");
				String param_re_status = request.getParameter("photo_re_status");
				Integer photo_re_status = new Integer(param_re_status);
				List<PhotoReportVO> list = photoReportSvc.getByPhoto(photo_no);
				PhotoReportVO photoReportVO = photoReportSvc.getOnePhotoReport(photo_re_no);
				
				PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);
				if (photo_re_status == 1) {
					for (int i = 0; i < list.size(); i++) {
						photo_re_no = list.get(i).getPhoto_re_no();
						Timestamp photo_re_time = list.get(i).getPhoto_re_time();
						String photo_re_note = list.get(i).getPhoto_re_note();
						photoReportSvc.updatePhotoReport(photo_re_no, photo_re_time, photo_re_note, photo_re_status);
					}
				} else if (photo_re_status == 2) {
					for (int i = 0; i < list.size(); i++) {
						photo_re_no = list.get(i).getPhoto_re_no();
						Timestamp photo_re_time = list.get(i).getPhoto_re_time();
						String photo_re_note = list.get(i).getPhoto_re_note();
						photoReportSvc.updatePhotoReport(photo_re_no, photo_re_time, photo_re_note, photo_re_status);
					}
					photoSvc.updatePhoto(photo_no, photoVO.getPhoto_name(), photoVO.getPhoto_pic(),
							photoVO.getPhoto_note(), 1);

				} else {
					errorMsgs.add("未選擇");
				}

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("photoReportVO", photoReportVO);
					request.setAttribute("photoVO", photoVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/photo_report/photo_report_select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************** 修改完成 *******************/
				request.setAttribute("photoRequestVO", photoReportVO);

				String url = null;
//				String from = (String) request.getSession().getAttribute("from");
//				System.out.println(from);
//				if ("listAll".equals(from)) {
//					url = "/back-end/photo_report/listAllPhotoReport.jsp";
//				} else {
//					url = "/photo_report/photo_report.do?action=listByStatus";
//				}
				url = "/back-end/photo_report/photo_report_select_page.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/photo_report/photo_report_select_page.jsp");
				failureView.forward(request, response);
			}
		}

		/* =========================================================== */

		/* ===============================根據狀態查詢========================= */
		if ("listByStatus".equals(action)) {
			String photo_re_check = request.getParameter("photo_re_check");
//			String check1 = (String) request.getSession().getAttribute("check1");
			PhotoReportService photoReportSvc = null;
			List<PhotoReportVO> list = null;
			if (photo_re_check != null && (photo_re_check.trim().length() != 0)) {
				if ("false".equals(photo_re_check)) {
					photoReportSvc = new PhotoReportService();
					list = photoReportSvc.getByStatus(0);
//					check1 = new String(photo_re_check);
				} else if ("true".equals(photo_re_check)) {
					photoReportSvc = new PhotoReportService();
					list = photoReportSvc.getByStatus(1);
					List<PhotoReportVO> list1 = photoReportSvc.getByStatus(2);
					list.addAll(1, list1);
//					check1 = new String(photo_re_check);
				}
			} else {
//				if ("false".equals(check1)) {
//					photoReportSvc = new PhotoReportService();
//					list = photoReportSvc.getByStatus(0);
//				} else if ("true".equals(check1)) {
//					photoReportSvc = new PhotoReportService();
//					list = photoReportSvc.getByStatus(1);
//					List<PhotoReportVO> list1 = photoReportSvc.getByStatus(2);
//					list.addAll(1, list1);
//				}
			}
			request.getSession().setAttribute("list", list);
//			request.getSession().setAttribute("check1", check1);

			String url = "/back-end/photo_report/listCheckPhotoReport.jsp";

			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		/* =========================================================== */
	}
}
