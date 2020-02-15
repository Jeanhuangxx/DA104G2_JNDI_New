package com.equipment.model;
import java.sql.Date;

public class EquipmentVO implements java.io.Serializable{
	private String equip_no;
	private String equip_name;
	private String equip_des;
	private byte[] equip_img1;
	private byte[] equip_img2;
	private byte[] equip_img3;
	private Integer equip_status;
	private Integer equip_price;
	private Date equip_update;

	
	
	public String getEquip_no() {
		return equip_no;
	}
	public void setEquip_no(String equip_no) {
		this.equip_no = equip_no;
	}
	public String getEquip_name() {
		return equip_name;
	}
	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	public String getEquip_des() {
		return equip_des;
	}
	public void setEquip_des(String equip_des) {
		this.equip_des = equip_des;
	}
	public byte[] getEquip_img1() {
		return equip_img1;
	}
	public void setEquip_img1(byte[] equip_img1) {
		this.equip_img1 = equip_img1;
	}
	public byte[] getEquip_img2() {
		return equip_img2;
	}
	public void setEquip_img2(byte[] equip_img2) {
		this.equip_img2 = equip_img2;
	}
	public byte[] getEquip_img3() {
		return equip_img3;
	}
	public void setEquip_img3(byte[] equip_img3) {
		this.equip_img3 = equip_img3;
	}
	public Integer getEquip_status() {
		return equip_status;
	}
	public void setEquip_status(Integer equip_status) {
		this.equip_status = equip_status;
	}
	public Integer getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(Integer equip_price) {
		this.equip_price = equip_price;
	}
	public Date getEquip_update() {
		return equip_update;
	}
	public void setEquip_update(Date equip_update) {
		this.equip_update = equip_update;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equip_name == null) ? 0 : equip_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipmentVO other = (EquipmentVO) obj;
		if (equip_name == null) {
			if (other.equip_name != null)
				return false;
		} else if (!equip_name.equals(other.equip_name))
			return false;
		return true;
	}
	
	
	
	
}
