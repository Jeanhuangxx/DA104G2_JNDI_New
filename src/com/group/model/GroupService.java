package com.group.model;

import java.util.List;

import com.dive.model.DiveVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class GroupService {

	private GroupDAO_interface dao;

	public GroupService() {
		dao = new GroupJNDIDAO();
	}

	public GroupVO addGroup(String mem_no, String dive_no, String group_name, Timestamp Group_tour_stop_time,
			Timestamp Group_begin_time, Timestamp Group_end_time, Integer group_max_num, String group_des,
			String group_tel, String group_mp, byte[] group_photo) {

		GroupVO groupVO = new GroupVO();

		groupVO.setMem_no(mem_no);
		groupVO.setDive_no(dive_no);
		groupVO.setGroup_name(group_name);
		groupVO.setGroup_tour_stop_time(Group_tour_stop_time);
		groupVO.setGroup_begin_time(Group_begin_time);
		groupVO.setGroup_end_time(Group_end_time);
		groupVO.setGroup_max_num(group_max_num);
		groupVO.setGroup_des(group_des);
		groupVO.setGroup_tel(group_tel);
		groupVO.setGroup_mp(group_mp);
		groupVO.setGroup_photo(group_photo);// 處理圖片
		dao.insert(groupVO);

		return groupVO;
	}

	public GroupVO updateGroup(String group_no, String dive_no, String group_name, Timestamp Group_tour_stop_time,
			Timestamp Group_begin_time, Timestamp Group_end_time, Integer group_max_num, String group_des,
			String group_tel, String group_mp, byte[] group_photo) {

		GroupVO groupVO = new GroupVO();

		groupVO.setGroup_no(group_no);
		groupVO.setDive_no(dive_no);
		groupVO.setGroup_name(group_name);
		groupVO.setGroup_tour_stop_time(Group_tour_stop_time);
		groupVO.setGroup_begin_time(Group_begin_time);
		groupVO.setGroup_end_time(Group_end_time);
		groupVO.setGroup_max_num(group_max_num);
		groupVO.setGroup_des(group_des);
		groupVO.setGroup_tel(group_tel);
		groupVO.setGroup_mp(group_mp);
		groupVO.setGroup_photo(group_photo);// 處理圖片
		dao.update(groupVO);

		return groupVO;
	}

	public GroupVO updateStatus(String group_no, Integer group_status, Integer group_tour_num) {

		GroupVO groupVO = new GroupVO();

		groupVO.setGroup_no(group_no);
		groupVO.setGroup_status(group_status);
		groupVO.setGroup_tour_num(group_tour_num);

		dao.updateStatus(groupVO);

		return groupVO;
	}

	public void deleteGroup(String group_no) {
		dao.delete(group_no);
	}

	public GroupVO getOneGroup(String group_no) {
		return dao.findByPrimaryKey(group_no);
	}

	public List<GroupVO> getAll() {
		return dao.getAll();
	}

	public List<GroupVO> getAll_Mem(String mem_no) {
		return dao.getAll_Mem(mem_no);
	}

	public List<GroupVO> getAll_Dive(String dive_no) {
		return dao.getAll_Dive(dive_no);
	}
	
	public List<GroupVO> getKeyWord(String word){
		return dao.getKeyWord(word);			
	}
	
	public List<DiveVO> getKeyWordDive(String word){
		return dao.getKeyWordDive(word);			
	}
	
	

//	public static void main(String[] args) throws IOException {
//		String dive_no = "D000001";
//		String group_name = "潛進香港";
//		Timestamp group_tour_stop_time = java.sql.Timestamp.valueOf("2002-01-01 13:13:13");
//		Timestamp group_begin_time = java.sql.Timestamp.valueOf("2002-01-01 13:13:13");
//		Timestamp group_sop_stop_time = java.sql.Timestamp.valueOf("2002-01-01 13:13:13");
//		Timestamp group_end_time = java.sql.Timestamp.valueOf("2002-01-01 13:13:13");
//		Integer group_max_num = 10;
//		String group_des = "一起來潛水~";
//		String group_tel = "0988205866";
//		String group_mp = "香港";
//		String group_no = "G000001";
//		byte[] group_photo = getPictureByteArray("C:/Users/Java/Downloads/丞.jpg");
//
//		GroupService gs = new GroupService();
//		gs.updateGroup(group_no, dive_no, group_name, group_tour_stop_time, group_begin_time, group_end_time, group_max_num, group_des, group_tel, group_mp, group_photo);
//	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}
}
