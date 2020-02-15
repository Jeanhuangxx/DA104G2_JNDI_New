package com.tour_report.model;

import java.sql.Timestamp;

public class TourReportVO implements java.io.Serializable{
	private String tour_re_no;
	private String tour_no;
	private String mem_no;
	private String tour_re_note;
	private Timestamp tour_re_time;
	private Integer tour_re_status;
	
	public String getTour_re_no() {
		return tour_re_no;
	}
	public void setTour_re_no(String tour_re_no) {
		this.tour_re_no = tour_re_no;
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
	public String getTour_re_note() {
		return tour_re_note;
	}
	public void setTour_re_note(String tour_re_note) {
		this.tour_re_note = tour_re_note;
	}
	public Timestamp getTour_re_time() {
		return tour_re_time;
	}
	public void setTour_re_time(Timestamp tour_re_time) {
		this.tour_re_time = tour_re_time;
	}
	public Integer getTour_re_status() {
		return tour_re_status;
	}
	public void setTour_re_status(Integer tour_re_status) {
		this.tour_re_status = tour_re_status;
	}
	
	
}
