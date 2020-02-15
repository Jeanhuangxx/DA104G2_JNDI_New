package com.group.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dive.model.DiveVO;
import com.group_join.model.Group_joinService;
import com.util.MyDataSource;

public class GroupJNDIDAO implements GroupDAO_interface {


	private static final String INSERT_STMT = "INSERT INTO DIVEGROUP(GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, GROUP_TOUR_STOP_TIME, GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_TEL, GROUP_MP, GROUP_PHOTO)"
			+ " VALUES ('G'||LPAD(TO_CHAR(GROUP_SEQ.nextval),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE DIVEGROUP set DIVE_NO=?, GROUP_NAME=?, GROUP_TOUR_STOP_TIME=?, GROUP_BEGIN_TIME=?,"
			+ "GROUP_END_TIME=?, GROUP_MAX_NUM=?, GROUP_DES=?, GROUP_TEL=?, GROUP_MP=?, GROUP_PHOTO=? where GROUP_NO = ?";

	private static final String UPDATESTATUS = "UPDATE DIVEGROUP set GROUP_STATUS=?, GROUP_TOUR_NUM=? where GROUP_NO = ?";

	private static final String GET_ONE_STMT = "SELECT GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, GROUP_CT_TIME, GROUP_TOUR_STOP_TIME, GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME,GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM, GROUP_PHOTO FROM DIVEGROUP WHERE GROUP_NO = ?";

	private static final String GET_ALL_STMT = "SELECT GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, to_char(GROUP_CT_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_CT_TIME, GROUP_TOUR_STOP_TIME, to_char(GROUP_BEGIN_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME,GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM, GROUP_PHOTO FROM DIVEGROUP ORDER BY GROUP_NO DESC";

	private static final String DELETE = "DELETE FROM DIVEGROUP where GROUP_NO = ?";

	private static final String GET_MEM_ALL_STMT = "SELECT GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, to_char(GROUP_CT_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_CT_TIME, GROUP_TOUR_STOP_TIME, to_char(GROUP_BEGIN_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME,GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM, GROUP_PHOTO FROM DIVEGROUP WHERE MEM_NO = ?";

	private static final String GET_DIVE_ALL_STMT = "SELECT GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, to_char(GROUP_CT_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_CT_TIME, GROUP_TOUR_STOP_TIME, to_char(GROUP_BEGIN_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME,GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM, GROUP_PHOTO FROM DIVEGROUP WHERE DIVE_NO = ? ORDER BY GROUP_NO DESC";

	// 模糊搜尋
	private static final String LIKE_SEARCH = "SELECT GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, to_char(GROUP_CT_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_CT_TIME, GROUP_TOUR_STOP_TIME, to_char(GROUP_BEGIN_TIME,'yyyy-mm-dd hh24:mm:ss')GROUP_BEGIN_TIME,"
			+ "GROUP_END_TIME,GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM, GROUP_PHOTO FROM DIVEGROUP WHERE GROUP_NAME LIKE '%'||?||'%' ORDER BY GROUP_NO DESC";
	
	private static final String LIKE_SEARCH_DIVE = "SELECT DIVE_NO, DIVE_NAME FROM DIVE WHERE DIVE_NAME LIKE '%'||?||'%'";
	
	
	@Override
	public void insert(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			String[] key = { "group_no" };
			pstmt = con.prepareStatement(INSERT_STMT, key);

			pstmt.setString(1, groupVO.getMem_no());
			pstmt.setString(2, groupVO.getDive_no());
			pstmt.setString(3, groupVO.getGroup_name());
			pstmt.setTimestamp(4, groupVO.getGroup_tour_stop_time());
			pstmt.setTimestamp(5, groupVO.getGroup_begin_time());
			pstmt.setTimestamp(6, groupVO.getGroup_end_time());
			pstmt.setInt(7, groupVO.getGroup_max_num());
			pstmt.setString(8, groupVO.getGroup_des());
			pstmt.setString(9, groupVO.getGroup_tel());
			pstmt.setString(10, groupVO.getGroup_mp());
			pstmt.setBytes(11, groupVO.getGroup_photo());
			pstmt.executeUpdate();
			System.out.println("新增成功！");

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				String pk = rs.getString(1);
				Group_joinService group_joinSvc = new Group_joinService();
				group_joinSvc.addGroup_join(pk, groupVO.getMem_no(), 1);
			}
			rs.close();
	
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
	public void update(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, groupVO.getDive_no());
			pstmt.setString(2, groupVO.getGroup_name());
			pstmt.setTimestamp(3, groupVO.getGroup_tour_stop_time());
			pstmt.setTimestamp(4, groupVO.getGroup_begin_time());
			pstmt.setTimestamp(5, groupVO.getGroup_end_time());
			pstmt.setInt(6, groupVO.getGroup_max_num());
			pstmt.setString(7, groupVO.getGroup_des());
			pstmt.setString(8, groupVO.getGroup_tel());
			pstmt.setString(9, groupVO.getGroup_mp());

