package com.group_report.model;

import java.sql.Timestamp;

public class Group_reportVO {
	private String group_re_no;
	private String group_no;
	private String mem_no;
	private String group_re_note;
	private Integer group_re_status;
	private Timestamp group_re_time;
	public String getGroup_re_no() {
		return group_re_no;
	}
	public void setGroup_re_no(String group_re_no) {
		this.group_re_no = group_re_no;
	}
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getGroup_re_note() {
		return group_re_note;
	}
	public void setGroup_re_note(String group_re_note) {
		this.group_re_note = group_re_note;
	}
	public Integer getGroup_re_status() {
		return group_re_status;
	}
	public void setGroup_re_status(Integer group_re_status) {
		this.group_re_status = group_re_status;
	}
	public Timestamp getGroup_re_time() {
		return group_re_time;
	}
	public void setGroup_re_time(Timestamp group_re_time) {
		this.group_re_time = group_re_time;
	}
	
}
