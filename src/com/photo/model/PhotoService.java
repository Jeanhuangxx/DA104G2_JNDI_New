package com.photo.model;

import java.sql.Timestamp;
import java.util.List;

public class PhotoService {
	private PhotoDAO_interface dao;

	public PhotoService() {
		dao=new PhotoJNDIDAO();
	}
	
	public PhotoVO addPhoto(String album_no, String photo_name, byte[] photo_pic,
			String photo_note, Integer photo_status) {
		PhotoVO photoVO=new PhotoVO();
		photoVO.setAlbum_no(album_no);
		photoVO.setPhoto_name(photo_name);
		photoVO.setPhoto_pic(photo_pic);
		photoVO.setPhoto_note(photo_note);
		photoVO.setPhoto_status(photo_status);
		dao.insert(photoVO);
		
		return photoVO;
	}
	
	public PhotoVO updatePhoto(String photo_no, String photo_name, byte[] photo_pic,
			String photo_note, Integer photo_status) {
		PhotoVO photoVO=new PhotoVO();
		photoVO.setPhoto_no(photo_no);
		photoVO.setPhoto_name(photo_name);
		photoVO.setPhoto_pic(photo_pic);
		photoVO.setPhoto_note(photo_note);
		photoVO.setPhoto_status(photo_status);
		dao.update(photoVO);
		
		return photoVO;
	}
	
	public PhotoVO getOnePhoto(String photo_no) {
		return dao.findByPrimaryKey(photo_no);
	}
	
	public List<PhotoVO> getAll(){
		return dao.getAll();
	}
	
	public List<PhotoVO> getAll(String album_no){
		return dao.getAll(album_no);
	}
}
