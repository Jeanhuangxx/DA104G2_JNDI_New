package com.dive.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.util.MyDataSource;



public class DiveJNDIDAO implements DiveDAO_interface{

	
	//SQL指令入/
	private static final String INSERT_STMT = 
			"INSERT INTO DIVE (dive_no, weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang) VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT dive_no, weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang FROM DIVE "
			+ "order by dive_no";
	private static final String GET_ONE_STMT = 
			"SELECT dive_no, weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang FROM DIVE "
			+ "where dive_no = ?";
	private static final String GET_ONE_STMT_BY_NAME = 
			"SELECT dive_no, weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang FROM DIVE "
			+ "where dive_name LIKE '%'||?||'%' ";
	private static final String GET_ONE_STMT_BY_WeatherArea = 
			"SELECT dive_no, weather_area_no, dive_name, dive_des, dive_img, dive_status, dive_lat, dive_lang FROM DIVE "
			+ "where weather_area_no = ?";
	private static final String UPDATE = 
			"UPDATE DIVE set weather_area_no=?, dive_name=?, dive_des=?, dive_img=?, dive_status=?, dive_lat=?, dive_lang=? "
			+ "where dive_no = ?";
	private static final String GET_ALL_BY_STATUS = 
			"SELECT * FROM DIVE "
			+ "where dive_status = ?";
	
