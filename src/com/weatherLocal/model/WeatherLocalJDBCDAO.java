package com.weatherLocal.model;
import java.sql.Timestamp;
import java.util.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weatherLocal.model.WeatherLocalVO;


//卡main方法中在時間的測試上面



public class WeatherLocalJDBCDAO implements WeatherLocalDAO_interface{
	//載入/登入
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String passwd = "123456";
	
	//SQL指令入/
	private static final String INSERT_STMT = 
			"INSERT INTO WEATHER_LOCAL (wt_local , wt_temp , wt_watertemp , wt_rainrate , wt_waveheight , wt_sunrise , wt_sundown , wt_time  )" + 
			"VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),"
			+ "? ,?, ?," + 
			"?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT wt_local, wt_temp, wt_watertemp, wt_rainrate, wt_waveheight, wt_sunrise, wt_sundown, wt_time FROM WEATHER_LOCAL "
			+ "order by wt_local";
	private static final String GET_ONE_STMT = 
			"SELECT wt_local, wt_temp, wt_watertemp, wt_rainrate, wt_waveheight, wt_sunrise, wt_sundown, wt_time FROM WEATHER_LOCAL "
			+ "where wt_local = ?";
	private static final String UPDATE = 
			"UPDATE WEATHER_LOCAL set wt_temp=?, wt_watertemp=?, wt_rainrate=?, wt_waveheight=?, wt_sunrise=?, wt_sundown=?, wt_time=? "
			+ "where wt_local = ?";
	
	//CRU方法
	@Override
	public void insert(WeatherLocalVO DiveLocalVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, DiveLocalVO.getWt_temp());
			pstmt.setString(2, DiveLocalVO.getWt_watertemp());
			pstmt.setString(3, DiveLocalVO.getWt_rainrate());
			pstmt.setString(4, DiveLocalVO.getWt_waveheight());
			pstmt.setTimestamp(5, DiveLocalVO.getWt_sunrise());
			pstmt.setTimestamp(6, DiveLocalVO.getWt_sundown());
			pstmt.setTimestamp(7, DiveLocalVO.getWt_time());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
		
	}

	@Override
	public void update(WeatherLocalVO DiveLocalVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, DiveLocalVO.getWt_temp());
			pstmt.setString(2, DiveLocalVO.getWt_watertemp());
			pstmt.setString(3, DiveLocalVO.getWt_rainrate());
			pstmt.setString(4, DiveLocalVO.getWt_waveheight());
			pstmt.setTimestamp(5, DiveLocalVO.getWt_sunrise());
			pstmt.setTimestamp(6, DiveLocalVO.getWt_sundown());
			pstmt.setTimestamp(7, DiveLocalVO.getWt_time());
			pstmt.setString(8, DiveLocalVO.getWt_local());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}

	@Override
	public WeatherLocalVO findByPrimaryKey(String wt_local) {
		// TODO Auto-generated method stub
		WeatherLocalVO DiveLocalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, wt_local);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				DiveLocalVO = new WeatherLocalVO();
				DiveLocalVO.setWt_local(rs.getString("wt_local"));
				DiveLocalVO.setWt_temp(rs.getString("wt_temp"));
				DiveLocalVO.setWt_watertemp(rs.getString("wt_watertemp"));
				DiveLocalVO.setWt_rainrate(rs.getString("wt_rainrate"));
				DiveLocalVO.setWt_waveheight(rs.getString("wt_waveheight"));
				DiveLocalVO.setWt_sunrise(rs.getTimestamp("wt_sunrise"));
				DiveLocalVO.setWt_sundown(rs.getTimestamp("wt_sundown"));
				DiveLocalVO.setWt_time(rs.getTimestamp("wt_time"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return DiveLocalVO;
	}

	@Override
	public List<WeatherLocalVO> getAll() {
		// TODO Auto-generated method stub
		List<WeatherLocalVO> list = new ArrayList<WeatherLocalVO>();
		WeatherLocalVO DiveLocalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				DiveLocalVO = new WeatherLocalVO();
				DiveLocalVO.setWt_local(rs.getString("wt_local"));
				DiveLocalVO.setWt_temp(rs.getString("wt_temp"));
				DiveLocalVO.setWt_watertemp(rs.getString("wt_watertemp"));
				DiveLocalVO.setWt_rainrate(rs.getString("wt_rainrate"));
				DiveLocalVO.setWt_waveheight(rs.getString("wt_waveheight"));
				DiveLocalVO.setWt_sunrise(rs.getTimestamp("wt_sunrise"));
				DiveLocalVO.setWt_sundown(rs.getTimestamp("wt_sundown"));
				DiveLocalVO.setWt_time(rs.getTimestamp("wt_time"));

				list.add(DiveLocalVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	//測試用
	public static void main(String[] args) {

		WeatherLocalJDBCDAO dao = new WeatherLocalJDBCDAO();

		//=======================insert測試==========================
//		DiveLocalVO DiveLocalVO = new DiveLocalVO();
//		DiveLocalVO.setWt_local("");
//		DiveLocalVO.setWt_temp("25");
//		DiveLocalVO.setWt_watertemp("18");
//		DiveLocalVO.setWt_rainrate("80%");
//		DiveLocalVO.setWt_waveheight("1.5m");
//		//取得特定時間
//		Calendar cal = new GregorianCalendar(2007, Calendar.JANUARY, 15, 20, 17, 36);
//		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
//		//取得目前時間
//		Calendar nowTimeCal = Calendar.getInstance();
//		Timestamp nowTime = new Timestamp(nowTimeCal.getTimeInMillis());
//		
//		DiveLocalVO.setWt_sunrise(timestamp);
//		DiveLocalVO.setWt_sundown(timestamp);
//		DiveLocalVO.setWt_time(nowTime);
//		
//		dao.insert(DiveLocalVO);
//		System.out.println("Insert - OK");
		
//		=====================Update測試==============================
//		DiveLocalVO DiveLocalVO = new DiveLocalVO();
//		DiveLocalVO.setWt_local("WL00005");
//		DiveLocalVO.setWt_temp("25");
//		DiveLocalVO.setWt_watertemp("18");
//		DiveLocalVO.setWt_rainrate("80%");
//		DiveLocalVO.setWt_waveheight("1.5m");
//		//取得特定時間
//		Calendar cal = new GregorianCalendar(2007, Calendar.JANUARY, 15, 20, 17, 36);
//		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
//		//取得目前時間
//		Calendar nowTimeCal = Calendar.getInstance();
//		Timestamp nowTime = new Timestamp(nowTimeCal.getTimeInMillis());
//		
//		DiveLocalVO.setWt_sunrise(timestamp);
//		DiveLocalVO.setWt_sundown(timestamp);
//		DiveLocalVO.setWt_time(nowTime);
//		
//		dao.update(DiveLocalVO);
//		System.out.println("update - OK");
		
		
		//=====================find測試================================
		WeatherLocalVO DiveLocalVO1 = dao.findByPrimaryKey("WL00001");
		System.out.println(DiveLocalVO1);
		System.out.println("find - OK");
		
		
//		=====================ListAll測試==============================
//		List<DiveLocalVO> allDiveItems =  dao.getAll();
//		for(DiveLocalVO diveItem: allDiveItems) {
//			System.out.println(diveItem);
//		}
//		System.out.println("ListAll - OK");
		
	}
	
	
}
