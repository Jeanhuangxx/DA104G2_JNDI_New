package android.member.model;

import java.util.List;

public interface AndroidMemDAO {
	boolean isMem(String userId, String password);
	boolean isUserIdExist(String email);
	boolean add(AndroidMem mem);
	boolean update(AndroidMem mem);
	boolean delete(String userId);
	AndroidMem findById(String userId);
	 List<AndroidMem> getAll();
}