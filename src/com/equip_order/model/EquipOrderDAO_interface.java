package com.equip_order.model;

import java.util.List;

import com.equip_odlist.model.EquipOdlistVO;

public interface EquipOrderDAO_interface {
	public void insert(EquipOrderVO equiporderVO, List<EquipOdlistVO> list);
	public void update(EquipOrderVO equiporderVO);
	public EquipOrderVO findByPrimaryKey(String equip_order_no);
	public List<EquipOrderVO> getAll();
	public List<EquipOrderVO> getMem(String mem_no);
	public List<EquipOrderVO> getOrderStatus(Integer equip_order_status);
	
	
}
