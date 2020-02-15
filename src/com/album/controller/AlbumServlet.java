package com.album.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.util.MyUtil;

//@WebServlet("/AlbumServlet")
@MultipartConfig
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		/* ============================= 選擇相簿 ================================ */

		if ("getOne_For_Display".equals(action)) {
			String requestURL = request.getParameter("requestURL");
			String album_no = request.getParameter("album_no");
			AlbumService albumSvc = new AlbumService();
			AlbumVO albumVO = albumSvc.getOneAlbum(album_no);

			HttpSession albumSession = request.getSession();
			albumSession.setAttribute("albumVO", albumVO);
request.getSession().setAttribute("requestURL", requestURL);
//			request.setAttribute("album_no", album_no);
			String url = "/front-end/photo/photoList.jsp";
//			String url = requestURL;
			
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		/* ================================================================== */

		/* =============================新增相簿 =============================== */

		if ("insert".equals(action)) {
			HttpSession session = request.getSession();
			Object account = session.getAttribute("account");

			if (account == null) {
				session.setAttribute("location", request.getContextPath() + "/front-end/album/albumList.jsp");
				response.sendRedirect(request.getContextPath() + "/front-end/member/login.jsp");
				return;
			}

			List<String> errorMsgs_album = new LinkedList();

			request.setAttribute("errorMsgs_album", errorMsgs_album);

			try {
				String album_name = request.getParameter("album_name");
				String album_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,6}$";
				if (album_name == null || album_name.trim().length() == 0) {
					errorMsgs_album.add("名稱請勿空白");
				} else if (!album_name.trim().matches(album_nameReg)) {
					errorMsgs_album.add("只能是中、英文字母、數字和_ , 且長度必需在1到6之間");
				}

				Part part = request.getPart("album_pic");
				String type = part.getHeader("Content-type").substring(0, 4);
				if (type == null || type.trim().trim().length() == 0) {
					errorMsgs_album.add("請放封面");
				} else if ("image".indexOf(type) == -1) {
					errorMsgs_album.add("檔案格式錯誤");
				}
				byte[] album_pic = MyUtil.pathToByteArray(part);

				String album_note = request.getParameter("album_note");
				String album_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,66}$";
				if (album_note == null || album_note.trim().length() == 0) {
					errorMsgs_album.add("簡介請勿空白");
				} else if (!album_note.trim().matches(album_noteReg)) {
					errorMsgs_album.add("字數過長");
				}
				AlbumVO albumVO = new AlbumVO();

//				albumVO.setMem_no((String)session.getAttribute("mem_no"));
				albumVO.setAlbum_name(album_name);
				albumVO.setAlbum_note(album_note);
				albumVO.setAlbum_pic(album_pic);
				albumVO.setAlbum_status(0);

				if (!errorMsgs_album.isEmpty()) {
					request.setAttribute("albumVO", albumVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/album/albumList.jsp");
					failureView.forward(request, response);
					return;
				}

				/**************** 新增相簿 **********************/
				AlbumService albumSvc = new AlbumService();
				albumVO = albumSvc.addAlbum((String) session.getAttribute("mem_no"), album_name, album_pic, album_note,
						0);
				/***************** 新增完成 *********************/
				String url = "/front-end/album/albumList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs_album.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/album/albumList.jsp");
				failureView.forward(request, response);
			}

		}

		/* ========================================================= */

		/* ==========================修改相簿========================== */

		if ("update".equals(action)) {
			List<String> errorMsgs_album = new LinkedList();

			request.setAttribute("errorMsgs_album", errorMsgs_album);
			try {
				String album_no = request.getParameter("album_no").trim();
				String album_name = request.getParameter("album_name");
				String album_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,6}$";
				if (album_name == null || album_name.trim().length() == 0) {
					errorMsgs_album.add("名稱請勿空白");
				} else if (!album_name.trim().matches(album_nameReg)) {
					errorMsgs_album.add("只能是中、英文字母、數字和_ , 且長度必需在1到6之間");
				}

				byte[] buf = null;
				byte[] album_pic = null;
				Part part = request.getPart("album_pic");
				InputStream in = part.getInputStream();
				buf = new byte[in.available()];
				if (buf.length != 0) {
					String type = part.getHeader("Content-type").substring(0, 4);
					if ("image".indexOf(type) == -1) {
						errorMsgs_album.add("檔案格式錯誤");
					}
					in.read(buf);
					album_pic = buf;
					in.close();
				} else if (buf.length == 0) {
					album_no = request.getParameter("album_no");
					AlbumService albumSvc = new AlbumService();
					AlbumVO albumVO = albumSvc.getOneAlbum(album_no);
					buf = albumVO.getAlbum_pic();
					album_pic = buf;
				}

				String album_note = request.getParameter("album_note");
				String album_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,66}$";
				if (album_note == null || album_note.trim().length() == 0) {
					errorMsgs_album.add("簡介請勿空白");
				} else if (!album_note.trim().matches(album_noteReg)) {
					errorMsgs_album.add("字數過長");
				}

				AlbumVO albumVO = new AlbumVO();
				albumVO.setAlbum_no(album_no);
				albumVO.setMem_no((String) request.getSession().getAttribute("mem_no"));
				albumVO.setAlbum_name(album_name);
				albumVO.setAlbum_note(album_note);
				albumVO.setAlbum_pic(album_pic);
				albumVO.setAlbum_status(0);

				if (!errorMsgs_album.isEmpty()) {
					request.setAttribute("albumVO", albumVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
					failureView.forward(request, response);
					return;
				}

				/**************** 修改相簿 **********************/
				AlbumService albumSvc = new AlbumService();
				albumVO = albumSvc.updateAlbum(album_no, album_name, album_pic, album_note, 0);
				/***************** 修改完成 *********************/
				albumVO = albumSvc.getOneAlbum(album_no);
				request.getSession().setAttribute("albumVO", albumVO);

				String url = "/front-end/photo/photoList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs_album.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
				failureView.forward(request, response);
			}
		}
		/* ========================================================= */

		/* ===========================刪除相簿======================== */

		if ("delete".equals(action)) {

			try {
				String album_no = request.getParameter("album_no");
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(album_no);

				/**************** 修改相簿 **********************/
				albumSvc.updateAlbum(albumVO.getAlbum_no(), albumVO.getAlbum_name(), albumVO.getAlbum_pic(),
						albumVO.getAlbum_note(), 1);

				/***************** 修改完成 *********************/
				albumVO = albumSvc.getOneAlbum(album_no);
				request.getSession().setAttribute("albumVO", albumVO);

				String url = "/front-end/album/albumList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
				failureView.forward(request, response);
			}
		}
		/*------------------------------------------------------------*/
	}
}
