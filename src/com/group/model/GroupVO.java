package com.group.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.sun.xml.internal.ws.api.model.SEIModel;

public class GroupVO implements Serializable{
	private String group_no;
	private String mem_no;
	private String dive_no;
	private String group_name;
	private Timestamp group_ct_time;
	private Timestamp group_tour_stop_time;
	private Timestamp group_begin_time;
	private Timestamp group_end_time;
	private Timestamp group_modify_time;
	private Integer group_max_num;
	private String group_des;
	private Integer group_status;
	private String group_tel;
	private String group_mp;
	private Integer group_tour_num;
	private byte[] group_photo;

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

	public String getDive_no() {
		return dive_no;
	}

	public void setDive_no(String dive_no) {
		this.dive_no = dive_no;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Timestamp getGroup_ct_time() {
		return group_ct_time;
	}

	public void setGroup_ct_time(Timestamp group_ct_time) {
		this.group_ct_time = group_ct_time;
	}

	public Timestamp getGroup_tour_stop_time() {
		return group_tour_stop_time;
	}

	public void setGroup_tour_stop_time(Timestamp group_tour_stop_time) {
		this.group_tour_stop_time = group_tour_stop_time;
	}

	public Timestamp getGroup_begin_time() {
		return group_begin_time;
	}

	public void setGroup_begin_time(Timestamp group_begin_time) {
		this.group_begin_time = group_begin_time;
	}

	public Timestamp getGroup_end_time() {
		return group_end_time;
	}

	public void setGroup_end_time(Timestamp group_end_time) {
		this.group_end_time = group_end_time;
	}

	public Timestamp getGroup_modify_time() {
		return group_modify_time;
	}

	public void setGroup_modify_time(Timestamp group_modify_time) {
		this.group_modify_time = group_modify_time;
	}

	public Integer getGroup_max_num() {
		return group_max_num;
	}

	public void setGroup_max_num(Integer group_max_num) {
		this.group_max_num = group_max_num;
	}

	public String getGroup_des() {
		return group_des;
	}

	public void setGroup_des(String group_des) {
		this.group_des = group_des;
	}

	public Integer getGroup_status() {
		return group_status;
	}

	public void setGroup_status(Integer group_status) {
		this.group_status = group_status;
	}

	public String getGroup_tel() {
		return group_tel;
	}

	public void setGroup_tel(String group_tel) {
		this.group_tel = group_tel;
	}

	public String getGroup_mp() {
		return group_mp;
	}

	public void setGroup_mp(String group_mp) {
		this.group_mp = group_mp;
	}

	public Integer getGroup_tour_num() {
		return group_tour_num;
	}

	public void setGroup_tour_num(Integer group_tour_num) {
		this.group_tour_num = group_tour_num;
	}

	public byte[] getGroup_photo() {
		return group_photo;
	}

	public void setGroup_photo(byte[] group_photo) {
		this.group_photo = group_photo;
	}
	
	
}