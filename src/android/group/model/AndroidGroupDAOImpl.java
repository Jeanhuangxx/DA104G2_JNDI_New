package android.group.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AndroidGroupDAOImpl implements AndroidGroupDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA104G2";
	String passwd = "123456";

	//透過MEM表格的MEM_ID找到該欄位的MEM_NO，並且使用此MEM_NO去修改GROUP_JOIN表格的GROUP_QR_STATUS欄位改為1
	private static final String UPDATE = 
			"UPDATE GROUP_JOIN Set GROUP_JOIN.GROUP_QR_STATUS = '1'  Where GROUP_JOIN.MEM_NO in (Select MEM_NO From MEM Where MEM.MEM_ID= ? )";
	
	
	@Override
	public void update(String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
//			System.out.println("+++++");//測試
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			
			System.out.println("報到成功！");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	
}
