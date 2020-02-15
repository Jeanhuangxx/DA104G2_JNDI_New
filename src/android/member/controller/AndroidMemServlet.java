package android.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.member.model.AndroidMem;
import android.member.model.AndroidMemDAO;
import android.member.model.AndroidMemDAOImpl;

public class AndroidMemServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		AndroidMemDAO memDao = new AndroidMemDAOImpl();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isMember")) {
			String userId = jsonObject.get("userId").getAsString();
			String password = jsonObject.get("password").getAsString();
			writeText(res,	String.valueOf(memDao.isMem(userId, password)));
		}  else if (action.equals("add")) {
			AndroidMem userId = gson.fromJson(jsonObject.get("member").getAsString(), AndroidMem.class);
			writeText(res, String.valueOf(memDao.add(userId)));
		} else if (action.equals("findByPrimaryKey")) {
			String userId = jsonObject.get("userId").getAsString();
			AndroidMem memvo = memDao.findById(userId);
			writeText(res, memvo == null ? "" : gson.toJson(userId));
		} else if (action.equals("update")) {
			AndroidMem mem = gson.fromJson(jsonObject.get("mem").getAsString(), AndroidMem.class);
			writeText(res, String.valueOf(memDao.update(mem)));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}
