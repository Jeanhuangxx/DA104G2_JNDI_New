package com.equipment.model;
import java.sql.Date;
import java.util.List;


public class EquipmentService {
	
	private EquipmentDAO_interface dao;
	
	public EquipmentService() {
		dao = new EquipmentJNDIDAO();
	}
	
	public EquipmentVO addEquip(String equip_name, String equip_des, byte[] equip_img1, 
			byte[] equip_img2, byte[] equip_img3, Integer equip_status, Integer equip_price, Date equip_update) {
		
		EquipmentVO equipmentVO = new EquipmentVO();

		equipmentVO.setEquip_name(equip_name);
		equipmentVO.setEquip_des(equip_des);
		equipmentVO.setEquip_img1(equip_img1);
		equipmentVO.setEquip_img2(equip_img2);
		equipmentVO.setEquip_img3(equip_img3);
		equipmentVO.setEquip_status(equip_status);
		equipmentVO.setEquip_price(equip_price);
		equipmentVO.setEquip_update(equip_update);
		
		dao.insert(equipmentVO);

		return equipmentVO;
	}
	
	public EquipmentVO updateEquip(String equip_no, String equip_name, String equip_des, byte[] equip_img1, 
			byte[] equip_img2, byte[] equip_img3, Integer equip_status, Integer equip_price, Date equip_update) {

		EquipmentVO equipmentVO = new EquipmentVO();
				
		equipmentVO.setEquip_no(equip_no);
		equipmentVO.setEquip_name(equip_name);
		equipmentVO.setEquip_des(equip_des);
		equipmentVO.setEquip_img1(equip_img1);
		equipmentVO.setEquip_img2(equip_img2);
		equipmentVO.setEquip_img3(equip_img3);
		equipmentVO.setEquip_status(equip_status);
		equipmentVO.setEquip_price(equip_price);
		equipmentVO.setEquip_update(equip_update);
		
		dao.update(equipmentVO);

		return equipmentVO;
	}
	
	public void deleteEquip(String equip_no) {
		dao.delete(equip_no);
	}
	
	public EquipmentVO getOneEquip(String equip_no) {
		return dao.findByPrimaryKey(equip_no);
	}
	
	public List<EquipmentVO> getAll() {
		return dao.getAll();
	}
	
	public List<EquipmentVO> getStatus(Integer equip_status){
		return dao.getStatus(equip_status);
	}
	
	public List<EquipmentVO> get_keyword(String keyword){
		return dao.get_keyword(keyword);
	}
	
}
