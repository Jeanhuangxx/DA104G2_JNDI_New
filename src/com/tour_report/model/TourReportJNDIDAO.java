package com.tour_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;

public class TourReportJNDIDAO implements TourReportDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO TOUR_REPORT (TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE) \r\n" + 
			"VALUES ('TR'||LPAD(TO_CHAR(TOUR_REPORT_SEQ.NEXTVAL), 6, '0'),  ?,  ?,  ?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_TIME, TOUR_RE_STATUS FROM TOUR_REPORT";
	
	private static final String GET_ONE_STMT =
			"SELECT TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_TIME, TOUR_RE_STATUS FROM TOUR_REPORT WHERE TOUR_RE_NO = ?";
	
	private static final String UPDATE = 
			"UPDATE TOUR_REPORT SET TOUR_NO = ?, MEM_NO = ?, TOUR_RE_NOTE = ?, TOUR_RE_STATUS = ? WHERE TOUR_RE_NO = ?";
	
	private static final String GET_ONE_STMT_MEM_TOUR = 
			"SELECT * FROM TOUR_REPORT WHERE TOUR_NO = ? AND MEM_NO = ?";
	
	private static final String UPDATE_REPORT_STATUS = 
			"UPDATE TOUR_REPORT SET TOUR_RE_STATUS = ? WHERE TOUR_NO = ? AND MEM_NO = ?";
	@Override
	public void insert(TourReportVO tourReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tourReportVO.getTour_no());
			pstmt.setString(2, tourReportVO.getMem_no());
			pstmt.setString(3, tourReportVO.getTour_re_note());
			
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
	public void update(TourReportVO tourReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, tourReportVO.getTour_no());
			pstmt.setString(2, tourReportVO.getMem_no());
			pstmt.setString(3, tourReportVO.getTour_re_note());
			pstmt.setInt(4, tourReportVO.getTour_re_status());
			pstmt.setString(5,  tourReportVO.getTour_re_no());
			
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
	
	public void updateReviewStatus (TourReportVO tourReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE_REPORT_STATUS);
			
			pstmt.setInt(1, tourReportVO.getTour_re_status());
			pstmt.setString(2,  tourReportVO.getTour_no());
			pstmt.setString(3, tourReportVO.getMem_no());
			
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
	public TourReportVO findByPrimaryKey(String tour_re_no) {
		TourReportVO tourReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,  tour_re_no);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				tourReportVO = new TourReportVO();
				tourReportVO.setTour_re_no(rs.getString("tour_re_no"));
				tourReportVO.setTour_no(rs.getString("tour_no"));
				tourReportVO.setMem_no(rs.getString("mem_no"));
				tourReportVO.setTour_re_note(rs.getString("tour_re_note"));
				tourReportVO.setTour_re_time(rs.getTimestamp("tour_re_time"));
				tourReportVO.setTour_re_status(rs.getInt("tour_re_status"));
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
		return tourReportVO;
	}
	
	public TourReportVO findbyMemNOTourNO(String mem_no, String tour_no) {
		TourReportVO tourReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_TOUR);

			pstmt.setString(1, tour_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourReportVO = new TourReportVO();
				tourReportVO.setTour_re_no(rs.getString("tour_re_no"));
				tourReportVO.setTour_no(rs.getString("tour_no"));
				tourReportVO.setMem_no(rs.getString("mem_no"));
				tourReportVO.setTour_re_note(rs.getString("tour_re_note"));
				tourReportVO.setTour_re_time(rs.getTimestamp("tour_re_time"));
				tourReportVO.setTour_re_status(rs.getInt("tour_re_status"));
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
		return tourReportVO;
	}

	@Override
	public List<TourReportVO> getAll() {
		List<TourReportVO> list = new ArrayList<TourReportVO>();
		TourReportVO tourReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tourReportVO = new TourReportVO();
				tourReportVO.setTour_re_no(rs.getString("tour_re_no"));
				tourReportVO.setTour_no(rs.getString("tour_no"));
				tourReportVO.setMem_no(rs.getString("mem_no"));
				tourReportVO.setTour_re_note(rs.getString("tour_re_note"));
				tourReportVO.setTour_re_time(rs.getTimestamp("tour_re_time"));
				tourReportVO.setTour_re_status(rs.getInt("tour_re_status"));
				list.add(tourReportVO);
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
		TourReportJNDIDAO tourReportDAO = new TourReportJNDIDAO();
		
		//新增
//		TourReportVO tourReportVO = new TourReportVO();
//		tourReportVO.setTour_no("T000006");
//		tourReportVO.setMem_no("M000005");
//		tourReportVO.setTour_re_note("XDDDDDDDDDDDDDDDDDDDD");
//		tourReportDAO.insert(tourReportVO);
//		
//		//修改
//		TourReportVO tourReportVO1 = new TourReportVO();
//		tourReportVO1.setTour_re_no("TR000001");
//		tourReportVO1.setTour_no("T000004");
//		tourReportVO1.setMem_no("M000001");
//		tourReportVO1.setTour_re_note("55787855555");
//		tourReportVO1.setTour_re_status(0);
//		tourReportDAO.update(tourReportVO1);
		
////		//查詢(primary key)
//		TourReportVO tourReportVO2 = tourReportDAO.findByPrimaryKey("TR000003");
//		System.out.print(tourReportVO2.getTour_re_no() + ", ");
//		System.out.print(tourReportVO2.getTour_no() + ", ");
//		System.out.print(tourReportVO2.getMem_no() + ", ");
//		System.out.print(tourReportVO2.getTour_re_note() + ", ");
//		System.out.print(tourReportVO2.getTour_re_time() + ", ");
//		System.out.println(tourReportVO2.getTour_re_status() + ", ");
//		
//		System.out.println("--------------");
//		
//		//查詢(get all)
//		List<TourReportVO> list = tourReportDAO.getAll();
//		for(TourReportVO tReportVO : list) {
//			System.out.print(tReportVO.getTour_re_no() + ", ");
//			System.out.print(tReportVO.getTour_no() + ", ");
//			System.out.print(tReportVO.getMem_no() + ", ");
//			System.out.print(tReportVO.getTour_re_note() + ", ");
//			System.out.print(tReportVO.getTour_re_time() + ", ");
//			System.out.print(tReportVO.getTour_re_status() + ", ");
//			System.out.println();
//		}
		
		System.out.println("--------------");
		
		//find by mem_no and tour_no 
//		TourReportVO tourReportVO3 = tourReportDAO.findbyMemNOTourNO("M000002", "T000002");
//		System.out.print(tourReportVO3.getTour_re_no() + ", ");
//		System.out.print(tourReportVO3.getTour_no() + ", ");
//		System.out.print(tourReportVO3.getMem_no() + ", ");
//		System.out.print(tourReportVO3.getTour_re_note() + ", ");
//		System.out.print(tourReportVO3.getTour_re_time() + ", ");
//		System.out.println(tourReportVO3.getTour_re_status() + ", ");
		
		TourReportVO tourReportVO1 = new TourReportVO();
		tourReportVO1.setTour_no("T000004");
		tourReportVO1.setMem_no("M000001");
		tourReportVO1.setTour_re_status(1);
		tourReportDAO.updateReviewStatus(tourReportVO1);
		
	}

}
