package com.group_join.model;

import java.util.*;
import com.group_join.model.Group_joinVO;

public interface Group_joinDAO_interface {
	public void insert(Group_joinVO group_joinVO);
	public void update(Group_joinVO group_joinVO);
	public Group_joinVO findByPrimaryKey(String groupno, String memno);
	public List<Group_joinVO> getAll();
	public List<Group_joinVO> findByGroup_no(String groupno);
	public List<Group_joinVO> findByMemno(String memno);
	public void delete(String groupno, String memno);
}
