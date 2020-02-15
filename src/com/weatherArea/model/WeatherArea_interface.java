package com.weatherArea.model;

import java.util.List;


public interface WeatherArea_interface {
    public void insert(WeatherAreaVO weatherAreaVO);
    public void update(WeatherAreaVO weatherAreaVO);
    public WeatherAreaVO findByPrimaryKey(String weather_area_no);
    public List<WeatherAreaVO> getAll();
}
