package com.tour_order.model;

import java.sql.Timestamp;

public class TourOrderVO implements java.io.Serializable{
	
	private String tour_order_no;
	private String tour_no;
	private String mem_no;
	private Timestamp tour_order_date;
	private Integer tour_order_status;
	private Integer ttl_price;
	private String tour_rev_note;
	private Integer tour_rev;
	
	
	public String getTour_order_no() {
		return tour_order_no;
	}
	public void setTour_order_no(String tour_order_no) {
		this.tour_order_no = tour_order_no;
	}
	public String getTour_no() {
		return tour_no;
	}
	public void setTour_no(String tour_no) {
		this.tour_no = tour_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getTour_order_date() {
		return tour_order_date;
	}
	public void setTour_order_date(Timestamp tour_order_date) {
		this.tour_order_date = tour_order_date;
	}
	public Integer getTour_order_status() {
		return tour_order_status;
	}
	public void setTour_order_status(Integer tour_order_status) {
		this.tour_order_status = tour_order_status;
	}
	public Integer getTtl_price() {
		return ttl_price;
	}
	public void setTtl_price(Integer ttl_price) {
		this.ttl_price = ttl_price;
	}
	public String getTour_rev_note() {
		return tour_rev_note;
	}
	public void setTour_rev_note(String tour_rev_note) {
		this.tour_rev_note = tour_rev_note;
	}
	public Integer getTour_rev() {
		return tour_rev;
	}
	public void setTour_rev(Integer tour_rev) {
		this.tour_rev = tour_rev;
	}
	
}
