package com.post_report.model;

import java.util.List;

public interface PostReportDAO_interface {
	public void insert(PostReportVO postReportVO);

	public void update(PostReportVO postReportVO);

	public void delete(String post_re_no);

	public PostReportVO findByPrimaryKey(String post_re_no);

	public List<PostReportVO> getAll();
}
