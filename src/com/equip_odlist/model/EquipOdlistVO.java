package com.equip_odlist.model;

import java.io.Serializable;

public class EquipOdlistVO implements Serializable{
	
	private String equip_order_no;
	private String equip_no;
	private String equip_name;
	private Integer buy_amt;
	private Integer equip_price;
	
	public String getEquip_order_no() {
		return equip_order_no;
	}
	public void setEquip_order_no(String equip_order_no) {
		this.equip_order_no = equip_order_no;
	}
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
	public Integer getBuy_amt() {
		return buy_amt;
	}
	public void setBuy_amt(Integer buy_amt) {
		this.buy_amt = buy_amt;
	}
	public Integer getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(Integer equip_price) {
		this.equip_price = equip_price;
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
		EquipOdlistVO other = (EquipOdlistVO) obj;
		if (equip_name == null) {
			if (other.equip_name != null)
				return false;
		} else if (!equip_name.equals(other.equip_name))
			return false;
		return true;
	}
	
}
