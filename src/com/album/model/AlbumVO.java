package com.album.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AlbumVO implements Serializable {
	private String album_no;
	private String mem_no;
	private String album_name;
	private Timestamp album_time;
	private byte[] album_pic;
	private String album_note;
	private Integer album_status;
	
	public AlbumVO() {
		super();
	}

	public AlbumVO(String album_no, String mem_no, String album_name, Timestamp album_time, byte[] album_pic,
			String album_note, Integer album_status) {
		super();
		this.album_no = album_no;
		this.mem_no = mem_no;
		this.album_name = album_name;
		this.album_time = album_time;
		this.album_pic = album_pic;
		this.album_note = album_note;
		this.album_status = album_status;
	}

	public String getAlbum_no() {
		return album_no;
	}

	public void setAlbum_no(String album_no) {
		this.album_no = album_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public Timestamp getAlbum_time() {
		return album_time;
	}

	public void setAlbum_time(Timestamp album_time) {
		this.album_time = album_time;
	}

	public byte[] getAlbum_pic() {
		return album_pic;
	}

	public void setAlbum_pic(byte[] album_pic) {
		this.album_pic = album_pic;
	}

	public String getAlbum_note() {
		return album_note;
	}

	public void setAlbum_note(String album_note) {
		this.album_note = album_note;
	}

	public Integer getAlbum_status() {
		return album_status;
	}

	public void setAlbum_status(Integer album_status) {
		this.album_status = album_status;
	}

}
