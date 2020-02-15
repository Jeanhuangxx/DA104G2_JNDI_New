package com.adm.model;

import java.util.*;

import java.sql.*;

public class AdmJDBCDAO implements AdmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String passwd = "123456";
	
	private static final String INSERT = 
		"INSERT INTO adm (adm_no,adm_id,adm_psw,adm_name,adm_tel,adm_email,adm_img) VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL = 
		"SELECT adm_no,adm_id,adm_psw,adm_name,adm_tel,adm_email,adm_img FROM adm order by adm_no";
	private static final String GET_ONE = 
		"SELECT adm_no,adm_id,adm_psw,adm_name,adm_tel,adm_email,adm_img FROM adm where adm_no = ?";
	private static final String DELETE = 
		"DELETE FROM adm where adm_no = ?";
	private static final String UPDATE = 
		"UPDATE adm set adm_id= ?, adm_psw= ?, adm_name= ?, adm_tel= ?, adm_email= ?, adm_img= ? where adm_no = ?";
//方法getOneAdmId(String adm_id)
	private static final String GET_ID = 
		"SELECT adm_no,adm_id,adm_psw,adm_name,adm_tel,adm_email,adm_img FROM adm where adm_id = ?";	
	
	
	@Override
	public void insert(AdmVO admVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, admVO.getAdm_id());
			pstmt.setString(2, admVO.getAdm_psw());
			pstmt.setString(3, admVO.getAdm_name());
			pstmt.setInt(4, admVO.getAdm_tel());
			pstmt.setString(5, admVO.getAdm_email());
			pstmt.setBytes(6, admVO.getAdm_img());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(AdmVO admVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, admVO.getAdm_id());
			pstmt.setString(2, admVO.getAdm_psw());
			pstmt.setString(3, admVO.getAdm_name());
			pstmt.setInt(4, admVO.getAdm_tel());
			pstmt.setString(5, admVO.getAdm_email());
			pstmt.setBytes(6, admVO.getAdm_img());
			pstmt.setString(7, admVO.getAdm_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String adm_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adm_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public AdmVO findByPrimaryKey(String adm_no) {
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, adm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("adm_no"));
				admVO.setAdm_id(rs.getString("adm_id"));
				admVO.setAdm_psw(rs.getString("adm_psw"));
				admVO.setAdm_name(rs.getString("adm_name"));
				admVO.setAdm_tel(rs.getInt("adm_tel"));
				admVO.setAdm_email(rs.getString("adm_email"));
				admVO.setAdm_img(rs.getBytes("adm_img"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return admVO;
	}
	
	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("adm_no"));
				admVO.setAdm_id(rs.getString("adm_id"));
				admVO.setAdm_psw(rs.getString("adm_psw"));
				admVO.setAdm_name(rs.getString("adm_name"));
				admVO.setAdm_tel(rs.getInt("adm_tel"));
				admVO.setAdm_email(rs.getString("adm_email"));
				admVO.setAdm_img(rs.getBytes("adm_img"));
				list.add(admVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public AdmVO findByAdmId(String adm_id) {
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ID);

			pstmt.setString(1, adm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// admVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("adm_no"));
				admVO.setAdm_id(rs.getString("adm_id"));
				admVO.setAdm_psw(rs.getString("adm_psw"));
				admVO.setAdm_name(rs.getString("adm_name"));
				admVO.setAdm_tel(rs.getInt("adm_tel"));
				admVO.setAdm_email(rs.getString("adm_email"));
				admVO.setAdm_img(rs.getBytes("adm_img"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return admVO;
	}

	public static void main(String[] args) {

		AdmJDBCDAO dao = new AdmJDBCDAO();
		byte[] xx = {1,2};
		
		// 新增
//		AdmVO admVO1 = new AdmVO();
//		admVO1.setAdm_id("乖寶寶1");
//		admVO1.setAdm_psw("乖寶寶1");
//		admVO1.setAdm_name("乖寶寶1");
//		admVO1.setAdm_tel(900000001);
//		admVO1.setAdm_email("ADM001@gmail.com");
//		admVO1.setAdm_img(xx);
//		dao.insert(admVO1);

		// 修改 
//		AdmVO admVO2 = new AdmVO();
//		admVO2.setAdm_no("A000015");
//		admVO2.setAdm_id("乖寶寶2");
//		admVO2.setAdm_psw("乖寶寶2");
//		admVO2.setAdm_name("乖寶寶2");
//		admVO2.setAdm_tel(900000002);
//		admVO2.setAdm_email("ADM002@gmail.com");
//		admVO2.setAdm_img(xx);
//		dao.update(admVO2);
//
//		// 刪除
//	    dao.delete("A000016");
//
//		// 查詢
//		AdmVO admVO3 = dao.findByPrimaryKey("A000001");
//		System.out.print(admVO3.getAdm_no() + ",");
//		System.out.print(admVO3.getAdm_id() + ",");
//		System.out.print(admVO3.getAdm_psw() + ",");
//		System.out.print(admVO3.getAdm_name() + ",");
//		System.out.print(admVO3.getAdm_tel() + ",");
//		System.out.print(admVO3.getAdm_email() + ",");
//		System.out.println(admVO3.getAdm_img());
//		System.out.println("---------------------");

//		// 查詢
//		List<AdmVO> list = dao.getAll();
//		for (AdmVO aAdm : list) {
//			System.out.print(aAdm.getAdm_no() + ",");
//			System.out.print(aAdm.getAdm_id() + ",");
//			System.out.print(aAdm.getAdm_psw() + ",");
//			System.out.print(aAdm.getAdm_name() + ",");
//			System.out.print(aAdm.getAdm_tel() + ",");
//			System.out.print(aAdm.getAdm_email() + ",");
//			System.out.print(aAdm.getAdm_img());
//			System.out.println();
//		}
	}	
}

