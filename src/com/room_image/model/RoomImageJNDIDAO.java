package com.room_image.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;
import com.util.MyUtil;

public class RoomImageJNDIDAO implements RoomImageDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO, ROOM_IMG) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), ?, ?)";

	private static final String GET_ALL_STMT = 
			"SELECT ROOM_IMG_NO, TOUR_ROOM_NO, ROOM_IMG FROM ROOM_IMAGE";

	private static final String GET_ONE_STMT =
			"SELECT ROOM_IMG_NO, TOUR_ROOM_NO, ROOM_IMG FROM ROOM_IMAGE WHERE ROOM_IMG_NO = ?";

	private static final String DELETE = 
			"DELETE FROM ROOM_IMAGE WHERE ROOM_IMG_NO = ?";

	private static final String UPDATE = 
			"UPDATE ROOM_IMAGE SET TOUR_ROOM_NO = ?, ROOM_IMG = ? WHERE ROOM_IMG_NO = ?";

	private static final String GET_PICBYROOMNO = 
			"SELECT ROOM_IMG_NO, TOUR_ROOM_NO, ROOM_IMG FROM ROOM_IMAGE WHERE TOUR_ROOM_NO = ?";
	


	@Override
	public void insert(RoomImageVO roomImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roomImageVO.getTour_room_no());
			pstmt.setBytes(2, roomImageVO.getRoom_img());

			pstmt.executeUpdate();

		} catch(SQLException se) {
			se.printStackTrace();
	
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
	public void update(RoomImageVO roomImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, roomImageVO.getTour_room_no());
			pstmt.setBytes(2, roomImageVO.getRoom_img());
			pstmt.setString(3, roomImageVO.getRoom_img_no());

			pstmt.executeUpdate();

		} catch(SQLException se) {
			se.printStackTrace();
		
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
	public void delete(String room_img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, room_img_no);
			pstmt.executeUpdate();

		
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

	@Override
	public RoomImageVO findByPrimaryKey(String room_img_no) {
		RoomImageVO roomImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {		
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, room_img_no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				roomImageVO = new RoomImageVO();
				roomImageVO.setRoom_img_no(rs.getString("room_img_no"));
				roomImageVO.setTour_room_no(rs.getString("tour_room_no"));
				roomImageVO.setRoom_img(rs.getBytes("room_img"));	
			}

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

		return roomImageVO;
	}

	@Override
	public List<RoomImageVO> getAll() {
		List<RoomImageVO> list = new ArrayList<RoomImageVO>();
		RoomImageVO roomImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				roomImageVO = new RoomImageVO();
				roomImageVO.setRoom_img_no(rs.getString("room_img_no"));
				roomImageVO.setTour_room_no(rs.getString("tour_room_no"));
				roomImageVO.setRoom_img(rs.getBytes("room_img"));
				list.add(roomImageVO);
			}

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
	

	public void rImgInsertWithTourRoomNo(RoomImageVO roomImageVO, Connection con) {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roomImageVO.getTour_room_no());
			pstmt.setBytes(2, roomImageVO.getRoom_img());

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

	public List<RoomImageVO> findbyTourRoomNo(String tour_room_no){
		
		List<RoomImageVO> list = new ArrayList<RoomImageVO>();
		RoomImageVO roomImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_PICBYROOMNO);
			
			pstmt.setString(1, tour_room_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				roomImageVO = new RoomImageVO();
				roomImageVO.setRoom_img_no(rs.getString("room_img_no"));
				roomImageVO.setTour_room_no(rs.getString("tour_room_no"));
				roomImageVO.setRoom_img(rs.getBytes("room_img"));	
				list.add(roomImageVO);
			}
	
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


	public static void main(String[] args) {
		RoomImageJNDIDAO roomImageDAO = new RoomImageJNDIDAO();

		//新增
		//		RoomImageVO roomImageVO = new RoomImageVO();
		//		roomImageVO.setTour_room_no("TRM000001");
		//		roomImageVO.setRoom_img(MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\room\\room1.jpg"));
		//		roomImageDAO.insert(roomImageVO);

		//修改
		//		RoomImageVO roomImageVO1 = new RoomImageVO();
		//		roomImageVO1.setRoom_img_no("RI000003");
		//		roomImageVO1.setTour_room_no("TRM000003");
		//		roomImageVO1.setRoom_img(MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\room\\room3.jpg"));
		//		roomImageDAO.update(roomImageVO1);

		//刪除
		//		roomImageDAO.delete("RI000004");

//		//查詢(PK)
//		RoomImageVO roomImageVO2 = roomImageDAO.findByPrimaryKey("RI000003");
//		System.out.print(roomImageVO2.getRoom_img_no() + ", ");
//		System.out.print(roomImageVO2.getTour_room_no() + ", ");
//		System.out.println(roomImageVO2.getRoom_img());
//		System.out.println("---------------------");
//
//		//查詢(ALL)
//		List<RoomImageVO> list = roomImageDAO.getAll();
//		for(RoomImageVO rImageVO : list) {
//			System.out.print(rImageVO.getRoom_img_no() + ", ");
//			System.out.print(rImageVO.getTour_room_no() + ", ");
//			System.out.print(rImageVO.getRoom_img());
//			System.out.println();
//		}
		
		List<RoomImageVO> list2 = roomImageDAO.findbyTourRoomNo("TRM000009");
		for(RoomImageVO rImg : list2) {
			System.out.println(rImg.getRoom_img());
		}
	}

}
