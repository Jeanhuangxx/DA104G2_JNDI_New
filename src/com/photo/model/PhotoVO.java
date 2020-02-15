package com.photo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PhotoVO implements Serializable{
	private String photo_no;
	private String album_no;
	private String photo_name;
	private Timestamp photo_time;
	private byte[] photo_pic;
	private String photo_note;
	private Integer photo_status;

	public PhotoVO() {
		super();
	}

	public PhotoVO(String photo_no, String album_no, String photo_name, Timestamp photo_time, byte[] photo_pic,
			String photo_note, Integer photo_status) {
		super();
		this.photo_no = photo_no;
		this.album_no = album_no;
		this.photo_name = photo_name;
		this.photo_time = photo_time;
		this.photo_pic = photo_pic;
		this.photo_note = photo_note;
		this.photo_status = photo_status;
	}
	
	public String getPhoto_no() {
		return photo_no;
	}

	public void setPhoto_no(String photo_no) {
		this.photo_no = photo_no;
	}

	public String getAlbum_no() {
		return album_no;
	}

	public void setAlbum_no(String album_no) {
		this.album_no = album_no;
	}

	public String getPhoto_name() {
		return photo_name;
	}

	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}

	public Timestamp getPhoto_time() {
		return photo_time;
	}

	public void setPhoto_time(Timestamp photo_time) {
		this.photo_time = photo_time;
	}

	public byte[] getPhoto_pic() {
		return photo_pic;
	}

	public void setPhoto_pic(byte[] photo_pic) {
		this.photo_pic = photo_pic;
	}

	public String getPhoto_note() {
		return photo_note;
	}

	public void setPhoto_note(String photo_note) {
		this.photo_note = photo_note;
	}

	public Integer getPhoto_status() {
		return photo_status;
	}

	public void setPhoto_status(Integer photo_status) {
		this.photo_status = photo_status;
	}

}
