package com.diveMessage.model;

import java.sql.Timestamp;
import java.util.List;

public class DiveMessageService {

	private DiveMessage_interface dao;

	public DiveMessageService() {
		dao = new DiveMessageJNDIDAO();
	}

//===================================INSERT=======================================

	public DiveMessageVO addDiveMessage(String divmeg_no, String dive_no, String mem_no, 
			Timestamp divmeg_time, String divmeg_note) {

		DiveMessageVO diveMessageVO = new DiveMessageVO();
		diveMessageVO.setDivmeg_no(divmeg_no);
		diveMessageVO.setDive_no(dive_no);
		diveMessageVO.setMem_no(mem_no);
		diveMessageVO.setDivmeg_time(divmeg_time);
		diveMessageVO.setDivmeg_note(divmeg_note);
		dao.insert(diveMessageVO);

		return diveMessageVO;
	}
	
//=====================================Update=======================================
	
	public DiveMessageVO updateDiveMessage(String divmeg_no, String dive_no, String mem_no, 
			Timestamp divmeg_time, String divmeg_note) {

		DiveMessageVO diveMessageVO = new DiveMessageVO();
		diveMessageVO.setDivmeg_no(divmeg_no);
		diveMessageVO.setDive_no(dive_no);
		diveMessageVO.setMem_no(mem_no);
		diveMessageVO.setDivmeg_time(divmeg_time);
		diveMessageVO.setDivmeg_note(divmeg_note);
		dao.update(diveMessageVO);

		return diveMessageVO;
	}


//========================FindOneById=============================================
	public DiveMessageVO getOneDive(String divmeg_no) {
		return dao.findByPrimaryKey(divmeg_no);
	}

//========================ListAll===================================================
	public List<DiveMessageVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
//========================測試區(變數自己改)==================================================
//	public static void main(String[] args) {
//
//		DiveLocalService diveService = new DiveLocalService();
//		
//
//		byte[] img=null;
//		try {
//			img = getPictureByteArray("C:\\DA104_WebApp\\eclipse_WTP_workspace1\\DA104G2\\src\\com\\dive\\model\\diving-in-phuket.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		insert
//		diveService.addDive("", "WA00001", "DiveName", "Beautifull Place", 
//				null, 0, "125.0215648",  "25.15646845");
//		update
//		diveService.updateDive("D000002", "WA00001", "DiveName", "Beautifull Place", 
//				null, 0, "125.0215648",  "25.15646845");
//	
//	find
//	System.out.println(diveService.getOneDive("D000001"));
//	ListAll
//	System.out.println(diveService.getAll());
//}
//	
//	insert()呼叫用
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {//Input管子裝水桶(fis.read(buffer))
//			baos.write(buffer, 0, i);//利用Output管子(baos.write(buffer))來連結水桶，輸出到資料庫
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
}
