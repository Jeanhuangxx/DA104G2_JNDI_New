package com.equipment.model;

import java.util.List;

public interface EquipmentDAO_interface {
	public void insert(EquipmentVO equipmentVO);
	public void update(EquipmentVO equipmentVO);
	public void delete(String equip_no);
	public EquipmentVO findByPrimaryKey(String equip_no);
	public List<EquipmentVO> getAll();
	public List<EquipmentVO> getStatus(Integer equip_status);
	public List<EquipmentVO> get_keyword(String keyword);
	
	
}