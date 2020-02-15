package com.post_report.model;

import java.sql.Timestamp;
import java.util.List;

public class PostReportService {
	private PostReportDAO_interface dao;

	public PostReportService() {
		dao = new PostReportDAO();
	}

	public PostReportVO addPostReport(String mem_no, String post_no, String post_re_note, Integer post_re_status) {
		PostReportVO postReportVO = new PostReportVO();
		postReportVO.setMem_no(mem_no);
		postReportVO.setPost_no(post_no);
		postReportVO.setPost_re_note(post_re_note);
		postReportVO.setPost_re_status(post_re_status);
		dao.insert(postReportVO);

		return postReportVO;
	}

	public PostReportVO updatePostReport(String post_re_no, String post_re_note, Timestamp post_re_time,
			Integer post_re_status) {
		PostReportVO postReportVO = new PostReportVO();
		postReportVO.setPost_re_no(post_re_no);
		postReportVO.setPost_re_time(post_re_time);
		postReportVO.setPost_re_note(post_re_note);
		postReportVO.setPost_re_status(post_re_status);
		dao.update(postReportVO);
		return postReportVO;
	}

	public PostReportVO getOnePostReport(String post_re_no) {
		return dao.findByPrimaryKey(post_re_no);
	}

	public List<PostReportVO> getAll() {
		return dao.getAll();
	}

}
