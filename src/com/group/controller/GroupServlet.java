package com.group.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.dive.model.DiveService;
import com.dive.model.DiveVO;
import com.group.model.*;
import com.group_join.model.*;

@MultipartConfig
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String group_no = req.getParameter("group_no");
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.開始查詢資料 *****************************************/
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroup(group_no);
//				Group_joinService group_joinSvc = new Group_joinService();
//				Group_joinVO group_joinVO = group_joinSvc.getOneGroup_join(group_no, mem_no);

				if (groupVO == null) {
					errorMsgs.add("查無資料");
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("group_joinVO", group_joinVO);
				req.setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Dive".equals(action)) { // 來自listAllGroup.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String dive_no = req.getParameter("dive_no");
				/*************************** 2.開始查詢資料 *****************************************/
				GroupService groupSvc = new GroupService();
				List<GroupVO> groupVO = groupSvc.getAll_Dive(dive_no);
				if (groupVO == null) {
					errorMsgs.add("查無資料");
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.getSession().setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listAllGroupForDive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getArea".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String WeatherAreaNo = req.getParameter("WeatherAreaNo");
				/*************************** 2.開始查詢資料 *****************************************/
				DiveService diveSvc = new DiveService();
				List<GroupVO> groupVO = new ArrayList<>();
				GroupService groupSvc = new GroupService();
				// 南部、離島潛場
				if (WeatherAreaNo.equals("WA00001") || WeatherAreaNo.equals("WA00006")) {
					List<DiveVO> listD = diveSvc.getAllByWeatherArea(WeatherAreaNo);
					for (DiveVO d : listD) {
						List<GroupVO> g = groupSvc.getAll_Dive(d.getDive_no());
						for (GroupVO DivegroupVO : g) {
							groupVO.add(DivegroupVO);
						}
					}
				}

				// 北部潛場
				if (WeatherAreaNo.equals("WA00004") || WeatherAreaNo.equals("WA00005")) {
					List<DiveVO> listD = diveSvc.getAllByWeatherArea("WA00004");
					List<DiveVO> listD2 = diveSvc.getAllByWeatherArea("WA00005");
					for (int i = 0; i < listD2.size(); i++) {
						DiveVO d = listD2.get(i);
						listD.add(d);
					}
					for (DiveVO d : listD) {
						List<GroupVO> g = groupSvc.getAll_Dive(d.getDive_no());
						for (GroupVO DivegroupVO : g) {
							groupVO.add(DivegroupVO);
						}
					}
				}
				// 東部潛場
				if (WeatherAreaNo.equals("WA00002") || WeatherAreaNo.equals("WA00003")
						|| WeatherAreaNo.equals("WA00007")) {
					List<DiveVO> listD = diveSvc.getAllByWeatherArea("WA00002");
					List<DiveVO> listD2 = diveSvc.getAllByWeatherArea("WA00003");
					List<DiveVO> listD3 = diveSvc.getAllByWeatherArea("WA00007");
					for (int i = 0; i < listD2.size(); i++) {
						DiveVO d = listD2.get(i);
						listD.add(d);
					}
					for (int i = 0; i < listD3.size(); i++) {
						DiveVO d = listD3.get(i);
						listD.add(d);
					}
					for (DiveVO d : listD) {
						List<GroupVO> g = groupSvc.getAll_Dive(d.getDive_no());
						for (GroupVO DivegroupVO : g) {
							groupVO.add(DivegroupVO);
						}
					}
				}
				if (groupVO == null) {
					errorMsgs.add("查無資料");
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.getSession().setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listAllGroupForDive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}
		if ("checking".equals(action) || "mygroup".equals(action) || "history".equals(action))

		{ // 來自listAllMemGroup.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mem_no = req.getParameter("mem_no");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				GroupService groupSvc = new GroupService();
				List<Group_joinVO> group_joinList = null;
				if ("checking".equals(action)) {
					group_joinList = group_joinSvc.getAllChecking(mem_no);
				}
				if ("mygroup".equals(action)) {
					group_joinList = group_joinSvc.getAllForMem(mem_no);
					for (int i = 0; i < group_joinList.size(); i++) {
						Group_joinVO gj = group_joinList.get(i);
						GroupVO groupVO = groupSvc.getOneGroup(gj.getGroup_no());
						if (!groupVO.getMem_no().equals(mem_no) || groupVO.getGroup_status() != 0) {
							group_joinList.remove(i);
							i--;
						}
					}
				}

				if ("history".equals(action)) {
					group_joinList = group_joinSvc.getAllHistory(mem_no);
				}

				if (group_joinList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memberCenter/member_myGroup.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("group_joinList", group_joinList); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/memberCenter/member_myGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getAll_For_Mem".equals(action)) { // 來自select_page.jsp的請求
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GroupService groupSvc = new GroupService();
				List<GroupVO> groupVO = groupSvc.getAll_Mem(mem_no);
				if (groupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

//				req.getSession().setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
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

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String group_no = new String(req.getParameter("group_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroup(group_no);
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("groupupdateVO", groupVO); // 資料庫取出的groupVO物件,存入req
				req.setAttribute("group_begin_time", sdFormat.format(groupVO.getGroup_begin_time()));
				req.setAttribute("group_end_time", sdFormat.format(groupVO.getGroup_end_time()));
				req.setAttribute("group_tour_stop_time", sdFormat.format(groupVO.getGroup_tour_stop_time()));
				String url = "/front-end/group/update_group.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_group_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/listAllgroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_group_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String enameReg2 = "^[(0-9_)]{10}$";
				String group_no = req.getParameter("group_no");
				String dive_no = req.getParameter("dive_no");
				String group_name = req.getParameter("group_name").trim();
				if (group_name == null || group_name.trim().length() == 0) {
					errorMsgs.add("揪團名稱請勿空白");
				}
				String group_tel = req.getParameter("group_tel").trim();
				if (group_tel == null || group_tel.trim().length() == 0) {
					errorMsgs.add("連絡電話請勿空白");
				} else if (!group_tel.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 只能輸入數字 ,且長度必需是10位數");
				}

				java.sql.Timestamp group_tour_stop_time = null;
				try {
					group_tour_stop_time = java.sql.Timestamp.valueOf(req.getParameter("group_tour_stop_time").trim());
				} catch (IllegalArgumentException e) {
					group_tour_stop_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp group_begin_time = null;
				try {
					group_begin_time = java.sql.Timestamp.valueOf(req.getParameter("group_begin_time").trim());
				} catch (IllegalArgumentException e) {
					group_begin_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp group_end_time = null;
				try {
					group_end_time = java.sql.Timestamp.valueOf(req.getParameter("group_end_time").trim());
				} catch (IllegalArgumentException e) {
					group_end_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				if (group_end_time.getTime() < group_begin_time.getTime()) {
					errorMsgs.add("活動開始日不得小於活動結束日!");
				}
				if (group_tour_stop_time.getTime() > group_begin_time.getTime()) {
					errorMsgs.add("報名截止日不得大於活動開始日!");
				}

				Integer group_max_num = null;
				try {
					group_max_num = new Integer(req.getParameter("group_max_num").trim());
				} catch (NumberFormatException e) {
					group_max_num = 0;
					errorMsgs.add("上限人數請填數字.");
				}
				String county = req.getParameter("county").trim();
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("請選擇集合縣/市");
				}

				String district = req.getParameter("district");
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("請選擇集合區域");
				}

				String group_mp3 = req.getParameter("group_mp3").trim();
				if (group_mp3 == null || group_mp3.trim().length() == 0) {
					errorMsgs.add("請填寫集合路(街)名或鄉里名稱");
				}

				String group_mp = county + district + group_mp3;

				String group_des = req.getParameter("group_des").trim();
				if (group_des == null || group_des.trim().length() == 0) {
					errorMsgs.add("揪團描述請勿空白");
				}

				Part part = req.getPart("group_photo");
				InputStream in = part.getInputStream();
				byte[] group_photo = new byte[in.available()];
				in.read(group_photo);
				// 如果使用者沒上傳圖片，則以各潛點照為預設封面
				if (group_photo.length == 0 && !dive_no.equals("erro")) {
					GroupService groupSvc = new GroupService();
					group_photo = groupSvc.getOneGroup(group_no).getGroup_photo();
				}
				GroupVO groupVO = new GroupVO();
				groupVO.setDive_no(dive_no);
				groupVO.setGroup_no(group_no);
				groupVO.setGroup_name(group_name);
				groupVO.setGroup_tour_stop_time(group_tour_stop_time);
				groupVO.setGroup_begin_time(group_begin_time);
				groupVO.setGroup_end_time(group_end_time);
				groupVO.setGroup_max_num(group_max_num);
				groupVO.setGroup_des(group_des);
				groupVO.setGroup_tel(group_tel);
				groupVO.setGroup_mp(group_mp);
				groupVO.setGroup_photo(group_photo);

				if (!errorMsgs.isEmpty()) {
//				GroupService groupSvc = new GroupService();

//				req.setAttribute("groupupdateVO", groupSvc.getOneGroup(group_no));
//				groupVO = groupSvc.getOneGroup(group_no);
					req.setAttribute("groupupdateVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					req.setAttribute("county", county);
					req.setAttribute("district", district);
					req.setAttribute("group_mp3", group_mp3);
					req.setAttribute("group_begin_time", req.getParameter("group_begin_time").trim());
					req.setAttribute("group_end_time", req.getParameter("group_end_time").trim());
					req.setAttribute("group_tour_stop_time", req.getParameter("group_tour_stop_time").trim());

					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/update_group.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				GroupService groupSvc = new GroupService();
				groupVO = groupSvc.updateGroup(group_no, dive_no, group_name, group_tour_stop_time, group_begin_time,
						group_end_time, group_max_num, group_des, group_tel, group_mp, group_photo);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				groupVO = groupSvc.getOneGroup(group_no);
				req.setAttribute("groupVO", groupVO); // 資料庫update成功後,正確的的groupVO物件,存入req
				String url = "/front-end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/update_group.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("insert".equals(action)) { // 來自addGroup.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String enameReg2 = "^[(0-9_)]{10}$";
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!mem_no.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String dive_no = req.getParameter("dive_no").trim();
				if (dive_no.equals("erro")) {
					errorMsgs.add("潛點編號請勿空白");
				}
				String group_name = req.getParameter("group_name").trim();
				if (group_name == null || group_name.trim().length() == 0) {
					errorMsgs.add("揪團名稱請勿空白");
				}
				String group_tel = req.getParameter("group_tel").trim();
				if (group_tel == null || group_tel.trim().length() == 0) {
					errorMsgs.add("連絡電話請勿空白");
				} else if (!group_tel.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 只能輸入數字 ,且長度必需是10位數");
				}

				java.sql.Timestamp group_tour_stop_time = null;
				try {
					group_tour_stop_time = java.sql.Timestamp.valueOf(req.getParameter("group_tour_stop_time").trim());

				} catch (IllegalArgumentException e) {
					group_tour_stop_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp group_begin_time = null;
				try {
					group_begin_time = java.sql.Timestamp.valueOf(req.getParameter("group_begin_time").trim());
				} catch (IllegalArgumentException e) {
					group_begin_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp group_end_time = null;
				try {
					group_end_time = java.sql.Timestamp.valueOf(req.getParameter("group_end_time").trim());
				} catch (IllegalArgumentException e) {
					group_end_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				if (group_end_time.getTime() < group_begin_time.getTime()) {
					errorMsgs.add("活動開始日不得小於活動結束日!");
				}
				if (group_tour_stop_time.getTime() > group_begin_time.getTime()) {
					errorMsgs.add("報名截止日不得大於活動開始日!");
				}
				Integer group_max_num = null;
				try {
					group_max_num = new Integer(req.getParameter("group_max_num").trim());
				} catch (NumberFormatException e) {
					group_max_num = 2;
					errorMsgs.add("上限人數請填數字.");
				}
				String county = req.getParameter("county").trim();
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("請選擇集合縣/市");
				}

				String district = req.getParameter("district");
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("請選擇集合區域");
				}

				String group_mp3 = req.getParameter("group_mp3").trim();
				if (group_mp3 == null || group_mp3.trim().length() == 0) {
					errorMsgs.add("請填寫集合路(街)名或鄉里名稱");
				}

				String group_mp = county + district + group_mp3;

				String group_des = req.getParameter("group_des").trim();
				if (group_des == null || group_des.trim().length() == 0) {
					errorMsgs.add("揪團描述請勿空白");
				}

				Part part = req.getPart("group_photo");
				InputStream in = part.getInputStream();
				byte[] group_photo = new byte[in.available()];
				in.read(group_photo);

				if (group_photo.length == 0 && !dive_no.equals("erro")) {
					DiveService diveSvc = new DiveService();
					group_photo = diveSvc.getOneDive(dive_no).getDive_img();
				}

				GroupVO groupVO = new GroupVO();
				groupVO.setMem_no(mem_no);
				groupVO.setDive_no(dive_no);
				groupVO.setGroup_name(group_name);
				groupVO.setGroup_tour_stop_time(group_tour_stop_time);
				groupVO.setGroup_begin_time(group_begin_time);
				groupVO.setGroup_end_time(group_end_time);
				groupVO.setGroup_max_num(group_max_num);
				groupVO.setGroup_des(group_des);
				groupVO.setGroup_tel(group_tel);
				groupVO.setGroup_mp(group_mp);
				groupVO.setGroup_photo(group_photo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupaddVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					req.setAttribute("county", county);
					req.setAttribute("district", district);
					req.setAttribute("group_mp3", group_mp3);
					req.setAttribute("group_begin_time", req.getParameter("group_begin_time").trim());
					req.setAttribute("group_end_time", req.getParameter("group_end_time").trim());
					req.setAttribute("group_tour_stop_time", req.getParameter("group_tour_stop_time").trim());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/addGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				GroupService groupSvc = new GroupService();
				groupVO = groupSvc.addGroup(mem_no, dive_no, group_name, group_tour_stop_time, group_begin_time,
						group_end_time, group_max_num, group_des, group_tel, group_mp, group_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("likesearch".equals(action)) { // 模糊搜尋
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String word = req.getParameter("word");
				List<GroupVO> groupVO = new ArrayList<GroupVO>();
				String erro="";
				if (word == null || (word.trim()).length() == 0) {
					erro = "請輸入關鍵字";
					req.setAttribute("erro", erro);
				}
				/*************************** 2.開始查詢資料 *****************************************/
				GroupService groupSvc = new GroupService();
				if (word.trim().length() != 0) {
					groupVO = groupSvc.getKeyWord(word);
					groupSvc = new GroupService();
					Date toDay = new Date();
						for (int i = 0; i < groupVO.size(); i++) {
							GroupVO g = groupVO.get(i);
							GroupVO gpVO = groupSvc.getOneGroup(g.getGroup_no());
							if (g.getGroup_tour_stop_time().getTime() < toDay.getTime() || g.getGroup_status() != 0) {
								groupVO.remove(g);
								i--;
							}
						}
				}
				if(groupVO.size()==0 && erro.equals("")) {
					erro = "查無資料";
					req.setAttribute("erroadd", erro);
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				req.getSession().setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
				String url = "/front-end/group/listAllGroupForDive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action) || "open".equals(action)) { // 關閉揪團
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String group_no = req.getParameter("group_no");
				String mem_no = req.getParameter("mem_no");
				/*************************** 2.開始查詢資料 *****************************************/
				Group_joinService group_joinSvc = new Group_joinService();
				Group_joinVO group_joinVO = group_joinSvc.getOneGroup_join(group_no, mem_no);
				GroupService groupSvc = new GroupService();

				GroupVO groupVO = groupSvc.getOneGroup(group_no);
				if ("delete".equals(action))
					groupSvc.updateStatus(group_no, 2, groupVO.getGroup_tour_num());
				if ("open".equals(action))
					groupSvc.updateStatus(group_no, 0, groupVO.getGroup_tour_num());

				groupVO = groupSvc.getOneGroup(group_no);// 改變狀態後再查一次
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("groupVO", groupVO); // 資料庫取出的groupVO物件,存入req
				req.setAttribute("group_joinVO", group_joinVO);
				String url = "/front-end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("C:/DA104WebApp/eclipse_WTP_workspace1/Group/images/2.jpg");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}
