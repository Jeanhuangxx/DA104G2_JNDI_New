package com.group_message.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.group.model.GroupVO;

public class Group_messageJDBCDAO implements Group_messageDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO GROUP_MESSAGE(GROUP_ME_NO, GROUP_NO, MEM_NO, GROUP_ME_NOTE) VALUES ('GM'||LPAD(TO_CHAR(GROUP_ME_SEQ.nextval), 5,'0'), ?, ?, ?)";
	private static final String UPDATE = "UPDATE GROUP_MESSAGE SET GROUP_NO=?, MEM_NO=?, GROUP_ME_NOTE=? where GROUP_ME_NO = ?";
	private static final String GET_ONE_STMT = "SELECT GROUP_ME_NO, GROUP_NO, MEM_NO, GROUP_ME_NOTE FROM GROUP_MESSAGE WHERE GROUP_ME_NO = ?";
	private static final String GET_ALL_STMT = "SELECT GROUP_ME_NO, GROUP_NO, MEM_NO, GROUP_ME_NOTE, GROUP_ME_TIME FROM GROUP_MESSAGE ORDER BY GROUP_ME_NO";
	private static final String DELETE = "DELETE FROM GROUP_MESSAGE where GROUP_ME_NO = ?";
	private static final String GET_ONE_GROUP_STMT = "SELECT GROUP_ME_NO, GROUP_NO, MEM_NO, GROUP_ME_NOTE, GROUP_ME_TIME FROM GROUP_MESSAGE WHERE GROUP_NO = ? ORDER BY GROUP_ME_NO";
	@Override
	public void insert(Group_messageVO group_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_messageVO.getGroup_no());
			pstmt.setString(2, group_messageVO.getMem_no());
			pstmt.setString(3, group_messageVO.getGroup_me_note());
			pstmt.executeUpdate();
			System.out.println("新增成功！");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Group_messageVO group_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, group_messageVO.getGroup_no());
			pstmt.setString(2, group_messageVO.getMem_no());
			pstmt.setString(3, group_messageVO.getGroup_me_note());
			pstmt.setString(4, group_messageVO.getGroup_me_no());
			pstmt.executeUpdate();
			System.out.println("修改成功！");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String groupmeno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, groupmeno);

			pstmt.executeUpdate();
			System.out.println("刪除成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Group_messageVO findByPrimaryKey(String groupmeno) {
		Group_messageVO group_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, groupmeno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				group_messageVO = new Group_messageVO();

				group_messageVO.setGroup_me_no(rs.getString("group_me_no"));
				group_messageVO.setGroup_no(rs.getString("group_no"));
				group_messageVO.setMem_no(rs.getString("mem_no"));
				group_messageVO.setGroup_me_note(rs.getString("group_me_note"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return group_messageVO;
	}

	@Override
	public List<Group_messageVO> getOneForGroup(String groupno) {
		List<Group_messageVO> list = new ArrayList<Group_messageVO>();
		Group_messageVO group_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_GROUP_STMT);
			
			pstmt.setString(1, groupno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_messageVO = new Group_messageVO();
				group_messageVO.setGroup_me_no(rs.getString("group_me_no"));
				group_messageVO.setGroup_no(rs.getString("group_no"));
				group_messageVO.setMem_no(rs.getString("mem_no"));
				group_messageVO.setGroup_me_note(rs.getString("group_me_note"));
				group_messageVO.setGroup_me_time(rs.getTimestamp("group_me_time"));
				list.add(group_messageVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public List<Group_messageVO> getAll() {
		List<Group_messageVO> list = new ArrayList<Group_messageVO>();
		Group_messageVO group_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_messageVO = new Group_messageVO();
				group_messageVO.setGroup_me_no(rs.getString("group_me_no"));
				group_messageVO.setGroup_no(rs.getString("group_no"));
				group_messageVO.setMem_no(rs.getString("mem_no"));
				group_messageVO.setGroup_me_note(rs.getString("group_me_note"));
				group_messageVO.setGroup_me_time(rs.getTimestamp("group_me_time"));
				list.add(group_messageVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Group_messageJDBCDAO dao = new Group_messageJDBCDAO();
		Group_messageVO group_messageVO1 = new Group_messageVO();

		// insert
//		group_messageVO1.setGroup_no("G000001");
//		group_messageVO1.setMem_no("M000001");
//		group_messageVO1.setGroup_me_note("剛過黑五不適合拉");
//		dao.insert(group_messageVO1);

		// update
//		group_messageVO1.setGroup_no("G000002");
//		group_messageVO1.setMem_no("M000002");
//		group_messageVO1.setGroup_me_note("希望這可以幫助任何人在同一個泡菜中。");
//		group_messageVO1.setGroup_me_no("GM00003");
//		dao.update(group_messageVO1);

		// 查詢
//		Group_messageVO groupVO3 = dao.findByPrimaryKey("GM00002");
//
//		System.out.println(groupVO3.getGroup_me_no() + ",");
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_me_note() + ",");
//		
//		System.out.println("---------------------");

		// 查詢全部
//		List<Group_messageVO> list = dao.getAll();
//		for (Group_messageVO aGroup : list) {
//			System.out.println(aGroup.getGroup_me_no() + ",");
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_me_note() + ",");
//			System.out.println();
//		}
		// 刪除
//		dao.delete("GM00004");
	}

}
