package com.photo.model;

import java.sql.Timestamp;
import java.util.List;

public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO);

	public void update(PhotoVO photoVO);

	public void delete(String photo_no);

	public PhotoVO findByPrimaryKey(String photo_no);

	public List<PhotoVO> getAll();
	
	public List<PhotoVO> getAll(String album_no);
}
