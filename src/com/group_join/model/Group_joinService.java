package com.group_join.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import com.group.model.*;
import com.group_join.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.util.MailService;

public class Group_joinService {
	private Group_joinDAO_interface dao;

	public Group_joinService() {
		dao = new Group_joinJNDIDAO();
	}

	public Group_joinVO addGroup_join(String group_no, String mem_no, Integer group_join_status) {
		Group_joinVO group_joinVO = new Group_joinVO();

		group_joinVO.setGroup_no(group_no);
		group_joinVO.setMem_no(mem_no);
		group_joinVO.setGroup_jo_status(group_join_status);
		dao.insert(group_joinVO);
		return group_joinVO;
	}

	public Group_joinVO updateGroup_join(String group_no, String mem_no, Integer group_jo_status,
			Integer group_qr_status) {
		Group_joinVO group_joinVO = new Group_joinVO();

		group_joinVO.setGroup_no(group_no);
		group_joinVO.setMem_no(mem_no);
		group_joinVO.setGroup_jo_status(group_jo_status);
		group_joinVO.setGroup_qr_status(group_qr_status);
		dao.update(group_joinVO);

		// 當審核通過立即判斷是否人數已達上限並更改揪團狀態
		if (group_jo_status == 1) {
			GroupService groupSvc = new GroupService();
			GroupVO groupVO = groupSvc.getOneGroup(group_no);
			Integer group_status = groupVO.getGroup_status();
			Integer group_tour_num = groupVO.getGroup_tour_num();
			Integer group_max_num = groupVO.getGroup_max_num();

			if (group_max_num == group_tour_num + 1) {//判斷是否已達上限人數
				groupSvc.updateStatus(group_no, 1, groupVO.getGroup_tour_num() + 1);
				//確認報名已達上限，更改為其餘未審核者狀態
				Group_joinService group_joinSvc = new Group_joinService();
				List<Group_joinVO> listJ = group_joinSvc.getOneGroup(groupVO.getGroup_no());				
				for(int i = 0; i<listJ.size(); i++) {
					Group_joinVO gj = listJ.get(i);
					if(gj.getGroup_jo_status()==0) {
						group_joinSvc.updateGroup_join(group_no, gj.getMem_no(), 2, 0);	
						MemService memSvc = new MemService();
						MailService mailSvc = new MailService();
						MemVO memVO = memSvc.getOneMem(gj.getMem_no());
						mailSvc.sendMail(memVO.getMem_email(), "潛進台灣-報名揪團審核信", "審核不通過，謝謝您的報名。");
					}
				}
			} else if(group_max_num > group_tour_num + 1){
				groupSvc.updateStatus(group_no, group_status, groupVO.getGroup_tour_num() + 1);//更改參團人數
			}
		}

		return group_joinVO;
	}

	public Group_joinVO getOneGroup_join(String group_no, String mem_no) {
		return dao.findByPrimaryKey(group_no, mem_no);
	}

	public List<Group_joinVO> getAll() {
		return dao.getAll();
	}

	public List<Group_joinVO> getOneGroup(String groupno) {
		return dao.findByGroup_no(groupno);
	}

	public List<Group_joinVO> getAllForMem(String mem_no) {
		return dao.findByMemno(mem_no);
	}

