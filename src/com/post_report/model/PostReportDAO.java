package com.post_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostReportDAO implements PostReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String password = "123456";

	private static final String INSERT_STMT = " INSERT INTO POST_REPORT (POST_RE_NO,POST_NO,MEM_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)"
			+ "VALUES('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),?,?,SYSTIMESTAMP,?,?)";
	private static final String UPDATE_STMT = "UPDATE POST_REPORT SET post_re_note=?,post_re_time=?,post_re_status=? WHERE post_re_no=?";
	private static final String GET_ONE_STMT = "SELECT POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS FROM POST_REPORT WHERE POST_RE_NO = ? ORDER BY POST_RE_NO DESC";
	private static final String GET_ALL_STMT = "SELECT POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS FROM POST_REPORT ORDER BY POST_RE_NO";

	@Override
	public void insert(PostReportVO postReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, postReportVO.getPost_no());
			pstmt.setString(2, postReportVO.getMem_no());
			pstmt.setString(3, postReportVO.getPost_re_note());
			pstmt.setInt(4, postReportVO.getPost_re_status());

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

	@Override
	public void update(PostReportVO postReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, postReportVO.getPost_re_note());
			pstmt.setTimestamp(2, postReportVO.getPost_re_time());
			pstmt.setInt(3, postReportVO.getPost_re_status());
			pstmt.setString(4, postReportVO.getPost_re_no());

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

	@Override
	public void delete(String post_re_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostReportVO findByPrimaryKey(String post_re_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostReportVO postReportVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, post_re_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postReportVO = new PostReportVO();
				postReportVO.setPost_re_no(rs.getString("post_re_no"));
				postReportVO.setPost_no(rs.getString("post_no"));
				postReportVO.setMem_no(rs.getString("mem_no"));
				postReportVO.setPost_re_note(rs.getString("post_re_note"));
				postReportVO.setPost_re_time(rs.getTimestamp("post_re_time"));
				postReportVO.setPost_re_status(rs.getInt("post_re_status"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return postReportVO;
	}

	@Override
	public List<PostReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PostReportVO> list = new ArrayList<PostReportVO>();
		PostReportVO postReportVO = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				postReportVO = new PostReportVO();
				postReportVO.setPost_re_no(rs.getString("post_re_no"));
				postReportVO.setPost_no(rs.getString("post_no"));
				postReportVO.setMem_no(rs.getString("mem_no"));
				postReportVO.setPost_re_note(rs.getString("post_re_note"));
				postReportVO.setPost_re_time(rs.getTimestamp("post_re_time"));
				postReportVO.setPost_re_status(rs.getInt("post_re_status"));
				list.add(postReportVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void main(String[] args) {
//		PostReportDAO postReportDAO = new PostReportDAO();

		/************ 新增測試 *********************/
//		PostReportVO postReportVO = new PostReportVO();
//		postReportVO.setPost_no("PT00002");
//		postReportVO.setMem_no("M000003");
//		postReportVO.setPost_re_note("加油好嗎?");
//		postReportVO.setPost_re_status(0);
//		postReportDAO.insert(postReportVO);

		/***************** 修改測試 ******************/
//		postReportVO=postReportDAO.findByPrimaryKey("PRN0002");
//		postReportVO.setPost_re_note("....");
//		postReportVO.setPost_re_status(1);
//		postReportDAO.update(postReportVO);

		/*************** 查單筆 *****************/
//		PostReportVO postReportVO = postReportDAO.findByPrimaryKey("PRN0001");
//		System.out.println(postReportVO.getPost_re_no());
//		System.out.println(postReportVO.getPost_no());
//		System.out.println(postReportVO.getMem_no());
//		System.out.println(postReportVO.getPost_re_note());
//		System.out.println(postReportVO.getPost_re_time());
//		System.out.println(postReportVO.getPost_re_status());

		/******************* 查全部 **************************/
//		List<PostReportVO> ll = new ArrayList();
//		ll = postReportDAO.getAll();
//		for (PostReportVO postReportVO : ll) {
//			System.out.println(postReportVO.getPost_re_no());
//			System.out.println(postReportVO.getPost_no());
//			System.out.println(postReportVO.getMem_no());
//			System.out.println(postReportVO.getPost_re_note());
//			System.out.println(postReportVO.getPost_re_time());
//			System.out.println(postReportVO.getPost_re_status());
//			System.out.println("===============================");
//		}

	}

}
