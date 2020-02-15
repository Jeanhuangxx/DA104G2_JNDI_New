package com.fun.model;

import java.util.*;

public interface FunDAO_interface {
	 public void insert(FunVO funVO);
     public void update(FunVO funVO);
     public void delete(String fun_no);
     public FunVO findByPrimaryKey(String fun_no);
     public List<FunVO> getAll();
}
