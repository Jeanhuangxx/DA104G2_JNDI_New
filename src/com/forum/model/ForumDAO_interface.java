package com.forum.model;

import java.util.List;

public interface ForumDAO_interface {
	public void insert(ForumVO forumVO);
	public void update(ForumVO forumVO);
	public void delete(String post_no);
	public ForumVO findByPrimaryKey(String post_no);
	public List<ForumVO>getAll();
}
