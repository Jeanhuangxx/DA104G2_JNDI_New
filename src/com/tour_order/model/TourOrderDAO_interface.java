package com.tour_order.model;

import java.util.List;

public interface TourOrderDAO_interface {
	public void insert(TourOrderVO tourOrderVO);
	public void update(TourOrderVO tourOrderVO);
	public void updateReview(TourOrderVO tourOrderVO);
	public TourOrderVO findByPrimaryKey(String tour_order_no);
	public List<TourOrderVO> getAll();
	public List<TourOrderVO> findOrderbyMemNo(String mem_no);
	public Integer numberOfAttd(String tour_no);
	public Double avgTourScore(String tour_no);
	public Integer countbyReviewNote(String tour_no);
	public List<TourOrderVO> getListByTourNo(String tour_no);
	public TourOrderVO oneOrderbyMemTour(String tour_no, String mem_no);
}
