package com.photo_report.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PhotoReportVO implements Serializable{
	private String photo_re_no;
	private String photo_no;
	private String mem_no;
	private String photo_re_note;
	private Timestamp photo_re_time;
	private Integer photo_re_status;

	public PhotoReportVO() {
		super();
	}
	
	public PhotoReportVO(String photo_re_no, String photo_no, String mem_no, String photo_re_note,
			Timestamp photo_re_time, Integer photo_re_status) {
		super();
		this.photo_re_no = photo_re_no;
		this.photo_no = photo_no;
		this.mem_no = mem_no;
		this.photo_re_note = photo_re_note;
		this.photo_re_time = photo_re_time;
		this.photo_re_status = photo_re_status;
	}

	public String getPhoto_re_no() {
		return photo_re_no;
	}

	public void setPhoto_re_no(String photo_re_no) {
		this.photo_re_no = photo_re_no;
	}

	public String getPhoto_no() {
		return photo_no;
	}

	public void setPhoto_no(String photo_no) {
		this.photo_no = photo_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getPhoto_re_note() {
		return photo_re_note;
	}

	public void setPhoto_re_note(String photo_re_note) {
		this.photo_re_note = photo_re_note;
	}

	public Timestamp getPhoto_re_time() {
		return photo_re_time;
	}

	public void setPhoto_re_time(Timestamp photo_re_time) {
		this.photo_re_time = photo_re_time;
	}

	public Integer getPhoto_re_status() {
		return photo_re_status;
	}

	public void setPhoto_re_status(Integer photo_re_status) {
		this.photo_re_status = photo_re_status;
	}

}
