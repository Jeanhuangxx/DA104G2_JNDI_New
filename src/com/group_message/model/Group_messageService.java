package com.group_message.model;

import java.util.List;

import com.group.model.GroupVO;

public class Group_messageService {
	private Group_messageDAO_interface dao;

	public Group_messageService() {
		dao = new Group_messageJNDIDAO();
	}

	public Group_messageVO addGroup_message(String group_no, String mem_no, String group_me_note) {
		Group_messageVO group_messageVO = new Group_messageVO();

		group_messageVO.setGroup_no(group_no);
		group_messageVO.setMem_no(mem_no);
		group_messageVO.setGroup_me_note(group_me_note);
		dao.insert(group_messageVO);
		return group_messageVO;
	}

	public Group_messageVO updateGroup_message(String group_me_no, String group_no, String mem_no,
			String group_me_note) {
		Group_messageVO group_messageVO = new Group_messageVO();

		group_messageVO.setGroup_me_no(group_me_no);
		group_messageVO.setGroup_no(group_no);
		group_messageVO.setMem_no(mem_no);
		group_messageVO.setGroup_me_note(group_me_note);
		dao.update(group_messageVO);
		return group_messageVO;
	}

	public void deleteGroup_message(String group_me_no) {
		dao.delete(group_me_no);
	}

	public Group_messageVO getOneGroup_message(String group_me_no) {
		return dao.findByPrimaryKey(group_me_no);
	}
	
	public List<Group_messageVO> getAll() {
		return dao.getAll();
	}
	
	public List<Group_messageVO> getOneForGroup(String group_no) {
		return dao.getOneForGroup(group_no);
	}

	public static void main(String[] args) {
		Group_messageService gm = new Group_messageService();

		String group_me_no = "GM00004";
		String group_no = "G000003";
		String mem_no = "M000003";
		String group_me_note = "你好啊~";
		
		//新增
//		gm.addGroup_message(group_no, mem_no, group_me_note);
		
		//刪除
//		gm.deleteGroup_message(group_me_no);
		
		//修改
//		gm.updateGroup_message(group_me_no, group_no, mem_no, group_me_note);
		
		//查一筆
//		Group_messageVO groupVO3 = gm.getOneGroup_message("GM00002");
//
//		System.out.println(groupVO3.getGroup_me_no() + ",");
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_me_note() + ",");
//
//		System.out.println("---------------------");
		
		
		
		//查全部
//		List<Group_messageVO> list = gm.getAll();
//		for (Group_messageVO aGroup : list) {
//			System.out.println(aGroup.getGroup_me_no() + ",");
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_me_note() + ",");
//			System.out.println();
//		}
		
		//查某一揪團留言
		List<Group_messageVO> list = gm.getOneForGroup("G000002");
		for (Group_messageVO aGroup : list) {
			System.out.println(aGroup.getGroup_me_no() + ",");
			System.out.println(aGroup.getGroup_no() + ",");
			System.out.println(aGroup.getMem_no() + ",");
			System.out.println(aGroup.getGroup_me_note() + ",");
			System.out.println();
		}
		
	}
}
