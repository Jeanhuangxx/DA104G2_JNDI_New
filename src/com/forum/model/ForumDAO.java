package com.forum.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumDAO implements ForumDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)"
			+ "VALUES ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),?,?,SYSTIMESTAMP,?,?)";
	private static final String UPDATE_STMT = "UPDATE FORUM SET POST_TITLE=?, POST_TIME=SYSTIMESTAMP, POST_CONT=?, POST_STATUS=? WHERE POST_NO = ?";
	private static final String GET_ONE_STMT = "SELECT POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS FROM FORUM POST_NO WHERE POST_NO = ? ORDER BY POST_NO";
	private static final String GET_ALL_STMT = "SELECT POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS FROM FORUM ORDER BY POST_NO";

	/********************* 新增論壇文章 *****************************/
	@Override
	public void insert(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			/********************* 新增文章資料 *****************************/
			pstmt.setString(1, forumVO.getMen_no());
			pstmt.setString(2, forumVO.getPost_title());
			pstmt.setString(3, forumVO.getPost_cont());
			pstmt.setInt(4, forumVO.getPost_status());

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

	/********************** 修改論壇文章 *********************/
	@Override
	public void update(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			/********************** 更新論壇文章 *********************/
			pstmt.setString(1, forumVO.getPost_title());
			pstmt.setString(2, forumVO.getPost_cont());
			pstmt.setInt(3, forumVO.getPost_status());
			pstmt.setString(4, forumVO.getPost_no());

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

	@Override
	public void delete(String post_no) {

	}

	/************************* 查詢單一文章 *************************/
	@Override
	public ForumVO findByPrimaryKey(String post_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumVO forumVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, post_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setPost_no(rs.getString("post_no"));
				forumVO.setMen_no(rs.getString("mem_no"));
				forumVO.setPost_title(rs.getString("post_title"));
				forumVO.setPost_time(rs.getTimestamp("post_time"));
				forumVO.setPost_cont(rs.getString("post_cont"));
				forumVO.setPost_status(rs.getInt("post_status"));
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
		return forumVO;
	}

	/************************ 查詢全部文章 ***************************/
	@Override
	public List<ForumVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ForumVO> list = new ArrayList<ForumVO>();
		ResultSet rs = null;
		ForumVO forumVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setPost_no(rs.getString("post_no"));
				forumVO.setMen_no(rs.getString("mem_no"));
				forumVO.setPost_title(rs.getString("post_title"));
				forumVO.setPost_time(rs.getTimestamp("post_time"));
				forumVO.setPost_cont(rs.getString("post_cont"));
				forumVO.setPost_status(rs.getInt("post_status"));
				list.add(forumVO);
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

	public static void main(String[] args) {
		ForumDAO forumDAO = new ForumDAO();
		/*****************新增測試**********************/
//		ForumVO forumVO=new ForumVO();
//		forumVO.setPost_title("垃圾");
//		forumVO.setPost_cont("可以回家了");
//		forumVO.setMen_no("M000001");
//		forumVO.setPost_status(0);
//		
//		forumDAO.insert(forumVO);
		
		/********************************************/
		
		ForumVO forumVO=new ForumVO();
		forumVO.setPost_title("垃圾");
		forumVO.setPost_cont("可以回家了");
		forumVO.setPost_status(1);
		forumVO.setPost_no("PT00004");
		forumDAO.update(forumVO);
		
		
		
		/***************單一查詢*****************/
//		ForumVO forumVO =forumDAO.findByPrimaryKey("PT00001");
//		System.out.println(forumVO.getPost_title());
//		System.out.println(forumVO.getPost_cont());
//		System.out.println(forumVO.getPost_time());
//		System.out.println(forumVO.getPost_status());
//		System.out.println(forumVO.getMen_no());
//		System.out.println(forumVO.getPost_no());
		
		/***********查詢全部**********/
//		List<ForumVO> ll = forumDAO.getAll();
//		ll.forEach(System.out::println);

	}

}
