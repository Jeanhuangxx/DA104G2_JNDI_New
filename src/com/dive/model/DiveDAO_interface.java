package com.dive.model;

import java.util.List;

import com.dive.model.DiveVO;

public interface DiveDAO_interface {
    public void insert(DiveVO diveVO);
    public void update(DiveVO diveVO);
    public DiveVO findByPrimaryKey(String dive_no);
//    public DiveVO findByDiveName(String dive_name);
    public List<DiveVO> getAll();
    public List<DiveVO> getListByWeatherArea(String WeatherAreaNo);
    public List<DiveVO> getListByDiveName(String dive_name);
    public List<DiveVO> getAllByStatus(Integer dive_status);
}
