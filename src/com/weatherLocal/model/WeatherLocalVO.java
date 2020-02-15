package com.weatherLocal.model;

import java.sql.Timestamp;


public class WeatherLocalVO implements java.io.Serializable {
	private String wt_local;
	private String wt_temp;
	private String wt_watertemp;
	private String wt_rainrate;
	private String  wt_waveheight;
	private Timestamp wt_sunrise;
	private Timestamp wt_sundown;
	private Timestamp wt_time;
	
	
	public String getWt_local() {
		return wt_local;
	}
	public void setWt_local(String wt_local) {
		this.wt_local = wt_local;
	}
	public String getWt_temp() {
		return wt_temp;
	}
	public void setWt_temp(String wt_temp) {
		this.wt_temp = wt_temp;
	}
	public String getWt_watertemp() {
		return wt_watertemp;
	}
	public void setWt_watertemp(String wt_watertemp) {
		this.wt_watertemp = wt_watertemp;
	}
	public String getWt_rainrate() {
		return wt_rainrate;
	}
	public void setWt_rainrate(String wt_rainrate) {
		this.wt_rainrate = wt_rainrate;
	}
	public String getWt_waveheight() {
		return wt_waveheight;
	}
	public void setWt_waveheight(String wt_waveheight) {
		this.wt_waveheight = wt_waveheight;
	}
	public Timestamp getWt_sunrise() {
		return wt_sunrise;
	}
	public void setWt_sunrise(Timestamp wt_sunrise) {
		this.wt_sunrise = wt_sunrise;
	}
	public Timestamp getWt_sundown() {
		return wt_sundown;
	}
	public void setWt_sundown(Timestamp wt_sundown) {
		this.wt_sundown = wt_sundown;
	}
	public Timestamp getWt_time() {
		return wt_time;
	}
	public void setWt_time(Timestamp wt_time) {
		this.wt_time = wt_time;
	}
	@Override
	public String toString() {
		return "DiveLocalVO [wt_local=" + wt_local + ", wt_temp=" + wt_temp + ", wt_watertemp=" + wt_watertemp
				+ ", wt_rainrate=" + wt_rainrate + ", wt_waveheight=" + wt_waveheight + ", wt_sunrise=" + wt_sunrise
				+ ", wt_sundown=" + wt_sundown + ", wt_time=" + wt_time + "]";
	}

	
	
	
	
}
