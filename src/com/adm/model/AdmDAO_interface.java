package com.adm.model;

import java.util.*;

public interface AdmDAO_interface {
	public void insert(AdmVO admVO);
    public void update(AdmVO admVO);
    public void delete(String adm_no);
    public AdmVO findByPrimaryKey(String adm_no);
    public AdmVO findByAdmId(String adm_id);
    public List<AdmVO> getAll();

}

