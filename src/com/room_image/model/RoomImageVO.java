package com.room_image.model;

public class RoomImageVO implements java.io.Serializable{
	private String room_img_no;
	private String tour_room_no;
	private byte[] room_img;
	
	
	public String getRoom_img_no() {
		return room_img_no;
	}
	public void setRoom_img_no(String room_img_no) {
		this.room_img_no = room_img_no;
	}
	public String getTour_room_no() {
		return tour_room_no;
	}
	public void setTour_room_no(String tour_room_no) {
		this.tour_room_no = tour_room_no;
	}
	public byte[] getRoom_img() {
		return room_img;
	}
	public void setRoom_img(byte[] room_img) {
		this.room_img = room_img;
	}

	

}
