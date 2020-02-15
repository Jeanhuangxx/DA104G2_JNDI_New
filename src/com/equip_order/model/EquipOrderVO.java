package com.equip_order.model;
import java.sql.*;

public class EquipOrderVO implements java.io.Serializable{
	private String equip_order_no;
	private String mem_no;
	private Timestamp equip_order_time;
	private Integer equip_order_price;
	private Date equip_shipping_date;
	private Integer equip_order_status;
	private String equip_note;
	private String cus_name;
	private Integer cus_tel;
	private String cus_add;
	
	
	public String getEquip_order_no() {
		return equip_order_no;
	}
	public void setEquip_order_no(String equip_order_no) {
		this.equip_order_no = equip_order_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getEquip_order_time() {
		return equip_order_time;
	}
	public void setEquip_order_time(Timestamp equip_order_time) {
		this.equip_order_time = equip_order_time;
	}
	public Integer getEquip_order_price() {
		return equip_order_price;
	}
	public void setEquip_order_price(Integer equip_order_price) {
		this.equip_order_price = equip_order_price;
	}
	public Date getEquip_shipping_date() {
		return equip_shipping_date;
	}
	public void setEquip_shipping_date(Date equip_shipping_date) {
		this.equip_shipping_date = equip_shipping_date;
	}
	public Integer getEquip_order_status() {
		return equip_order_status;
	}
	public void setEquip_order_status(Integer equip_order_status) {
		this.equip_order_status = equip_order_status;
	}
	public String getEquip_note() {
		return equip_note;
	}
	public void setEquip_note(String equip_note) {
		this.equip_note = equip_note;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public Integer getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(Integer cus_tel) {
		this.cus_tel = cus_tel;
	}
	public String getCus_add() {
		return cus_add;
	}
	public void setCus_add(String cus_add) {
		this.cus_add = cus_add;
	}
	
}
