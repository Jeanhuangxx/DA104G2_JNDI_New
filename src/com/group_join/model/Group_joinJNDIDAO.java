package com.group_join.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;

public class Group_joinJNDIDAO implements Group_joinDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO GROUP_JOIN(GROUP_NO, MEM_NO, GROUP_JO_STATUS) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE GROUP_JOIN SET GROUP_JO_STATUS=?, GROUP_QR_STATUS=? where GROUP_NO = ? AND MEM_NO = ?";
	private static final String GET_ONE_STMT = "SELECT GROUP_NO, MEM_NO, GROUP_JO_STATUS, GROUP_QR_STATUS FROM GROUP_JOIN WHERE GROUP_NO = ? AND MEM_NO = ?";
	private static final String GET_ALL_STMT = "SELECT GROUP_NO, MEM_NO, GROUP_JO_STATUS, GROUP_QR_STATUS FROM GROUP_JOIN ORDER BY GROUP_NO";
	private static final String GET_ONE_GROUP_STMT = "SELECT GROUP_NO, MEM_NO, GROUP_JO_STATUS, GROUP_QR_STATUS FROM GROUP_JOIN WHERE GROUP_NO = ? ORDER BY GROUP_NO";
	private static final String GET_ALL_MEM_STMT = "SELECT GROUP_NO, MEM_NO, GROUP_JO_STATUS, GROUP_QR_STATUS FROM GROUP_JOIN WHERE MEM_NO = ? ORDER BY MEM_NO";
	private static final String DELETE = "DELETE FROM GROUP_JOIN WHERE GROUP_NO = ? AND MEM_NO = ?";
	@Override
	public void insert(Group_joinVO group_joinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_joinVO.getGroup_no());
			pstmt.setString(2, group_joinVO.getMem_no());
			pstmt.setInt(3, group_joinVO.getGroup_jo_status());
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
	public void delete(String groupno, String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, groupno);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();
			System.out.println("刪除成功");

		
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Group_joinVO group_joinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, group_joinVO.getGroup_jo_status());
			pstmt.setInt(2, group_joinVO.getGroup_qr_status());
			pstmt.setString(3, group_joinVO.getGroup_no());
			pstmt.setString(4, group_joinVO.getMem_no());
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
	public Group_joinVO findByPrimaryKey(String groupno, String memno) {
		Group_joinVO group_joinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, groupno);
			pstmt.setString(2, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				group_joinVO = new Group_joinVO();

				group_joinVO.setGroup_no(rs.getString("group_no"));
				group_joinVO.setMem_no(rs.getString("mem_no"));
				group_joinVO.setGroup_jo_status(rs.getInt("group_jo_status"));
				group_joinVO.setGroup_qr_status(rs.getInt("group_qr_status"));
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
		return group_joinVO;
	}

	@Override
	public List<Group_joinVO> getAll() {
		List<Group_joinVO> list = new ArrayList<Group_joinVO>();
		Group_joinVO group_joinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_joinVO = new Group_joinVO();
				group_joinVO.setGroup_no(rs.getString("group_no"));
				group_joinVO.setMem_no(rs.getString("mem_no"));
				group_joinVO.setGroup_jo_status(rs.getInt("group_jo_status"));
				group_joinVO.setGroup_qr_status(rs.getInt("group_qr_status"));
				list.add(group_joinVO); // Store the row in the list
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

	@Override
	public List<Group_joinVO> findByMemno(String memno) {
		List<Group_joinVO> list = new ArrayList<Group_joinVO>();
		Group_joinVO group_joinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEM_STMT);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_joinVO = new Group_joinVO();
				group_joinVO.setGroup_no(rs.getString("group_no"));
				group_joinVO.setMem_no(rs.getString("mem_no"));
				group_joinVO.setGroup_jo_status(rs.getInt("group_jo_status"));
				group_joinVO.setGroup_qr_status(rs.getInt("group_qr_status"));
				list.add(group_joinVO); // Store the row in the list
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

	@Override
	public List<Group_joinVO> findByGroup_no(String groupno) {
		List<Group_joinVO> list = new ArrayList<Group_joinVO>();
		Group_joinVO group_joinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_GROUP_STMT);
			pstmt.setString(1, groupno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_joinVO = new Group_joinVO();
				group_joinVO.setGroup_no(rs.getString("group_no"));
				group_joinVO.setMem_no(rs.getString("mem_no"));
				group_joinVO.setGroup_jo_status(rs.getInt("group_jo_status"));
				group_joinVO.setGroup_qr_status(rs.getInt("group_qr_status"));
				list.add(group_joinVO); // Store the row in the list
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

//	public static void main(String[] args) {
//		Group_joinJDBCDAO dao = new Group_joinJDBCDAO();
//		Group_joinVO group_joinVO1 = new Group_joinVO();

	// insert
//		group_joinVO1.setGroup_no("G000001");
//		group_joinVO1.setMem_no("M000003");
//		dao.insert(group_joinVO1);

	// update
//		group_joinVO1.setGroup_no("G000001");
//		group_joinVO1.setMem_no("M000002");
//		group_joinVO1.setGroup_jo_status(1);
//		group_joinVO1.setGroup_qr_status(1);
//		dao.update(group_joinVO1);

	// 查詢
//		Group_joinVO groupVO3 = dao.findByPrimaryKey("G000001", "M000002");
//
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getGroup_jo_status() + ",");
//		System.out.println(groupVO3.getGroup_qr_status() + ",");
//		
//		System.out.println("---------------------");

	// 查詢全部
//		List<Group_joinVO> list = dao.getAll();
//		for (Group_joinVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_jo_status() + ",");
//			System.out.println(aGroup.getGroup_qr_status() + ",");
//			System.out.println();
//		}

	// 查詢全部
//		List<Group_joinVO> list = dao.findByGroup_no("G000001");
//		for (Group_joinVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getGroup_jo_status() + ",");
//			System.out.println(aGroup.getGroup_qr_status() + ",");
//			System.out.println();
//		}	

//	}

}
