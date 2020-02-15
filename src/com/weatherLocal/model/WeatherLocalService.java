package com.weatherLocal.model;

import java.sql.Timestamp;
import java.util.List;

public class WeatherLocalService {

	private WeatherLocalDAO_interface dao;

	public WeatherLocalService() {
		dao = new WeatherLocalJNDIDAO();
	}

//===================================INSERT=======================================

	public WeatherLocalVO addDiveLocal(String wt_local, String wt_temp, String wt_watertemp, String wt_rainrate, 
			String wt_waveheight, Timestamp wt_sunrise, Timestamp wt_sundown,  Timestamp wt_time) {

		WeatherLocalVO diveLocalVO = new WeatherLocalVO();
		diveLocalVO.setWt_local(wt_local);
		diveLocalVO.setWt_temp(wt_temp);
		diveLocalVO.setWt_watertemp(wt_watertemp);
		diveLocalVO.setWt_rainrate(wt_rainrate);
		diveLocalVO.setWt_waveheight(wt_waveheight);
		diveLocalVO.setWt_sunrise(wt_sunrise);
		diveLocalVO.setWt_sundown(wt_sundown);
		diveLocalVO.setWt_time(wt_time);
		dao.insert(diveLocalVO);

		return diveLocalVO;
	}
	
//=====================================Update=======================================
	
	public WeatherLocalVO updateDiveLocal(String wt_local, String wt_temp, String wt_watertemp, String wt_rainrate, 
			String wt_waveheight, Timestamp wt_sunrise, Timestamp wt_sundown,  Timestamp wt_time) {
		
		WeatherLocalVO diveLocalVO = new WeatherLocalVO();
		diveLocalVO.setWt_local(wt_local);
		diveLocalVO.setWt_temp(wt_temp);
		diveLocalVO.setWt_watertemp(wt_watertemp);
		diveLocalVO.setWt_rainrate(wt_rainrate);
		diveLocalVO.setWt_waveheight(wt_waveheight);
		diveLocalVO.setWt_sunrise(wt_sunrise);
		diveLocalVO.setWt_sundown(wt_sundown);
		diveLocalVO.setWt_time(wt_time);
		dao.update(diveLocalVO);
		
		return diveLocalVO;
	}


//========================FindOneById=============================================
	public WeatherLocalVO getOneDive(String wt_local) {
		return dao.findByPrimaryKey(wt_local);
	}

//========================ListAll===================================================
	public List<WeatherLocalVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
//========================測試區(變數要自己改)==================================================
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
