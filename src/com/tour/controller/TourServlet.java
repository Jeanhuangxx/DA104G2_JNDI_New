package com.tour.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemVO;
import com.room_image.model.RoomImageService;
import com.room_image.model.RoomImageVO;
import com.tour.model.TourJDBCDAO;
import com.tour.model.TourService;
import com.tour.model.TourVO;
import com.tour_image.model.TourImageService;
import com.tour_image.model.TourImageVO;
import com.tour_order.model.TourOrderVO;
import com.tour_room.model.TourRoomService;
import com.tour_room.model.TourRoomVO;
import com.util.MyUtil;

@MultipartConfig
public class TourServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		System.out.println(action);

		// request from store_add.jsp
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);
			// 驗證錯誤
			try {
				String tour_name = req.getParameter("tour_name");
				if (tour_name == null || tour_name.trim().length() == 0) {
					errorMsgs.put("tourNameError", "請輸入名稱");
				}

				java.sql.Date tour_bgn_date = null;
				try {
					tour_bgn_date = java.sql.Date.valueOf(req.getParameter("tour_bgn_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("bgnDateError", "請輸入出發日期");
				}

				java.sql.Date tour_end_date = null;
				try {
					tour_end_date = java.sql.Date.valueOf(req.getParameter("tour_end_date"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("endDateError", "請輸入回程日期");
				}

				Integer tour_price = null;
				try {
					tour_price = new Integer(req.getParameter("tour_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourPriceError", "請輸入價格");
				}

				Integer tour_dives = null;
				try {
					tour_dives = new Integer(req.getParameter("tour_dives").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourDivesError", "請輸入潛水支數");
				}

				String tour_place = req.getParameter("tour_place");

				Integer tour_max_num = null;
				try {
					tour_max_num = new Integer(req.getParameter("tour_max_num").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourMaxNumError", "請輸入人數");
				}

				java.sql.Date tour_stop_date = null;
				try {
					tour_stop_date = java.sql.Date.valueOf(req.getParameter("tour_stop_date"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("stopDate", "請輸入報名截止日期");
				}

				Integer room_ppl = null;
				try {
					room_ppl = new Integer(req.getParameter("room_ppl").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("roomPpl", "請輸入房間可容納人數");
				}

				Integer bed_size = new Integer(req.getParameter("bed_size"));

				String str_br = req.getParameter("room_priv_br");
				Integer room_priv_br = null;
				if (str_br == null) {
					room_priv_br = 0;
				} else {
					room_priv_br = new Integer(str_br);
				}

				String str_aircon = req.getParameter("room_aircon");
				Integer room_aircon = null;
				if (str_aircon == null) {
					room_aircon = 0;
				} else {
					room_aircon = new Integer(str_aircon);
				}

				Collection<Part> parts = req.getParts();
				List<TourImageVO> tourImageList = new ArrayList<TourImageVO>();
				List<RoomImageVO> roomImageList = new ArrayList<RoomImageVO>();

				for (Part p : parts) {
					if (p.getName().equals("tour_img")) {
						String type = p.getHeader("Content-type").substring(0, 4);
						if ("image".indexOf(type) == -1) {
							errorMsgs.put("tourImgTypeError", "請放照片");
						}

						TourImageVO tourImageVO = new TourImageVO();
						tourImageVO.setTour_img(MyUtil.pathToByteArray(p));
						tourImageList.add(tourImageVO);
					}

					if (p.getName().equals("room_img")) {
						String type = p.getHeader("Content-type").substring(0, 4);
						if ("image".indexOf(type) == -1) {
							errorMsgs.put("roomImgTypeError", "請放照片");
						}

						RoomImageVO roomImageVO = new RoomImageVO();
						roomImageVO.setRoom_img(MyUtil.pathToByteArray(p));
						roomImageList.add(roomImageVO);
					}
				}

				Integer tour_status = new Integer(req.getParameter("tour_status"));

				// getsession 取得物件 memVO
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");

				String mem_no = memVO.getMem_no(); // --------取得登入的商家會員------------

				TourVO tourVO = new TourVO();
				tourVO.setMem_no(mem_no);
				tourVO.setTour_name(tour_name);
				tourVO.setTour_bgn_date(tour_bgn_date);
				tourVO.setTour_end_date(tour_end_date);
				tourVO.setTour_price(tour_price);
				tourVO.setTour_dives(tour_dives);
				tourVO.setTour_place(tour_place);
				tourVO.setTour_max_num(tour_max_num);
				tourVO.setTour_stop_date(tour_stop_date);
				tourVO.setTour_status(tour_status);

				List<TourRoomVO> tourRoomList = new ArrayList<TourRoomVO>();
				TourRoomVO tourRoomVO = new TourRoomVO();
				tourRoomVO.setBed_size(bed_size);
				tourRoomVO.setRoom_aircon(room_aircon);
				tourRoomVO.setRoom_ppl(room_ppl);
				tourRoomVO.setRoom_priv_br(room_priv_br);
				tourRoomList.add(tourRoomVO);
				
				if((tour_bgn_date == null || tour_end_date == null || tour_stop_date == null) || (tour_bgn_date.getTime() > tour_end_date.getTime() || tour_stop_date.getTime() > tour_bgn_date.getTime())) {
					errorMsgs.put("timeError", "日期順序錯誤");
				}
				
				// 若有錯誤訊息，存入VO後返回
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tourVO", tourVO);
					req.setAttribute("tourRoomVO", tourRoomVO);
					req.setAttribute("roomImageVO", tourImageList);
					req.setAttribute("tourImageVO", roomImageList);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_add.jsp");
					failureView.forward(req, res);
					return;
				}

				// 開始新增資料
				TourJDBCDAO tourDAO = new TourJDBCDAO();
				tourDAO.insertTour(tourVO, tourImageList, tourRoomList, roomImageList);

				// 轉交成功頁面
				String url = "/front-end/store/store_trip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("insertError", "新增失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateOneTour".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tour_name = req.getParameter("tour_name");
				if (tour_name == null || tour_name.trim().length() == 0) {
					errorMsgs.put("tourNameError", "請輸入名稱");
				}

				java.sql.Date tour_bgn_date = null;
				try {
					tour_bgn_date = java.sql.Date.valueOf(req.getParameter("tour_bgn_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("bgnDateError", "請輸入出發日期");
				}

				java.sql.Date tour_end_date = null;
				try {
					tour_end_date = java.sql.Date.valueOf(req.getParameter("tour_end_date"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("endDateError", "請輸入回程日期");
				}

				Integer tour_price = null;
				try {
					tour_price = new Integer(req.getParameter("tour_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourPriceError", "請輸入價格");
				}

				Integer tour_dives = null;
				try {
					tour_dives = new Integer(req.getParameter("tour_dives").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourDivesError", "請輸入潛水支數");
				}

				String tour_place = req.getParameter("tour_place");

				Integer tour_max_num = null;
				try {
					tour_max_num = new Integer(req.getParameter("tour_max_num").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tourMaxNumError", "請輸入人數");
				}

				java.sql.Date tour_stop_date = null;
				try {
					tour_stop_date = java.sql.Date.valueOf(req.getParameter("tour_stop_date"));
				} catch (NumberFormatException e) {
					errorMsgs.put("stopDate", "請輸入報名截止日期");
				}

				Integer room_ppl = null;
				try {
					room_ppl = new Integer(req.getParameter("room_ppl").trim());
				} catch (NumberFormatException e) {
//					room_ppl = 0;
					errorMsgs.put("roomPpl", "請輸入房間可容納人數");
				}

				Integer bed_size = new Integer(req.getParameter("bed_size"));

				String str_br = req.getParameter("room_priv_br");
				Integer room_priv_br = null;
				if (str_br == null) {
					room_priv_br = 0;
				} else {
					room_priv_br = new Integer(str_br);
				}

				String str_aircon = req.getParameter("room_aircon");
				Integer room_aircon = null;
				if (str_aircon == null) {
					room_aircon = 0;
				} else {
					room_aircon = new Integer(str_aircon);
				}

				String tour_no = req.getParameter("tour_no");
				String tour_room_no = req.getParameter("tour_room_no");

				Collection<Part> parts = req.getParts();
				List<TourImageVO> tourImageList = new ArrayList<TourImageVO>();
				List<RoomImageVO> roomImageList = new ArrayList<RoomImageVO>();


				for (Part p : parts) {
					if (p.getName().equals("tour_img")) {
						if(p.getSize() != 0) {
							String type = p.getHeader("Content-type").substring(0, 4);
							if ("image".indexOf(type) == -1) {
								errorMsgs.put("tourImgTypeError", "照片格式錯誤");
							}

							TourImageService tImgSvc = new TourImageService();
							tImgSvc.addTourImage(tour_no, MyUtil.pathToByteArray(p));
						}
					}

					if (p.getName().equals("room_img")) {
						if(p.getSize() != 0) {
							String type = p.getHeader("Content-type").substring(0, 4);
							if ("image".indexOf(type) == -1) {
								errorMsgs.put("roomImgTypeError", "照片格式錯誤");
							}

							RoomImageService rImgSvc = new RoomImageService();
							rImgSvc.addRoomImage(tour_room_no, MyUtil.pathToByteArray(p));
						}
					}
				}


				Integer tour_status = new Integer(req.getParameter("tour_status"));

				TourVO tourVO = new TourVO();
				tourVO.setTour_no(tour_no);
				tourVO.setTour_name(tour_name);
				tourVO.setTour_bgn_date(tour_bgn_date);
				tourVO.setTour_end_date(tour_end_date);
				tourVO.setTour_price(tour_price);
				tourVO.setTour_dives(tour_dives);
				tourVO.setTour_place(tour_place);
				tourVO.setTour_max_num(tour_max_num);
				tourVO.setTour_stop_date(tour_stop_date);
				tourVO.setTour_status(tour_status);

				TourRoomVO tourRoomVO = new TourRoomVO();
				tourRoomVO.setBed_size(bed_size);
				tourRoomVO.setRoom_aircon(room_aircon);
				tourRoomVO.setRoom_ppl(room_ppl);
				tourRoomVO.setRoom_priv_br(room_priv_br);
				
				if((tour_bgn_date == null || tour_end_date == null || tour_stop_date == null) || (tour_bgn_date.getTime() > tour_end_date.getTime() || tour_stop_date.getTime() > tour_bgn_date.getTime())) {
					errorMsgs.put("timeError", "日期順序錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tourVO", tourVO);
					req.setAttribute("tourRoomVO", tourRoomVO);
//					req.setAttribute("roomImageVO", tourImageList);
//					req.setAttribute("tourImageVO", roomImageList);
					
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_trip_update.jsp");
					failureView.forward(req, res);
					return;
				}

				TourService tourSvc = new TourService();
				tourVO = tourSvc.updateTour(tour_no, tour_name, tour_bgn_date, tour_end_date, tour_price, tour_max_num,
						tour_place, tour_dives, tour_stop_date, tour_status);

				TourRoomService tourRoomSvc = new TourRoomService();
				tourRoomVO = tourRoomSvc.updateTourRoom(tour_no, bed_size, room_ppl, room_priv_br, room_aircon);
				
				
				
				req.setAttribute("tourVO", tourVO);
				req.setAttribute("tourRoomVO", tourRoomVO);
				//				req.setAttribute("roomImageVO", tourImageList);
				//				req.setAttribute("tourImageVO", roomImageList);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/store/store_trip.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("updateError", "更新失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/store_trip_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getTourUpdate".equals(action)) {
			try {
				String tour_no = req.getParameter("tour_no");

				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);

				req.setAttribute("tourVO", tourVO);
				String url = "/front-end/store/store_trip_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception Ignored) {
			}

		}

		//		searching by date and place / only date

		if ("search".equals(action)) {
			Calendar s = Calendar.getInstance();
			Calendar e = Calendar.getInstance();

			java.util.Date sDate = new java.util.Date();
			java.util.Date eDate = new java.util.Date();

			java.sql.Date bgn_date = null;
			java.sql.Date end_date = null;

			try {
				Integer dateChoose = new Integer(req.getParameter("dateChoose"));
				if (dateChoose == 1) {
					e.add(Calendar.DATE, 30);
					eDate = e.getTime();
					end_date = new java.sql.Date(eDate.getTime());
					bgn_date = new java.sql.Date(sDate.getTime());
				}

				if (dateChoose == 2) {
					// start
					s.set(Calendar.DATE, 1);
					s.add(Calendar.MONTH, 1);
					sDate = s.getTime();

					// end
					e.set(Calendar.DATE, 31);
					e.add(Calendar.MONTH, 1);
					eDate = e.getTime();

					bgn_date = new java.sql.Date(sDate.getTime());
					end_date = new java.sql.Date(eDate.getTime());
				}

				if (dateChoose == 3) {
					// start
					s.set(Calendar.DATE, 1);
					s.add(Calendar.MONTH, 2);
					sDate = s.getTime();

					// end
					e.set(Calendar.DATE, 31);
					e.add(Calendar.MONTH, 2);
					eDate = e.getTime();

					bgn_date = new java.sql.Date(sDate.getTime());
					end_date = new java.sql.Date(eDate.getTime());
				}

				if (dateChoose == 4) {
					// start
					s.set(Calendar.DATE, 1);
					s.add(Calendar.MONTH, 3);
					sDate = s.getTime();

					// end
					e.set(Calendar.DATE, 31);
					e.add(Calendar.MONTH, 3);
					eDate = e.getTime();

					bgn_date = new java.sql.Date(sDate.getTime());
					end_date = new java.sql.Date(eDate.getTime());
				}

				String tour_place = req.getParameter("tour_place");

				// 查詢資料
				TourService tourSvc = new TourService();
				List<TourVO> list = tourSvc.getTourbyDate(bgn_date, end_date, tour_place);

				// 轉送
				req.setAttribute("tour_place", tour_place);
				req.setAttribute("tourVO", list);
				String url = "/front-end/travel-page/travel_inside.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception Ignored) {
			}
		}

		if ("searchbyPlace".equals(action)) {
			try {
				String tour_place = req.getParameter("tour_place");
				TourService tourSvc = new TourService();
				List<TourVO> list = tourSvc.getTourbyPlace(tour_place);

				req.setAttribute("tour_place", tour_place); // 取得輸入的地點
				req.setAttribute("tourVO", list);
				String url = "/front-end/travel-page/travel_inside.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception Ignored) {
			}
		}

		if ("getOneTour".equals(action)) {
			try {
				String tour_no = req.getParameter("tour_no");

				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);

				req.setAttribute("tourVO", tourVO);
				String url = "/front-end/travel-page/travel-detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("checkout".equals(action)) {
			try {

				String tour_no = req.getParameter("tour_no");
				TourService tourSvc = new TourService();
				TourVO tourVO = tourSvc.getOneTour(tour_no);

				HttpSession session = req.getSession(); // 改
				session.setAttribute("tourVO", tourVO); // 改


				String url = "/front-end/travel-page/travel_checkout.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception Ignored) {
			}
		}

		// 以下修改
		if ("removeSession".equals(action)) {
			try {
				HttpSession session = req.getSession();
				session.removeAttribute("tourVO");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 以上修改

	}
}
