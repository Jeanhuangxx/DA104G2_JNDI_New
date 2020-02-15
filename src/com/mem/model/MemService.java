package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemJNDIDAO();
	}

	public MemVO addMem(String mem_name, String mem_id, String mem_psw, Integer mem_general_gen, Date mem_general_bd,
			Integer mem_tel, String mem_email, String mem_add, byte[] mem_img, Integer mem_per, Integer mem_type,
			byte[] mem_store_business, String mem_store_owner, String mem_store_taxid) {
		MemVO memVO = new MemVO();

		memVO.setMem_name(mem_name);
		memVO.setMem_id(mem_id);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_general_gen(mem_general_gen);
		memVO.setMem_general_bd(mem_general_bd);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_email(mem_email);
		memVO.setMem_add(mem_add);
		memVO.setMem_img(mem_img);
		memVO.setMem_per(mem_per);
		memVO.setMem_type(mem_type);
		memVO.setMem_store_business(mem_store_business);
		memVO.setMem_store_owner(mem_store_owner);
		memVO.setMem_store_taxid(mem_store_taxid);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(String mem_no, String mem_name, String mem_id, String mem_psw, Integer mem_general_gen,
			Date mem_general_bd, Integer mem_tel, String mem_email, String mem_add, byte[] mem_img, Integer mem_per,
			Integer mem_type, byte[] mem_store_business, String mem_store_owner, String mem_store_taxid) {
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setMem_name(mem_name);
		memVO.setMem_id(mem_id);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_general_gen(mem_general_gen);
		memVO.setMem_general_bd(mem_general_bd);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_email(mem_email);
		memVO.setMem_add(mem_add);
		memVO.setMem_img(mem_img);
		memVO.setMem_per(mem_per);
		memVO.setMem_type(mem_type);
		memVO.setMem_store_business(mem_store_business);
		memVO.setMem_store_owner(mem_store_owner);
		memVO.setMem_store_taxid(mem_store_taxid);
		dao.update(memVO);

		return memVO;
	}

	public MemVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public MemVO getOneMemByAccount(String mem_id) {
		return dao.findByAccount(mem_id);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemVO> getAllStores() {
		return dao.getAllStores();
	}
	
	public List<MemVO> getAllByStatus(Integer mem_per,Integer mem_type) {
		return dao.getAllByStatus(mem_per, mem_type);
	}
	
	
	public static void main(String[] args) {
		MemService MS = new MemService();
	}
}
