package com.group_message.controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.group.model.*;
import com.group_message.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@MultipartConfig
public class Group_messageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println(action);
//		if ("insert".equals(action)) { // 來自addGroup.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			Map<String,String> word = new HashMap<>();
//			req.setAttribute("word", word);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String group_no = req.getParameter("group_no").trim();
//				String mem_no = req.getParameter("mem_no");				
//				String group_me_note = req.getParameter("group_me_note");
//				word.put(group_me_note, group_me_note);
//				
//				Group_messageVO group_meVO = new Group_messageVO();
//				group_meVO.setGroup_me_no(group_no);
//				group_meVO.setMem_no(mem_no);
//				group_meVO.setGroup_me_note(group_me_note);
//				
//				GroupService gruopSvc = new GroupService();				
//				GroupVO groupVO = gruopSvc.getOneGroup(group_no);
//				
//				
//				/*************************** 2.開始新增資料 ***************************************/
//				
//				Group_messageService group_meSvc = new Group_messageService();
//				group_meVO = group_meSvc.addGroup_message(group_no, mem_no, group_me_note);
//				
//				req.setAttribute("group_meVO", group_meVO);
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				req.setAttribute("groupVO", groupVO);
//				String url = "/front-end/group/listOneGroup.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGroup.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("請先登入後方可留言~");
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
if ("insert".equals(action)) {
	String group_no = req.getParameter("group_no");
	System.out.println(group_no);
	String mem_no = req.getParameter("mem_no");
	System.out.println(mem_no);
	String group_me_note = req.getParameter("group_me_note");
	System.out.println(group_me_note);
	JSONArray array = new JSONArray();
	
	Group_messageService group_meSvc = new Group_messageService();
	Group_messageVO group_meVO = group_meSvc.addGroup_message(group_no, mem_no, group_me_note);	
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/M/d  ah:m");
		JSONObject obj = new JSONObject();
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMem(mem_no);
		List<Group_messageVO> list= group_meSvc.getOneForGroup(group_no);	
		Iterator itr = list.iterator();
		while(itr.hasNext()) {
			group_meVO = (Group_messageVO)itr.next();
		}

		 
		try {
			obj.put("mem_name", memVO.getMem_name());
			obj.put("mem_no", memVO.getMem_no());
			obj.put("group_me_note", group_meVO.getGroup_me_note());
			obj.put("group_me_time", sdFormat.format(group_meVO.getGroup_me_time()));
			System.out.println(sdFormat.format(group_meVO.getGroup_me_time()));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		array.put(obj);
	
	
	res.setContentType("text/plain");
	res.setCharacterEncoding("UTF-8");
	PrintWriter out = res.getWriter();
	out.write(obj.toString());
	out.flush();
	out.close();
}

}

	}