	public List<Group_joinVO> getAllChecking(String mem_no) {
		List<Group_joinVO> list = dao.findByMemno(mem_no);
		GroupService groupSvc = new GroupService();
		GroupVO groupVO=null;
		for (int i = 0; i < list.size(); i++) {
			Group_joinVO gj = list.get(i);
			groupVO = groupSvc.getOneGroup(gj.getGroup_no());
			long long_now = new java.util.Date().getTime();
			long group_tour_stop_time = groupVO.getGroup_tour_stop_time().getTime();
				if (mem_no.equals(groupVO.getMem_no()) || gj.getGroup_jo_status() == 2 || groupVO.getGroup_status() != 0 || long_now > group_tour_stop_time) {
					list.remove(gj);
					i--;
				}
			
		}
		return list;
	}

//	public List<Group_joinVO> getAllProcessing(String mem_no) {
//		List<Group_joinVO> list = dao.findByMemno(mem_no);
//		GroupService groupSvc = new GroupService();
//
//		for (int i = 0; i < list.size(); i++) {
//			Group_joinVO gj = list.get(i);
//			GroupVO groupVO = groupSvc.getOneGroup(gj.getGroup_no());
//			long long_now = new java.util.Date().getTime();
//			long group_tour_stop_time = groupVO.getGroup_tour_stop_time().getTime();
//			if (gj.getGroup_jo_status() != 1 || groupVO.getGroup_status() != 0 || long_now > group_tour_stop_time) {
//				list.remove(gj);
//				i--;
//			}
//		}
//		return list;
//	}

	public List<Group_joinVO> getAllHistory(String mem_no) {
		List<Group_joinVO> list = dao.findByMemno(mem_no);
		GroupService groupSvc = new GroupService();

		for (int i = 0; i < list.size(); i++) {
			Group_joinVO gj = list.get(i);
			GroupVO groupVO = groupSvc.getOneGroup(gj.getGroup_no());
			if (gj.getGroup_jo_status() != 1 || groupVO.getGroup_status() == 0) {
				list.remove(gj);
				i--;
			}
		}
		return list;
	}

	public List<Group_joinVO> getAllFail(String mem_no) {
		List<Group_joinVO> list = dao.findByMemno(mem_no);
		GroupService groupSvc = new GroupService();

		for (int i = 0; i < list.size(); i++) {
			Group_joinVO gj = list.get(i);
			GroupVO groupVO = groupSvc.getOneGroup(gj.getGroup_no());
			if (gj.getGroup_jo_status() != 2 || groupVO.getGroup_status() != 3 || groupVO.getGroup_status() != 4) {
				list.remove(gj);
				i--;
			}
		}
		return list;
	}

	public void delete(String group_no, String mem_no) {
		dao.delete(group_no, mem_no);
	}

	public static void main(String[] args) {
		Group_joinService gr = new Group_joinService();
		Group_joinVO group_joinVO1 = new Group_joinVO();

		String group_no = "G000002";
		String mem_no = "M000001";
		Integer group_jo_status = 1;
		Integer group_qr_status = 0;
//	// insert

//		gr.addGroup_join(group_no, mem_no);

		// update
//		gr.updateGroup_join(group_no, mem_no, group_jo_status, group_qr_status);

		// 查詢
//		Group_joinVO groupVO3 = gr.getOneGroup_join(group_no, mem_no);
//
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_jo_status() + ",");
//		System.out.println(groupVO3.getGroup_qr_status() + ",");
//		
//		System.out.println("---------------------");

		// 查詢全部
//		List<Group_joinVO> list = gr.getAll();
//		for (Group_joinVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_jo_status() + ",");
//			System.out.println(aGroup.getGroup_qr_status() + ",");
//			System.out.println();
//		}
//
		//
//		List<Group_joinVO> list = gr.getOneGroup(group_n);
//		for (Group_joinVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_jo_status() + ",");
//			System.out.println(aGroup.getGroup_qr_status() + ",");
//			System.out.println();
//		}

//		刪除
//		gr.delete(group_no, mem_no);

//		List<Group_joinVO> list = gr.getAllHistory(mem_no);
//		for (Group_joinVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_jo_status() + ",");
//			System.out.println(aGroup.getGroup_qr_status() + ",");
//			System.out.println();
//		}

		List<Group_joinVO> list = gr.getAllFail(mem_no);
		for (Group_joinVO aGroup : list) {
			System.out.println(aGroup.getGroup_no() + ",");
			System.out.println(aGroup.getMem_no() + ",");
			System.out.println(aGroup.getGroup_jo_status() + ",");
			System.out.println(aGroup.getGroup_qr_status() + ",");
			System.out.println();
		}

	}

}
