package com.tour_room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.room_image.model.RoomImageJDBCDAO;
import com.room_image.model.RoomImageVO;

public class TourRoomJDBCDAO implements TourRoomDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String psw = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) \r\n" + 
					"VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = 
			"SELECT TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON FROM TOUR_ROOM";

	private static final String GET_ONE_STMT = 
			"SELECT TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON FROM TOUR_ROOM WHERE TOUR_ROOM_NO = ?";

	private static final String UPDATE = 
			"UPDATE TOUR_ROOM SET BED_SIZE = ?, ROOM_PPL = ?, ROOM_PRIV_BR = ?, ROOM_AIRCON = ? WHERE TOUR_NO = ?";

	private static final String GET_ONE_TOURNO = 
			"SELECT TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON FROM TOUR_ROOM WHERE TOUR_NO = ?";
	
	@Override
	public void insert(TourRoomVO tourRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tourRoomVO.getTour_no());
			pstmt.setInt(2, tourRoomVO.getBed_size());
			pstmt.setInt(3, tourRoomVO.getRoom_ppl());
			pstmt.setInt(4, tourRoomVO.getRoom_priv_br());
			pstmt.setInt(5, tourRoomVO.getRoom_aircon());

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
	public void update(TourRoomVO tourRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tourRoomVO.getBed_size());
			pstmt.setInt(2, tourRoomVO.getRoom_ppl());
			pstmt.setInt(3, tourRoomVO.getRoom_priv_br());
			pstmt.setInt(4, tourRoomVO.getRoom_aircon());
			pstmt.setString(5, tourRoomVO.getTour_no());

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
	public TourRoomVO findByPrimaryKey(String tour_room_no) {
		TourRoomVO tourRoomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, tour_room_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourRoomVO = new TourRoomVO();
				tourRoomVO.setTour_room_no(rs.getString("tour_room_no"));
				tourRoomVO.setTour_no(rs.getString("tour_no"));
				tourRoomVO.setBed_size(rs.getInt("bed_size"));
				tourRoomVO.setRoom_ppl(rs.getInt("room_ppl"));
				tourRoomVO.setRoom_priv_br(rs.getInt("room_priv_br"));
				tourRoomVO.setRoom_aircon(rs.getInt("room_aircon"));
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
		return tourRoomVO;
	}
	
	public TourRoomVO findbyTourNo(String tour_no) {
		TourRoomVO tourRoomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_TOURNO);
			
			pstmt.setString(1, tour_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourRoomVO = new TourRoomVO();
				tourRoomVO.setTour_room_no(rs.getString("tour_room_no"));
				tourRoomVO.setTour_no(rs.getString("tour_no"));
				tourRoomVO.setBed_size(rs.getInt("bed_size"));
				tourRoomVO.setRoom_ppl(rs.getInt("room_ppl"));
				tourRoomVO.setRoom_priv_br(rs.getInt("room_priv_br"));
				tourRoomVO.setRoom_aircon(rs.getInt("room_aircon"));
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
		return tourRoomVO;
	}

	@Override
	public List<TourRoomVO> getAll() {
		List<TourRoomVO> list = new ArrayList<TourRoomVO>();
		TourRoomVO tourRoomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				tourRoomVO = new TourRoomVO();
				tourRoomVO.setTour_room_no(rs.getString("tour_room_no"));
				tourRoomVO.setTour_no(rs.getString("tour_no"));
				tourRoomVO.setBed_size(rs.getInt("bed_size"));
				tourRoomVO.setRoom_ppl(rs.getInt("room_ppl"));
				tourRoomVO.setRoom_priv_br(rs.getInt("room_priv_br"));
				tourRoomVO.setRoom_aircon(rs.getInt("room_aircon"));
				list.add(tourRoomVO);
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
	
	public void tRoomInsertWithTourNo(TourRoomVO tourRoomVO, List<RoomImageVO> roomImageList, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			String cols[] = {"TOUR_ROOM_NO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, tourRoomVO.getTour_no());
			pstmt.setInt(2, tourRoomVO.getBed_size());
			pstmt.setInt(3, tourRoomVO.getRoom_ppl());
			pstmt.setInt(4, tourRoomVO.getRoom_priv_br());
			pstmt.setInt(5, tourRoomVO.getRoom_aircon());
			
			pstmt.executeUpdate();
			
			String next_tourRoomNO = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_tourRoomNO = rs.getString(1);
			}
			
			//新增房間相片
			RoomImageJDBCDAO rImageDAO = new RoomImageJDBCDAO();
			for(RoomImageVO rImg : roomImageList) {
				rImg.setTour_room_no(next_tourRoomNO);
				rImageDAO.rImgInsertWithTourRoomNo(rImg, con);
			}
			
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
		TourRoomJDBCDAO tourRoomDAO = new TourRoomJDBCDAO();
		
		//新增
//		TourRoomVO tourRoomVO = new TourRoomVO();
//		tourRoomVO.setTour_no("T000004");
//		tourRoomVO.setBed_size(0);
//		tourRoomVO.setRoom_ppl(4);
//		tourRoomVO.setRoom_priv_br(1);
//		tourRoomVO.setRoom_aircon(1);
//		tourRoomDAO.insert(tourRoomVO);
		
		//修改
//		TourRoomVO tourRoomVO1 = new TourRoomVO();
//		tourRoomVO1.setTour_room_no("TRM000007");
//		tourRoomVO1.setTour_no("T000004");
//		tourRoomVO1.setBed_size(0);
//		tourRoomVO1.setRoom_ppl(8);
//		tourRoomVO1.setRoom_priv_br(0);
//		tourRoomVO1.setRoom_aircon(0);
//		tourRoomDAO.update(tourRoomVO1);
		
		//查詢(primary key)
		TourRoomVO tourRoomVO2 = tourRoomDAO.findByPrimaryKey("TRM000005");
		System.out.print(tourRoomVO2.getTour_room_no() + ", ");
		System.out.print(tourRoomVO2.getTour_no() + ", ");
		System.out.print(tourRoomVO2.getBed_size() + ", ");
		System.out.print(tourRoomVO2.getRoom_ppl() + ", ");
		System.out.print(tourRoomVO2.getRoom_priv_br() + ", ");
		System.out.println(tourRoomVO2.getRoom_aircon());
		
		System.out.println("-------------");
		
		//查詢(get all)
		List<TourRoomVO> list = tourRoomDAO.getAll();
		for(TourRoomVO tRoomVO : list) {
			System.out.print(tRoomVO.getTour_room_no() + ", ");
			System.out.print(tRoomVO.getTour_no() + ", ");
			System.out.print(tRoomVO.getBed_size() + ", ");
			System.out.print(tRoomVO.getRoom_ppl() + ", ");
			System.out.print(tRoomVO.getRoom_priv_br() + ", ");
			System.out.print(tRoomVO.getRoom_aircon());
			System.out.println();
		}
	
		
	}

}
