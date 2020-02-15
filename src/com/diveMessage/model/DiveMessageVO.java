package com.diveMessage.model;

import java.sql.Timestamp;

public class DiveMessageVO implements java.io.Serializable {
	private String divmeg_no;
	private String dive_no;
	private String mem_no;
	private Timestamp divmeg_time;
	private String divmeg_note;
	
	public String getDivmeg_no() {
		return divmeg_no;
	}
	public void setDivmeg_no(String divmeg_no) {
		this.divmeg_no = divmeg_no;
	}
	public String getDive_no() {
		return dive_no;
	}
	public void setDive_no(String dive_no) {
		this.dive_no = dive_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getDivmeg_time() {
		return divmeg_time;
	}
	public void setDivmeg_time(Timestamp divmeg_time) {
		this.divmeg_time = divmeg_time;
	}
	public String getDivmeg_note() {
		return divmeg_note;
	}
	public void setDivmeg_note(String divmeg_note) {
		this.divmeg_note = divmeg_note;
	}
	@Override
	public String toString() {
		return "DiveMessageVO [divmeg_no=" + divmeg_no + ", dive_no=" + dive_no + ", mem_no=" + mem_no
				+ ", divmeg_time=" + divmeg_time + ", divmeg_note=" + divmeg_note + "]";
	}
	
	
}
