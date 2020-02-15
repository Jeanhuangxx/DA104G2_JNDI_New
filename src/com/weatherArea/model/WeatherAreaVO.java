package com.weatherArea.model;


public class WeatherAreaVO implements java.io.Serializable {
	private String weather_area_no;
	private String wt_local;
	
	public String getWeather_area_no() {
		return weather_area_no;
	}
	public void setWeather_area_no(String weather_area_no) {
		this.weather_area_no = weather_area_no;
	}
	public String getWt_local() {
		return wt_local;
	}
	public void setWt_local(String wt_local) {
		this.wt_local = wt_local;
	}
	@Override
	public String toString() {
		return "WeatherAreaVO [weather_area_no=" + weather_area_no + ", wt_local=" + wt_local + "]";
	}
	
	
	
}
