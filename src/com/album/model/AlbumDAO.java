package com.album.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.MyUtil;

public class AlbumDAO implements AlbumDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)"
			+ "VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),?,?,SYSTIMESTAMP,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ALBUM SET album_name=?,album_pic=?,album_note=?,album_time=SYSTIMESTAMP,album_status=? WHERE album_no=?";
	private static final String GET_ONE_STMT = "SELECT album_no,mem_no,album_name,album_note,album_pic,album_time,album_status FROM ALBUM WHERE album_no=?";
	private static final String GET_ALL_STMT = "SELECT album_no,mem_no,album_name,album_note,album_pic,album_time,album_status FROM ALBUM ORDER BY album_no DESC";

	/*========================新增相簿==========================*/
	@Override
	public void insert(AlbumVO albumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			/********************** 新增相簿資料 *********************/
			pstmt.setString(1, albumVO.getMem_no());
			pstmt.setString(2, albumVO.getAlbum_name());
			pstmt.setBytes(3, albumVO.getAlbum_pic());
			pstmt.setString(4, albumVO.getAlbum_note());
			pstmt.setInt(5, albumVO.getAlbum_status());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	/*-------------------------------------------------------------*/
	
	/*========================== 修改相簿 ===========================*/
	@Override
	public void update(AlbumVO albumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			/********************** 更新相簿 *********************/
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, albumVO.getAlbum_name());
			pstmt.setBytes(2, albumVO.getAlbum_pic());
			pstmt.setString(3, albumVO.getAlbum_note());
			pstmt.setInt(4, albumVO.getAlbum_status());
			pstmt.setString(5, albumVO.getAlbum_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	
	/*----------------------刪除相簿---------------------------*/
	@Override
	public void delete(String album_no) {

	}

	/*=========================查詢單一相簿 ========================*/
	@Override
	public AlbumVO findByPrimaryKey(String album_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AlbumVO albumVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, album_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				albumVO = new AlbumVO();
				albumVO.setAlbum_no(rs.getString("album_no"));
				albumVO.setMem_no(rs.getString("mem_no"));
				albumVO.setAlbum_name(rs.getString("album_name"));
				albumVO.setAlbum_note(rs.getString("album_note"));
				albumVO.setAlbum_pic(rs.getBytes("album_pic"));
				albumVO.setAlbum_time(rs.getTimestamp("album_time"));
				albumVO.setAlbum_status(rs.getInt("album_status"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return albumVO;
	}

	/*==========================查詢所有相簿========================*/
	@Override
	public List<AlbumVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		ResultSet rs = null;
		AlbumVO albumVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				albumVO = new AlbumVO();
				albumVO.setAlbum_no(rs.getString("album_no"));
				albumVO.setMem_no(rs.getString("mem_no"));
				albumVO.setAlbum_name(rs.getString("album_name"));
				albumVO.setAlbum_note(rs.getString("album_note"));
				albumVO.setAlbum_pic(rs.getBytes("album_pic"));
				albumVO.setAlbum_time(rs.getTimestamp("album_time"));
				albumVO.setAlbum_status(rs.getInt("album_status"));
				list.add(albumVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

	/*============================測試區 ===============================*/
	public static void main(String[] args) {
		AlbumDAO albumDAO = new AlbumDAO();
		/************** 新增測試 *****************/
		AlbumVO albumVO = new AlbumVO();
		for (int i = 1; i < 20; i++) {

			albumVO.setMem_no("M00000" + (int) (Math.floor(Math.random() * 5) + 1));
			albumVO.setAlbum_name("好冷");
			albumVO.setAlbum_pic(MyUtil
					.pathToByteArray("D:/DA104G2/workspace/DA104G2_1224/WebContent/images/mike1.jpg"));
			albumVO.setAlbum_note("超冷");
			albumVO.setAlbum_status(0);
			albumDAO.insert(albumVO);
		}

		/************** 修改測試 *****************/
//		AlbumVO albumVO1 = new AlbumVO();
//		for (int j = 1; j <= 10; j++) {
//			if (j >= 10) {
//				 albumVO1 = albumDAO.findByPrimaryKey("A0000" + j);
//			} else {
//				 albumVO1 = albumDAO.findByPrimaryKey("A00000"+j);
//			}
//			albumVO1.setAlbum_no(albumVO1.getAlbum_no());
//			albumVO1.setAlbum_name(albumVO1.getAlbum_name());
//			albumVO1.setAlbum_pic(MyUtil.pathToByteArray("D:/DA104G2/workspace/DA104G2_1216/WebContent/front-end/images/diving-in-phuket.jpg"));
//			albumVO1.setAlbum_note(albumVO1.getAlbum_note());
//			albumDAO.update(albumVO1);
//		}
		/************** 查詢測試 *****************/
//		AlbumVO albumVO1=albumDAO.findByPrimaryKey("A000001");
//		System.out.println(albumVO1.getAlbum_no());
//		System.out.println(albumVO1.getMem_no());
//		System.out.println(albumVO1.getAlbum_name());
//		System.out.println(albumVO1.getAlbum_pic());
//		System.out.println(albumVO1.getAlbum_note());
//		System.out.println(albumVO1.getAlbum_time());

		/************** 全查測試 *****************/
//		List<AlbumVO> ll = albumDAO.getAll();
//		for (AlbumVO albumVO : ll) {
//			System.out.println(albumVO.getAlbum_name());
//		}
	}

}
