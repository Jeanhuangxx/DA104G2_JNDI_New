package com.room_image.model;

import java.util.List;

import com.tour_image.model.TourImageVO;

public interface RoomImageDAO_interface {
	public void insert(RoomImageVO roomImageVO);
	public void update(RoomImageVO roomImageVO);
	public void delete(String room_img_no);
	public RoomImageVO findByPrimaryKey(String room_img_no);
	public List<RoomImageVO> findbyTourRoomNo(String tour_room_no);
	public List<RoomImageVO> getAll();
	public void rImgInsertWithTourRoomNo(RoomImageVO roomImageVO, java.sql.Connection con); 
}
