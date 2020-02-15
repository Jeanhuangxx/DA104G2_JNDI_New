package com.tour_image.model;

public class TourImageVO implements java.io.Serializable{
	private String tour_img_no;
	private String tour_no;
	private byte[] tour_img;
	
	
	public String getTour_img_no() {
		return tour_img_no;
	}
	public void setTour_img_no(String tour_img_no) {
		this.tour_img_no = tour_img_no;
	}
	public String getTour_no() {
		return tour_no;
	}
	public void setTour_no(String tour_no) {
		this.tour_no = tour_no;
	}
	public byte[] getTour_img() {
		return tour_img;
	}
	public void setTour_img(byte[] tour_img) {
		this.tour_img = tour_img;
	}
	
	
}
