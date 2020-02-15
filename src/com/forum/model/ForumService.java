package com.forum.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumService {
	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}

	public ForumVO addForum(String men_no, String post_title, String post_cont, Integer post_status) {
		ForumVO forumVO = new ForumVO();
		forumVO.setMen_no(men_no);
		forumVO.setPost_title(post_title);
		forumVO.setPost_cont(post_cont);
		forumVO.setPost_status(post_status);
		dao.insert(forumVO);

		return forumVO;
	}

	public ForumVO updateForum(String post_title, String post_cont, Integer post_status, String post_no) {
		ForumVO forumVO = new ForumVO();
		forumVO.setPost_title(post_title);
		forumVO.setPost_cont(post_cont);
		forumVO.setPost_status(post_status);
		forumVO.setPost_no(post_no);
		dao.update(forumVO);

		return forumVO;
	}

	public ForumVO getOneForum(String post_no) {
		return dao.findByPrimaryKey(post_no);
	}

	public List<ForumVO> getAll() {
		return dao.getAll();
	}
}
