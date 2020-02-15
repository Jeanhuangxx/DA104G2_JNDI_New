package com.mem.model;

import java.io.Serializable;
import java.sql.Date;

public class MemVO implements Serializable {
	private String mem_no;
	private String mem_name;
	private String mem_id;
	private String mem_psw;
	private Integer mem_general_gen;
	private Date mem_general_bd;
	private Integer mem_tel;
	private String mem_email;
	private String mem_add;
	private byte[] mem_img;
	private Integer mem_per;
	private Integer mem_type;
	private byte[] mem_store_business;
	private String mem_store_owner;
	private String mem_store_taxid;

	public MemVO() {
		super();
	}
	
	public MemVO(String mem_no, String mem_name, String mem_id, String mem_psw, Integer mem_general_gen,
			Date mem_general_bd, Integer mem_tel, String mem_email, String mem_add, byte[] mem_img, Integer mem_per,
			Integer mem_type, byte[] mem_store_business, String mem_store_owner, String mem_store_taxid) {
		super();
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_psw = mem_psw;
		this.mem_general_gen = mem_general_gen;
		this.mem_general_bd = mem_general_bd;
		this.mem_tel = mem_tel;
		this.mem_email = mem_email;
		this.mem_add = mem_add;
		this.mem_img = mem_img;
		this.mem_per = mem_per;
		this.mem_type = mem_type;
		this.mem_store_business = mem_store_business;
		this.mem_store_owner = mem_store_owner;
		this.mem_store_taxid = mem_store_taxid;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_psw() {
		return mem_psw;
	}

	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public Integer getMem_general_gen() {
		return mem_general_gen;
	}

	public void setMem_general_gen(Integer mem_general_gen) {
		this.mem_general_gen = mem_general_gen;
	}

	public Date getMem_general_bd() {
		return mem_general_bd;
	}

	public void setMem_general_bd(Date mem_general_bd) {
		this.mem_general_bd = mem_general_bd;
	}

	public Integer getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(Integer mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_add() {
		return mem_add;
	}

	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
	}

	public byte[] getMem_img() {
		return mem_img;
	}

	public void setMem_img(byte[] mem_img) {
		this.mem_img = mem_img;
	}

	public Integer getMem_per() {
		return mem_per;
	}

	public void setMem_per(Integer mem_per) {
		this.mem_per = mem_per;
	}

	public Integer getMem_type() {
		return mem_type;
	}

	public void setMem_type(Integer mem_type) {
		this.mem_type = mem_type;
	}

	public byte[] getMem_store_business() {
		return mem_store_business;
	}

	public void setMem_store_business(byte[] mem_store_business) {
		this.mem_store_business = mem_store_business;
	}

	public String getMem_store_owner() {
		return mem_store_owner;
	}

	public void setMem_store_owner(String mem_store_owner) {
		this.mem_store_owner = mem_store_owner;
	}

	public String getMem_store_taxid() {
		return mem_store_taxid;
	}

	public void setMem_store_taxid(String mem_store_taxid) {
		this.mem_store_taxid = mem_store_taxid;
	}

}
