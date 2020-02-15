package com.photo_report.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;

public class PhotoReportJNDIDAO implements PhotoReportDAO_interface {


	private static final String INSERT_STMT = "INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)"
			+ "VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),?,?,?,SYSTIMESTAMP,?)";
	private static final String UPDATE_STMT = "UPDATE PHOTO_REPORT SET photo_re_note=?,photo_re_time=?,photo_re_status=? WHERE photo_re_no=?";
	private static final String GET_ONE_STMT = "SELECT photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status FROM PHOTO_REPORT WHERE photo_re_no=?";
	private static final String GET_ALL_STMT = "SELECT photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status FROM PHOTO_REPORT ORDER BY photo_re_time DESC";
	private static final String GET_STATUS_STMT = "SELECT photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status FROM PHOTO_REPORT WHERE photo_re_status=? ORDER BY photo_re_time DESC";
	private static final String GET_PHOTO_STMT = "SELECT photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status FROM PHOTO_REPORT WHERE photo_no=?";
//SELECT DISTINCT photo_no,photo_re_no,mem_no,photo_re_note,photo_re_time,photo_re_status FROM PHOTO_REPORT where photo_re_status='0' ORDER BY photo_re_time DESC" ;
	/******************** 新增相片檢舉 ********************/
	@Override
	public void insert(PhotoReportVO photoReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, photoReportVO.getPhoto_no());
			pstmt.setString(2, photoReportVO.getMem_no());
			pstmt.setString(3, photoReportVO.getPhoto_re_note());
			pstmt.setInt(4, photoReportVO.getPhoto_re_status());

			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/******************** 修改相片檢舉 ********************/
	@Override
	public void update(PhotoReportVO photoReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, photoReportVO.getPhoto_re_note());
			pstmt.setTimestamp(2, photoReportVO.getPhoto_re_time());
			pstmt.setInt(3, photoReportVO.getPhoto_re_status());
			pstmt.setString(4, photoReportVO.getPhoto_re_no());

			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	
	
	
	
	
	
	@Override
	public void delete(String photo_re_no) {
	}

	/******************** 查詢一筆相片檢舉 ********************/
	@Override
	public PhotoReportVO findByPrimaryKey(String photo_re_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PhotoReportVO photoReportVO = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, photo_re_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoReportVO = new PhotoReportVO();
				photoReportVO.setPhoto_re_no(rs.getString("photo_re_no"));
				photoReportVO.setPhoto_no(rs.getString("photo_no"));
				photoReportVO.setMem_no(rs.getString("mem_no"));
				photoReportVO.setPhoto_re_note(rs.getString("photo_re_note"));
				photoReportVO.setPhoto_re_time(rs.getTimestamp("photo_re_time"));
				photoReportVO.setPhoto_re_status(rs.getInt("photo_re_status"));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return photoReportVO;
	}

	/******************** 查詢全部相片檢舉 ********************/
	@Override
	public List<PhotoReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PhotoReportVO> list = new ArrayList<PhotoReportVO>();
		PhotoReportVO photoReportVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoReportVO = new PhotoReportVO();
				photoReportVO.setPhoto_re_no(rs.getString("photo_re_no"));
				photoReportVO.setPhoto_no(rs.getString("photo_no"));
				photoReportVO.setMem_no(rs.getString("mem_no"));
				photoReportVO.setPhoto_re_note(rs.getString("photo_re_note"));
				photoReportVO.setPhoto_re_time(rs.getTimestamp("photo_re_time"));
				photoReportVO.setPhoto_re_status(rs.getInt("photo_re_status"));
				list.add(photoReportVO);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/****************** 狀態查詢 ***********************/
	@Override
	public List<PhotoReportVO> findByStatus(Integer photo_re_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PhotoReportVO> list = new ArrayList<PhotoReportVO>();
		PhotoReportVO photoReportVO = null;
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_STATUS_STMT);
			pstmt.setInt(1, photo_re_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoReportVO = new PhotoReportVO();
				photoReportVO.setPhoto_re_no(rs.getString("photo_re_no"));
				photoReportVO.setPhoto_no(rs.getString("photo_no"));
				photoReportVO.setMem_no(rs.getString("mem_no"));
				photoReportVO.setPhoto_re_note(rs.getString("photo_re_note"));
				String time = dateFormat.format(rs.getTimestamp("photo_re_time"));
				photoReportVO.setPhoto_re_time(Timestamp.valueOf(time));
				photoReportVO.setPhoto_re_status(rs.getInt("photo_re_status"));
				list.add(photoReportVO);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	/****************** 檢舉圖片查詢 ***********************/
	@Override
	public List<PhotoReportVO> findByPhoto(String photo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PhotoReportVO> list = new ArrayList<PhotoReportVO>();
		PhotoReportVO photoReportVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_PHOTO_STMT);
			pstmt.setString(1, photo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoReportVO = new PhotoReportVO();
				photoReportVO.setPhoto_re_no(rs.getString("photo_re_no"));
				photoReportVO.setPhoto_no(rs.getString("photo_no"));
				photoReportVO.setMem_no(rs.getString("mem_no"));
				photoReportVO.setPhoto_re_note(rs.getString("photo_re_note"));
				photoReportVO.setPhoto_re_time(rs.getTimestamp("photo_re_time"));
				photoReportVO.setPhoto_re_status(rs.getInt("photo_re_status"));
				list.add(photoReportVO);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	

	/********************* 測試區 ************************/
	public static void main(String[] args) {
		PhotoReportJNDIDAO photoReportDAO = new PhotoReportJNDIDAO();

		/************** 新增測試 *****************/
//		PhotoReportVO photoReportVO = new PhotoReportVO();
//		photoReportVO.setPhoto_no("P000001");
//		photoReportVO.setMem_no("M000001");
//		photoReportVO.setPhoto_re_note("你好嗎?");
//		photoReportVO.setPhoto_re_status(0);
//		photoReportDAO.insert(photoReportVO);

		/************** 修改測試 *****************/
//		photoReportVO photoReportVO2=photoReportDAO.findByPrimaryKey("PR00002");
//		photoReportVO2.setPhoto_re_note("不太好");
//		photoReportDAO.update(photoReportVO2);

		/************** 查詢測試 *****************/
//		PhotoReportVO photoReportVO2=photoReportDAO.findByPrimaryKey("PR00002");
//		System.out.println(photoReportVO2);

		/************** 全查測試 *****************/
		List<PhotoReportVO>ll=new ArrayList();
		ll=photoReportDAO.findByPhoto("P000071");
		for(PhotoReportVO photoReportVO1 : ll) {
			System.out.println(photoReportVO1.getPhoto_re_no());
		}
	}
}
