package com.group_message.model;

import java.util.*;
import com.group_message.model.Group_messageVO;

public interface Group_messageDAO_interface {
	public void insert(Group_messageVO group_messageVO);
	public void update(Group_messageVO group_messageVO);
	public void delete(String groupmeno);
	public Group_messageVO findByPrimaryKey(String groupmeno);
	public List<Group_messageVO> getAll();
	public List<Group_messageVO> getOneForGroup(String groupno);
}
