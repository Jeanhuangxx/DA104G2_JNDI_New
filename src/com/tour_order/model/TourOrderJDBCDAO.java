package com.tour_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourOrderJDBCDAO implements TourOrderDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String psw = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TTL_PRICE)"
			+ "VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'),? , ?, ?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_DATE, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV FROM TOUR_ORDER";
	
	private static final String GET_ONE_STMT = 
			"SELECT TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_DATE, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV FROM TOUR_ORDER WHERE TOUR_ORDER_NO = ?";	
	
	private static final String GET_MEM_ORDER = 
			"SELECT TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_DATE, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV FROM TOUR_ORDER WHERE MEM_NO = ?";
	
	private static final String UPDATE = 
			"UPDATE TOUR_ORDER SET TOUR_NO = ?, MEM_NO = ?, TOUR_ORDER_STATUS = ?, TTL_PRICE = ?, TOUR_REV_NOTE = ?, TOUR_REV = ?"
			+ "WHERE TOUR_ORDER_NO = ?";
	
	private static final String UPDATE_REVIEW = 
			"UPDATE TOUR_ORDER SET TOUR_REV_NOTE = ?, TOUR_REV = ? WHERE TOUR_NO = ? AND MEM_NO = ?";
	
	private static final String TOUR_PPL = 
			"SELECT COUNT(TOUR_NO) FROM TOUR_ORDER WHERE TOUR_NO = ?";
	
	private static final String COUNT_REVIEW = 
			"SELECT COUNT(TOUR_REV_NOTE) FROM TOUR_ORDER WHERE TOUR_NO = ?";
	
	private static final String AVG_REV_SCORE = 
			"SELECT AVG(TOUR_REV) FROM TOUR_ORDER WHERE TOUR_NO = ?";
	
	private static final String REVIEW_LIST = 
			"SELECT MEM_NO, TOUR_ORDER_DATE, TOUR_REV_NOTE FROM TOUR_ORDER WHERE TOUR_NO = ?";
		
	
	private static final String GETORDER_TOURNO_MEMNO = 
			"SELECT TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_DATE, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV FROM TOUR_ORDER WHERE MEM_NO = ? AND TOUR_NO = ?";
	
	@Override
	public void insert(TourOrderVO tourOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tourOrderVO.getTour_no());
			pstmt.setString(2, tourOrderVO.getMem_no());
			pstmt.setInt(3, tourOrderVO.getTtl_price());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public  void updateReview(TourOrderVO tourOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE_REVIEW);
			
			pstmt.setString(1, tourOrderVO.getTour_rev_note());
			pstmt.setInt(2, tourOrderVO.getTour_rev());
			pstmt.setString(3, tourOrderVO.getTour_no());
			pstmt.setString(4, tourOrderVO.getMem_no());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(TourOrderVO tourOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, tourOrderVO.getTour_no());
			pstmt.setString(2, tourOrderVO.getMem_no());
			pstmt.setInt(3, tourOrderVO.getTour_order_status());
			pstmt.setInt(4, tourOrderVO.getTtl_price());
			pstmt.setString(5, tourOrderVO.getTour_rev_note());
			pstmt.setInt(6, tourOrderVO.getTour_rev());
			pstmt.setString(7, tourOrderVO.getTour_order_no());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public TourOrderVO oneOrderbyMemTour(String tour_no, String mem_no) {
		TourOrderVO tourOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GETORDER_TOURNO_MEMNO);
			
			pstmt.setString(1, mem_no);
			pstmt.setString(2, tour_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourOrderVO = new TourOrderVO();
				tourOrderVO.setTour_order_no(rs.getString("tour_order_no"));
				tourOrderVO.setTour_no(rs.getString("tour_no"));
				tourOrderVO.setMem_no(rs.getString("mem_no"));
				tourOrderVO.setTour_order_date(rs.getTimestamp("tour_order_date"));
				tourOrderVO.setTour_order_status(rs.getInt("tour_order_status"));
				tourOrderVO.setTtl_price(rs.getInt("ttl_price"));
				tourOrderVO.setTour_rev_note(rs.getString("tour_rev_note"));
				tourOrderVO.setTour_rev(rs.getInt("tour_rev"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return tourOrderVO;
	}

	@Override
	public TourOrderVO findByPrimaryKey(String tour_order_no) {
		TourOrderVO tourOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, tour_order_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourOrderVO = new TourOrderVO();
				tourOrderVO.setTour_order_no(rs.getString("tour_order_no"));
				tourOrderVO.setTour_no(rs.getString("tour_no"));
				tourOrderVO.setMem_no(rs.getString("mem_no"));
				tourOrderVO.setTour_order_date(rs.getTimestamp("tour_order_date"));
				tourOrderVO.setTour_order_status(rs.getInt("tour_order_status"));
				tourOrderVO.setTtl_price(rs.getInt("ttl_price"));
				tourOrderVO.setTour_rev_note(rs.getString("tour_rev_note"));
				tourOrderVO.setTour_rev(rs.getInt("tour_rev"));
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return tourOrderVO;
	}

	@Override
	public List<TourOrderVO> getAll() {
		List<TourOrderVO> list = new ArrayList<TourOrderVO>();
		TourOrderVO tourOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourOrderVO = new TourOrderVO();
				tourOrderVO.setTour_order_no(rs.getString("tour_order_no"));
				tourOrderVO.setTour_no(rs.getString("tour_no"));
				tourOrderVO.setMem_no(rs.getString("mem_no"));
				tourOrderVO.setTour_order_date(rs.getTimestamp("tour_order_date"));
				tourOrderVO.setTour_order_status(rs.getInt("tour_order_status"));
				tourOrderVO.setTtl_price(rs.getInt("ttl_price"));
				tourOrderVO.setTour_rev_note(rs.getString("tour_rev_note"));
				tourOrderVO.setTour_rev(rs.getInt("tour_rev"));
				list.add(tourOrderVO);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public List<TourOrderVO> findOrderbyMemNo(String mem_no){
		List<TourOrderVO> list = new ArrayList<TourOrderVO>();
		TourOrderVO tourOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_MEM_ORDER);
			
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourOrderVO = new TourOrderVO();
				tourOrderVO.setTour_order_no(rs.getString("tour_order_no"));
				tourOrderVO.setTour_no(rs.getString("tour_no"));
				tourOrderVO.setMem_no(rs.getString("mem_no"));
				tourOrderVO.setTour_order_date(rs.getTimestamp("tour_order_date"));
				tourOrderVO.setTour_order_status(rs.getInt("tour_order_status"));
				tourOrderVO.setTtl_price(rs.getInt("ttl_price"));
				tourOrderVO.setTour_rev_note(rs.getString("tour_rev_note"));
				tourOrderVO.setTour_rev(rs.getInt("tour_rev"));
				list.add(tourOrderVO);
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}

			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
			
	}
	
	public Integer numberOfAttd(String tour_no) {
		Integer ppl = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(TOUR_PPL);
			
			pstmt.setString(1, tour_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ppl = rs.getInt("count(tour_no)");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ppl;
	}
	
	public Integer countbyReviewNote(String tour_no) {
		Integer tourReviewNote = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(COUNT_REVIEW);
			
			pstmt.setString(1, tour_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourReviewNote = rs.getInt("count(tour_rev_note)");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return tourReviewNote;
	}
	
	public Double avgTourScore(String tour_no) {
		Double tourReviewScore = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(AVG_REV_SCORE);
			
			pstmt.setString(1, tour_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourReviewScore = rs.getDouble("avg(tour_rev)");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tourReviewScore;
	}
	
	public List<TourOrderVO> getListByTourNo(String tour_no){
		List<TourOrderVO> list = new ArrayList<TourOrderVO>();
		TourOrderVO tourOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(REVIEW_LIST);
			
			pstmt.setString(1, tour_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourOrderVO = new TourOrderVO();
				tourOrderVO.setMem_no(rs.getString("mem_no"));
				tourOrderVO.setTour_order_date(rs.getTimestamp("tour_order_date"));
				tourOrderVO.setTour_rev_note(rs.getString("tour_rev_note"));
				list.add(tourOrderVO);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}

			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		TourOrderJDBCDAO tourOrderDAO = new TourOrderJDBCDAO();
		
//		//新增
//		TourOrderVO tourOrderVO = new TourOrderVO();
//		tourOrderVO.setTour_no("T000006");
//		tourOrderVO.setMem_no("M000003");
//		tourOrderVO.setTour_order_status(1);
//		tourOrderVO.setTtl_price(75000);
//		tourOrderVO.setTour_rev_note("provide the great service, the best experience ever. ");
//		tourOrderVO.setTour_rev(5);
//		tourOrderDAO.insert(tourOrderVO);
		
		//修改
//		TourOrderVO tourOrderVO1 = new TourOrderVO();
//		tourOrderVO1.setTour_order_no("TO000001");
//		tourOrderVO1.setTour_no("T000001");
//		tourOrderVO1.setMem_no("M000001");
//		tourOrderVO1.setTour_order_status(1);
//		tourOrderVO1.setTtl_price(75000);
//		tourOrderVO1.setTour_rev_note("服務態度有待加強");
//		tourOrderVO1.setTour_rev(2);
//		tourOrderDAO.update(tourOrderVO1);
		
		
		//查詢(primary key)
//		TourOrderVO tourOrderVO2 = tourOrderDAO.findByPrimaryKey("TO000003");
//		System.out.print(tourOrderVO2.getTour_order_no() + ", ");
//		System.out.print(tourOrderVO2.getTour_no() + ", ");
//		System.out.print(tourOrderVO2.getMem_no() + ", ");
//		System.out.print(tourOrderVO2.getTour_order_date() + ", ");
//		System.out.print(tourOrderVO2.getTour_order_status() + ", ");
//		System.out.print(tourOrderVO2.getTtl_price() + ", ");
//		System.out.println(tourOrderVO2.getTour_rev_note() + ", ");
//		
//		System.out.println("-----------------------------");
//		
//		//查詢(get all)
//		List<TourOrderVO> list = tourOrderDAO.getAll();
//		for(TourOrderVO tOrderVO : list) {
//			System.out.print(tOrderVO.getTour_order_no() + ", ");
//			System.out.print(tOrderVO.getTour_no() + ", ");
//			System.out.print(tOrderVO.getMem_no() + ", ");
//			System.out.print(tOrderVO.getTour_order_date() + ", ");
//			System.out.print(tOrderVO.getTour_order_status() + ", ");
//			System.out.print(tOrderVO.getTtl_price() + ", ");
//			System.out.println(tOrderVO.getTour_rev_note() + ", ");
//			System.out.println();
//		}
		
		// GET_MEM_ORDER
		List<TourOrderVO> listMemOrder = tourOrderDAO.findOrderbyMemNo("M000003"); 
		for(TourOrderVO tOrderVO : listMemOrder) {
//			System.out.print(tOrderVO.getTour_order_no() + ", ");
			System.out.print(tOrderVO.getTour_no() + ", ");
//			System.out.print(tOrderVO.getMem_no() + ", ");
//			System.out.print(tOrderVO.getTour_order_date() + ", ");
//			System.out.print(tOrderVO.getTour_order_status() + ", ");
//			System.out.print(tOrderVO.getTtl_price() + ", ");
//			System.out.println(tOrderVO.getTour_rev_note() + ", ");
			System.out.println();
		}
		
		//UPDATE_REVIEW
//		TourOrderVO tourOrderVO1 = new TourOrderVO();
//		tourOrderVO1.setTour_no("T000003");
//		tourOrderVO1.setMem_no("M000003");
//		tourOrderVO1.setTour_rev_note("XOXOXOXO");
//		tourOrderVO1.setTour_rev(4);
//		tourOrderDAO.updateReview(tourOrderVO1);
	
		
		Integer attd = tourOrderDAO.numberOfAttd("T000002");
		System.out.println(attd);
		
		Integer numReview = tourOrderDAO.countbyReviewNote("T000002");
		System.out.println(numReview);
		
		Double scoreReview = tourOrderDAO.avgTourScore("T000002");
		System.out.println(scoreReview);
		
		List<TourOrderVO> listReview = tourOrderDAO.getListByTourNo("T000002");
		for(TourOrderVO tOrderVO : listReview) {
			System.out.print(tOrderVO.getTour_rev_note() + ", ");
			System.out.print(tOrderVO.getMem_no() + ", ");
			System.out.print(tOrderVO.getTour_order_date() + ", ");
			System.out.println();
		}
	}
}
