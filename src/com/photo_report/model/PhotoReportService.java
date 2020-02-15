package com.photo_report.model;

import java.sql.Timestamp;
import java.util.List;

public class PhotoReportService {
	private PhotoReportDAO_interface dao;

	public PhotoReportService() {
		dao = new PhotoReportJNDIDAO();
	}

	public PhotoReportVO addPhotoReport(String photo_no, String mem_no, String photo_re_note, Integer photo_re_status) {
		PhotoReportVO photoReportVO = new PhotoReportVO();
		photoReportVO.setPhoto_no(photo_no);
		photoReportVO.setMem_no(mem_no);
		photoReportVO.setPhoto_re_note(photo_re_note);
		photoReportVO.setPhoto_re_status(photo_re_status);
		dao.insert(photoReportVO);

		return photoReportVO;
	}

	public PhotoReportVO updatePhotoReport(String photo_re_no, Timestamp photo_re_time,  String photo_re_note, Integer photo_re_status) {
		PhotoReportVO photoReportVO = new PhotoReportVO();
		photoReportVO.setPhoto_re_no(photo_re_no);
		photoReportVO.setPhoto_re_time(photo_re_time);
		photoReportVO.setPhoto_re_note(photo_re_note);
		photoReportVO.setPhoto_re_status(photo_re_status);
		dao.update(photoReportVO);

		return photoReportVO;
	}

	public PhotoReportVO getOnePhotoReport(String photo_re_no) {
		return dao.findByPrimaryKey(photo_re_no);
	}

	public List<PhotoReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<PhotoReportVO> getByStatus(Integer photo_re_status){
		return dao.findByStatus(photo_re_status);
	}
	
	public List<PhotoReportVO> getByPhoto(String photo_no){
		return dao.findByPhoto(photo_no);
	}
}
