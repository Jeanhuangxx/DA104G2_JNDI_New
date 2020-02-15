package com.forum.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ForumVO implements java.io.Serializable {
	private String post_no;
	private String men_no;
	private String post_title;
	private Timestamp post_time;
	private String post_cont;
	private Integer post_status;

	public ForumVO() {
		super();
	}

	public ForumVO(String post_no, String men_no, String post_title, Timestamp post_time, String post_cont,
			Integer post_status) {
		super();
		this.post_no = post_no;
		this.men_no = men_no;
		this.post_title = post_title;
		this.post_time = post_time;
		this.post_cont = post_cont;
		this.post_status = post_status;
	}

	public String getPost_no() {
		return post_no;
	}

	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}

	public String getMen_no() {
		return men_no;
	}

	public void setMen_no(String men_no) {
		this.men_no = men_no;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public Timestamp getPost_time() {
		return post_time;
	}

	public void setPost_time(Timestamp post_time) {
		this.post_time = post_time;
	}

	public String getPost_cont() {
		return post_cont;
	}

	public void setPost_cont(String post_cont) {
		this.post_cont = post_cont;
	}

	public Integer getPost_status() {
		return post_status;
	}

	public void setPost_status(Integer post_status) {
		this.post_status = post_status;
	}

	@Override
	public String toString() {
		return "ForumVO [post_no=" + post_no + ", men_no=" + men_no + ", post_title=" + post_title + ", post_time="
				+ post_time + ", post_cont=" + post_cont + ", post_status=" + post_status + "]";
	}

}
