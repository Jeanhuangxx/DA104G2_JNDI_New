package com.dive.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dive.model.*;


public class DBGifReader2_dive extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//設定中文編碼
		res.setContentType("image/gif");
ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
//			MemVO memVO = (MemVO)req.getSession().getAttribute("memVO");	
//			String mem_no = (String)req.getSession().getAttribute("mem_no");
			String dive_no = req.getParameter("dive_no").trim();
			System.out.println(dive_no);
			ResultSet rs = stmt.executeQuery(
				"SELECT dive_img FROM dive WHERE dive_no ='"+dive_no+"'" );

			if (rs.next()) {
BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("dive_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer+++++
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/no.png");
				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				out.write(buffer);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/no.png");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA104G2", "123456");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}