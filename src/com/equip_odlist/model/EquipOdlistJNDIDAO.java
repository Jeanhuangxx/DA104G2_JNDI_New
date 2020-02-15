package com.equip_odlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.MyDataSource;


public class EquipOdlistJNDIDAO implements EquipOdlistDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) VALUES (?, ?, ?, ?, ?)";
	
	// 以下未使用到  故SQL語法不正確 (有新增equip_name 指令沒改)
	private static final String UPDATE = 
			"UPDATE EQUIP_ODLIST set buy_amt=?, equip_price=? where equip_order_no = ? and equip_no = ?";
	private static final String GET_ONE_STMT = 
			"SELECT equip_order_no, equip_no, equip_name, buy_amt, equip_price FROM EQUIP_ODLIST where equip_order_no = ? and equip_no = ?";
	private static final String GET_ALL_STMT = 
			"SELECT equip_order_no, equip_no, buy_amt, equip_price FROM EQUIP_ODLIST ORDER BY equip_order_no";
	// 以上
	
	
	private static final String GET_ONE_ODLIST = 
			"SELECT equip_order_no, equip_no, equip_name, buy_amt, equip_price FROM EQUIP_ODLIST where equip_order_no = ?";

	
	@Override
	public void insert(EquipOdlistVO equipodlistVO, Connection con) {
		
		PreparedStatement pstmt = null;

		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, equipodlistVO.getEquip_order_no());
			pstmt.setString(2, equipodlistVO.getEquip_no());
			pstmt.setString(3, equipodlistVO.getEquip_name());
			pstmt.setInt(4, equipodlistVO.getBuy_amt());
			pstmt.setInt(5, equipodlistVO.getEquip_price());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
			
		}

	}

	@Override
	public void update(EquipOdlistVO equipodlistVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, equipodlistVO.getBuy_amt());
			pstmt.setInt(2, equipodlistVO.getEquip_price());
			pstmt.setString(3, equipodlistVO.getEquip_order_no());
			pstmt.setString(4, equipodlistVO.getEquip_no());
			
			

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
	public EquipOdlistVO findByPrimaryKey(String equip_order_no, String equip_no) {
		
		EquipOdlistVO equipodlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, equip_order_no);
			pstmt.setString(2, equip_no);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipodlistVO = new EquipOdlistVO();
				equipodlistVO.setEquip_order_no(rs.getString("equip_order_no"));
				equipodlistVO.setEquip_no(rs.getString("equip_no"));
				equipodlistVO.setBuy_amt(rs.getInt("buy_amt"));
				equipodlistVO.setEquip_price(rs.getInt("equip_price"));
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
		return equipodlistVO;
		
	}

	@Override
	public List<EquipOdlistVO> getAll() {
		
		List<EquipOdlistVO> list = new ArrayList<EquipOdlistVO>();
		EquipOdlistVO equipodlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipodlistVO = new EquipOdlistVO();
				equipodlistVO.setEquip_order_no(rs.getString("equip_order_no"));
				equipodlistVO.setEquip_no(rs.getString("equip_no"));
				equipodlistVO.setBuy_amt(rs.getInt("buy_amt"));
				equipodlistVO.setEquip_price(rs.getInt("equip_price"));
				list.add(equipodlistVO);
				// Store the row in the list
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
	
	public List<EquipOdlistVO> getOneOdlist(String equip_order_no) {
		
		List<EquipOdlistVO> list = new ArrayList<EquipOdlistVO>();
		EquipOdlistVO equipodlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_ODLIST);
			
			pstmt.setString(1, equip_order_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipodlistVO = new EquipOdlistVO();
				equipodlistVO.setEquip_order_no(rs.getString("equip_order_no"));
				equipodlistVO.setEquip_no(rs.getString("equip_no"));
				equipodlistVO.setEquip_name(rs.getString("equip_name"));
				equipodlistVO.setBuy_amt(rs.getInt("buy_amt"));
				equipodlistVO.setEquip_price(rs.getInt("equip_price"));
				list.add(equipodlistVO);
				// Store the row in the list
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
	
	
	
	
	
	public static void main(String args[]) {
		EquipOdlistJNDIDAO dao = new EquipOdlistJNDIDAO();
		
		// insert
//		EquipOdlistVO equipodlistVO1 = new EquipOdlistVO();
//		equipodlistVO1.setEquip_order_no("20191207-00011");
//		equipodlistVO1.setEquip_no("EQ00002");
//		equipodlistVO1.setBuy_amt(2);
//		equipodlistVO1.setEquip_price(400);
//		dao.insert(equipodlistVO1);
		
		
		// update
//		EquipOdlistVO equipmentVO2 = new EquipOdlistVO();
//		equipmentVO2.setEquip_order_no("20191207-00011");
//		equipmentVO2.setEquip_no("EQ00001");
//		equipmentVO2.setBuy_amt(4);
//		equipmentVO2.setEquip_price(400);
//		dao.update(equipmentVO2);
		
		
		// findByPrimaryKey
//		EquipOdlistVO empVO3 = dao.findByPrimaryKey("20191207-00011","EQ00002");
//		System.out.print(empVO3.getEquip_order_no() + ",");
//		System.out.print(empVO3.getEquip_no() + ",");
//		System.out.print(empVO3.getBuy_amt() + ",");
//		System.out.println(empVO3.getEquip_price());
//		System.out.println("---------------------");
		
		
		// getAll
//		List<EquipOdlistVO> list = dao.getAll();
//		for (EquipOdlistVO aequipodlist : list) {
//			System.out.print(aequipodlist.getEquip_order_no() + ",");
//			System.out.print(aequipodlist.getEquip_no() + ",");
//			System.out.print(aequipodlist.getBuy_amt() + ",");
//			System.out.println(aequipodlist.getEquip_price());
//			System.out.println("---------------------");
//		}
		
		// getOneOdlist
		
//		List<EquipOdlistVO> list = dao.getOneOdlist("20191210-00010");
//		for (EquipOdlistVO aequipodlist : list) {
//			System.out.print(aequipodlist.getEquip_order_no() + ",");
//			System.out.print(aequipodlist.getEquip_no() + ",");
//			System.out.print(aequipodlist.getBuy_amt() + ",");
//			System.out.println(aequipodlist.getEquip_price());
//			System.out.println("---------------------");
//		}
		
		
	}

}
