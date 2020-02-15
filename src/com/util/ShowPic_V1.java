package com.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adm.model.AdmService;
import com.album.model.AlbumService;
import com.dive.model.DiveService;
import com.equipment.model.EquipmentService;
import com.group.model.GroupService;
import com.mem.model.MemService;
//import com.photo.model.PhotoService;
import com.photo.model.PhotoService;
import com.room_image.model.RoomImageService;
import com.tour_image.model.TourImageService;

public class ShowPic_V1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowPic_V1() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		byte[] bytePic = null;
		try {
			/* ======================根據網頁來源顯示圖片(可擴充)============================ */
			String album_no = request.getParameter("album_no");// 顯示相簿圖片
			String photo_no = request.getParameter("photo_no");// 顯示相片圖片
			String mem_no = request.getParameter("mem_no");// 顯示會員圖片
			String group_no = request.getParameter("group_no");// 顯示揪團圖片
			String mem_store_no = request.getParameter("mem_store_no");// 顯示會員圖片
			String equip_no = request.getParameter("equip_no");// 顯示裝備圖片
			String equip_img = request.getParameter("equip_img");// 顯示裝備三張圖片
			String tour_img_no = request.getParameter("tour_img_no");
			String room_img_no = request.getParameter("room_img_no");
			String mem_per = request.getParameter("mem_per");
			String dive_no = request.getParameter("dive_no");
			String adm_no = request.getParameter("adm_no");

			if (album_no != null && (album_no.trim().length() != 0)) {
				AlbumService albumSvc = new AlbumService();
				bytePic = albumSvc.getOneAlbum(album_no).getAlbum_pic();
			} else if (photo_no != null && (photo_no.trim().length() != 0)) {
				PhotoService photoSvc = new PhotoService();
				bytePic = photoSvc.getOnePhoto(photo_no).getPhoto_pic();
			} else if (mem_no != null && (mem_no.trim().length() != 0)) {
				MemService memSvc = new MemService();
				if (mem_per == null || mem_per.equals("0")) {
					bytePic = memSvc.getOneMem(mem_no).getMem_img();
				} else {
					bytePic = memSvc.getOneMem(mem_no).getMem_store_business();
				}
			} else if (group_no != null && (group_no.trim().length() != 0)) {
				GroupService groupSvc = new GroupService();
				bytePic = groupSvc.getOneGroup(group_no).getGroup_photo();
			} else if (mem_store_no != null && (mem_store_no.trim().length() != 0)) {
				MemService memSvc = new MemService();
				bytePic = memSvc.getOneMem(mem_store_no).getMem_store_business();
			} else if (equip_no != null && (equip_no.trim().length() != 0)) {
				if ("1".equals(equip_img)) {
					EquipmentService equipSvc = new EquipmentService();
					bytePic = equipSvc.getOneEquip(equip_no).getEquip_img1();
				}
				if ("2".equals(equip_img)) {
					EquipmentService equipSvc = new EquipmentService();
					bytePic = equipSvc.getOneEquip(equip_no).getEquip_img2();
				}
				if ("3".equals(equip_img)) {
					EquipmentService equipSvc = new EquipmentService();
					bytePic = equipSvc.getOneEquip(equip_no).getEquip_img3();
				}
			} else if (tour_img_no != null && (tour_img_no.trim().length() != 0)) {
				TourImageService tourImageSvc = new TourImageService();
				bytePic = tourImageSvc.getOneTourImage(tour_img_no).getTour_img();
			} else if (room_img_no != null && (room_img_no.trim().length() != 0)) {
				RoomImageService roomImageSvc = new RoomImageService();
				bytePic = roomImageSvc.getOneRoomImage(room_img_no).getRoom_img();
			} else if (dive_no != null && (dive_no.trim().length() != 0)) {
				DiveService diveSvc = new DiveService();
				bytePic = diveSvc.getOneDive(dive_no).getDive_img();
			} else if (adm_no != null && (adm_no.trim().length() != 0)) {
				AdmService diveSvc = new AdmService();
				bytePic = diveSvc.getOneAdm(adm_no).getAdm_img();
			}

			out.write(bytePic);
		} catch (Exception e) {
			if (out != null) {
				out.close();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
