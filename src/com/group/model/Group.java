package com.group.model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Timer Expired = null; 
	long long_now = new java.util.Date().getTime();

	public void init() throws ServletException{
		//排程器 每小時找找已過揪團時間的揪團
		Expired = new Timer();
		Expired.scheduleAtFixedRate(new TimerTask() {
			
			public void run() {
				GroupService g = new GroupService();
				List<GroupVO> list = g.getAll();
				for(GroupVO groupVO:list) {					
					if(long_now>groupVO.getGroup_tour_stop_time().getTime() && groupVO.getGroup_status()==0) {
						g.updateStatus(groupVO.getGroup_no(), 1, groupVO.getGroup_tour_num());
					}
				}
			}
		}, new java.util.Date(), 3600000);
	}
	
	public void destroy() {
		Expired.cancel();
	}
	
	
}
