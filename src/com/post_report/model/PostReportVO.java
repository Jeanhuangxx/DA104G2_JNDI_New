package com.post_report.model;

import java.sql.Timestamp;

public class PostReportVO {
	private String post_re_no;
	private String mem_no;
	private String post_no;
	private Timestamp post_re_time;
	private String post_re_note;
	private Integer post_re_status;

	public PostReportVO() {
		super();
	}

	public PostReportVO(String post_re_no, String mem_no, String post_no, Timestamp post_re_time, String post_re_note,
			Integer post_re_status) {
		super();
		this.post_re_no = post_re_no;
		this.mem_no = mem_no;
		this.post_no = post_no;
		this.post_re_time = post_re_time;
		this.post_re_note = post_re_note;
		this.post_re_status = post_re_status;
	}

	public String getPost_re_no() {
		return post_re_no;
	}

	public void setPost_re_no(String post_re_no) {
		this.post_re_no = post_re_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getPost_no() {
		return post_no;
	}

	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}

	public Timestamp getPost_re_time() {
		return post_re_time;
	}

	public void setPost_re_time(Timestamp post_re_time) {
		this.post_re_time = post_re_time;
	}

	public String getPost_re_note() {
		return post_re_note;
	}

	public void setPost_re_note(String post_re_note) {
		this.post_re_note = post_re_note;
	}

	public Integer getPost_re_status() {
		return post_re_status;
	}

	public void setPost_re_status(Integer post_re_status) {
		this.post_re_status = post_re_status;
	}

}
