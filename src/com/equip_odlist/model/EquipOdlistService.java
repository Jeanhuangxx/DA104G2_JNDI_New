package com.equip_odlist.model;

import java.sql.Connection;
import java.util.List;


public class EquipOdlistService {
	
		private EquipOdlistDAO_interface dao;
	
		public EquipOdlistService() {
			dao = new EquipOdlistJNDIDAO();
		}
	
		public List<EquipOdlistVO> getOneOdlist(String equip_order_no) {
			return dao.getOneOdlist(equip_order_no);
		}
		
		public void insert (EquipOdlistVO equipodlistVO , Connection con) {
			dao.insert(equipodlistVO, con);
		}
		
		
}
