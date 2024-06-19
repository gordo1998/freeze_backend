package service;

import java.util.Map;

public interface UsersIntS {
	Map<String, Object> addUser(String email, String passwd);
	Map<String, Object> existUserService(String email, String passwd);
}
