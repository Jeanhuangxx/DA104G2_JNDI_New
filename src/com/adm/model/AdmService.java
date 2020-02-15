package com.adm.model;

import java.util.List;

public class AdmService {
	
	private AdmDAO_interface dao;

	public AdmService() {
		dao = new AdmJNDIDAO();
	}

	public AdmVO addAdm(String adm_id, String adm_psw, String adm_name, Integer adm_tel,
			String adm_email, byte[] adm_img) {

		AdmVO admVO = new AdmVO();

		admVO.setAdm_id(adm_id);
		admVO.setAdm_psw(adm_psw);
		admVO.setAdm_name(adm_name);
		admVO.setAdm_tel(adm_tel);
		admVO.setAdm_email(adm_email);
		admVO.setAdm_img(adm_img);
		dao.insert(admVO);

		return admVO;
	}

	public AdmVO updateAdm(String adm_no, String adm_id, String adm_psw, String adm_name, Integer adm_tel,
			String adm_email, byte[] adm_img) {

		AdmVO admVO = new AdmVO();

		admVO.setAdm_no(adm_no);
		admVO.setAdm_id(adm_id);
		admVO.setAdm_psw(adm_psw);
		admVO.setAdm_name(adm_name);
		admVO.setAdm_tel(adm_tel);
		admVO.setAdm_email(adm_email);
		admVO.setAdm_img(adm_img);
		dao.update(admVO);

		return admVO;
	}

	public void deleteAdm(String adm_no) {
		dao.delete(adm_no);
	}

	public AdmVO getOneAdm(String adm_no) {
		return dao.findByPrimaryKey(adm_no);
	}

	public AdmVO getOneAdmId(String adm_id) {
		return dao.findByAdmId(adm_id);
	}
	
	public List<AdmVO> getAll() {
		return dao.getAll();
	}
}

