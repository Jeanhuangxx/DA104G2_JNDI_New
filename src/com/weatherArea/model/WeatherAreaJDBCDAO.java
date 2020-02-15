package com.weatherArea.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class WeatherAreaJDBCDAO implements WeatherArea_interface{
	//載入/登入
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String passwd = "123456";
	
	//SQL指令入/
	private static final String INSERT_STMT = 
			"INSERT INTO WEATHER_AREA (weather_area_no , wt_local  )" + 
			"VALUES ('WA'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),? )";
	private static final String GET_ALL_STMT = 
			"SELECT weather_area_no, wt_local " + 
			"FROM WEATHER_AREA order by weather_area_no";
	private static final String GET_ONE_STMT = 
			"SELECT weather_area_no, wt_local " + 
			"FROM WEATHER_AREA where weather_area_no = ?";
	private static final String UPDATE = 
			"UPDATE WEATHER_AREA set wt_local=?" + 
			"where weather_area_no = ?";
	
	//CRU方法
	@Override
	public void insert(WeatherAreaVO weatherAreaVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, weatherAreaVO.getWt_local());
			


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
	public void update(WeatherAreaVO weatherAreaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, weatherAreaVO.getWt_local());
			pstmt.setString(2, weatherAreaVO.getWeather_area_no());


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
	public WeatherAreaVO findByPrimaryKey(String weather_area_no) {
		// TODO Auto-generated method stub
		WeatherAreaVO weatherAreaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, weather_area_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				weatherAreaVO = new WeatherAreaVO();
				weatherAreaVO.setWeather_area_no(rs.getString("weather_area_no"));
				weatherAreaVO.setWt_local(rs.getString("wt_local"));


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
		return weatherAreaVO;
	}

	@Override
	public List<WeatherAreaVO> getAll() {
		// TODO Auto-generated method stub
		List<WeatherAreaVO> list = new ArrayList<WeatherAreaVO>();
		WeatherAreaVO weatherAreaVO = null;

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
				weatherAreaVO = new WeatherAreaVO();
				weatherAreaVO.setWeather_area_no(rs.getString("weather_area_no"));
				weatherAreaVO.setWt_local(rs.getString("wt_local"));

;

				list.add(weatherAreaVO); // Store the row in the list
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

		WeatherAreaJDBCDAO dao = new WeatherAreaJDBCDAO();

		//=======================insert測試==========================
		WeatherAreaVO weatherAreaVO = new WeatherAreaVO();
		weatherAreaVO.setWeather_area_no("");
		weatherAreaVO.setWt_local("WL00002");

		
		
		dao.insert(weatherAreaVO);
		System.out.println("Insert - OK");
		
//		=====================Update測試==============================
		WeatherAreaVO weatherAreaVO1 = new WeatherAreaVO();
		weatherAreaVO1.setWeather_area_no("WA00003");
		weatherAreaVO1.setWt_local("WL00004");
		
		dao.update(weatherAreaVO);
		System.out.println("update - OK");
		
		
		//=====================find測試================================
		WeatherAreaVO weatherAreaVO2 = dao.findByPrimaryKey("WA00001");
		System.out.println(weatherAreaVO2);
		System.out.println("find - OK");
		
		
//		=====================ListAll測試==============================
		List<WeatherAreaVO> allDiveMsgItems =  dao.getAll();
		for(WeatherAreaVO diveMsgItem: allDiveMsgItems) {
			System.out.println(diveMsgItem);
		}
		System.out.println("ListAll - OK");
		
	}
	
	
	
}
