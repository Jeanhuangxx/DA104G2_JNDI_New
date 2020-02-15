package com.mem.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.util.*;

public class MemJNDIDAO implements MemDAO_interface {


	private static final String INSERT_STMT = "INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_IMG,MEM_PER,MEM_TYPE,MEM_STORE_BUSINESS,MEM_STORE_OWNER,MEM_STORE_TAXID)"
			+ "VALUES('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEM SET MEM_NAME=?,MEM_ID=?,MEM_PSW=?,MEM_GENERAL_GEN=?,MEM_GENERAL_BD=?,MEM_TEL=?,MEM_EMAIL=?,MEM_ADD=?,MEM_IMG=?,MEM_PER=?,MEM_TYPE=?,MEM_STORE_BUSINESS=?,MEM_STORE_OWNER=?,MEM_STORE_TAXID=? WHERE MEM_NO=?";
	private static final String GET_ONE_STMT = "SELECT MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_IMG,MEM_PER,MEM_TYPE,MEM_STORE_BUSINESS,MEM_STORE_OWNER,MEM_STORE_TAXID FROM MEM WHERE MEM_NO=?";
	private static final String GET_ONE_BY_ACCOUNT_STMT = "SELECT MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_IMG,MEM_PER,MEM_TYPE,MEM_STORE_BUSINESS,MEM_STORE_OWNER,MEM_STORE_TAXID FROM MEM WHERE MEM_ID=?";
	private static final String GET_ALL_STMT = "SELECT MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_IMG,MEM_PER,MEM_TYPE,MEM_STORE_BUSINESS,MEM_STORE_OWNER,MEM_STORE_TAXID FROM MEM";
	private static final String GET_ALL_BY_STATUS_STMT = "SELECT * FROM MEM WHERE MEM_PER=? AND MEM_TYPE=?" ;
	private static final String GET_ALL_BY_STORE = "SELECT * FROM MEM WHERE MEM_TYPE=1 ";
	
