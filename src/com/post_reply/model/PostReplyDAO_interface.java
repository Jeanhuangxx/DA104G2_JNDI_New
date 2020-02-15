package com.post_reply.model;

import java.util.List;


public interface PostReplyDAO_interface {
	public void insert(PostReplyVO postReplyVO );
	public void update(PostReplyVO postReplyVO);
	public void delete(String reply_no);
	public PostReplyVO findByPrimaryKey(String reply_no);
	public List<PostReplyVO>getAll();
}
