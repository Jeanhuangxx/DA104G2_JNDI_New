package com.weatherArea.model;

import java.util.List;

public class WeatherAreaService {

	private WeatherArea_interface dao;

	public WeatherAreaService() {
		dao = new WeatherAreaJNDIDAO();
	}

//===================================INSERT=======================================

	public WeatherAreaVO addWeatherArea(String weather_area_no, String wt_local) {

		WeatherAreaVO weatherAreaVO = new WeatherAreaVO();
		weatherAreaVO.setWeather_area_no(weather_area_no);
		weatherAreaVO.setWt_local(wt_local);
		dao.insert(weatherAreaVO);

		return weatherAreaVO;
	}
	
//=====================================Update=======================================
	
	public WeatherAreaVO updateweatherArea(String weather_area_no, String wt_local) {

		WeatherAreaVO weatherAreaVO = new WeatherAreaVO();
		weatherAreaVO.setWeather_area_no(weather_area_no);
		weatherAreaVO.setWt_local(wt_local);
		dao.update(weatherAreaVO);

		return weatherAreaVO;
	}


//========================FindOneById=============================================
	public WeatherAreaVO getOneDive(String weather_area_no) {
		return dao.findByPrimaryKey(weather_area_no);
	}

//========================ListAll===================================================
	public List<WeatherAreaVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
//========================測試區(變數自己改)==================================================
//	public static void main(String[] args) {
//
//		DiveLocalService diveService = new DiveLocalService();
//		
//
//		byte[] img=null;
//		try {
//			img = getPictureByteArray("C:\\DA104_WebApp\\eclipse_WTP_workspace1\\DA104G2\\src\\com\\dive\\model\\diving-in-phuket.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		insert
//		diveService.addDive("", "WA00001", "DiveName", "Beautifull Place", 
//				null, 0, "125.0215648",  "25.15646845");
//		update
//		diveService.updateDive("D000002", "WA00001", "DiveName", "Beautifull Place", 
//				null, 0, "125.0215648",  "25.15646845");
//	
//	find
//	System.out.println(diveService.getOneDive("D000001"));
//	ListAll
//	System.out.println(diveService.getAll());
//}
//	
//	insert()呼叫用
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {//Input管子裝水桶(fis.read(buffer))
//			baos.write(buffer, 0, i);//利用Output管子(baos.write(buffer))來連結水桶，輸出到資料庫
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
}
