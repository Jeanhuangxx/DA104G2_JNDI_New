package com.pe.model;

import java.sql.*;
import java.util.*;

import com.util.MyDataSource;

public class PeJNDIDAO implements PeDAO_interface{

	private static final String INSERT = 
			"INSERT INTO pe (adm_no,fun_no) VALUES (? , ?)";
	private static final String GET_ALL = 
			"SELECT adm_no,fun_no FROM pe order by adm_no";
	private static final String GET_ONE = 
			"SELECT adm_no,fun_no FROM pe where adm_no = ?";
	private static final String DELETE = 
			"DELETE FROM pe where adm_no = ?";
	private static final String UPDATE = 
			"UPDATE pe set fun_no= ? where adm_no = ?";
	
	@Override
	public void insert(PeVO peVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, peVO.getAdm_no());
			pstmt.setString(2, peVO.getFun_no());

			pstmt.executeUpdate();
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());	
			
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
	public void update(PeVO peVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, peVO.getFun_no());
			pstmt.setString(1, peVO.getAdm_no());

			pstmt.executeUpdate();

		
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
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adm_no);

			pstmt.executeUpdate();

		
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
	public List<PeVO> findByPrimaryKey(String adm_no) {
		PeVO peVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PeVO> list=new ArrayList<PeVO>();

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, adm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				peVO = new PeVO();
				peVO.setAdm_no(rs.getString("adm_no"));
				peVO.setFun_no(rs.getString("fun_no"));
				list.add(peVO);
			}

		
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
	public List<PeVO> getAll() {
		List<PeVO> list = new ArrayList<PeVO>();
		PeVO peVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				peVO = new PeVO();
				peVO.setAdm_no(rs.getString("adm_no"));
				peVO.setFun_no(rs.getString("fun_no"));
				
				list.add(peVO); // Store the row in the list
			}

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
	
	public static void main(String[] args) {

		PeJNDIDAO dao = new PeJNDIDAO();

		// 新增
//		PeVO peVO1 = new PeVO();
//		peVO1.setAdm_no("A000010");
//		peVO1.setFun_no("F000010");
//		dao.insert(peVO1);
	
		// 修改 ?
//		PeVO peVO2 = new PeVO();
//		peVO2.setFun_no("1");
//		peVO2.setAdm_no("3");
//		dao.update(peVO2);
//		
//		// 刪除
//				
//		dao.delete("A000010");
//		
//		// 查詢
		List<PeVO> list = dao.findByPrimaryKey("A001");
		for(PeVO p:list) {
			System.out.println(p.getAdm_no()+p.getFun_no());
		}
		
		
//		System.out.print(peVO3.getAdm_no() + ",");
//		System.out.print(peVO3.getFun_no() + ",");
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<PeVO> list = dao.getAll();
//		for (PeVO aPe : list) {
//		System.out.print(aPe.getAdm_no() + ",");
//		System.out.print(aPe.getFun_no() + ",");
//		System.out.println();
//	    }
    }
}

