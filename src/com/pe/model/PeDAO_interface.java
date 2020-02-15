package com.pe.model;

import java.util.*;

public interface PeDAO_interface {
	public void insert(PeVO peVO);
    public void update(PeVO peVO);
    public void delete(String adm_no);
    public List<PeVO> findByPrimaryKey(String adm_no);
    public List<PeVO> getAll();
}
