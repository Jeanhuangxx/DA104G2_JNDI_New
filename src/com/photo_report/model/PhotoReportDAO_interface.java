package com.photo_report.model;

import java.util.List;

public interface PhotoReportDAO_interface {
	public void insert(PhotoReportVO photoReportVO);

	public void update(PhotoReportVO photoReportVO);

	public void delete(String photo_re_no);

	public PhotoReportVO findByPrimaryKey(String photo_re_no);

	public List<PhotoReportVO> getAll();

	public List<PhotoReportVO> findByStatus(Integer photo_re_status);

	public List<PhotoReportVO> findByPhoto(String photo_no);
}
