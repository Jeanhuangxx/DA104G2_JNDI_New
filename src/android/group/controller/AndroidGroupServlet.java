package android.group.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.group.model.AndroidGroupDAO;
import android.group.model.AndroidGroupDAOImpl;


public class AndroidGroupServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
				
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		res.setContentType(contentType);
		
		String userId = req.getParameter("name");
		System.out.println(userId);
		AndroidGroupDAO dao = new AndroidGroupDAOImpl();
		dao.update(userId);
		PrintWriter out = res.getWriter();
		out.println("Success");
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}