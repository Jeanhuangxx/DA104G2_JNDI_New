package com.group.model;

import java.util.*;

import com.dive.model.DiveVO;
import com.group.model.GroupVO;

public interface GroupDAO_interface {
	public void insert(GroupVO groupVO);
    public void update(GroupVO groupVO);
    public void updateStatus(GroupVO groupVO);
    public void delete(String groupno);
    public GroupVO findByPrimaryKey(String groupno);
    public List<GroupVO> getAll();
    public List<GroupVO> getAll_Mem(String memno);
    public List<GroupVO> getAll_Dive(String diveno);
    public List<GroupVO> getKeyWord(String word);
    public List<DiveVO> getKeyWordDive(String word);
}
