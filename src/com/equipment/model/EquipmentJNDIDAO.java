package com.equipment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.MyDataSource;

import java.util.ArrayList;


public class EquipmentJNDIDAO implements EquipmentDAO_interface {

	
	private static final String INSERT_STMT = 
			"INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, equip_update) "
			+ "VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE EQUIPMENT set equip_name = ?, equip_des = ?, equip_img1 = ?, equip_img2 = ?, equip_img3 = ?, equip_status = ?, equip_price = ?, equip_update = ? where equip_no = ?";
	private static final String DELETE = 
			"DELETE EQUIPMENT where equip_no = ?";
	private static final String GET_ONE_STMT = 
			"SELECT equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, to_char(equip_update,'yyyy-mm-dd') equip_update FROM EQUIPMENT where equip_no = ?";
	private static final String GET_ALL_STMT = 
			"SELECT equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, to_char(equip_update,'yyyy-mm-dd') equip_update FROM EQUIPMENT ORDER BY equip_no";
	private static final String GET_STATUS_STMT = 
			"SELECT equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, to_char(equip_update,'yyyy-mm-dd') equip_update FROM EQUIPMENT where equip_status = ?";
	private static final String GET_KEYWORD = 
			"SELECT equip_no, equip_name, equip_des, equip_img1, equip_img2, equip_img3, equip_status, equip_price, to_char(equip_update,'yyyy-mm-dd') equip_update FROM EQUIPMENT where equip_name LIKE '%' || ? || '%' AND equip_status = 1";

			
			
			
			
	@Override
	public void insert(EquipmentVO equipmentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, equipmentVO.getEquip_name());
			pstmt.setString(2, equipmentVO.getEquip_des());
			pstmt.setBytes(3, equipmentVO.getEquip_img1());
			pstmt.setBytes(4, equipmentVO.getEquip_img2());
			pstmt.setBytes(5, equipmentVO.getEquip_img3());
			pstmt.setInt(6, equipmentVO.getEquip_status());
			pstmt.setInt(7, equipmentVO.getEquip_price());
			pstmt.setDate(8, equipmentVO.getEquip_update());

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
	public void update(EquipmentVO equipmentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equipmentVO.getEquip_name());
			pstmt.setString(2, equipmentVO.getEquip_des());
			pstmt.setBytes(3, equipmentVO.getEquip_img1());
			pstmt.setBytes(4, equipmentVO.getEquip_img2());
			pstmt.setBytes(5, equipmentVO.getEquip_img3());
			pstmt.setInt(6, equipmentVO.getEquip_status());
			pstmt.setInt(7, equipmentVO.getEquip_price());
			pstmt.setDate(8, equipmentVO.getEquip_update());
			pstmt.setString(9, equipmentVO.getEquip_no());


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
	public void delete(String equip_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, equip_no);

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
	public EquipmentVO findByPrimaryKey(String equip_no) {
		
		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, equip_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_no(rs.getString("equip_no"));
				equipmentVO.setEquip_name(rs.getString("equip_name"));
				equipmentVO.setEquip_des(rs.getString("equip_des"));
				equipmentVO.setEquip_img1(rs.getBytes("equip_img1"));
				equipmentVO.setEquip_img2(rs.getBytes("equip_img2"));
				equipmentVO.setEquip_img3(rs.getBytes("equip_img3"));
				equipmentVO.setEquip_status(rs.getInt("equip_status"));
				equipmentVO.setEquip_price(rs.getInt("equip_price"));
				equipmentVO.setEquip_update(rs.getDate("equip_update"));
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
		return equipmentVO;
	}

