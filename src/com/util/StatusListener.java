package com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class StatusListener implements ServletContextListener {

	public StatusListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		Map<Integer, String> memPer = new HashMap<Integer, String>();
		memPer.put(0, "正常");
		memPer.put(1, "停權");
		application.setAttribute("memPer", memPer);

		Map<Integer, String> memGenderStatus = new HashMap<Integer, String>();
		memGenderStatus.put(0, "不表示");
		memGenderStatus.put(1, "男生");
		memGenderStatus.put(2, "女生");
		application.setAttribute("memGenderStatus", memGenderStatus);

		Map<Integer, String> memTypeStatus = new HashMap<Integer, String>();
		memTypeStatus.put(0, "一般會員");
		memTypeStatus.put(1, "潛水店家");
		application.setAttribute("memTypeStatus", memTypeStatus);

		Map<Integer, String> albumStatus = new HashMap<Integer, String>();
		albumStatus.put(0, "顯示");
		albumStatus.put(1, "隱藏");
		application.setAttribute("albumStatus", albumStatus);

		Map<Integer, String> photoStatus = new HashMap<Integer, String>();
		photoStatus.put(0, "顯示");
		photoStatus.put(1, "隱藏");
		application.setAttribute("photoStatus", photoStatus);

		Map<Integer, String> photoReportStatus = new HashMap<Integer, String>();
		photoReportStatus.put(0, "未審核");
		photoReportStatus.put(1, "不封鎖");
		photoReportStatus.put(2, "已封鎖");
		application.setAttribute("photoReportStatus", photoReportStatus);
		
		Map<Integer, String> tourReportStatus = new HashMap<Integer, String>();
		tourReportStatus.put(0, "未審核");
		tourReportStatus.put(1, "通過");
		tourReportStatus.put(2, "未通過");
		application.setAttribute("tourReportStatus", tourReportStatus);
		
		Map<Integer, String> tourRoomBedSize = new HashMap<Integer, String>();
		tourRoomBedSize.put(0, "單人床");
		tourRoomBedSize.put(1, "雙人床");
		application.setAttribute("tourRoomBedSize", tourRoomBedSize);
		
		Map<Integer, String> tourRoomPrivBath = new HashMap<Integer, String>();
		tourRoomPrivBath.put(0, "否");
		tourRoomPrivBath.put(1, "是");
		application.setAttribute("tourRoomPrivBath", tourRoomPrivBath);
		
		Map<Integer, String> tourRoomAircon = new HashMap<Integer, String>();
		tourRoomAircon.put(0, "否");
		tourRoomAircon.put(1, "是");
		application.setAttribute("tourRoomAircon", tourRoomAircon);
		
		Map<Integer, String> tourReleased = new HashMap<Integer, String>();
		tourReleased.put(0, "下架");
		tourReleased.put(1, "上架");
		application.setAttribute("tourReleased", tourReleased);
		
		Map<Integer, String> diveStatus = new HashMap<Integer, String>();
		diveStatus.put(0, "開放");
		diveStatus.put(1, "關閉");
		application.setAttribute("diveStatus", diveStatus);
	}
}
