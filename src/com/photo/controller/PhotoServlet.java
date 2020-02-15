package com.photo.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.photo.model.PhotoService;
import com.photo.model.PhotoVO;
import com.util.MyUtil;

import sun.misc.BASE64Decoder;

//@WebServlet("/PhotoServlet")
@MultipartConfig
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoServlet() {
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

//		/************************ 選擇相片 ************************/
//		if ("getOne_For_Display".equals(action)) {
//			String photo_no = request.getParameter("photo_no");
//
//			PhotoService photoSvc = new PhotoService();
//			PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);
//			request.getSession().setAttribute("photoVO", photoVO);
//			request.setAttribute("photoVO", photoVO);
//			String url = "/front-end/photo/listOnePhoto.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url);
//			successView.forward(request, response);
//		}

		/* =============================新增相片=============================== */

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			request.setAttribute("errorMsgs", errorMsgs);

			try {

				PhotoVO photoVO = null;
				String album_no = request.getParameter("album_no");
				String photo_name = "";
				String photo_note = "";
				byte[] photo_pic = null;
				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
					if (part.getName().equals("inputfile")) {
						String type = part.getHeader("Content-type").substring(0, 4);
						if (type == null || type.trim().trim().length() == 0) {
							errorMsgs.add("請放圖片");
						} else if ("image".indexOf(type) == -1) {
							errorMsgs.add("檔案格式錯誤");
						} else {
							/* ======================新增相片==================== */
							photo_pic = MyUtil.pathToByteArray(part);
							photoVO = new PhotoVO();
							photoVO.setAlbum_no(album_no);
							photoVO.setPhoto_name(photo_name);
							photoVO.setPhoto_note(photo_note);
							photoVO.setPhoto_pic(photo_pic);
							photoVO.setPhoto_status(0);
							PhotoService photoSvc = new PhotoService();
							photoVO = photoSvc.addPhoto(album_no, photo_name, photo_pic, photo_note, 0);
						}
					}
				}
//				Part part = request.getPart("photo_pic");
//				

//				String photo_note = request.getParameter("photo_note");

//				String photo_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,66}$";
//				if (photo_note == null || photo_note.trim().length() == 0) {
//					photo_note = "";
//				} else if (!photo_note.trim().matches(photo_noteReg)) {
//					errorMsgs.add("字數過長");
//				}

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("photoVO", photoVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/addPhoto.jsp");
					failureView.forward(request, response);
					return;
				}

				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(album_no);
				request.setAttribute("albumVO", albumVO);
				request.setAttribute("album_no", album_no);

				/***************** 新增完成 *********************/

				String url = "/front-end/photo/photoList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/addPhoto.jsp");
				failureView.forward(request, response);

			}
		}
		/* ================================================================ */

		/* =================================修改相片========================== */
		if ("update".equals(action))

		{
			List<String> errorMsgs = new LinkedList();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				PhotoService photoSvc = new PhotoService();
				String photo_no = request.getParameter("photo_no").trim();
				String photo_name = request.getParameter("photo_name");
				String photo_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,6}$";
				
				if (photo_name == null || photo_name.trim().length() == 0) {
					photo_name="";
				} else if (!photo_name.trim().matches(photo_nameReg)) {
					PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);
					photo_name = photoVO.getPhoto_name();
					errorMsgs.add("只能是中、英文字母、數字 , 且長度必需在1到6之間");
				}

				String photo_note = request.getParameter("photo_note");
				String photo_noteReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,66}$";
				if (photo_note == null || photo_note.trim().length() == 0) {
					photo_note = "";
				} else if (!photo_note.trim().matches(photo_noteReg)) {
					errorMsgs.add("只能是中、英文字母、數字 , 且長度必需在66之內");
				}

				String album_no = request.getParameter("album_no");

				PhotoVO photoVO = new PhotoVO();
				photoVO.setPhoto_no(photo_no);
				photoVO.setAlbum_no(album_no);
				photoVO.setPhoto_name(photo_name);
				byte[] photo_pic = photoSvc.getOnePhoto(photo_no).getPhoto_pic();
				photoVO.setPhoto_note(photo_note);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("photoVO", photoVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
					failureView.forward(request, response);
					return;
				}

				/**************** 修改相簿 **********************/
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(album_no);
				photoSvc.updatePhoto(photo_no, photo_name, photo_pic, photo_note, 0);

				/***************** 修改完成 *********************/
				request.setAttribute("albumVO", albumVO);
				request.setAttribute("photoVO", photoVO);
				String url = "/front-end/photo/photoList.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/photo/photoList.jsp");
				failureView.forward(request, response);
			}
		}

		/* ================================================================= */

		/* ==========================刪除圖片====================================== */
		if ("delete_pic".equals(action)) {

			PhotoService photoSvc = new PhotoService();
			String photo_no = request.getParameter("photo_no").trim();
			/********************* 取得.更新資料 ******************************/
			PhotoVO photoVO = photoSvc.getOnePhoto(photo_no);
			String photo_name = photoVO.getPhoto_name();
			byte[] photo_pic = photoVO.getPhoto_pic();
			String photo_note = photoVO.getPhoto_note();
			photoSvc.updatePhoto(photo_no, photo_name, photo_pic, photo_note, 1);

			/***************** 修改完成 *********************/
			String url = "/front-end/photo/photoList.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		}
		
		/* ================================================================== */
		
		/* =============================拖拉上傳============================== */

		if ("upPic".equals(action)) {

			String album_no = request.getParameter("album_no");
			String base = request.getParameter("base");
			String base_n=base.substring(base.indexOf(",")+1);

			BASE64Decoder d = new BASE64Decoder();
			byte[] photo_pic = d.decodeBuffer(base_n);
			PhotoService photoSvc = new PhotoService();
			photoSvc.addPhoto(album_no, "", photo_pic, "", 0);
		}
		
		/*-----------------------------------------------------------------------*/
	}

}
