package com.room_image.model;

import java.util.List;

public class RoomImageService {

	private RoomImageDAO_interface dao;

	public RoomImageService() {
		dao = new RoomImageJNDIDAO();
	}

	public RoomImageVO addRoomImage(String tour_room_no, byte[] room_img) {
		RoomImageVO roomImageVO = new RoomImageVO();

		roomImageVO.setTour_room_no(tour_room_no);
		roomImageVO.setRoom_img(room_img);
		dao.insert(roomImageVO);

		return roomImageVO;
	}

	public RoomImageVO updateRoomImage(String room_img_no, String tour_room_no, byte[] room_img) {
		RoomImageVO roomImageVO = new RoomImageVO();

		roomImageVO.setRoom_img_no(room_img_no);
		roomImageVO.setTour_room_no(tour_room_no);
		roomImageVO.setRoom_img(room_img);
		dao.update(roomImageVO);

		return roomImageVO;
	}

	public void deleteRoomImage(String room_img_no) {
		dao.delete(room_img_no);
	}

	public RoomImageVO getOneRoomImage(String room_img_no) {
		return dao.findByPrimaryKey(room_img_no);
	}

	public List<RoomImageVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomImageVO> getRoomImageList(String tour_room_no){
		return dao.findbyTourRoomNo(tour_room_no);
	}

}
