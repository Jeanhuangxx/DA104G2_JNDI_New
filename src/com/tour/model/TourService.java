package com.tour.model;

import java.sql.Date;
import java.util.List;

public class TourService {

	private TourDAO_interface dao;

	public TourService() {
		dao = new TourJNDIDAO();
	}

	public TourVO addTour(String mem_no, String tour_name, java.sql.Date tour_bgn_date, java.sql.Date tour_end_date,
			Integer tour_price, Integer tour_max_num, String tour_place, Integer tour_dives,
			java.sql.Date tour_stop_date, Integer tour_status) {

		TourVO tourVO = new TourVO();

		tourVO.setMem_no(mem_no);
		tourVO.setTour_name(tour_name);
		tourVO.setTour_bgn_date(tour_bgn_date);
		tourVO.setTour_end_date(tour_end_date);
		tourVO.setTour_price(tour_price);
		tourVO.setTour_max_num(tour_max_num);
		tourVO.setTour_place(tour_place);
		tourVO.setTour_dives(tour_dives);
		tourVO.setTour_stop_date(tour_stop_date);
		tourVO.setTour_status(tour_status);
		dao.insert(tourVO);

		return tourVO;
	}

	public TourVO updateTour(String tour_no, String tour_name, java.sql.Date tour_bgn_date,
			java.sql.Date tour_end_date, Integer tour_price, Integer tour_max_num, String tour_place,
			Integer tour_dives, java.sql.Date tour_stop_date, Integer tour_status) {

		TourVO tourVO = new TourVO();

		tourVO.setTour_no(tour_no);
		tourVO.setTour_name(tour_name);
		tourVO.setTour_bgn_date(tour_bgn_date);
		tourVO.setTour_end_date(tour_end_date);
		tourVO.setTour_price(tour_price);
		tourVO.setTour_max_num(tour_max_num);
		tourVO.setTour_place(tour_place);
		tourVO.setTour_dives(tour_dives);
		tourVO.setTour_stop_date(tour_stop_date);
		tourVO.setTour_status(tour_status);
		dao.update(tourVO);

		return tourVO;
	}
	
	public TourVO updateTourStatus(String tour_no, Integer tour_status) {
		TourVO tourVO = new TourVO();
		
		tourVO.setTour_no(tour_no);
		tourVO.setTour_status(tour_status);
		dao.updateStatus(tourVO);
		
		return tourVO;
		
	}

	public void deleteTour(String tour_no) {
		dao.delete(tour_no);
	}

	public TourVO getOneTour(String tour_no) {
		return dao.findbyPrimaryKey(tour_no);
	}

	public List<TourVO> getTourbyMemNo(String mem_no) {
		return dao.findbyMemNo(mem_no);
	}

	public List<TourVO> getAll() {
		return dao.getAll();
	}

	public List<TourVO> getTourbyDate(Date tour_bgn_date, Date tour_end_date, String tour_place) {
		return dao.findbyDate(tour_bgn_date, tour_end_date, tour_place);
	}

	public List<TourVO> getTourbyPlace(String tour_place) {
		return dao.findbyPlace(tour_place);
	}

	public Integer getPlaceCount(String tour_place) {
		return dao.countbyTourPlace(tour_place);
	}
	
}
