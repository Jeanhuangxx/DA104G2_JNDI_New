package com.group_message.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Group_messageVO implements Serializable{
	private String group_me_no;
	private String group_no;
	private String mem_no;
	private String group_me_note;
	private Timestamp group_me_time;
	public String getGroup_me_no() {
		return group_me_no;
	}
	public void setGroup_me_no(String group_me_no) {
		this.group_me_no = group_me_no;
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
	public String getGroup_me_note() {
		return group_me_note;
	}
	public void setGroup_me_note(String group_me_note) {
		this.group_me_note = group_me_note;
	}
	public Timestamp getGroup_me_time() {
		return group_me_time;
	}
	public void setGroup_me_time(Timestamp group_me_time) {
		this.group_me_time = group_me_time;
	}
	
	
}
