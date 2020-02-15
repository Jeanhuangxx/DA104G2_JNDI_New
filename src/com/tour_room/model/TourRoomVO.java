package com.tour_room.model;

public class TourRoomVO implements java.io.Serializable{
	private String tour_room_no;
	private String tour_no;
	private Integer bed_size;
	private Integer room_ppl;
	private Integer room_priv_br;
	private Integer room_aircon;
	
	public String getTour_room_no() {
		return tour_room_no;
	}
	public void setTour_room_no(String tour_room_no) {
		this.tour_room_no = tour_room_no;
	}
	public String getTour_no() {
		return tour_no;
	}
	public void setTour_no(String tour_no) {
		this.tour_no = tour_no;
	}
	public Integer getBed_size() {
		return bed_size;
	}
	public void setBed_size(Integer bed_size) {
		this.bed_size = bed_size;
	}
	public Integer getRoom_ppl() {
		return room_ppl;
	}
	public void setRoom_ppl(Integer room_ppl) {
		this.room_ppl = room_ppl;
	}
	public Integer getRoom_priv_br() {
		return room_priv_br;
	}
	public void setRoom_priv_br(Integer room_priv_br) {
		this.room_priv_br = room_priv_br;
	}
	public Integer getRoom_aircon() {
		return room_aircon;
	}
	public void setRoom_aircon(Integer room_aircon) {
		this.room_aircon = room_aircon;
	}
	
	
}
