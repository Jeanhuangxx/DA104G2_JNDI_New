package com.tour_like.model;

import java.util.List;

public class TourLikeService {
	
	private TourLikeDAO_interface dao;
	
	public TourLikeService() {
		dao = new TourLikeJNDIDAO();
	}
	
	public TourLikeVO addTourLike(String tour_no, String mem_no) {
		TourLikeVO tourLikeVO = new TourLikeVO();
		
		tourLikeVO.setTour_no(tour_no);
		tourLikeVO.setMem_no(mem_no);
		dao.insert(tourLikeVO);
		
		return tourLikeVO;
	}
	
	public TourLikeVO updateTourLike(String tour_no, String mem_no) {
		TourLikeVO tourLikeVO = new TourLikeVO();
		
		tourLikeVO.setTour_no(tour_no);
		tourLikeVO.setMem_no(mem_no);
		dao.update(tourLikeVO);
		
		return tourLikeVO;
	}
	
	public void deleteTourLike(String tour_no, String mem_no) {
		dao.delete(tour_no, mem_no);
	}
	
	public TourLikeVO getOneTourLike(String tour_no, String mem_no) {
		return dao.findByPrimaryKey(tour_no, mem_no);
	}
	
	public List<TourLikeVO> getAll(){
		return dao.getAll();
	}
	
	public List<TourLikeVO> getListFromMem(String mem_no){
		return dao.findbyMemNo(mem_no);
	}
}
