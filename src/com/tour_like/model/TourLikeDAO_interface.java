package com.tour_like.model;

import java.util.List;

public interface TourLikeDAO_interface {
	public void insert(TourLikeVO tourLikeVO);
	public void update(TourLikeVO tourLikeVO);
	public void delete(String tour_no, String mem_no);
	public TourLikeVO findByPrimaryKey(String tour_no, String mem_no);
	public List<TourLikeVO> getAll();
	public List<TourLikeVO> findbyMemNo(String mem_no);
}
