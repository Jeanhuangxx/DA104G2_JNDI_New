package com.tour_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;


public class TourLikeJNDIDAO implements TourLikeDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES (?, ?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT TOUR_NO, MEM_NO FROM TOUR_LIKE";
	
	private static final String GET_ONE_STMT =
			"SELECT TOUR_NO, MEM_NO FROM TOUR_LIKE WHERE TOUR_NO = ? AND MEM_NO = ?";
	
	private static final String DELETE = 
			"DELETE FROM TOUR_LIKE WHERE TOUR_NO = ? AND MEM_NO = ?";
	
	private static final String UPDATE = 
			"UPDATE TOUR_LIKE SET TOUR_NO = ?, MEM_NO = ? WHERE TOUR_NO = ? AND MEM_NO = ?";

	private static final String GET_LISTBYMEM = 
			"SELECT TOUR_NO, MEM_NO FROM TOUR_LIKE WHERE MEM_NO = ?";
	
	@Override
	public void insert(TourLikeVO tourLikeVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tourLikeVO.getTour_no());
			pstmt.setString(2, tourLikeVO.getMem_no());
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
	public void update(TourLikeVO tourLikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, tourLikeVO.getTour_no());
			pstmt.setString(2, tourLikeVO.getMem_no());
			pstmt.setString(3, tourLikeVO.getTour_no());
			pstmt.setString(2, tourLikeVO.getMem_no());
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
	public void delete(String tour_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, tour_no);
			pstmt.setString(2, mem_no);
			
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
	public TourLikeVO findByPrimaryKey(String tour_no, String mem_no) {
		TourLikeVO tourLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, tour_no);
			pstmt.setString(2, mem_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourLikeVO = new TourLikeVO();
				tourLikeVO.setTour_no(rs.getString("tour_no"));
				tourLikeVO.setMem_no(rs.getString("mem_no"));
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
				
		return tourLikeVO;
	}

	@Override
	public List<TourLikeVO> getAll() {
		List<TourLikeVO> list = new ArrayList<TourLikeVO>();
		TourLikeVO tourLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourLikeVO = new TourLikeVO();
				tourLikeVO.setTour_no(rs.getString("tour_no"));
				tourLikeVO.setMem_no(rs.getString("mem_no"));
				list.add(tourLikeVO);
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
	
	
	public List<TourLikeVO> findbyMemNo(String mem_no){
		List<TourLikeVO> list = new ArrayList<TourLikeVO>();
		TourLikeVO tourLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_LISTBYMEM);
			
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourLikeVO = new TourLikeVO();
				tourLikeVO.setTour_no(rs.getString("tour_no"));
				tourLikeVO.setMem_no(rs.getString("mem_no"));
				list.add(tourLikeVO);
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
	
	public static void main (String[] args) {
		TourLikeJNDIDAO tourLikeDAO = new TourLikeJNDIDAO();
		
		//新增
//		TourLikeVO tourLikeVO = new TourLikeVO();
//		tourLikeVO.setMem_no("M000006");
//		tourLikeVO.setTour_no("T000003");
//		tourLikeDAO.insert(tourLikeVO);
		
//		//修改
//		TourLikeVO tourLikeVO1 = new TourLikeVO();
//		tourLikeVO1.setMem_no("");
//		tourLikeVO1.setTour_no("");
		
		//刪除
//		tourLikeDAO.delete("T000003", "M000001");
		
		
		//查詢(PK)
		TourLikeVO tourLikeVO2 = tourLikeDAO.findByPrimaryKey("T000001", "M000006");
		System.out.print(tourLikeVO2.getTour_no() + ", ");
		System.out.println(tourLikeVO2.getMem_no());
		
		System.out.println("----------------------");
		
		//查詢(ALL)
		List<TourLikeVO> list = tourLikeDAO.getAll();
		for(TourLikeVO tLikeVO : list) {
			System.out.print(tLikeVO.getTour_no() + ", ");
			System.out.print(tLikeVO.getMem_no() + ", ");
			System.out.println();
		}
		
		System.out.println("-------------------------");
		
		List<TourLikeVO> list2 = tourLikeDAO.findbyMemNo("M000003");
		for(TourLikeVO tLikeVO : list2) {
			System.out.print(tLikeVO.getTour_no() + ", ");
		}
	}
}

