package com.weatherLocal.model;

import java.util.List;

import com.dive.model.DiveVO;

public interface WeatherLocalDAO_interface {
    public void insert(WeatherLocalVO diveLocalVO);
    public void update(WeatherLocalVO diveLocalVO);
    public WeatherLocalVO findByPrimaryKey(String wt_local);
    public List<WeatherLocalVO> getAll();
}
