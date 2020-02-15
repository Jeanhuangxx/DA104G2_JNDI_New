package com.tour_image.model;

import java.util.List;

public interface TourImageDAO_interface {

	public void insert(TourImageVO tourImageVO);
	public void update(TourImageVO tourImageVO);
	public void delete(String tour_img_no);
	public TourImageVO findByPrimaryKey(String tour_img_no);
	public List<TourImageVO> getAll();
	public void tImgInsertWithTourNo(TourImageVO tourImageVO, java.sql.Connection con);
	public List<TourImageVO> imgFindbyTourNo (String tour_no);
	
}