	/***************************** 新增會員 *********************************/
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/************************** 設定會員資料 ***************************/
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_id());
			pstmt.setString(3, memVO.getMem_psw());
			pstmt.setInt(4, memVO.getMem_general_gen());
			pstmt.setDate(5, memVO.getMem_general_bd());
			pstmt.setInt(6, memVO.getMem_tel());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_add());
			pstmt.setBytes(9, memVO.getMem_img());
			pstmt.setInt(10, memVO.getMem_per());
			pstmt.setInt(11, memVO.getMem_type());
			pstmt.setBytes(12, memVO.getMem_store_business());
			pstmt.setString(13, memVO.getMem_store_owner());
			pstmt.setString(14, memVO.getMem_store_taxid());

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

	/***************************** 修改會員 *********************************/
	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			/************************** 更新會員資料 ************************************/
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_id());
			pstmt.setString(3, memVO.getMem_psw());
			pstmt.setInt(4, memVO.getMem_general_gen());
			pstmt.setDate(5, memVO.getMem_general_bd());
			pstmt.setInt(6, memVO.getMem_tel());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_add());
			pstmt.setBytes(9, memVO.getMem_img());
			pstmt.setInt(10, memVO.getMem_per());
			pstmt.setInt(11, memVO.getMem_type());
			pstmt.setBytes(12, memVO.getMem_store_business());
			pstmt.setString(13, memVO.getMem_store_owner());
			pstmt.setString(14, memVO.getMem_store_taxid());
			pstmt.setString(15, memVO.getMem_no());

			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/***************************** 略 ********************************/
	@Override
	public void delete(String mem_no) {
		// TODO Auto-generated method stub
	}

	/************************** 查詢單一會員 ********************************/
	@Override
	public MemVO findByPrimaryKey(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MemVO memVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			/****************** 會員編號查詢 **********************/
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("MEM_NO"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_psw(rs.getString("MEM_PSW"));
				memVO.setMem_general_gen(rs.getInt("MEM_GENERAL_GEN"));
				memVO.setMem_general_bd(rs.getDate("MEM_GENERAL_BD"));
				memVO.setMem_tel(rs.getInt("MEM_TEL"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_add(rs.getString("MEM_ADD"));
				memVO.setMem_img(rs.getBytes("MEM_IMG"));
				memVO.setMem_per(rs.getInt("MEM_PER"));
				memVO.setMem_type(rs.getInt("MEM_TYPE"));
				memVO.setMem_store_business(rs.getBytes("MEM_STORE_BUSINESS"));
				memVO.setMem_store_owner(rs.getString("MEM_STORE_OWNER"));
				memVO.setMem_store_taxid(rs.getString("MEM_STORE_TAXID"));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memVO;
	}

	/************************** 查詢單一會員 by Account ********************************/
	@Override
	public MemVO findByAccount(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MemVO memVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ACCOUNT_STMT);
			/****************** 會員帳號查詢 **********************/
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("MEM_NO"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_psw(rs.getString("MEM_PSW"));
				memVO.setMem_general_gen(rs.getInt("MEM_GENERAL_GEN"));
				memVO.setMem_general_bd(rs.getDate("MEM_GENERAL_BD"));
				memVO.setMem_tel(rs.getInt("MEM_TEL"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_add(rs.getString("MEM_ADD"));
				memVO.setMem_img(rs.getBytes("MEM_IMG"));
				memVO.setMem_per(rs.getInt("MEM_PER"));
				memVO.setMem_type(rs.getInt("MEM_TYPE"));
				memVO.setMem_store_business(rs.getBytes("MEM_STORE_BUSINESS"));
				memVO.setMem_store_owner(rs.getString("MEM_STORE_OWNER"));
				memVO.setMem_store_taxid(rs.getString("MEM_STORE_TAXID"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memVO;
	}

	/***************************** 全部會員 *********************************/
	@Override
	public List<MemVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("MEM_NO"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_psw(rs.getString("MEM_PSW"));
				memVO.setMem_general_gen(rs.getInt("MEM_GENERAL_GEN"));
				memVO.setMem_general_bd(rs.getDate("MEM_GENERAL_BD"));
				memVO.setMem_tel(rs.getInt("MEM_TEL"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_add(rs.getString("MEM_ADD"));
				memVO.setMem_img(rs.getBytes("MEM_IMG"));
				memVO.setMem_per(rs.getInt("MEM_PER"));
				memVO.setMem_type(rs.getInt("MEM_TYPE"));
				memVO.setMem_store_business(rs.getBytes("MEM_STORE_BUSINESS"));
				memVO.setMem_store_owner(rs.getString("MEM_STORE_OWNER"));
				memVO.setMem_store_taxid(rs.getString("MEM_STORE_TAXID"));
				list.add(memVO);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<MemVO> getAllByStatus(Integer mem_per,Integer mem_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS_STMT);
			pstmt.setInt(1, mem_per);
			pstmt.setInt(2, mem_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("MEM_NO"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_psw(rs.getString("MEM_PSW"));
				memVO.setMem_general_gen(rs.getInt("MEM_GENERAL_GEN"));
				memVO.setMem_general_bd(rs.getDate("MEM_GENERAL_BD"));
				memVO.setMem_tel(rs.getInt("MEM_TEL"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_add(rs.getString("MEM_ADD"));
				memVO.setMem_img(rs.getBytes("MEM_IMG"));
				memVO.setMem_per(rs.getInt("MEM_PER"));
				memVO.setMem_type(rs.getInt("MEM_TYPE"));
				memVO.setMem_store_business(rs.getBytes("MEM_STORE_BUSINESS"));
				memVO.setMem_store_owner(rs.getString("MEM_STORE_OWNER"));
				memVO.setMem_store_taxid(rs.getString("MEM_STORE_TAXID"));
				list.add(memVO);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<MemVO> getAllStores() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STORE);
//			pstmt.setInt(2, mem_per);
//			pstmt.setInt(1, mem_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("MEM_NO"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_psw(rs.getString("MEM_PSW"));
//				memVO.setMem_general_gen(rs.getInt("MEM_GENERAL_GEN"));
//				memVO.setMem_general_bd(rs.getDate("MEM_GENERAL_BD"));
				memVO.setMem_tel(rs.getInt("MEM_TEL"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_add(rs.getString("MEM_ADD"));
//				memVO.setMem_img(rs.getBytes("MEM_IMG"));
				memVO.setMem_per(rs.getInt("MEM_PER"));
				memVO.setMem_type(rs.getInt("MEM_TYPE"));
				memVO.setMem_store_business(rs.getBytes("MEM_STORE_BUSINESS"));
				memVO.setMem_store_owner(rs.getString("MEM_STORE_OWNER"));
				memVO.setMem_store_taxid(rs.getString("MEM_STORE_TAXID"));
				list.add(memVO);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/************************* 測試區 *******************************************/
	public static void main(String[] args) {
		Date dd = new Date(System.currentTimeMillis());
		MemJNDIDAO memDAO = new MemJNDIDAO();
		List<MemVO> ll = new ArrayList<>();
	/*************** 新增測試 *******************/
//		MemVO memVO_1 = new MemVO("", "測試一號", "test", "abc", 0, dd, 987654321, "zxc123", "桃園",
//				MyUtil.pathToByteArray("C:\\Users\\USER\\Desktop\\59116fe4144be.jpg"), 0, 0,
//				MyUtil.pathToByteArray("C:\\Users\\USER\\Desktop\\59116fe4144be.jpg"), "", "");
//		memDAO.insert(memVO_1);
	/*************** 修改測試 *******************/

//		MemVO memVO_2=memDAO.findByPrimaryKey("M000001");
//		memVO_2.setMem_no(memVO_2.getMem_no());
//		memVO_2.setMem_name("測試ing");
//		memVO_2.setMem_id(memVO_2.getMem_id());
//		memVO_2.setMem_psw("zxc123");
//		memVO_2.setMem_general_gen(memVO_2.getMem_general_gen());
//		memVO_2.setMem_general_bd(memVO_2.getMem_general_bd());
//		memVO_2.setMem_tel(memVO_2.getMem_tel());
//		memVO_2.setMem_email("zzz@123");
//		memVO_2.setMem_add(memVO_2.getMem_add());
//		memVO_2.setMem_img(memVO_2.getMem_img());
//		memVO_2.setMem_per(memVO_2.getMem_per());
//		memVO_2.setMem_type(memVO_2.getMem_type());
//		memVO_2.setMem_store_business(memVO_2.getMem_store_business());
//		memVO_2.setMem_store_owner(memVO_2.getMem_store_owner());
//		memVO_2.setMem_store_taxid(memVO_2.getMem_store_taxid());
//	
//		memDAO.update(memVO_2);

	/****************** 查詢測試 ***********************/
//		System.out.println(memDAO.findByAccount("LUFY").getMem_no());
//		System.out.println(memDAO.findByAccount("LUFY").getMem_name());
//		System.out.println(memDAO.findByAccount("LUFY").getMem_psw());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_id());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_general_gen());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_general_bd());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_tel());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_email());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_add());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_img());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_per());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_type());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_store_business());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_store_owner());
//		System.out.println(memDAO.findByPrimaryKey("M000003").getMem_store_taxid());

	/******************* GETALL **************************/

		ll = memDAO.getAllByStatus(0,1);

		for (MemVO memVO : ll) {
			System.out.println(memVO.getMem_name());
		}
	}
//
}
