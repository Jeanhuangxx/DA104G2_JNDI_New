package com.photo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.album.model.AlbumVO;
import com.util.MyDataSource;
import com.util.MyUtil;

public class PhotoJNDIDAO implements PhotoDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)"
			+ "VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),?,?,SYSTIMESTAMP,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PHOTO SET photo_name=?,photo_time=SYSTIMESTAMP,photo_pic=?,photo_note=?,photo_status=? WHERE photo_no=?";
	private static final String GET_ONE_STMT = "SELECT photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status FROM PHOTO WHERE photo_no=?";
	private static final String GET_ALL_STMT = "SELECT photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status FROM PHOTO ORDER BY photo_no DESC";
	private static final String GET_ALBUM_STMT = "SELECT photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status FROM PHOTO WHERE album_no=? AND photo_status=0 ORDER BY photo_no DESC";

	/*********************** 新增相片 ********************/
	@Override
	public void insert(PhotoVO photoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/*********************** 新增相片資料 ********************/
			pstmt.setString(1, photoVO.getAlbum_no());
			pstmt.setString(2, photoVO.getPhoto_name());
			pstmt.setBytes(3, photoVO.getPhoto_pic());
			pstmt.setString(4, photoVO.getPhoto_note());
			pstmt.setInt(5, photoVO.getPhoto_status());

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

	/*********************** 修改相片 ********************/
	@Override
	public void update(PhotoVO photoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			/*********************** 更新相片 ********************/
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, photoVO.getPhoto_name());
			pstmt.setBytes(2, photoVO.getPhoto_pic());
			pstmt.setString(3, photoVO.getPhoto_note());
			pstmt.setInt(4, photoVO.getPhoto_status());
			pstmt.setString(5, photoVO.getPhoto_no());

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
	public void delete(String photo_no) {
		// TODO Auto-generated method stub

	}

	/*********************** 查詢單一相片 ********************/
	@Override
	public PhotoVO findByPrimaryKey(String photo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PhotoVO photoVO = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, photo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setAlbum_no(rs.getString("album_no"));
				photoVO.setPhoto_name(rs.getString("photo_name"));
				photoVO.setPhoto_time(rs.getTimestamp("photo_time"));
				photoVO.setPhoto_pic(rs.getBytes("photo_pic"));
				photoVO.setPhoto_note(rs.getString("photo_note"));
				photoVO.setPhoto_status(rs.getInt("photo_status"));
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
		return photoVO;
	}

	/*********************** 查詢全部相片 ********************/
	@Override
	public List<PhotoVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setAlbum_no(rs.getString("album_no"));
				photoVO.setPhoto_name(rs.getString("photo_name"));
				photoVO.setPhoto_time(rs.getTimestamp("photo_time"));
				photoVO.setPhoto_pic(rs.getBytes("photo_pic"));
				photoVO.setPhoto_note(rs.getString("photo_note"));
				photoVO.setPhoto_status(rs.getInt("photo_status"));
				list.add(photoVO);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return list;
	}

	/*********************** 查詢全部相片 ********************/
	@Override
	public List<PhotoVO> getAll(String album_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALBUM_STMT);
			pstmt.setString(1, album_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setAlbum_no(rs.getString("album_no"));
				photoVO.setPhoto_name(rs.getString("photo_name"));
				photoVO.setPhoto_time(rs.getTimestamp("photo_time"));
				photoVO.setPhoto_pic(rs.getBytes("photo_pic"));
				photoVO.setPhoto_note(rs.getString("photo_note"));
				photoVO.setPhoto_status(rs.getInt("photo_status"));
				list.add(photoVO);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return list;
	}

	/********************* 測試區 ************************/
	public static void main(String[] args) {
		PhotoJNDIDAO photoDAO = new PhotoJNDIDAO();
//		PhotoVO photoVO = new PhotoVO();
//		/************** 新增測試 *****************/
//		for (int i = 11; i < 49; i++) {
//		photoVO.setAlbum_no("A00000"+(int)(Math.floor(Math.random()*5+1)));
//		photoVO.setAlbum_no("A000048");
//		photoVO.setPhoto_name("潛水照");
//		photoVO.setPhoto_pic(MyUtil.pathToByteArray("D:\\progect\\pic\\22.jpg"));
//		photoVO.setPhoto_note("越看越頭痛");
//		photoVO.setPhoto_status(0);
//		photoDAO.insert(photoVO);
//		}
		/************** 修改測試 *****************/
		PhotoVO photoVO = new PhotoVO();
		for (int j = 1; j <= 10; j++) {
			if (j >= 10) {
				photoVO = photoDAO.findByPrimaryKey("P0000" + j);
			} else {
				photoVO = photoDAO.findByPrimaryKey("P00000" + j);
			}
			photoVO.setPhoto_name(photoVO.getPhoto_name());
			photoVO.setPhoto_pic(MyUtil
					.pathToByteArray("D:/DA104G2/workspace/DA104G2_1223_1/WebContent/images/9.png"));
			photoVO.setPhoto_note("321");
			photoVO.setPhoto_status(0);
			photoDAO.update(photoVO);
		}
			/************** 查詢測試 *****************/
//		PhotoVO photoVO = photoDAO.findByPrimaryKey("P000001");
//		System.out.println(photoVO.getAlbum_no());
//		System.out.println(photoVO.getPhoto_no());
//		System.out.println(photoVO.getPhoto_name());
//		System.out.println(photoVO.getPhoto_pic());
//		System.out.println(photoVO.getPhoto_status());
//		System.out.println(photoVO.getPhoto_time());
//		System.out.println(photoVO.getPhoto_note());

			/************** 全查測試 *****************/
		List<PhotoVO> ll = photoDAO.getAll("A000050");
		for (PhotoVO photoVO1 : ll) {
			System.out.println(photoVO1.getPhoto_name());
		}
//		
	}
}
