package com.tour_room.model;

import java.util.List;

import com.room_image.model.RoomImageVO;
import com.tour_image.model.TourImageVO;

public interface TourRoomDAO_interface {
	public void insert(TourRoomVO tourRoomVO);
	public void update(TourRoomVO tourRoomVO);
	public TourRoomVO findByPrimaryKey(String tour_room_no);
	public List<TourRoomVO> getAll();
	public void tRoomInsertWithTourNo(TourRoomVO tourRoomVO, List<RoomImageVO> roomImageList, java.sql.Connection con);
	public TourRoomVO findbyTourNo(String tour_no);
}
