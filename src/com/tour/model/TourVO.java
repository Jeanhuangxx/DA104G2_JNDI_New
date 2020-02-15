package com.tour.model;

import java.sql.Date;

public class TourVO implements java.io.Serializable{
	
	private String tour_no;
	private String mem_no;
	private String tour_name;
	private Date tour_bgn_date;
	private Date tour_end_date;
	private Integer tour_price;
	private Integer tour_max_num;
	private String tour_place;
	private Integer tour_dives;
	private Date tour_stop_date;
	private Integer tour_status;
	private Integer tour_num;
	private Double tour_rev_avg;
	
	
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
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public Date getTour_bgn_date() {
		return tour_bgn_date;
	}
	public void setTour_bgn_date(Date tour_bgn_date) {
		this.tour_bgn_date = tour_bgn_date;
	}
	public Date getTour_end_date() {
		return tour_end_date;
	}
	public void setTour_end_date(Date tour_end_date) {
		this.tour_end_date = tour_end_date;
	}
	public Integer getTour_price() {
		return tour_price;
	}
	public void setTour_price(Integer tour_price) {
		this.tour_price = tour_price;
	}
	public Integer getTour_max_num() {
		return tour_max_num;
	}
	public void setTour_max_num(Integer tour_max_num) {
		this.tour_max_num = tour_max_num;
	}
	public String getTour_place() {
		return tour_place;
	}
	public void setTour_place(String tour_place) {
		this.tour_place = tour_place;
	}
	public Integer getTour_dives() {
		return tour_dives;
	}
	public void setTour_dives(Integer tour_dives) {
		this.tour_dives = tour_dives;
	}
	public Date getTour_stop_date() {
		return tour_stop_date;
	}
	public void setTour_stop_date(Date tour_stop_date) {
		this.tour_stop_date = tour_stop_date;
	}
	public Integer getTour_status() {
		return tour_status;
	}
	public void setTour_status(Integer tour_status) {
		this.tour_status = tour_status;
	}
	public Integer getTour_num() {
		return tour_num;
	}
	public void setTour_num(Integer tour_num) {
		this.tour_num = tour_num;
	}
	public Double getTour_rev_avg() {
		return tour_rev_avg;
	}
	public void setTour_rev_avg(Double tour_rev_avg) {
		this.tour_rev_avg = tour_rev_avg;
	}
	
}
