package com.group_report.model;

import java.util.*;

import com.group_report.model.Group_reportVO;

public interface Group_reportDAO_interface {
	public void insert(Group_reportVO group_reportVO);
    public void update(Group_reportVO group_reportVO);
    public void delete(String groupreportno);
    public Group_reportVO findByPrimaryKey(String groupreportno);
    public List<Group_reportVO> getAll();
}