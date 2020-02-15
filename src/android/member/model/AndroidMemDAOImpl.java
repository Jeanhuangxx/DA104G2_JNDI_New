package android.member.model;

import android.main.AndroidMyData;
import android.member.model.AndroidMem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class AndroidMemDAOImpl implements AndroidMemDAO {
	private static final String INSERT_STMT = "INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID)"
			+ "VALUES('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEM SET MEM_NAME=?,MEM_ID=?,MEM_PSW=?,MEM_GENERAL_GEN=?,MEM_GENERAL_BD=?,MEM_TEL=?,MEM_EMAIL=?,MEM_ADD=?,MEM_PER=?,MEM_TYPE=?,MEM_STORE_OWNER=?,MEM_STORE_TAXID=? WHERE MEM_EMAIL=?";
	private static final String  FIND_BY_ID_PSW = "SELECT * FROM MEM WHERE MEM_ID = ? AND MEM_PSW = ?";
	private static final String FIND_BY_ID = "SELECT MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID FROM MEM WHERE MEM_PSW=?";
	private static final String CHECK_ID_EXIST = "SELECT MEM_EMAIL FROM MEM WHERE MEM_EMAIL = ?";
	
	public AndroidMemDAOImpl() {
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/***************************** 新增會員 *********************************/
	@Override
	public boolean add(AndroidMem mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;

		try {
			con = DriverManager.getConnection(AndroidMyData.URL, AndroidMyData.USER, AndroidMyData.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			/************************** 設定會員資料 ***************************/
			pstmt.setString(1, mem.getMem_name());
			pstmt.setString(2, mem.getMem_id());
			pstmt.setString(3, mem.getMem_psw());
			pstmt.setInt(4, mem.getMem_general_gen());
			pstmt.setDate(5, mem.getMem_general_bd());
			pstmt.setInt(6, mem.getMem_tel());
			pstmt.setString(7, mem.getMem_email());
			pstmt.setString(8, mem.getMem_add());
			pstmt.setInt(9, mem.getMem_per());
			pstmt.setInt(10, mem.getMem_type());
			pstmt.setString(11, mem.getMem_store_owner());
			pstmt.setString(12, mem.getMem_store_taxid());

			int count = pstmt.executeUpdate();
			isAdded = count > 0 ? true : false;
		}catch (SQLException e){
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		return isAdded;
	}

	/***************************** 修改會員 *********************************/
	@Override
	public boolean update(AndroidMem mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;

		try {
			con = DriverManager.getConnection(AndroidMyData.URL, AndroidMyData.USER, AndroidMyData.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			/************************** 更新會員資料 ************************************/
			pstmt.setString(1, mem.getMem_name());
			pstmt.setString(2, mem.getMem_id());
			pstmt.setString(3, mem.getMem_psw());
			pstmt.setInt(4, mem.getMem_general_gen());
			pstmt.setDate(5, mem.getMem_general_bd());
			pstmt.setInt(6, mem.getMem_tel());
			pstmt.setString(7, mem.getMem_email());
			pstmt.setString(8, mem.getMem_add());
			pstmt.setInt(9, mem.getMem_per());
			pstmt.setInt(10, mem.getMem_type());
			pstmt.setString(11, mem.getMem_store_owner());
			pstmt.setString(12, mem.getMem_store_taxid());
			pstmt.setString(13, mem.getMem_no());

			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;			
		
			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			// Clean up JDBC resources
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
		return isUpdated;
	}

	/***************************** 刪除會員 ********************************/
	@Override
	public boolean delete(String mem_eamil) {
		// TODO Auto-generated method stub
		return false;
	}

	/************************** 查詢單一會員 ********************************/
	@Override
	public AndroidMem findById(String mem_eamil) {
		AndroidMem mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(AndroidMyData.URL, AndroidMyData.USER, AndroidMyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ID);
			/****************** 會員帳號ID查詢 **********************/
			pstmt.setString(8, mem_eamil);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mem = new AndroidMem();
				mem.setMem_no(rs.getString(1));
				mem.setMem_name(rs.getString(2));
				mem.setMem_id(rs.getString(3));
				mem.setMem_psw(rs.getString(4));
				mem.setMem_general_gen(rs.getInt(5));
				mem.setMem_general_bd(rs.getDate(6));
				mem.setMem_tel(rs.getInt(7));
				mem.setMem_email(rs.getString(8));
				mem.setMem_add(rs.getString(9));
				mem.setMem_per(rs.getInt(10));
				mem.setMem_type(rs.getInt(11));
				mem.setMem_store_owner(rs.getString(12));
				mem.setMem_store_taxid(rs.getString(13));
			}
			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mem;
	}

	/***************************** 全部會員 *********************************/
	@Override
	public List<AndroidMem> getAll() {
		return null;
	}
	@Override
	public boolean isMem(String mem_email, String mem_psw) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMem = false;	
		try {
			conn = DriverManager.getConnection(AndroidMyData.URL, AndroidMyData.USER, AndroidMyData.PASSWORD);
			ps = conn.prepareStatement(FIND_BY_ID_PSW);
			ps.setString(1, mem_email);
			ps.setString(2, mem_psw);
			ResultSet rs = ps.executeQuery();
			isMem = rs.next();
			return isMem;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
					if(ps !=null) {
						ps.close();				
			} 
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return isMem;
		}

public boolean isUserIdExist(String mem_email) {
	Connection conn = null;
	PreparedStatement ps = null;
	boolean isUserIdExist = false;
	try {
		conn = DriverManager.getConnection(AndroidMyData.URL, AndroidMyData.USER,
				AndroidMyData.PASSWORD);
		ps = conn.prepareStatement(CHECK_ID_EXIST);
		ps.setString(1, mem_email);
		ResultSet rs = ps.executeQuery();
		isUserIdExist = rs.next();
		return isUserIdExist;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return isUserIdExist;
}

}
