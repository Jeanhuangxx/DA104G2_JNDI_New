package com.group_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group_message.model.Group_messageVO;
import com.util.MyDataSource;

public class Group_reportJNDIDAO implements Group_reportDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO GROUP_REPORT(GROUP_RE_NO, GROUP_NO, MEM_NO, GROUP_RE_NOTE) VALUES ('GR'||LPAD(TO_CHAR(GROUP_ME_SEQ.nextval), 5,'0'), ?, ?, ?)";
	private static final String UPDATE = "UPDATE GROUP_REPORT SET GROUP_NO=?, MEM_NO=?, GROUP_RE_NOTE=?, GROUP_RE_STATUS=? where GROUP_RE_NO = ?";
	private static final String GET_ONE_STMT = "SELECT GROUP_RE_NO, GROUP_NO, MEM_NO, GROUP_RE_NOTE, GROUP_RE_STATUS, GROUP_RE_TIME FROM GROUP_REPORT WHERE GROUP_RE_NO = ?";
	private static final String GET_ALL_STMT = "SELECT GROUP_RE_NO, GROUP_NO, MEM_NO, GROUP_RE_NOTE, GROUP_RE_STATUS, GROUP_RE_TIME FROM GROUP_REPORT ORDER BY GROUP_RE_NO";
	private static final String DELETE = "DELETE FROM GROUP_REPORT where GROUP_RE_NO = ?";
	@Override
	public void insert(Group_reportVO group_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_reportVO.getGroup_no());
			pstmt.setString(2, group_reportVO.getMem_no());
			pstmt.setString(3, group_reportVO.getGroup_re_note());
			pstmt.executeUpdate();
			System.out.println("新增成功！");
		
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
	public void update(Group_reportVO group_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, group_reportVO.getGroup_no());
			pstmt.setString(2, group_reportVO.getMem_no());
			pstmt.setString(3, group_reportVO.getGroup_re_note());
			pstmt.setInt(4, group_reportVO.getGroup_re_status());
			pstmt.setString(5, group_reportVO.getGroup_re_no());
			pstmt.executeUpdate();
			System.out.println("修改成功！");
	
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
	public void delete(String groupreportno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, groupreportno);

			pstmt.executeUpdate();
			System.out.println("刪除成功！");
	
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
	public Group_reportVO findByPrimaryKey(String groupreportno) {
		Group_reportVO group_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, groupreportno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				group_reportVO = new Group_reportVO();

				group_reportVO.setGroup_re_no(rs.getString("group_re_no"));
				group_reportVO.setGroup_no(rs.getString("group_no"));
				group_reportVO.setMem_no(rs.getString("mem_no"));
				group_reportVO.setGroup_re_note(rs.getString("group_re_note"));
				group_reportVO.setGroup_re_status(rs.getInt("group_re_status"));
				group_reportVO.setGroup_re_time(rs.getTimestamp("group_re_time"));
			}

		
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
		return group_reportVO;
	}

	@Override
	public List<Group_reportVO> getAll() {
		List<Group_reportVO> list = new ArrayList<Group_reportVO>();
		Group_reportVO group_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_reportVO = new Group_reportVO();
				group_reportVO.setGroup_re_no(rs.getString("group_re_no"));
				group_reportVO.setGroup_no(rs.getString("group_no"));
				group_reportVO.setMem_no(rs.getString("mem_no"));
				group_reportVO.setGroup_re_note(rs.getString("group_re_note"));
				group_reportVO.setGroup_re_status(rs.getInt("group_re_status"));
				group_reportVO.setGroup_re_time(rs.getTimestamp("group_re_time"));
				list.add(group_reportVO); // Store the row in the list
			}

		
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
		Group_reportJNDIDAO dao = new Group_reportJNDIDAO();
		Group_reportVO group_reportVO1 = new Group_reportVO();

		// insert
//		group_reportVO1.setGroup_no("G000001");
//		group_reportVO1.setMem_no("M000001");
//		group_reportVO1.setGroup_re_note("94要檢舉");
//		dao.insert(group_reportVO1);

		// update
//		group_reportVO1.setGroup_no("G000001");
//		group_reportVO1.setMem_no("M000001");
//		group_reportVO1.setGroup_re_note("不得不檢舉");
//		group_reportVO1.setGroup_re_status(0);
//		group_reportVO1.setGroup_re_no("GR00005");
//		dao.update(group_reportVO1);

		// 查詢
//		Group_reportVO groupVO3 = dao.findByPrimaryKey("GR00005");
//
//		System.out.println(groupVO3.getGroup_re_no() + ",");
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_re_note() + ",");
//		System.out.println(groupVO3.getGroup_re_status() + ",");
//		System.out.println("---------------------");

		// 查詢全部
//		List<Group_reportVO> list = dao.getAll();
//		for (Group_reportVO aGroup : list) {
//			System.out.println(aGroup.getGroup_re_no() + ",");
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_re_note() + ",");
//			System.out.println(aGroup.getGroup_re_status() + ",");
//			System.out.println();
//		}
		// 刪除
//				dao.delete("GR00005");
	}
}
