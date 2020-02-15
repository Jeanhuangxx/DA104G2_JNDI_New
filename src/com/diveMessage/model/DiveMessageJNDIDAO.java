package com.diveMessage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.util.MyDataSource;



public class DiveMessageJNDIDAO implements DiveMessage_interface{
	
	//SQL指令入/
	private static final String INSERT_STMT = 
			"INSERT INTO DIVEMESSAGE (divmeg_no , dive_no  , mem_no  , divmeg_time  , divmeg_note )" + 
			"VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),"
			+ "? ,?, ?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT divmeg_no, dive_no, mem_no, divmeg_time, divmeg_note " + 
			"FROM DIVEMESSAGE order by divmeg_no";
	private static final String GET_ONE_STMT = 
			"SELECT divmeg_no, dive_no, mem_no, divmeg_time, divmeg_note " + 
			"FROM DIVEMESSAGE where divmeg_no = ?";
	private static final String UPDATE = 
			"UPDATE DIVEMESSAGE set dive_no=?, mem_no=?, divmeg_time=?, divmeg_note=?" + 
			"where divmeg_no = ?";
	
	//CRU方法
	@Override
	public void insert(DiveMessageVO diveMessageVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, diveMessageVO.getDive_no());
			pstmt.setString(2, diveMessageVO.getMem_no());
			pstmt.setTimestamp(3, diveMessageVO.getDivmeg_time());
			pstmt.setString(4, diveMessageVO.getDivmeg_note());



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
	public void update(DiveMessageVO diveMessageVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, diveMessageVO.getDive_no());
			pstmt.setString(2, diveMessageVO.getMem_no());
			pstmt.setTimestamp(3, diveMessageVO.getDivmeg_time());
			pstmt.setString(4, diveMessageVO.getDivmeg_note());
			pstmt.setString(5, diveMessageVO.getDivmeg_no());

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
	public DiveMessageVO findByPrimaryKey(String divmeg_no) {
		// TODO Auto-generated method stub
		DiveMessageVO diveMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, divmeg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diveMessageVO = new DiveMessageVO();
				diveMessageVO.setDivmeg_no(rs.getString("divmeg_no"));
				diveMessageVO.setDive_no(rs.getString("dive_no"));
				diveMessageVO.setMem_no(rs.getString("mem_no"));
				diveMessageVO.setDivmeg_time(rs.getTimestamp("divmeg_time"));
				diveMessageVO.setDivmeg_note(rs.getString("divmeg_note"));

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
		return diveMessageVO;
	}

	@Override
	public List<DiveMessageVO> getAll() {
		// TODO Auto-generated method stub
		List<DiveMessageVO> list = new ArrayList<DiveMessageVO>();
		DiveMessageVO diveMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				diveMessageVO = new DiveMessageVO();
				diveMessageVO.setDivmeg_no(rs.getString("divmeg_no"));
				diveMessageVO.setDive_no(rs.getString("dive_no"));
				diveMessageVO.setMem_no(rs.getString("mem_no"));
				diveMessageVO.setDivmeg_time(rs.getTimestamp("divmeg_time"));
				diveMessageVO.setDivmeg_note(rs.getString("divmeg_note"));
;

				list.add(diveMessageVO); // Store the row in the list
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
	//測試用
	public static void main(String[] args) {

		DiveMessageJNDIDAO dao = new DiveMessageJNDIDAO();

		//=======================insert測試==========================
		DiveMessageVO diveMessageVO = new DiveMessageVO();
		diveMessageVO.setDivmeg_no("");
		diveMessageVO.setDive_no("D000003");
		diveMessageVO.setMem_no("M000004");
//		//取得特定時間
		Calendar cal = new GregorianCalendar(2007, Calendar.JANUARY, 15, 20, 17, 36);
		Timestamp specifiedTime = new Timestamp(cal.getTimeInMillis());
//		//取得目前時間
//		Calendar nowTimeCal = Calendar.getInstance();
//		Timestamp nowTime = new Timestamp(nowTimeCal.getTimeInMillis());
		
		diveMessageVO.setDivmeg_time(specifiedTime);
		diveMessageVO.setDivmeg_note("I want to diveXXXXXXXXXXXXXXXXXXXXX");

		
		dao.insert(diveMessageVO);
		System.out.println("Insert - OK");
		
//		=====================Update測試==============================
		DiveMessageVO diveMessageVO1 = new DiveMessageVO();
		diveMessageVO1.setDivmeg_no("DM00001");
		diveMessageVO1.setDive_no("D000002");
		diveMessageVO1.setMem_no("M000005");
		diveMessageVO1.setDivmeg_time(specifiedTime);
		diveMessageVO1.setDivmeg_note("I want to diveXXXXXXXXXXXXXXXXXXXXX");
		
		dao.update(diveMessageVO);
		System.out.println("update - OK");
		
		
		//=====================find測試================================
		DiveMessageVO diveMessageVO2 = dao.findByPrimaryKey("DM00001");
		System.out.println(diveMessageVO2);
		System.out.println("find - OK");
		
		
//		=====================ListAll測試==============================
		List<DiveMessageVO> allDiveMsgItems =  dao.getAll();
		for(DiveMessageVO diveMsgItem: allDiveMsgItems) {
			System.out.println(diveMsgItem);
		}
		System.out.println("ListAll - OK");
		
	}
	
	
	
}
