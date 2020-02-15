package com.post_reply.model;

import java.sql.Timestamp;
import java.util.List;

public class PostReplyService {
	private PostReplyDAO_interface dao;

	public PostReplyService() {
		dao = new PostReplyDAO();
	}

	public PostReplyVO addPostReport(String mem_no, String post_no, String reply_cont) {
		PostReplyVO postReplyVO = new PostReplyVO();
		postReplyVO.setMem_no(mem_no);
		postReplyVO.setPost_no(post_no);
		postReplyVO.setReply_cont(reply_cont);
		dao.insert(postReplyVO);

		return postReplyVO;
	}

	public PostReplyVO updatePhotoReport(String reply_no, String reply_cont) {
		PostReplyVO postReplyVO = new PostReplyVO();
		postReplyVO.setReply_no(reply_no);
		postReplyVO.setReply_cont(reply_cont);
		dao.update(postReplyVO);

		return postReplyVO;
	}

	public PostReplyVO getOnePhotoReport(String post_re_no) {
		return dao.findByPrimaryKey(post_re_no);
	}

	public List<PostReplyVO> getAll() {
		return dao.getAll();
	}
}
