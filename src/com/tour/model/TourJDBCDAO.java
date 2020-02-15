package com.tour.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.room_image.model.RoomImageVO;
import com.tour_image.model.TourImageJDBCDAO;
import com.tour_image.model.TourImageVO;
import com.tour_room.model.TourRoomJDBCDAO;
import com.tour_room.model.TourRoomVO;
import com.util.MyUtil;

public class TourJDBCDAO implements TourDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String psw = "123456";

	private static final String INSERT_STMT = "INSERT INTO TOUR (TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE, TOUR_STATUS) "
			+ "VALUES ('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT TOUR_NO, MEM_NO, TOUR_NAME, TO_CHAR(TOUR_BGN_DATE, 'YYYY-MM-DD')TOUR_BGN_DATE, TO_CHAR(TOUR_END_DATE, 'YYYY-MM-DD')TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, "
			+ "TOUR_PLACE, TOUR_DIVES, TO_CHAR(TOUR_STOP_DATE, 'YYYY-MM-DD')TOUR_STOP_DATE, TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG FROM TOUR";

	private static final String GET_ONE_STMT = "SELECT TOUR_NO, MEM_NO, TOUR_NAME, TO_CHAR(TOUR_BGN_DATE, 'YYYY-MM-DD')TOUR_BGN_DATE, TO_CHAR(TOUR_END_DATE, 'YYYY-MM-DD')TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, "
			+ "TOUR_PLACE, TOUR_DIVES, TO_CHAR(TOUR_STOP_DATE, 'YYYY-MM-DD')TOUR_STOP_DATE, TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG FROM TOUR WHERE TOUR_NO = ?";

	private static final String DELETE = "DELETE FROM TOUR WHERE TOUR_NO = ?";

	private static final String UPDATE = "UPDATE TOUR SET TOUR_NAME = ?, TOUR_BGN_DATE = ?, TOUR_END_DATE = ?, TOUR_PRICE = ?, TOUR_MAX_NUM = ?, "
			+ "TOUR_PLACE = ?, TOUR_DIVES = ?, TOUR_STOP_DATE = ?, TOUR_STATUS = ? WHERE TOUR_NO = ?";
	
	private static final String UPDATE_STATUS = 
			"UPDATE TOUR SET TOUR_STATUS = ? WHERE TOUR_NO = ?";

	private static final String GET_MEMLIST_STMT = "SELECT TOUR_NO, MEM_NO, TOUR_NAME, TO_CHAR(TOUR_BGN_DATE, 'YYYY-MM-DD')TOUR_BGN_DATE, TO_CHAR(TOUR_END_DATE, 'YYYY-MM-DD')TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, "
			+ "TOUR_PLACE, TOUR_DIVES, TO_CHAR(TOUR_STOP_DATE, 'YYYY-MM-DD')TOUR_STOP_DATE, TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG FROM TOUR WHERE MEM_NO = ?";

	private static final String GET_SEARCH = "SELECT TOUR_NO, MEM_NO, TOUR_NAME, TO_CHAR(TOUR_BGN_DATE, 'YYYY-MM-DD')TOUR_BGN_DATE, TO_CHAR(TOUR_END_DATE, 'YYYY-MM-DD')TOUR_END_DATE, TOUR_PRICE, "
			+ "TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TO_CHAR(TOUR_STOP_DATE, 'YYYY-MM-DD')TOUR_STOP_DATE, TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG FROM TOUR "
			+ "WHERE TOUR_BGN_DATE BETWEEN ? AND ? AND TOUR_PLACE LIKE '%' || ? || '%' AND TOUR_STATUS = 1";

	private static final String GET_PLACE_COUNT = "SELECT COUNT(TOUR_PLACE) FROM TOUR WHERE TOUR_PLACE LIKE '%' || ? || '%' AND TOUR_STATUS = 1 AND TOUR_STOP_DATE > CURRENT_DATE";

	private static final String GET_SERACH_PLACE = "SELECT TOUR_NO, MEM_NO, TOUR_NAME, TO_CHAR(TOUR_BGN_DATE, 'YYYY-MM-DD')TOUR_BGN_DATE, TO_CHAR(TOUR_END_DATE, 'YYYY-MM-DD')TOUR_END_DATE, TOUR_PRICE, "
			+ "TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TO_CHAR(TOUR_STOP_DATE, 'YYYY-MM-DD')TOUR_STOP_DATE, TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG FROM TOUR "
			+ "WHERE TOUR_PLACE LIKE '%' || ? || '%' AND TOUR_STATUS = 1";
	

	@Override
	public void insert(TourVO tourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tourVO.getMem_no());
			pstmt.setString(2, tourVO.getTour_name());
			pstmt.setDate(3, tourVO.getTour_bgn_date());
			pstmt.setDate(4, tourVO.getTour_end_date());
			pstmt.setInt(5, tourVO.getTour_price());
			pstmt.setInt(6, tourVO.getTour_max_num());
			pstmt.setString(7, tourVO.getTour_place());
			pstmt.setInt(8, tourVO.getTour_dives());
			pstmt.setDate(9, tourVO.getTour_stop_date());
			pstmt.setInt(10, tourVO.getTour_status());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(TourVO tourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tourVO.getTour_name());
			pstmt.setDate(2, tourVO.getTour_bgn_date());
			pstmt.setDate(3, tourVO.getTour_end_date());
			pstmt.setInt(4, tourVO.getTour_price());
			pstmt.setInt(5, tourVO.getTour_max_num());
			pstmt.setString(6, tourVO.getTour_place());
			pstmt.setInt(7, tourVO.getTour_dives());
			pstmt.setDate(8, tourVO.getTour_stop_date());
			pstmt.setInt(9, tourVO.getTour_status());
			pstmt.setString(10, tourVO.getTour_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public void updateStatus (TourVO tourVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, tourVO.getTour_status());
			pstmt.setString(2, tourVO.getTour_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String tour_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tour_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public TourVO findbyPrimaryKey(String tour_no) {

		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tour_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTour_no(rs.getString("tour_no"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_bgn_date(rs.getDate("tour_bgn_date"));
				tourVO.setTour_end_date(rs.getDate("tour_end_date"));
				tourVO.setTour_price(rs.getInt("tour_price"));
				tourVO.setTour_max_num(rs.getInt("tour_max_num"));
				tourVO.setTour_place(rs.getString("tour_place"));
				tourVO.setTour_dives(rs.getInt("tour_dives"));
				tourVO.setTour_stop_date(rs.getDate("tour_stop_date"));
				tourVO.setTour_status(rs.getInt("tour_status"));
				tourVO.setTour_num(rs.getInt("tour_num"));
				tourVO.setTour_rev_avg(rs.getDouble("tour_rev_avg"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tourVO;

	}

	@Override
	public List<TourVO> getAll() {

		List<TourVO> list = new ArrayList<TourVO>();
		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTour_no(rs.getString("tour_no"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_bgn_date(rs.getDate("tour_bgn_date"));
				tourVO.setTour_end_date(rs.getDate("tour_end_date"));
				tourVO.setTour_price(rs.getInt("tour_price"));
				tourVO.setTour_max_num(rs.getInt("tour_max_num"));
				tourVO.setTour_place(rs.getString("tour_place"));
				tourVO.setTour_dives(rs.getInt("tour_dives"));
				tourVO.setTour_stop_date(rs.getDate("tour_stop_date"));
				tourVO.setTour_status(rs.getInt("tour_status"));
				tourVO.setTour_num(rs.getInt("tour_num"));
				tourVO.setTour_rev_avg(rs.getDouble("tour_rev_avg"));
				list.add(tourVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public List<TourVO> findbyMemNo(String mem_no) {
		List<TourVO> list = new ArrayList<TourVO>();
		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_MEMLIST_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTour_no(rs.getString("tour_no"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_bgn_date(rs.getDate("tour_bgn_date"));
				tourVO.setTour_end_date(rs.getDate("tour_end_date"));
				tourVO.setTour_price(rs.getInt("tour_price"));
				tourVO.setTour_max_num(rs.getInt("tour_max_num"));
				tourVO.setTour_place(rs.getString("tour_place"));
				tourVO.setTour_dives(rs.getInt("tour_dives"));
				tourVO.setTour_stop_date(rs.getDate("tour_stop_date"));
				tourVO.setTour_status(rs.getInt("tour_status"));
				tourVO.setTour_num(rs.getInt("tour_num"));
				tourVO.setTour_rev_avg(rs.getDouble("tour_rev_avg"));
				list.add(tourVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public List<TourVO> findbyDate(Date tour_bgn_date, Date tour_end_date, String tour_place) {
		List<TourVO> list = new ArrayList<TourVO>();
		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_SEARCH);

			pstmt.setDate(1, tour_bgn_date);
			pstmt.setDate(2, tour_end_date);
			pstmt.setString(3, tour_place);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTour_no(rs.getString("tour_no"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_bgn_date(rs.getDate("tour_bgn_date"));
				tourVO.setTour_end_date(rs.getDate("tour_end_date"));
				tourVO.setTour_price(rs.getInt("tour_price"));
				tourVO.setTour_max_num(rs.getInt("tour_max_num"));
				tourVO.setTour_place(rs.getString("tour_place"));
				tourVO.setTour_dives(rs.getInt("tour_dives"));
				tourVO.setTour_stop_date(rs.getDate("tour_stop_date"));
				tourVO.setTour_status(rs.getInt("tour_status"));
				tourVO.setTour_num(rs.getInt("tour_num"));
				tourVO.setTour_rev_avg(rs.getDouble("tour_rev_avg"));
				list.add(tourVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;

	}

	public Integer countbyTourPlace(String tour_place) {
		Integer countPlace = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_PLACE_COUNT);

			pstmt.setString(1, tour_place);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				countPlace = rs.getInt("count(tour_place)");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return countPlace;

	}

	public List<TourVO> findbyPlace(String tour_place) {
		TourVO tourVO = null;
		List<TourVO> list = new ArrayList<TourVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_SERACH_PLACE);

			pstmt.setString(1, tour_place);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTour_no(rs.getString("tour_no"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_bgn_date(rs.getDate("tour_bgn_date"));
				tourVO.setTour_end_date(rs.getDate("tour_end_date"));
				tourVO.setTour_price(rs.getInt("tour_price"));
				tourVO.setTour_max_num(rs.getInt("tour_max_num"));
				tourVO.setTour_place(rs.getString("tour_place"));
				tourVO.setTour_dives(rs.getInt("tour_dives"));
				tourVO.setTour_stop_date(rs.getDate("tour_stop_date"));
				tourVO.setTour_status(rs.getInt("tour_status"));
				tourVO.setTour_num(rs.getInt("tour_num"));
				tourVO.setTour_rev_avg(rs.getDouble("tour_rev_avg"));
				list.add(tourVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;

	}

	public void insertTour(TourVO tourVO, List<TourImageVO> tourImageList, List<TourRoomVO> tourRoomList, List<RoomImageVO> roomImageList) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);

			// close auto commit
			con.setAutoCommit(false);

			// 先新增行程
			String cols[] = { "TOUR_NO" }; // columns to connect
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, tourVO.getMem_no());
			pstmt.setString(2, tourVO.getTour_name());
			pstmt.setDate(3, tourVO.getTour_bgn_date());
			pstmt.setDate(4, tourVO.getTour_end_date());
			pstmt.setInt(5, tourVO.getTour_price());
			pstmt.setInt(6, tourVO.getTour_max_num());
			pstmt.setString(7, tourVO.getTour_place());
			pstmt.setInt(8, tourVO.getTour_dives());
			pstmt.setDate(9, tourVO.getTour_stop_date());
			pstmt.setInt(10, tourVO.getTour_status());
			pstmt.executeUpdate();

			// get generate keys
			String next_tourNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_tourNo = rs.getString(1);
			}
			rs.close();

			// 新增行程相片
			TourImageJDBCDAO tImgDAO = new TourImageJDBCDAO();
			for (TourImageVO tImg : tourImageList) {
				tImg.setTour_no(next_tourNo);
				tImgDAO.tImgInsertWithTourNo(tImg, con);
			}
			
			// 新增行程房間
			TourRoomJDBCDAO tRoomDAO = new TourRoomJDBCDAO();
			for(TourRoomVO tRoom : tourRoomList) {
				tRoom.setTour_no(next_tourNo);
				tRoomDAO.tRoomInsertWithTourNo(tRoom, roomImageList , con);
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load DB" + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.out.println("Transaction roll back");
					con.rollback();
				} catch (SQLException sqle) {
					throw new RuntimeException("Rollback error occured" + sqle.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		TourJDBCDAO tourDAO = new TourJDBCDAO();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		// 新增
//		TourVO tourVO = new TourVO();
//		tourVO.setMem_no("M000007");
//		tourVO.setTour_name("北海岸之旅ABC");
//		tourVO.setTour_bgn_date(java.sql.Date.valueOf("2019-03-11"));
//		tourVO.setTour_end_date(java.sql.Date.valueOf("2019-03-15"));
//		tourVO.setTour_price(35000);
//		tourVO.setTour_max_num(10);
//		tourVO.setTour_place("墾丁");
//		tourVO.setTour_dives(5);
//		tourVO.setTour_stop_date(java.sql.Date.valueOf("2019-03-08"));
//		tourVO.setTour_status(0);
//		tourVO.setTour_num(5);
//		tourVO.setTour_rev_avg(4.5);
//		tourDAO.insert(tourVO);
//		
		//修改
//		TourVO tourVO1 = new TourVO();
//		tourVO1.setTour_no("T000006");
//		tourVO1.setTour_name("北海岸之旅New");
//		tourVO1.setTour_bgn_date(java.sql.Date.valueOf("2019-01-22"));
//		tourVO1.setTour_end_date(java.sql.Date.valueOf("2019-01-25"));
//		tourVO1.setTour_price(75000);
//		tourVO1.setTour_max_num(10);
//		tourVO1.setTour_place("北海岸");
//		tourVO1.setTour_dives(7);
//		tourVO1.setTour_stop_date(java.sql.Date.valueOf("2019-01-15"));
//		tourVO1.setTour_status(0);
//		tourDAO.update(tourVO1);
//		
//		//刪除
//		tourDAO.delete("T000008");

//		//查詢(primary key)
//		TourVO tourVO2 = tourDAO.findbyPrimaryKey("T000007");
//		System.out.print(tourVO2.getTour_no() + ", ");
//		System.out.print(tourVO2.getMem_no() + ", ");
//		System.out.print(tourVO2.getTour_name() + ", ");
//		System.out.print(tourVO2.getTour_bgn_date() + ", ");
//		System.out.print(tourVO2.getTour_end_date() + ", ");
//		System.out.print(tourVO2.getTour_price() + ", ");
//		System.out.print(tourVO2.getTour_max_num() + ", ");
//		System.out.print(tourVO2.getTour_place() + ", ");
//		System.out.print(tourVO2.getTour_dives() + ", ");
//		System.out.print(tourVO2.getTour_stop_date() + ", ");
//		System.out.print(tourVO2.getTour_status() + ", ");
//		System.out.print(tourVO2.getTour_num() + ", ");
//		System.out.println(tourVO2.getTour_rev_avg());
//		
//		System.out.println("---------------------------");
//		
//		//查詢(get_all)
//		List<TourVO> list = tourDAO.getAll();
//		for(TourVO tVO : list) {
//			System.out.print(tVO.getTour_no() + ", ");
//			System.out.print(tVO.getMem_no() + ", ");
//			System.out.print(tVO.getTour_name() + ", ");
//			System.out.print(tVO.getTour_bgn_date() + ", ");
//			System.out.print(tVO.getTour_end_date() + ", ");
//			System.out.print(tVO.getTour_price() + ", ");
//			System.out.print(tVO.getTour_max_num() + ", ");
//			System.out.print(tVO.getTour_place() + ", ");
//			System.out.print(tVO.getTour_dives() + ", ");
//			System.out.print(tVO.getTour_stop_date() + ", ");
//			System.out.print(tVO.getTour_status() + ", ");
//			System.out.print(tVO.getTour_num() + ", ");
//			System.out.print(tVO.getTour_rev_avg());
//			System.out.println();
//		}

//		List<TourVO> listTest = tourDAO.findbyMemNo("M000007");
//		for(TourVO tVO : listTest) {
//			System.out.print(tVO.getTour_no() + ", ");
//			System.out.print(tVO.getMem_no() + ", ");
//			System.out.print(tVO.getTour_name() + ", ");
//			System.out.print(tVO.getTour_bgn_date() + ", ");
//			System.out.print(tVO.getTour_end_date() + ", ");
//			System.out.print(tVO.getTour_price() + ", ");
//			System.out.print(tVO.getTour_max_num() + ", ");
//			System.out.print(tVO.getTour_place() + ", ");
//			System.out.print(tVO.getTour_dives() + ", ");
//			System.out.print(tVO.getTour_stop_date() + ", ");
//			System.out.print(tVO.getTour_status() + ", ");
//			System.out.print(tVO.getTour_num() + ", ");
//			System.out.print(tVO.getTour_rev_avg());
//			System.out.println();
//		}

//		List<TourVO> listSearch = tourDAO.findbyDate(java.sql.Date.valueOf("2020-03-11"), java.sql.Date.valueOf("2020-03-31"), "丁");
//		for(TourVO tVO : listSearch) {
//			System.out.print(tVO.getTour_no() + ", ");
//			System.out.print(tVO.getMem_no() + ", ");
//			System.out.print(tVO.getTour_name() + ", ");
//			System.out.print(tVO.getTour_bgn_date() + ", ");
//			System.out.print(tVO.getTour_end_date() + ", ");
//			System.out.print(tVO.getTour_price() + ", ");
//			System.out.print(tVO.getTour_max_num() + ", ");
//			System.out.print(tVO.getTour_place() + ", ");
//			System.out.print(tVO.getTour_dives() + ", ");
//			System.out.print(tVO.getTour_stop_date() + ", ");
//			System.out.print(tVO.getTour_status() + ", ");
//			System.out.print(tVO.getTour_num() + ", ");
//			System.out.print(tVO.getTour_rev_avg());
//			System.out.println();
//		}

//		List<TourVO> listPlace = tourDAO.findbyPlace("綠");
//		for (TourVO tVO : listPlace) {
//			System.out.print(tVO.getTour_no() + ", ");
//			System.out.print(tVO.getMem_no() + ", ");
//			System.out.print(tVO.getTour_name() + ", ");
//			System.out.print(tVO.getTour_bgn_date() + ", ");
//			System.out.print(tVO.getTour_end_date() + ", ");
//			System.out.print(tVO.getTour_price() + ", ");
//			System.out.print(tVO.getTour_max_num() + ", ");
//			System.out.print(tVO.getTour_place() + ", ");
//			System.out.print(tVO.getTour_dives() + ", ");
//			System.out.print(tVO.getTour_stop_date() + ", ");
//			System.out.print(tVO.getTour_status() + ", ");
//			System.out.print(tVO.getTour_num() + ", ");
//			System.out.print(tVO.getTour_rev_avg());
//			System.out.println();
//		}
//
		// Counting Place Test
		Integer x = tourDAO.countbyTourPlace("綠島");
		System.out.println(x);

//		Calendar c = Calendar.getInstance(); 	
//		java.util.Date uDate = new java.util.Date();
////		c.setTime(uDate);
//		c.set(Calendar.DATE, 1);
//		c.set(Calendar.DAY_OF_MONTH, 1);
//		c.add(Calendar.MONTH, 1);
//		uDate = c.getTime();		
//		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
//        System.out.println("Time in java.sql.Date is : " + sDate);
		
		
//		//行程
//		TourVO tourVO = new TourVO();
//		tourVO.setMem_no("M000007");
//		tourVO.setTour_name("靠北海岸之旅");
//		tourVO.setTour_bgn_date(java.sql.Date.valueOf("2020-03-01"));
//		tourVO.setTour_end_date(java.sql.Date.valueOf("2020-03-02"));
//		tourVO.setTour_price(35000);
//		tourVO.setTour_max_num(10);
//		tourVO.setTour_place("北海岸");
//		tourVO.setTour_dives(4);
//		tourVO.setTour_stop_date(java.sql.Date.valueOf("2019-02-27"));
//		tourVO.setTour_status(0);
//		tourVO.setTour_num(5);
//		tourVO.setTour_rev_avg(4.5);
//		
//		//行程相片
//		List<TourImageVO> tourImageList = new ArrayList<TourImageVO>();
//		TourImageVO tourImage1 = new TourImageVO();
//		tourImage1.setTour_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\turtle.jpg"));
//		
//		TourImageVO tourImage2 = new TourImageVO();
//		tourImage2.setTour_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\boat1.jpg"));
//		
//		TourImageVO tourImage3 = new TourImageVO();
//		tourImage3.setTour_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\reef.jpg"));
//		
//		tourImageList.add(tourImage1);
//		tourImageList.add(tourImage2);
//		tourImageList.add(tourImage3);
//		
		//行程房間
//		List<TourRoomVO> tourRoomList = new ArrayList<TourRoomVO>();
//		TourRoomVO tourRoomVO1 = new TourRoomVO();
//		tourRoomVO1.setBed_size(0);
//		tourRoomVO1.setRoom_ppl(4);
//		tourRoomVO1.setRoom_priv_br(1);
//		tourRoomVO1.setRoom_aircon(1);
//		
//		tourRoomList.add(tourRoomVO1);
//		
//		//房間相片
//		List<RoomImageVO> roomImageList = new ArrayList<RoomImageVO>();
//		RoomImageVO roomImageVO1 = new RoomImageVO();
//		roomImageVO1.setRoom_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\room1.jpg"));
//		
//		RoomImageVO roomImageVO2 = new RoomImageVO();
//		roomImageVO2.setRoom_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\room2.jpg"));
//		
//		RoomImageVO roomImageVO3 = new RoomImageVO();
//		roomImageVO3.setRoom_img(MyUtil.pathToByteArray("C:\\DA104G2\\DA104G2_workspace\\G2_Dive\\WebContent\\images\\room3.jpg"));
//		
//		roomImageList.add(roomImageVO1);
//		roomImageList.add(roomImageVO2);
//		roomImageList.add(roomImageVO3);
//		
//		tourDAO.insertTour(tourVO, tourImageList, tourRoomList, roomImageList);
		
//		TourVO tourVO1 = new TourVO();
//		tourVO1.setTour_no("T000006");
//		tourVO1.setTour_status(0);
//		tourDAO.updateStatus(tourVO1);

	}

}
