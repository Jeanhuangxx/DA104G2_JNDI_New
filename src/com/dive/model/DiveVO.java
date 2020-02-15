package com.dive.model;

import java.util.Arrays;

public class DiveVO implements java.io.Serializable {
	private String dive_no;
	private String weather_area_no;
	private String dive_name;
	private String dive_des;
	private byte[]  dive_img;
	private Integer dive_status;
	private String dive_lat;
	private String dive_lang;
	public String getDive_no() {
		return dive_no;
	}
	public void setDive_no(String dive_no) {
		this.dive_no = dive_no;
	}
	public String getWeather_area_no() {
		return weather_area_no;
	}
	public void setWeather_area_no(String weather_area_no) {
		this.weather_area_no = weather_area_no;
	}
	public String getDive_name() {
		return dive_name;
	}
	public void setDive_name(String dive_name) {
		this.dive_name = dive_name;
	}
	public String getDive_des() {
		return dive_des;
	}
	public void setDive_des(String dive_des) {
		this.dive_des = dive_des;
	}
	public byte[] getDive_img() {
		return dive_img;
	}
	public void setDive_img(byte[] dive_img) {
		this.dive_img = dive_img;
	}
	public Integer getDive_status() {
		return dive_status;
	}
	public void setDive_status(Integer dive_status) {
		this.dive_status = dive_status;
	}
	public String getDive_lat() {
		return dive_lat;
	}
	public void setDive_lat(String dive_lat) {
		this.dive_lat = dive_lat;
	}
	public String getDive_lang() {
		return dive_lang;
	}
	public void setDive_lang(String dive_lang) {
		this.dive_lang = dive_lang;
	}
	@Override
	public String toString() {
		return "DiveVO [dive_no=" + dive_no + ", weather_area_no=" + weather_area_no + ", dive_name=" + dive_name
				+ ", dive_des=" + dive_des + ", dive_img=" + Arrays.toString(dive_img) + ", dive_status=" + dive_status
				+ ", dive_lat=" + dive_lat + ", dive_lang=" + dive_lang + "]";
	}
	

}
