package com.pe.model;

import java.util.List;

public class PeService {

	private PeDAO_interface dao;

	public PeService() {
		dao = new PeJNDIDAO();
	}
	
	public PeVO addPe(String adm_no, String fun_no) {

		PeVO peVO = new PeVO();

		peVO.setAdm_no(adm_no);
		peVO.setFun_no(fun_no);
		
		dao.insert(peVO);

		return peVO;
	}

	public PeVO updatePe(String adm_no, String fun_no) {

		PeVO peVO = new PeVO();

		peVO.setAdm_no(adm_no);
		peVO.setFun_no(fun_no);
		dao.update(peVO);

		return peVO;
	}

	public void deletePe(String adm_no) {
		dao.delete(adm_no);
	}

	public List<PeVO> getOnePe(String adm_no) {
		return dao.findByPrimaryKey(adm_no);
	}

	public List<PeVO> getAll() {
		return dao.getAll();
	}
}
