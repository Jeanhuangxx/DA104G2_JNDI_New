package com.tour_report.model;

import java.util.List;

public interface TourReportDAO_interface {
	public void insert(TourReportVO tourReportVO);
	public void update(TourReportVO tourReportVO);
	public TourReportVO findByPrimaryKey(String tour_re_no);
	public TourReportVO findbyMemNOTourNO(String mem_no, String tour_no);
	public List<TourReportVO> getAll();
	public void updateReviewStatus (TourReportVO tourReportVO);
	
}
