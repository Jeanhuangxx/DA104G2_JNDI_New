package com.adm.model;

public class AdmVO implements java.io.Serializable{
	private String adm_no;
	private String adm_id;
	private String adm_psw;
	private String adm_name;
	private Integer adm_tel;
	private String adm_email;
	private byte[] adm_img;
	
	public String getAdm_no() {
		return adm_no;
	}
	public void setAdm_no(String adm_no) {
		this.adm_no = adm_no;
	}
	public String getAdm_id() {
		return adm_id;
	}
	public void setAdm_id(String adm_id) {
		this.adm_id = adm_id;
	}
	public String getAdm_psw() {
		return adm_psw;
	}
	public void setAdm_psw(String adm_psw) {
		this.adm_psw = adm_psw;
	}
	public String getAdm_name() {
		return adm_name;
	}
	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}
	public Integer getAdm_tel() {
		return adm_tel;
	}
	public void setAdm_tel(Integer adm_tel) {
		this.adm_tel = adm_tel;
	}
	public String getAdm_email() {
		return adm_email;
	}
	public void setAdm_email(String adm_email) {
		this.adm_email = adm_email;
	}
	public byte[] getAdm_img() {
		return adm_img;
	}
	public void setAdm_img(byte[] adm_img) {
		this.adm_img = adm_img;
	}
	
	
}

