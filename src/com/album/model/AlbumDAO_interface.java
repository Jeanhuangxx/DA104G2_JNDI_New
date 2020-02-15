package com.album.model;

import java.sql.Timestamp;
import java.util.List;

public interface AlbumDAO_interface {
public void insert(AlbumVO albumVO);
public void update(AlbumVO albumVO);
public void delete(String album_no);
public AlbumVO findByPrimaryKey(String album_no);
public List<AlbumVO> getAll();


}
