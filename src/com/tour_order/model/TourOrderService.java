package com.tour_order.model;

import java.sql.Timestamp;
import java.util.List;

public class TourOrderService {
	private TourOrderDAO_interface dao;
	
	public TourOrderService(){
		dao = new TourOrderJNDIDAO();
	}
	
	public TourOrderVO addTourOrder(String tour_no, String mem_no, Integer ttl_price){
		TourOrderVO tourOrderVO = new TourOrderVO();
		
		tourOrderVO.setTour_no(tour_no);
		tourOrderVO.setMem_no(mem_no);
		tourOrderVO.setTtl_price(ttl_price);
		dao.insert(tourOrderVO);
		
		return tourOrderVO;
		
	}
	
	public TourOrderVO updateTourOrder(String tour_order_no, String tour_no, String mem_no, Integer tour_order_status, 
			Integer ttl_price, String tour_rev_note, Integer tour_rev) {
		TourOrderVO tourOrderVO = new TourOrderVO();
		
		tourOrderVO.setTour_order_no(tour_order_no);
		tourOrderVO.setTour_no(tour_no);
		tourOrderVO.setMem_no(mem_no);
		tourOrderVO.setTour_order_status(tour_order_status);
		tourOrderVO.setTtl_price(ttl_price);
		tourOrderVO.setTour_rev_note(tour_rev_note);
		tourOrderVO.setTour_rev(tour_rev);
		dao.update(tourOrderVO);
		
		return tourOrderVO;
		
	}
	
	public TourOrderVO updateTourReview(String tour_rev_note, Integer tour_rev, String tour_no, String mem_no) {
		TourOrderVO tourOrderVO = new TourOrderVO();
		tourOrderVO.setMem_no(mem_no);
		tourOrderVO.setTour_no(tour_no);
		tourOrderVO.setTour_rev_note(tour_rev_note);
		tourOrderVO.setTour_rev(tour_rev);
		dao.updateReview(tourOrderVO);
		
		return tourOrderVO;
	}
	
	public TourOrderVO getTourByMemTour(String tour_no, String mem_no) {
		return dao.oneOrderbyMemTour(tour_no, mem_no);
	}
	
	public TourOrderVO getOneTourOrder(String tour_order_no) {
		return dao.findByPrimaryKey(tour_order_no);
	}
	
	public List<TourOrderVO> getAll(){
		return dao.getAll();
	}
	
	public List<TourOrderVO> getTourOrderList(String mem_no){
		return dao.findOrderbyMemNo(mem_no);
	}
	
	public Integer getnumberOfAttd(String tour_no) {
		return dao.numberOfAttd(tour_no);
	}
	
	public Integer getCountbyReviewNote(String tour_no) {
		return dao.countbyReviewNote(tour_no);
	}
	
	public Double getAvgTourScore(String tour_no) {
		return dao.avgTourScore(tour_no);
	}
	
	public List<TourOrderVO> getReviewList(String tour_no){
		return dao.getListByTourNo(tour_no);
	} 
	
}
