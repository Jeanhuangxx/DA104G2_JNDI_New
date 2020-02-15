package com.tour_image.model;

import java.util.List;

public class TourImageService {
	
	private TourImageDAO_interface dao;
	
	public TourImageService() {
		dao = new TourImageJNDIDAO();
	}
	
	public TourImageVO addTourImage(String tour_no, byte[] tour_img) {
		TourImageVO tourImageVO = new TourImageVO();
		
		tourImageVO.setTour_no(tour_no);
		tourImageVO.setTour_img(tour_img);
		dao.insert(tourImageVO);
		
		return tourImageVO; 
		
	}
	
	public TourImageVO updateTourImage(String tour_img_no, String tour_no, byte[] tour_img) {
		TourImageVO tourImageVO = new TourImageVO();
		
		tourImageVO.setTour_img_no(tour_img_no);
		tourImageVO.setTour_no(tour_no);
		tourImageVO.setTour_img(tour_img);
		dao.update(tourImageVO);
		
		return tourImageVO;
		
	}
	
	public void deleteTourImage(String tour_img_no) {
		dao.delete(tour_img_no);
	}
	
	public TourImageVO getOneTourImage(String tour_img_no) {
		return dao.findByPrimaryKey(tour_img_no);
	}
	
	public List<TourImageVO> getAll(){
		return dao.getAll();
	}
	
	public List<TourImageVO> getImgbyTourNo(String tour_no){
		return dao.imgFindbyTourNo(tour_no);
	}
	

}
