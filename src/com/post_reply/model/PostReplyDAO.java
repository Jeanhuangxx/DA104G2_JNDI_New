package com.post_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostReplyDAO implements PostReplyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)"
			+ "VALUES('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),?,?,SYSTIMESTAMP,?)";
	private static final String UPDATE_STMT = "UPDATE POST_REPLY SET REPLY_CONT=? WHERE REPLY_NO=?";
	private static final String GET_ONE_STMT = "SELECT REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT FROM POST_REPLY WHERE REPLY_NO=?";
	private static final String GET_ALL_STMT = "SELECT REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT FROM POST_REPLY";

	@Override
	public void insert(PostReplyVO postReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, postReplyVO.getMem_no());
			pstmt.setString(2, postReplyVO.getPost_no());
			pstmt.setString(3, postReplyVO.getReply_cont());

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

	/********************* 修改 ***********************/
	@Override
	public void update(PostReplyVO postReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, postReplyVO.getReply_cont());
			pstmt.setString(2, postReplyVO.getReply_no());

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
	public void delete(String reply_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostReplyVO findByPrimaryKey(String reply_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostReplyVO postReplyVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, reply_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postReplyVO = new PostReplyVO();
				postReplyVO.setReply_no(rs.getString("reply_no"));
				postReplyVO.setMem_no(rs.getString("mem_no"));
				postReplyVO.setPost_no(rs.getString("post_no"));
				postReplyVO.setReply_cont(rs.getString("reply_cont"));
				postReplyVO.setReply_time(rs.getTimestamp("reply_time"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return postReplyVO;
	}

	@Override
	public List<PostReplyVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostReplyVO postReplyVO = null;
		List<PostReplyVO> list = new ArrayList<PostReplyVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				postReplyVO = new PostReplyVO();
				postReplyVO.setReply_no(rs.getString("reply_no"));
				postReplyVO.setMem_no(rs.getString("mem_no"));
				postReplyVO.setPost_no(rs.getString("post_no"));
				postReplyVO.setReply_cont(rs.getString("reply_cont"));
				postReplyVO.setReply_time(rs.getTimestamp("reply_time"));
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
		PostReplyDAO postReplyDAO = new PostReplyDAO();
		/**************** 新增留言 *******************/
//		PostReplyVO postReplyVO = new PostReplyVO();
//		postReplyVO.setPost_no("PT00001");
//		postReplyVO.setMem_no("M000001");
//		postReplyVO.setReply_cont("退訓好嗎?");
//
//		postReplyDAO.insert(postReplyVO);

		/****************** 修改測試 ***********************/
//		PostReplyVO postReplyVO = new PostReplyVO();
//		postReplyVO.setReply_no("PR00002");
//		postReplyVO.setReply_cont("別浪費時間了");
//		postReplyDAO.update(postReplyVO);

//		PostReplyVO postReplyVO = postReplyDAO.findByPrimaryKey("PR00003");
//		System.out.println(postReplyVO.getReply_no());
//		System.out.println(postReplyVO.getPost_no());
//		System.out.println(postReplyVO.getMem_no());
//		System.out.println(postReplyVO.getReply_cont());
//		System.out.println(postReplyVO.getReply_time());

		List<PostReplyVO> ll = postReplyDAO.getAll();
		for (PostReplyVO postReplyVO : ll) {
			System.out.println(postReplyVO.getReply_no());
			System.out.println(postReplyVO.getPost_no());
			System.out.println(postReplyVO.getMem_no());
			System.out.println(postReplyVO.getReply_cont());
			System.out.println(postReplyVO.getReply_time());
		}

	}

}
