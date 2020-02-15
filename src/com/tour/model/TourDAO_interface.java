package com.tour.model;

import java.sql.Date;
import java.util.List;

import com.room_image.model.RoomImageVO;
import com.tour_image.model.TourImageVO;
import com.tour_room.model.TourRoomVO;

public interface TourDAO_interface {
	
	public void insert(TourVO tourVO);
	public void update(TourVO tourVO);
	public void delete(String tour_no);
	public TourVO findbyPrimaryKey(String tour_no);
	public List<TourVO> findbyMemNo(String mem_no);
	public List<TourVO> findbyDate(Date tour_bgn_date, Date tour_end_date, String tour_place);
	public List<TourVO> findbyPlace(String tour_place);
	public List<TourVO> getAll();
	public Integer countbyTourPlace(String tour_place);
	public void updateStatus (TourVO tourVO);
	public void insertTour(TourVO tourVO, List<TourImageVO> tourImageList, List<TourRoomVO> tourRoomList, List<RoomImageVO> roomImageList);
	
}