	//CRU方法
	@Override
	public void insert(DiveVO diveVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, diveVO.getWeather_area_no());
			pstmt.setString(2, diveVO.getDive_name());
			pstmt.setString(3, diveVO.getDive_des());
			pstmt.setBytes(4, diveVO.getDive_img());
			pstmt.setInt(5, diveVO.getDive_status());
			pstmt.setString(6, diveVO.getDive_lat());
			pstmt.setString(7, diveVO.getDive_lang());

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
	public void update(DiveVO diveVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, diveVO.getWeather_area_no());
			pstmt.setString(2, diveVO.getDive_name());
			pstmt.setString(3, diveVO.getDive_des());
			pstmt.setBytes(4, diveVO.getDive_img());
			pstmt.setInt(5, diveVO.getDive_status());
			pstmt.setString(6, diveVO.getDive_lat());
			pstmt.setString(7, diveVO.getDive_lang());
			pstmt.setString(8, diveVO.getDive_no());

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
	public DiveVO findByPrimaryKey(String dive_no) {
		// TODO Auto-generated method stub
		DiveVO diveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dive_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diveVO = new DiveVO();
				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setWeather_area_no(rs.getString("weather_area_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				diveVO.setDive_des(rs.getString("dive_des"));
				diveVO.setDive_img(rs.getBytes("dive_img"));
				diveVO.setDive_status(rs.getInt("dive_status"));
				diveVO.setDive_lat(rs.getString("dive_lat"));
				diveVO.setDive_lang(rs.getString("dive_lang"));
				diveVO.setDive_no(rs.getString("dive_no"));

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
		return diveVO;
	}

	@Override
	public List<DiveVO> getListByWeatherArea(String WeatherAreaNo) {
		// TODO Auto-generated method stub
		DiveVO diveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiveVO> list = new ArrayList<DiveVO>();
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_WeatherArea);
			
			pstmt.setString(1, WeatherAreaNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diveVO = new DiveVO();
				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setWeather_area_no(rs.getString("weather_area_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				diveVO.setDive_des(rs.getString("dive_des"));
				diveVO.setDive_img(rs.getBytes("dive_img"));
				diveVO.setDive_status(rs.getInt("dive_status"));
				diveVO.setDive_lat(rs.getString("dive_lat"));
				diveVO.setDive_lang(rs.getString("dive_lang"));
				diveVO.setDive_no(rs.getString("dive_no"));
				list.add(diveVO);
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
	public List<DiveVO> getListByDiveName(String dive_name) {
		// TODO Auto-generated method stub
		DiveVO diveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiveVO> list = new ArrayList<DiveVO>();
		
		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_NAME);
			
			pstmt.setString(1, dive_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diveVO = new DiveVO();
				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setWeather_area_no(rs.getString("weather_area_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				diveVO.setDive_des(rs.getString("dive_des"));
				diveVO.setDive_img(rs.getBytes("dive_img"));
				diveVO.setDive_status(rs.getInt("dive_status"));
				diveVO.setDive_lat(rs.getString("dive_lat"));
				diveVO.setDive_lang(rs.getString("dive_lang"));
				diveVO.setDive_no(rs.getString("dive_no"));
				list.add(diveVO);
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
	public List<DiveVO> getAll() {
		// TODO Auto-generated method stub
		List<DiveVO> list = new ArrayList<DiveVO>();
		DiveVO diveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				diveVO = new DiveVO();
				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setWeather_area_no(rs.getString("weather_area_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				diveVO.setDive_des(rs.getString("dive_des"));
				diveVO.setDive_img(rs.getBytes("dive_img"));
				diveVO.setDive_status(rs.getInt("dive_status"));
				diveVO.setDive_lat(rs.getString("dive_lat"));
				diveVO.setDive_lang(rs.getString("dive_lang"));

				list.add(diveVO); // Store the row in the list
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
	public List<DiveVO> getAllByStatus(Integer dive_status) {
		// TODO Auto-generated method stub
		List<DiveVO> list = new ArrayList<DiveVO>();
		DiveVO diveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=MyDataSource.getJNDI().getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS);
			
			pstmt.setInt(1, dive_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				diveVO = new DiveVO();
				diveVO.setDive_no(rs.getString("dive_no"));
				diveVO.setWeather_area_no(rs.getString("weather_area_no"));
				diveVO.setDive_name(rs.getString("dive_name"));
				diveVO.setDive_des(rs.getString("dive_des"));
//				diveVO.setDive_img(rs.getBytes("dive_img"));
				diveVO.setDive_img(compressPic(rs.getBytes("dive_img"),16,9,true));
				diveVO.setDive_status(rs.getInt("dive_status"));
				diveVO.setDive_lat(rs.getString("dive_lat"));
				diveVO.setDive_lang(rs.getString("dive_lang"));

				list.add(diveVO); // Store the row in the list
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

		DiveJNDIDAO dao = new DiveJNDIDAO();

		//=======================insert測試==========================
//		DiveVO diveVO = new DiveVO();
//		diveVO.setDIVE_NO("");
//		diveVO.setWEATHER_AREA_NO("WA00001");
//		diveVO.setDIVE_NAME("DiveName");
//		diveVO.setDIVE_DES("Beautifull Place");
//		byte[] img;
//		try {
//			img = getPictureByteArray("C:\\DA104_WebApp\\eclipse_WTP_workspace1\\DA104G2\\src\\com\\dive\\model\\diving-in-phuket.jpg");
//			diveVO.setDIVE_IMG(img);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		diveVO.setDIVE_STATUS(0);
//		diveVO.setDIVE_LAT("125.0215648");
//		diveVO.setDIVE_LANG("25.15646845");
		
//		dao.insert(diveVO);
//		System.out.println("Insert - OK");
		
//		=====================Update測試==============================
//		DiveVO diveVO = new DiveVO();
//		diveVO.setDive_no("D000001");
//		diveVO.setWeather_area_no("WA00001");
//		diveVO.setDive_name("DiveName45645646");
//		diveVO.setDive_des("Ugly Place");
//		byte[] img;
//		try {
//			img = getPictureByteArray("C:\\DA104_WebApp\\eclipse_WTP_workspace1\\DA104G2\\src\\com\\dive\\model\\diving-in-phuket.jpg");
//			diveVO.setDive_img(img);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		diveVO.setDive_status(0);
//		diveVO.setDive_lat("aaaaaaaaa");
//		diveVO.setDive_lang("bbbbbbbb");
//		
//		dao.update(diveVO);
//		System.out.println("update - OK");
		
		
		//=====================find測試================================
//		DiveVO diveVO1 = dao.findByPrimaryKey("D000001");
//		System.out.println(diveVO1);
//		System.out.println("find - OK");
		
		
//		=====================ListAll測試==============================
		List<DiveVO> allDiveItems =  new ArrayList<DiveVO>();
		List<DiveVO> allDiveItems1 =  dao.getAll();
		for(DiveVO diveItem: allDiveItems1) {
			System.out.println(diveItem);
		}
		System.out.println("ListAll - OK");
		
	}
	
	
	
	//insert()呼叫用
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {//Input管子裝水桶(fis.read(buffer))
			baos.write(buffer, 0, i);//利用Output管子(baos.write(buffer))來連結水桶，輸出到資料庫
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	
	public static byte[] compressPic(byte[] imageByte, int width, int height, boolean gp) {
		byte[] inByte = null;
		try { 
		ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
		Image img = ImageIO.read(byteInput);
		// 判斷圖片格式是否正確 
		if (img.getWidth(null) == -1) {
		return inByte;
		} else { 
		int newWidth; int newHeight; 
		// 判斷是否是等比縮放 
		if (gp == true) { 
		// 為等比縮放計算輸出的圖片寬度及高度 
		double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1; 
		double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
		// 根據縮放比率大的進行縮放控制 
		double rate = rate1 > rate2 ? rate1 : rate2; 
		newWidth = (int) (((double) img.getWidth(null)) / rate); 
		newHeight = (int) (((double) img.getHeight(null)) / rate); 
		} else { 
		newWidth = width; // 輸出的圖片寬度 
		newHeight = height; // 輸出的圖片高度 
		} 
		BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
		img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		/*
		* Image.SCALE_SMOOTH 的縮略演算法 生成縮圖片的平滑度的
		* 優先順序比速度高 生成的圖片質量比較好 但速度慢
		*/ 
		tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		// 指定寫圖片的方式為 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
//		       // 要使用壓縮，必須指定壓縮方式為MODE_EXPLICIT
//		       imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
//		       // 這裡指定壓縮的程度，引數qality是取值0~1範圍內，
//		       imgWriteParams.setCompressionQuality((float)45217/imageByte.length);
//		                        
//		       imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
//		       ColorModel colorModel = ColorModel.getRGBdefault();
//		       // 指定壓縮時使用的色彩模式
//		       imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
//		               .createCompatibleSampleModel(100, 100)));
		//將壓縮後的圖片返回位元組流
		ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);
		imgWrier.reset();
		// 必須先指定 out值，才能呼叫write方法, ImageOutputStream可以通過任何 OutputStream構造
		imgWrier.setOutput(ImageIO.createImageOutputStream(out));
		// 呼叫write方法，就可以向輸入流寫圖片
		imgWrier.write(null, new IIOImage(tag, null, null), imgWriteParams);
		out.flush();
		out.close();
		byteInput.close();
		inByte = out.toByteArray();

		} 
		} catch (IOException ex) { 
		ex.printStackTrace();
		} 
		return inByte;
		}
	
	//findByPrimaryKeym()與getAll()呼叫用
	
}
