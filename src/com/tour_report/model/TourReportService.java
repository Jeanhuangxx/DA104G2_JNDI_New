package com.tour_report.model;

import java.sql.Timestamp;
import java.util.List;

public class TourReportService {
	private TourReportDAO_interface dao;
	
	public TourReportService() {
		dao = new TourReportJNDIDAO();
	}
	
	public TourReportVO addTourReport(String tour_no, String mem_no, String tour_re_note) {
		TourReportVO tourReportVO = new TourReportVO();
		
		tourReportVO.setTour_no(tour_no);
		tourReportVO.setMem_no(mem_no);
		tourReportVO.setTour_re_note(tour_re_note);
		
		dao.insert(tourReportVO);
		
		return tourReportVO;
	}
	
	public TourReportVO updateTourReport(String tour_re_no, String tour_no, String mem_no, String tour_re_note,
			Integer tour_re_status) {
		TourReportVO tourReportVO = new TourReportVO();
		
		tourReportVO.setTour_re_no(tour_re_no);
		tourReportVO.setTour_no(tour_no);
		tourReportVO.setMem_no(mem_no);
		tourReportVO.setTour_re_note(tour_re_note);
		tourReportVO.setTour_re_status(tour_re_status);
		dao.update(tourReportVO);
		
		return tourReportVO;
	}
	
	public TourReportVO getOneTourReport(String tour_re_no) {
		return dao.findByPrimaryKey(tour_re_no);
	}
	
	public TourReportVO getReportbyMemTour(String mem_no, String tour_no) {
		return dao.findbyMemNOTourNO(mem_no, tour_no);
	}
	
	public List<TourReportVO> getAll(){
		return dao.getAll();
	}
	
	public TourReportVO updateTourReportStatus(String tour_no, Integer tour_re_status, String mem_no) {
		TourReportVO tourReportVO = new TourReportVO();
		
		tourReportVO.setTour_no(tour_no);
		tourReportVO.setTour_re_status(tour_re_status);
		tourReportVO.setMem_no(mem_no);
		
		dao.updateReviewStatus(tourReportVO);
		
		return tourReportVO;	
	}
}
