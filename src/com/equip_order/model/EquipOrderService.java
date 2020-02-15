package com.equip_order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.equip_odlist.model.EquipOdlistVO;
import com.equipment.model.EquipmentVO;

public class EquipOrderService {

	private EquipOrderDAO_interface dao;
	
	public EquipOrderService() {
		dao = new EquipOrderJNDIDAO();
	}
	
	public EquipOrderVO updateOrder(String equip_order_no, String mem_no, Timestamp equip_order_time, Integer equip_order_price, 
			Date equip_shipping_date, Integer equip_order_status, String equip_note, String cus_name, Integer cus_tel, String cus_add) {
		
		EquipOrderVO equiporderVO = new EquipOrderVO();
		
		equiporderVO.setEquip_order_no(equip_order_no);
		equiporderVO.setMem_no(mem_no);
		equiporderVO.setEquip_order_time(equip_order_time);
		equiporderVO.setEquip_order_price(equip_order_price);
		equiporderVO.setEquip_shipping_date(equip_shipping_date);
		equiporderVO.setEquip_order_status(equip_order_status);
		equiporderVO.setEquip_note(equip_note);
		equiporderVO.setCus_name(cus_name);
		equiporderVO.setCus_tel(cus_tel);
		equiporderVO.setCus_add(cus_add);
		
		dao.update(equiporderVO);
		
		
		return equiporderVO;
	}
	
	public EquipOrderVO getOneOrder(String equip_order_no) {
		return dao.findByPrimaryKey(equip_order_no);
	}
	
	public List<EquipOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<EquipOrderVO> getMem(String mem_no) {
		return dao.getMem(mem_no);
	}
	
	public List<EquipOrderVO> getOrderStatus(Integer equip_order_status) {
		return dao.getOrderStatus(equip_order_status);
	}
	
	public void addOrder(EquipOrderVO equiporderVO, List<EquipOdlistVO> buylist) {
		dao.insert(equiporderVO, buylist);
	}
	
	
}
