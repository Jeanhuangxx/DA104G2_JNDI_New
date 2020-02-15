package com.album.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumService {
	private AlbumDAO_interface dao;

	public AlbumService() {
		dao = new AlbumJNDIDAO();
	}

	public AlbumVO addAlbum(String mem_no, String album_name, byte[] album_pic, String album_note,Integer album_status) {
		AlbumVO albumVO = new AlbumVO();
		albumVO.setMem_no(mem_no);
		albumVO.setAlbum_name(album_name);
		albumVO.setAlbum_pic(album_pic);
		albumVO.setAlbum_note(album_note);
		albumVO.setAlbum_status(album_status);
		dao.insert(albumVO);

		return albumVO;
	}

	public AlbumVO updateAlbum(String album_no, String album_name, byte[] album_pic, String album_note,Integer album_status) {
		AlbumVO albumVO = new AlbumVO();
		albumVO.setAlbum_no(album_no);
		albumVO.setAlbum_name(album_name);
		albumVO.setAlbum_pic(album_pic);
		albumVO.setAlbum_note(album_note);
		albumVO.setAlbum_status(album_status);
		dao.update(albumVO);

		return albumVO;
	}
	
	public AlbumVO getOneAlbum(String album_no) {
		return dao.findByPrimaryKey(album_no);
	}
	
	public List<AlbumVO> getAll(){
		return dao.getAll();
	}
	
	public List<AlbumVO> getByStatus(){
		return dao.getAll().stream()
				.filter(albumVO->albumVO.getAlbum_status()==0)
				.collect(Collectors.toList());
	}

	public List<AlbumVO> getByMem(String mem_no){
		return dao.getAll().stream()
				.filter(albumVO->albumVO.getMem_no().equals((String)mem_no))
				.filter(albumVO->albumVO.getAlbum_status()==0)
				.collect(Collectors.toList());
	}
	
	
}
