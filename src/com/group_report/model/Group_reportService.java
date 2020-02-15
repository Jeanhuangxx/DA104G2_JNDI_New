package com.group_report.model;

import java.util.List;


public class Group_reportService {
	private Group_reportDAO_interface dao;

	public Group_reportService() {
		dao = new Group_reportJNDIDAO();
	}

	public Group_reportVO addGroup_report(String group_no, String mem_no, String group_re_note) {
		Group_reportVO group_reportVO = new Group_reportVO();

		group_reportVO.setGroup_no(group_no);
		group_reportVO.setMem_no(mem_no);
		group_reportVO.setGroup_re_note(group_re_note);
		dao.insert(group_reportVO);
		return group_reportVO;
	}

	public Group_reportVO updateGroup_report(String group_re_no, String group_no, String mem_no,
			String group_re_note, Integer group_re_status) {
		Group_reportVO group_reportVO = new Group_reportVO();

		group_reportVO.setGroup_re_no(group_re_no);
		group_reportVO.setGroup_no(group_no);
		group_reportVO.setMem_no(mem_no);
		group_reportVO.setGroup_re_note(group_re_note);
		group_reportVO.setGroup_re_status(group_re_status);
		dao.update(group_reportVO);
		return group_reportVO;
	}

	public void deleteGroup_report(String group_re_no) {
		dao.delete(group_re_no);
	}

	public Group_reportVO getOneGroup_report(String group_re_no) {
		return dao.findByPrimaryKey(group_re_no);
	}

	public List<Group_reportVO> getAll() {
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		Group_reportService gr = new Group_reportService();

		String group_re_no = "GR00001";
		String group_no = "G000004";
		String mem_no = "M000002";
		String group_re_note = "檢舉檢舉要檢舉";
		Integer group_re_status = 2;
		

		//updateGroup_report
		gr.updateGroup_report(group_re_no, group_no, mem_no, group_re_note, group_re_status);
		
		// insert
//		gr.addGroup_report(group_no, mem_no, group_re_note);
		
		
		// update
//		gr.updateGroup_report(group_re_no, group_no, mem_no, group_re_note, group_re_status);

		// 查詢
//		Group_reportVO groupVO3 = gr.getOneGroup_report(group_re_no);
//
//		System.out.println(groupVO3.getGroup_re_no() + ",");
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_re_note() + ",");
//		System.out.println(groupVO3.getGroup_re_status() + ",");
//		System.out.println("---------------------");

		// 查詢全部
//		List<Group_reportVO> list = gr.getAll();
//		for (Group_reportVO aGroup : list) {
//			System.out.println(aGroup.getGroup_re_no() + ",");
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_re_note() + ",");
//			System.out.println(aGroup.getGroup_re_status() + ",");
//			System.out.println();
//		}
		// 刪除
//				gr.deleteGroup_report(group_re_no);
	}
	
}
