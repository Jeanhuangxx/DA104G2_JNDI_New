package com.equip_odlist.model;

import java.sql.Connection;
import java.util.List;

public interface EquipOdlistDAO_interface {
	public void insert(EquipOdlistVO equipodlistVO, Connection con);
	public void update(EquipOdlistVO equipodlistVO);
	public EquipOdlistVO findByPrimaryKey(String equip_order_no, String equip_no);
	public List<EquipOdlistVO> getAll();
	public List<EquipOdlistVO> getOneOdlist(String equip_order_no);
	
	
}
