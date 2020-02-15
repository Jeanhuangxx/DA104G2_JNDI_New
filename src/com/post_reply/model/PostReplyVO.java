package com.post_reply.model;

import java.sql.Timestamp;

public class PostReplyVO {
	private String reply_no;
	private String mem_no;
	private String post_no;
	private Timestamp reply_time;
	private String reply_cont;

	public PostReplyVO() {
		super();
	}

	public PostReplyVO(String reply_no, String mem_no, String post_no, Timestamp reply_time, String reply_cont) {
		super();
		this.reply_no = reply_no;
		this.mem_no = mem_no;
		this.post_no = post_no;
		this.reply_time = reply_time;
		this.reply_cont = reply_cont;
	}

	public String getReply_no() {
		return reply_no;
	}

	public void setReply_no(String reply_no) {
		this.reply_no = reply_no;
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

	public Timestamp getReply_time() {
		return reply_time;
	}

	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}

	public String getReply_cont() {
		return reply_cont;
	}

	public void setReply_cont(String reply_cont) {
		this.reply_cont = reply_cont;
	}

}
