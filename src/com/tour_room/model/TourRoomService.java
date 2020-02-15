package com.tour_room.model;

import java.util.List;

public class TourRoomService {
	private TourRoomDAO_interface dao;
	
	public TourRoomService() {
		dao = new TourRoomJNDIDAO();
	}
	
	public TourRoomVO addTourRoom(String tour_no, Integer bed_size, Integer room_ppl, Integer room_priv_br,
			Integer room_aircon) {
		TourRoomVO tourRoomVO = new TourRoomVO();
		
		tourRoomVO.setTour_no(tour_no);
		tourRoomVO.setBed_size(bed_size);
		tourRoomVO.setRoom_ppl(room_ppl);
		tourRoomVO.setRoom_priv_br(room_priv_br);
		tourRoomVO.setRoom_aircon(room_aircon);
		dao.insert(tourRoomVO);
		
		return tourRoomVO;
		
	}
	
	public TourRoomVO updateTourRoom(String tour_no, Integer bed_size, Integer room_ppl, Integer room_priv_br,
			Integer room_aircon) {
		TourRoomVO tourRoomVO = new TourRoomVO();
		
		tourRoomVO.setTour_no(tour_no);
		tourRoomVO.setBed_size(bed_size);
		tourRoomVO.setRoom_ppl(room_ppl);
		tourRoomVO.setRoom_priv_br(room_priv_br);
		tourRoomVO.setRoom_aircon(room_aircon);
		dao.update(tourRoomVO);
		
		return tourRoomVO;
	}
	
	public TourRoomVO getOneTourRoom(String tour_room_no) {
		return dao.findByPrimaryKey(tour_room_no);
	}
	
	public TourRoomVO getByTourNo(String tour_no) {
		return dao.findbyTourNo(tour_no);
	}
	
	public List<TourRoomVO> getAll(){
		return dao.getAll();
	}
}
