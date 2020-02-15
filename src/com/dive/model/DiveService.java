package com.dive.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DiveService {

	private DiveDAO_interface dao;

	public DiveService() {
		dao = new DiveJNDIDAO();
	}

//===================================INSERT=======================================

	public DiveVO addDive(String weather_area_no, String dive_name, String dive_des, byte[] dive_img,
			Integer dive_status, String dive_lat, String dive_lang) {

		DiveVO diveVO = new DiveVO();
		diveVO.setWeather_area_no(weather_area_no);
		diveVO.setDive_name(dive_name);
		diveVO.setDive_des(dive_des);
		diveVO.setDive_img(dive_img);
		diveVO.setDive_status(dive_status);
		diveVO.setDive_lat(dive_lat);
		diveVO.setDive_lang(dive_lang);
		dao.insert(diveVO);

		return diveVO;
	}

//=====================================Update=======================================

	public DiveVO updateDive(String dive_no, String weather_area_no, String dive_name, String dive_des, byte[] dive_img,
			Integer dive_status, String dive_lat, String dive_lang) {

		DiveVO diveVO = new DiveVO();
		diveVO.setDive_no(dive_no);
		diveVO.setWeather_area_no(weather_area_no);
		diveVO.setDive_name(dive_name);
		diveVO.setDive_des(dive_des);
		diveVO.setDive_img(dive_img);
		diveVO.setDive_status(dive_status);
		diveVO.setDive_lat(dive_lat);
		diveVO.setDive_lang(dive_lang);
		dao.update(diveVO);

		return diveVO;
	}

//========================FindOneById=============================================
	public DiveVO getOneDive(String DIVE_NO) {
		return dao.findByPrimaryKey(DIVE_NO);
	}

//========================ListAll===================================================
	public List<DiveVO> getAll() {
		return dao.getAll();
	}

//========================ListAllByWeatherArea===================================================
	public List<DiveVO> getAllByWeatherArea(String WeatherAreaNo) {
		return dao.getListByWeatherArea(WeatherAreaNo);
	}

//========================ListAllByName===================================================
	public List<DiveVO> getListByDiveName(String dive_name) {
		return dao.getListByDiveName(dive_name);
	}

// ========================ListAllByStatus===================================================
	public List<DiveVO> getAllByStatus(Integer dive_status) {
		return dao.getAllByStatus(dive_status);
	}

//========================測試區==================================================
	public static void main(String[] args) {

		DiveService diveService = new DiveService();

//		byte[] img=null;
//		try {
//			img = getPictureByteArray("C:\\DA104_WebApp\\eclipse_WTP_workspace1\\DA104G2\\src\\com\\dive\\model\\diving-in-phuket.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		insert
//		diveService.addDive("", "WA00001", "DiveName", "Beautifull Place", 
//				img, 0, "125.0215648",  "25.15646845");
//		update
//		diveService.updateDive("D000002", "WA00001", "DiveName", "Beautifull Place", 
//				img, 0, "125.0215648",  "25.15646845");
//	
		// find
//	System.out.println(diveService.getOneDive("D000001"));
		// ListAll
//	System.out.println(diveService.getAll());
//	System.out.println(diveService.getAllByWeatherArea("WA00002"));
		System.out.println(diveService.getListByDiveName("DiveName"));
	}

	// insert()呼叫用
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {// Input管子裝水桶(fis.read(buffer))
			baos.write(buffer, 0, i);// 利用Output管子(baos.write(buffer))來連結水桶，輸出到資料庫
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