	@Override
	public List<EquipmentVO> getAll() {
		
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_no(rs.getString("equip_no"));
				equipmentVO.setEquip_name(rs.getString("equip_name"));
				equipmentVO.setEquip_des(rs.getString("equip_des"));
				equipmentVO.setEquip_img1(rs.getBytes("equip_img1"));
				equipmentVO.setEquip_img2(rs.getBytes("equip_img2"));
				equipmentVO.setEquip_img3(rs.getBytes("equip_img3"));
				equipmentVO.setEquip_status(rs.getInt("equip_status"));
				equipmentVO.setEquip_price(rs.getInt("equip_price"));
				equipmentVO.setEquip_update(rs.getDate("equip_update"));
				list.add(equipmentVO);
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
	public List<EquipmentVO> getStatus(Integer equip_status) {
		
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_STATUS_STMT);
			
			pstmt.setInt(1, equip_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_no(rs.getString("equip_no"));
				equipmentVO.setEquip_name(rs.getString("equip_name"));
				equipmentVO.setEquip_des(rs.getString("equip_des"));
				equipmentVO.setEquip_img1(rs.getBytes("equip_img1"));
				equipmentVO.setEquip_img2(rs.getBytes("equip_img2"));
				equipmentVO.setEquip_img3(rs.getBytes("equip_img3"));
				equipmentVO.setEquip_status(rs.getInt("equip_status"));
				equipmentVO.setEquip_price(rs.getInt("equip_price"));
				equipmentVO.setEquip_update(rs.getDate("equip_update"));
				list.add(equipmentVO);
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
	public List<EquipmentVO> get_keyword(String keyword) {
		
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_KEYWORD);
			
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEquip_no(rs.getString("equip_no"));
				equipmentVO.setEquip_name(rs.getString("equip_name"));
				equipmentVO.setEquip_des(rs.getString("equip_des"));
				equipmentVO.setEquip_img1(rs.getBytes("equip_img1"));
				equipmentVO.setEquip_img2(rs.getBytes("equip_img2"));
				equipmentVO.setEquip_img3(rs.getBytes("equip_img3"));
				equipmentVO.setEquip_status(rs.getInt("equip_status"));
				equipmentVO.setEquip_price(rs.getInt("equip_price"));
				equipmentVO.setEquip_update(rs.getDate("equip_update"));
				list.add(equipmentVO);
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
//		EquipmentJDBCDAO dao = new EquipmentJDBCDAO();
		
		// insert
//		byte[] pic1 = MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\final\\images\\裝備\\防寒衣\\suit1.jpg");
//		byte[] pic2 = MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\final\\images\\裝備\\防寒衣\\suit1.jpg");
//		byte[] pic3 = MyUtil.pathToByteArray("C:\\Users\\Java\\Desktop\\final\\images\\裝備\\防寒衣\\suit1.jpg");
//		
//		EquipmentVO equipmentVO1 = new EquipmentVO();
//		equipmentVO1.setEquip_name("裝備11");
//		equipmentVO1.setEquip_des("裝備詳情11");
//		equipmentVO1.setEquip_img1(pic1);
//		equipmentVO1.setEquip_img2(pic2);
//		equipmentVO1.setEquip_img3(pic3);
//		equipmentVO1.setEquip_status(1);
//		equipmentVO1.setEquip_price(1200);
//		equipmentVO1.setEquip_update(java.sql.Date.valueOf("2019-12-11"));
//		dao.insert(equipmentVO1);
		
		
		
		
		
		// update
//		byte[] pic1 = MyUtil.pathToByteArray("shop/mask2.jpg");
//		
//		EquipmentVO equipmentVO3 = new EquipmentVO();
//		equipmentVO3.setEquip_no("EQ00001");
//		equipmentVO3.setEquip_name("裝備xx");
//		equipmentVO3.setEquip_des("裝備詳情xx");
//		equipmentVO3.setEquip_img1(pic1);
//		equipmentVO3.setEquip_img2(null);
//		equipmentVO3.setEquip_img3(null);
//		equipmentVO3.setEquip_status(1);
//		equipmentVO3.setEquip_price(6600);
//		equipmentVO3.setEquip_update(java.sql.Date.valueOf("2019-12-11"));
//		dao.update(equipmentVO3);
		
		
		
		
		// delete
//		dao.delete("EQ00011");
		
		
		
		
		// findByPrimaryKey
//		EquipmentVO empVO4 = dao.findByPrimaryKey("EQ00001");
//		System.out.print(empVO4.getEquip_no() + ",");
//		System.out.print(empVO4.getEquip_name() + ",");
//		System.out.print(empVO4.getEquip_des() + ",");
//		System.out.print(empVO4.getEquip_img1() + ",");
//		System.out.print(empVO4.getEquip_img2() + ",");
//		System.out.print(empVO4.getEquip_img3() + ",");
//		System.out.print(empVO4.getEquip_status() + ",");
//		System.out.print(empVO4.getEquip_price() + ",");
//		System.out.println(empVO4.getEquip_update());
//		System.out.println("---------------------");
		
		
		
		
		// getAll
//		List<EquipmentVO> list = dao.getAll();
//		for (EquipmentVO aEquipment : list) {
//			System.out.print(aEquipment.getEquip_no() + ",");
//			System.out.print(aEquipment.getEquip_name() + ",");
//			System.out.print(aEquipment.getEquip_des() + ",");
//			System.out.print(aEquipment.getEquip_img1() + ",");
//			System.out.print(aEquipment.getEquip_img2() + ",");
//			System.out.print(aEquipment.getEquip_img3() + ",");
//			System.out.print(aEquipment.getEquip_status() + ",");
//			System.out.print(aEquipment.getEquip_price() + ",");
//			System.out.println(aEquipment.getEquip_update());
//			System.out.println("---------------------");
//		}
		
	}
	
	
	
}
