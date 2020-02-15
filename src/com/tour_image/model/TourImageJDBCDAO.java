package com.tour_image.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.MyUtil;

public class TourImageJDBCDAO implements TourImageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String psw = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO, TOUR_IMG) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), ?, ?)";

	private static final String GET_ALL_STMT = 
			"SELECT TOUR_IMG_NO, TOUR_NO, TOUR_IMG FROM TOUR_IMAGE";

	private static final String GET_ONE_STMT =
			"SELECT TOUR_IMG_NO, TOUR_NO, TOUR_IMG FROM TOUR_IMAGE WHERE TOUR_IMG_NO = ?";

	private static final String DELETE = 
			"DELETE FROM TOUR_IMAGE WHERE TOUR_IMG_NO = ?";

	private static final String UPDATE = 
			"UPDATE TOUR_IMAGE SET TOUR_NO = ?, TOUR_IMG = ? WHERE TOUR_IMG_NO = ?";
	
	private static final String GET_TIMG_LIST = 
			"SELECT TOUR_IMG_NO, TOUR_NO, TOUR_IMG FROM TOUR_IMAGE WHERE TOUR_NO = ?";

	@Override
	public void insert(TourImageVO tourImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tourImageVO.getTour_no());
			pstmt.setBytes(2, tourImageVO.getTour_img());

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
	public void update(TourImageVO tourImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tourImageVO.getTour_no());
			pstmt.setBytes(2, tourImageVO.getTour_img());
			pstmt.setString(3, tourImageVO.getTour_img_no());

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
	public TourImageVO findByPrimaryKey(String tour_img_no) {
		TourImageVO tourImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tour_img_no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				tourImageVO = new TourImageVO();
				tourImageVO.setTour_img_no(rs.getString("tour_img_no"));
				tourImageVO.setTour_no(rs.getString("tour_no"));
				tourImageVO.setTour_img(rs.getBytes("tour_img"));
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

		return tourImageVO;
	}
	
	public List<TourImageVO> imgFindbyTourNo (String tour_no){
		List<TourImageVO> list = new ArrayList<TourImageVO>();
		TourImageVO tourImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_TIMG_LIST);
			
			pstmt.setString(1, tour_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourImageVO = new TourImageVO();
				tourImageVO.setTour_img_no(rs.getString("tour_img_no"));
				tourImageVO.setTour_no(rs.getString("tour_no"));
				tourImageVO.setTour_img(rs.getBytes("tour_img"));
				list.add(tourImageVO);
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

	@Override
	public List<TourImageVO> getAll() {
		List<TourImageVO> list = new ArrayList<TourImageVO>();
		TourImageVO tourImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				tourImageVO = new TourImageVO();
				tourImageVO.setTour_img_no(rs.getString("tour_img_no"));
				tourImageVO.setTour_no(rs.getString("tour_no"));
				tourImageVO.setTour_img(rs.getBytes("tour_img"));
				list.add(tourImageVO);
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

	@Override
	public void delete(String tour_img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tour_img_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void tImgInsertWithTourNo(TourImageVO tourImageVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
		
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tourImageVO.getTour_no());
			pstmt.setBytes(2, tourImageVO.getTour_img());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException sqle) {
					throw new RuntimeException("Rollback error occured" + sqle.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TourImageJDBCDAO tourImageDAO = new TourImageJDBCDAO();
		
		//新增
//		TourImageVO tourImageVO = new TourImageVO();
//		tourImageVO.setTour_no("T000002");
//		tourImageVO.setTour_img(MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\tour_img\\boat1.jpg"));
//		tourImageDAO.insert(tourImageVO);
//		
		//修改
//		TourImageVO tourImageVO1 = new TourImageVO();
//		tourImageVO1.setTour_img_no("TI000007");
//		tourImageVO1.setTour_no("T000002");
//		tourImageVO1.setTour_img(MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\tour_img\\boat3.jpg"));
//		tourImageDAO.update(tourImageVO1);
		
		//刪除
//		tourImageDAO.delete("TI000003");
//		
//		//查詢(PK)
		TourImageVO tourImageVO2 = tourImageDAO.findByPrimaryKey("TI000005");
		System.out.print(tourImageVO2.getTour_img_no() + ", ");
		System.out.print(tourImageVO2.getTour_no() + ", ");
		System.out.println(tourImageVO2.getTour_img());
		
		System.out.println("----------------");
		
//		//查詢(ALL)
//		List<TourImageVO> list = tourImageDAO.getAll();
//		for(TourImageVO tImageVO : list) {
//			System.out.print(tImageVO.getTour_img_no() + ", ");
//			System.out.print(tImageVO.getTour_no() + ", ");
//			System.out.print(tImageVO.getTour_img());
//			System.out.println();
//		}
		
		List<TourImageVO> list = tourImageDAO.imgFindbyTourNo("T000005");
		for(TourImageVO tImageVO : list) {
			System.out.print(tImageVO.getTour_img_no() + ", ");
			System.out.print(tImageVO.getTour_no() + ", ");
			System.out.print(tImageVO.getTour_img());
			System.out.println();
		}
	}

}
