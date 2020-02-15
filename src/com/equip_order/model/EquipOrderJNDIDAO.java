	package com.equip_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.equip_odlist.model.EquipOdlistJDBCDAO;
import com.equip_odlist.model.EquipOdlistService;
import com.equip_odlist.model.EquipOdlistVO;
import com.equipment.model.EquipmentJDBCDAO;
import com.equipment.model.EquipmentVO;
import com.util.MyDataSource;


public class EquipOrderJNDIDAO implements EquipOrderDAO_interface{
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_order_status, equip_note, cus_name, cus_tel, cus_add) "
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), ?,systimestamp, ?, 0, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE EQUIP_ORDER set mem_no=?, equip_order_time =?, equip_order_price=?, equip_shipping_date=?, equip_order_status=?, equip_note=?, cus_name = ?, cus_tel = ?, cus_add = ? where equip_order_no = ?";
	private static final String GET_ONE_STMT = 
			"SELECT equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, equip_note, cus_name, cus_tel, cus_add FROM EQUIP_ORDER WHERE equip_order_no = ?";			
	private static final String GET_ALL_STMT = 
			"SELECT equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, equip_note, cus_name, cus_tel, cus_add FROM EQUIP_ORDER ORDER BY equip_order_no";
	private static final String GET_MEM = 
			"SELECT equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, equip_note, cus_name, cus_tel, cus_add FROM EQUIP_ORDER WHERE mem_no = ? ORDER BY equip_order_no";

	private static final String GET_ORDER_STATUS = 
			"SELECT equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, equip_note, cus_name, cus_tel, cus_add FROM EQUIP_ORDER WHERE equip_order_status = ? ORDER BY equip_order_no";
	
	
	
	@Override
	public void insert(EquipOrderVO equiporderVO, List<EquipOdlistVO> buylist) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {

			con=MyDataSource.getJNDI().getConnection();
			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先新增訂單
			String cols[] = {"equip_order_no"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, equiporderVO.getMem_no());
			pstmt.setInt(2, equiporderVO.getEquip_order_price());
			pstmt.setString(3, equiporderVO.getEquip_note());
			pstmt.setString(4, equiporderVO.getCus_name());
			pstmt.setInt(5, equiporderVO.getCus_tel());
			pstmt.setString(6, equiporderVO.getCus_add());

			pstmt.executeUpdate();
			
			// 取得對應的自增主鍵值
			String next_orderno = null;
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderno = rs.getString(1);
				System.out.println("自增主鍵值 = " + next_orderno + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			EquipOdlistService equipodlistSvc = new EquipOdlistService();
			for (EquipOdlistVO aOdlist : buylist) {
				aOdlist.setEquip_order_no(next_orderno);
				equipodlistSvc.insert(aOdlist, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			
			

	
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public void update(EquipOrderVO equiporderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equiporderVO.getMem_no());
			pstmt.setTimestamp(2, equiporderVO.getEquip_order_time());
			pstmt.setInt(3, equiporderVO.getEquip_order_price());
			pstmt.setDate(4, equiporderVO.getEquip_shipping_date());
			pstmt.setInt(5, equiporderVO.getEquip_order_status());
			pstmt.setString(6, equiporderVO.getEquip_note());
			pstmt.setString(7, equiporderVO.getCus_name());
			pstmt.setInt(8, equiporderVO.getCus_tel());
			pstmt.setString(9, equiporderVO.getCus_add());
			pstmt.setString(10, equiporderVO.getEquip_order_no());
			

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
	public EquipOrderVO findByPrimaryKey(String equip_order_no) {

		EquipOrderVO equiporderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, equip_order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				equiporderVO = new EquipOrderVO();
				equiporderVO.setEquip_order_no(rs.getString("equip_order_no"));
				equiporderVO.setMem_no(rs.getString("mem_no"));
				equiporderVO.setEquip_order_time(rs.getTimestamp("equip_order_time"));
				equiporderVO.setEquip_order_price(rs.getInt("equip_order_price"));
				equiporderVO.setEquip_shipping_date(rs.getDate("equip_shipping_date"));
				equiporderVO.setEquip_order_status(rs.getInt("equip_order_status"));
				equiporderVO.setEquip_note(rs.getString("equip_note"));
				equiporderVO.setCus_name(rs.getString("cus_name"));
				equiporderVO.setCus_tel(rs.getInt("cus_tel"));
				equiporderVO.setCus_add(rs.getString("cus_add"));
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
		return equiporderVO;
		
	}
	
	@Override
	public List<EquipOrderVO> getAll() {
		
		List<EquipOrderVO> list = new ArrayList<EquipOrderVO>();
		EquipOrderVO equiporderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equiporderVO = new EquipOrderVO();
				equiporderVO.setEquip_order_no(rs.getString("equip_order_no"));
				equiporderVO.setMem_no(rs.getString("mem_no"));
				equiporderVO.setEquip_order_time(rs.getTimestamp("equip_order_time"));
				equiporderVO.setEquip_order_price(rs.getInt("equip_order_price"));
				equiporderVO.setEquip_shipping_date(rs.getDate("equip_shipping_date"));
				equiporderVO.setEquip_order_status(rs.getInt("equip_order_status"));
				equiporderVO.setEquip_note(rs.getString("equip_note"));
				equiporderVO.setCus_name(rs.getString("cus_name"));
				equiporderVO.setCus_tel(rs.getInt("cus_tel"));
				equiporderVO.setCus_add(rs.getString("cus_add"));
				list.add(equiporderVO);
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
	
	@Override
	public List<EquipOrderVO> getMem(String mem_no) {
		
		List<EquipOrderVO> list = new ArrayList<EquipOrderVO>();
		EquipOrderVO equiporderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_MEM);
			
			pstmt.setString(1, mem_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equiporderVO = new EquipOrderVO();
				equiporderVO.setEquip_order_no(rs.getString("equip_order_no"));
				equiporderVO.setMem_no(rs.getString("mem_no"));
				equiporderVO.setEquip_order_time(rs.getTimestamp("equip_order_time"));
				equiporderVO.setEquip_order_price(rs.getInt("equip_order_price"));
				equiporderVO.setEquip_shipping_date(rs.getDate("equip_shipping_date"));
				equiporderVO.setEquip_order_status(rs.getInt("equip_order_status"));
				equiporderVO.setEquip_note(rs.getString("equip_note"));
				equiporderVO.setCus_name(rs.getString("cus_name"));
				equiporderVO.setCus_tel(rs.getInt("cus_tel"));
				equiporderVO.setCus_add(rs.getString("cus_add"));
				list.add(equiporderVO);
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
	
	@Override
	public List<EquipOrderVO> getOrderStatus(Integer equip_order_status) {
		
		List<EquipOrderVO> list = new ArrayList<EquipOrderVO>();
		EquipOrderVO equiporderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ORDER_STATUS);
			
			pstmt.setInt(1, equip_order_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equiporderVO = new EquipOrderVO();
				equiporderVO.setEquip_order_no(rs.getString("equip_order_no"));
				equiporderVO.setMem_no(rs.getString("mem_no"));
				equiporderVO.setEquip_order_time(rs.getTimestamp("equip_order_time"));
				equiporderVO.setEquip_order_price(rs.getInt("equip_order_price"));
				equiporderVO.setEquip_shipping_date(rs.getDate("equip_shipping_date"));
				equiporderVO.setEquip_order_status(rs.getInt("equip_order_status"));
				equiporderVO.setEquip_note(rs.getString("equip_note"));
				equiporderVO.setCus_name(rs.getString("cus_name"));
				equiporderVO.setCus_tel(rs.getInt("cus_tel"));
				equiporderVO.setCus_add(rs.getString("cus_add"));
				list.add(equiporderVO);
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
	
	
	
	
	public static void main(String[] args) {
		EquipOrderJNDIDAO dao = new EquipOrderJNDIDAO();
		
		// insert
//		EquipOrderVO equiporderVO1 = new EquipOrderVO();
//		equiporderVO1.setMem_no("M000006");
//		equiporderVO1.setEquip_order_price(8000);
//		equiporderVO1.setEquip_shipping_date(java.sql.Date.valueOf("2019-12-31"));
//		equiporderVO1.setEquip_order_status(0);
//		equiporderVO1.setEquip_note(null);
//		equiporderVO1.setCus_name("羅賓");
//		equiporderVO1.setCus_tel(900123456);
//		equiporderVO1.setCus_add("中壢區中央路232巷50號");
//		dao.insert(equiporderVO1);
		
		
		
		// update
//		EquipOrderVO equiporderVO2 = new EquipOrderVO();
//		equiporderVO2.setEquip_order_no("20191207-00011");
//		equiporderVO2.setMem_no("M000001");
//		equiporderVO2.setEquip_order_price(600);
//		equiporderVO2.setEquip_shipping_date(java.sql.Date.valueOf("2019-12-05"));
//		equiporderVO2.setEquip_order_status(0);
//		equiporderVO2.setEquip_note(null);
//		equiporderVO2.setCus_name("魯夫");
//		equiporderVO2.setCus_tel(900123456);
//		equiporderVO2.setCus_add("中壢區中央路232巷50號");
//		dao.update(equiporderVO2);
		
		
		
		// findByPrimaryKey
//		EquipOrderVO empVO3 = dao.findByPrimaryKey("20191203-00010");
//		System.out.print(empVO3.getEquip_order_no() + ",");
//		System.out.print(empVO3.getMem_no() + ",");
//		System.out.print(empVO3.getEquip_order_time() + ",");
//		System.out.print(empVO3.getEquip_order_price() + ",");
//		System.out.print(empVO3.getEquip_shipping_date() + ",");
//		System.out.print(empVO3.getEquip_order_status() + ",");
//		System.out.print(empVO3.getEquip_note() + ",");
//		System.out.print(empVO3.getCus_name() + ",");
//		System.out.print(empVO3.getCus_tel() + ",");
//		System.out.println(empVO3.getCus_add());
//		System.out.println("---------------------");
		
		
		
		// getAll
//		List<EquipOrderVO> list = dao.getAll();
//		for (EquipOrderVO aEquipOrder : list) {
//			System.out.print(aEquipOrder.getEquip_order_no() + ",");
//			System.out.print(aEquipOrder.getMem_no() + ",");
//			System.out.print(aEquipOrder.getEquip_order_time() + ",");
//			System.out.print(aEquipOrder.getEquip_order_price() + ",");
//			System.out.print(aEquipOrder.getEquip_shipping_date() + ",");
//			System.out.print(aEquipOrder.getEquip_order_status() + ",");
//			System.out.print(aEquipOrder.getEquip_note() + ",");
//			System.out.print(aEquipOrder.getCus_name() + ",");
//			System.out.print(aEquipOrder.getCus_tel() + ",");
//			System.out.println(aEquipOrder.getCus_add());
//			System.out.println("---------------------");
//		}
		
	}
}