			Blob blob = con.createBlob();
			blob.setBytes(1, groupVO.getGroup_photo());
			pstmt.setBlob(10, blob);

			pstmt.setString(11, groupVO.getGroup_no());

			pstmt.executeUpdate();
			System.out.println("修改成功！");
		
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	@Override
	public void delete(String groupno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, groupno);

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
	public List<GroupVO> getAll_Dive(String diveno) {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_DIVE_ALL_STMT);

			pstmt.setString(1, diveno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupVO = new GroupVO();

				groupVO.setGroup_no(rs.getString("group_no"));
				groupVO.setMem_no(rs.getString("mem_no"));
				groupVO.setDive_no(rs.getString("dive_no"));
				groupVO.setGroup_name(rs.getString("group_name"));
				groupVO.setGroup_ct_time(rs.getTimestamp("group_ct_time"));
				groupVO.setGroup_tour_stop_time(rs.getTimestamp("group_tour_stop_time"));
				groupVO.setGroup_begin_time(rs.getTimestamp("group_begin_time"));
				groupVO.setGroup_end_time(rs.getTimestamp("group_end_time"));
				groupVO.setGroup_modify_time(rs.getTimestamp("group_modify_time"));
				groupVO.setGroup_max_num(rs.getInt("group_max_num"));
				groupVO.setGroup_des(rs.getString("group_des"));
				groupVO.setGroup_status(rs.getInt("group_status"));
				groupVO.setGroup_tel(rs.getString("group_tel"));
				groupVO.setGroup_mp(rs.getString("group_mp"));
				groupVO.setGroup_tour_num(rs.getInt("group_tour_num"));
				groupVO.setGroup_photo(rs.getBytes("group_photo"));
				list.add(groupVO);
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
	public List<GroupVO> getAll_Mem(String memno) {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_MEM_ALL_STMT);

			pstmt.setString(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupVO = new GroupVO();

				groupVO.setGroup_no(rs.getString("group_no"));
				groupVO.setMem_no(rs.getString("mem_no"));
				groupVO.setDive_no(rs.getString("dive_no"));
				groupVO.setGroup_name(rs.getString("group_name"));
				groupVO.setGroup_ct_time(rs.getTimestamp("group_ct_time"));
				groupVO.setGroup_tour_stop_time(rs.getTimestamp("group_tour_stop_time"));
				groupVO.setGroup_begin_time(rs.getTimestamp("group_begin_time"));
				groupVO.setGroup_end_time(rs.getTimestamp("group_end_time"));
				groupVO.setGroup_modify_time(rs.getTimestamp("group_modify_time"));
				groupVO.setGroup_max_num(rs.getInt("group_max_num"));
				groupVO.setGroup_des(rs.getString("group_des"));
				groupVO.setGroup_status(rs.getInt("group_status"));
				groupVO.setGroup_tel(rs.getString("group_tel"));
				groupVO.setGroup_mp(rs.getString("group_mp"));
				groupVO.setGroup_tour_num(rs.getInt("group_tour_num"));
				groupVO.setGroup_photo(rs.getBytes("group_photo"));
				list.add(groupVO);
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
	public GroupVO findByPrimaryKey(String groupno) {
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, groupno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupVO = new GroupVO();

				groupVO.setGroup_no(rs.getString("group_no"));
				groupVO.setMem_no(rs.getString("mem_no"));
				groupVO.setDive_no(rs.getString("dive_no"));
				groupVO.setGroup_name(rs.getString("group_name"));
				groupVO.setGroup_ct_time(rs.getTimestamp("group_ct_time"));
				groupVO.setGroup_tour_stop_time(rs.getTimestamp("group_tour_stop_time"));
				groupVO.setGroup_begin_time(rs.getTimestamp("group_begin_time"));
				groupVO.setGroup_end_time(rs.getTimestamp("group_end_time"));
				groupVO.setGroup_modify_time(rs.getTimestamp("group_modify_time"));
				groupVO.setGroup_max_num(rs.getInt("group_max_num"));
				groupVO.setGroup_des(rs.getString("group_des"));
				groupVO.setGroup_status(rs.getInt("group_status"));
				groupVO.setGroup_tel(rs.getString("group_tel"));
				groupVO.setGroup_mp(rs.getString("group_mp"));
				groupVO.setGroup_tour_num(rs.getInt("group_tour_num"));
				groupVO.setGroup_photo(rs.getBytes("group_photo"));
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
		return groupVO;
	}

	@Override
	public List<GroupVO> getAll() {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groupVO = new GroupVO();
				groupVO.setGroup_no(rs.getString("group_no"));
				groupVO.setMem_no(rs.getString("mem_no"));
				groupVO.setDive_no(rs.getString("dive_no"));
				groupVO.setGroup_name(rs.getString("group_name"));
				groupVO.setGroup_ct_time(rs.getTimestamp("group_ct_time"));
				groupVO.setGroup_tour_stop_time(rs.getTimestamp("group_tour_stop_time"));
				groupVO.setGroup_begin_time(rs.getTimestamp("group_begin_time"));
				groupVO.setGroup_end_time(rs.getTimestamp("group_end_time"));
				groupVO.setGroup_modify_time(rs.getTimestamp("group_modify_time"));
				groupVO.setGroup_max_num(rs.getInt("group_max_num"));
				groupVO.setGroup_des(rs.getString("group_des"));
				groupVO.setGroup_status(rs.getInt("group_status"));
				groupVO.setGroup_tel(rs.getString("group_tel"));
				groupVO.setGroup_mp(rs.getString("group_mp"));
				groupVO.setGroup_tour_num(rs.getInt("group_tour_num"));
				list.add(groupVO); // Store the row in the list
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
	public void updateStatus(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, groupVO.getGroup_status());
			pstmt.setInt(2, groupVO.getGroup_tour_num());
			pstmt.setString(3, groupVO.getGroup_no());

			pstmt.executeUpdate();
			System.out.println("修改成功！");
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	//模糊搜尋
	@Override
	public List<GroupVO> getKeyWord(String word) {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(LIKE_SEARCH);

			pstmt.setString(1, word);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupVO = new GroupVO();

				groupVO.setGroup_no(rs.getString("group_no"));
				groupVO.setMem_no(rs.getString("mem_no"));
				groupVO.setDive_no(rs.getString("dive_no"));
				groupVO.setGroup_name(rs.getString("group_name"));
				groupVO.setGroup_ct_time(rs.getTimestamp("group_ct_time"));
				groupVO.setGroup_tour_stop_time(rs.getTimestamp("group_tour_stop_time"));
				groupVO.setGroup_begin_time(rs.getTimestamp("group_begin_time"));
				groupVO.setGroup_end_time(rs.getTimestamp("group_end_time"));
				groupVO.setGroup_modify_time(rs.getTimestamp("group_modify_time"));
				groupVO.setGroup_max_num(rs.getInt("group_max_num"));
				groupVO.setGroup_des(rs.getString("group_des"));
				groupVO.setGroup_status(rs.getInt("group_status"));
				groupVO.setGroup_tel(rs.getString("group_tel"));
				groupVO.setGroup_mp(rs.getString("group_mp"));
				groupVO.setGroup_tour_num(rs.getInt("group_tour_num"));
				groupVO.setGroup_photo(rs.getBytes("group_photo"));
				list.add(groupVO);
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
	public List<DiveVO> getKeyWordDive(String word) {
		List<DiveVO> list = new ArrayList<DiveVO>();
		DiveVO diveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(LIKE_SEARCH_DIVE);

			pstmt.setString(1, word);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diveVO = new DiveVO();

				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				
				list.add(diveVO);
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
	public static void main(String[] args) throws IOException {
		GroupJNDIDAO dao = new GroupJNDIDAO();
		GroupVO groupVO1 = new GroupVO();

	// insert
//		groupVO1.setMem_no("M000001");
//		groupVO1.setDive_no("D000001");
//		groupVO1.setGroup_name("潛進台灣");
//		groupVO1.setGroup_tour_stop_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_begin_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_end_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_max_num(10);
//		groupVO1.setGroup_des("一起來潛水~");
//		groupVO1.setGroup_tel("0988205866");
//		groupVO1.setGroup_mp("龍洞灣");		
//		groupVO1.setGroup_photo(getPictureByteArray("C:/Users/Java/Desktop/蝙蝠洞.jpg"));//處理圖片		
//		dao.insert(groupVO1);

	// update
//		groupVO1.setDive_no("D000003");
//		groupVO1.setGroup_name("潛進香港");
//		groupVO1.setGroup_tour_stop_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_begin_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_end_time(java.sql.Timestamp.valueOf("2002-01-01 13:13:13"));
//		groupVO1.setGroup_max_num(10);
//		groupVO1.setGroup_des("一起來潛水~");
//		groupVO1.setGroup_tel("0988205866");
//		groupVO1.setGroup_mp("香港");
//		groupVO1.setGroup_no("G000001");
//		groupVO1.setGroup_photo(getPictureByteArray("C:/Users/Java/Downloads/漢.jpg"));
//		dao.update(groupVO1);

	// 查詢
//		GroupVO groupVO3 = dao.findByPrimaryKey("G000004");
//
//		System.out.println(groupVO3.getGroup_no() + ",");
//		System.out.println(groupVO3.getMem_no() + ",");
//		System.out.println(groupVO3.getDive_no() + ",");
//		System.out.println(groupVO3.getGroup_name() + ",");
//		System.out.println(groupVO3.getGroup_ct_time() + ",");
//		System.out.println(groupVO3.getGroup_tour_stop_time() + ",");
//		System.out.println(groupVO3.getGroup_begin_time() + ",");
//		System.out.println(groupVO3.getGroup_end_time() + ",");
//		System.out.println(groupVO3.getGroup_modify_time());
//		System.out.println(groupVO3.getGroup_max_num());
//		System.out.println(groupVO3.getGroup_des());
//		System.out.println(groupVO3.getGroup_status());
//		System.out.println(groupVO3.getGroup_tel());
//		System.out.println(groupVO3.getGroup_mp());
//		System.out.println(groupVO3.getGroup_tour_num());
//		System.out.println("---------------------");
//	}
	// 以會員編號查詢

//		List<GroupVO> list = dao.getAll_Mem("M000001");
//		for (GroupVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getDive_no() + ",");
//			System.out.println(aGroup.getGroup_name() + ",");
//			System.out.println(aGroup.getGroup_ct_time() + ",");
//			System.out.println(aGroup.getGroup_tour_stop_time() + ",");
//			System.out.println(aGroup.getGroup_begin_time() + ",");
//			System.out.println(aGroup.getGroup_end_time() + ",");
//			System.out.println(aGroup.getGroup_modify_time());
//			System.out.println(aGroup.getGroup_max_num());
//			System.out.println(aGroup.getGroup_des());
//			System.out.println(aGroup.getGroup_status());
//			System.out.println(aGroup.getGroup_tel());
//			System.out.println(aGroup.getGroup_mp());
//			System.out.println(aGroup.getGroup_tour_num());
//			System.out.println(aGroup.getGroup_photo());
//			System.out.println();
//		}
//		String word= "澎湖";
//		List<GroupVO> list = dao.getKeyWord("1");
//		for (GroupVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getDive_no() + ",");
//			System.out.println(aGroup.getGroup_name() + ",");
//			System.out.println(aGroup.getGroup_ct_time() + ",");
//			System.out.println(aGroup.getGroup_tour_stop_time() + ",");
//			System.out.println(aGroup.getGroup_begin_time() + ",");
//			System.out.println(aGroup.getGroup_end_time() + ",");
//			System.out.println(aGroup.getGroup_modify_time());
//			System.out.println(aGroup.getGroup_max_num());
//			System.out.println(aGroup.getGroup_des());
//			System.out.println(aGroup.getGroup_status());
//			System.out.println(aGroup.getGroup_tel());
//			System.out.println(aGroup.getGroup_mp());
//			System.out.println(aGroup.getGroup_tour_num());
//			System.out.println(aGroup.getGroup_photo());
//			System.out.println();
//		}
		
	}
	
	

	
	
	// 查詢全部
//		List<GroupVO> list = dao.getAll();
//		for (GroupVO aGroup : list) {
//			System.out.println(aGroup.getGroup_no() + ",");
//			System.out.println(aGroup.getMem_no() + ",");
//			System.out.println(aGroup.getDive_no() + ",");
//			System.out.println(aGroup.getGroup_name() + ",");
//			System.out.println(aGroup.getGroup_ct_time() + ",");
//			System.out.println(aGroup.getGroup_tour_stop_time() + ",");
//			System.out.println(aGroup.getGroup_begin_time() + ",");
//			System.out.println(aGroup.getGroup_end_time() + ",");
//			System.out.println(aGroup.getGroup_modify_time());
//			System.out.println(aGroup.getGroup_max_num());
//			System.out.println(aGroup.getGroup_des());
//			System.out.println(aGroup.getGroup_status());
//			System.out.println(aGroup.getGroup_tel());
//			System.out.println(aGroup.getGroup_mp());
//			System.out.println(aGroup.getGroup_tour_num());
//			System.out.println(aGroup.getGroup_photo());
//			System.out.println();
//		}

	// 刪除
//		dao.delete("G000004");
//	}
//	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("images/2.jpg");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}
