package service;

import java.util.HashMap;
import java.util.Map;

import dao.UsersImpD;

public class UsersImpS implements UsersIntS{
	UsersImpD dao = new UsersImpD();
	Map<String, Object> estado; 

	@Override
	public Map<String, Object> addUser(String email, String passwd) {
		estado = new HashMap<>();
		if(dao.existUser(email) == 1) {
			estado.put("statusCode", 200);
			estado.put("existUser", true);
		}else if(dao.existUser(email) == 0){
			estado.put("statusCode", 200);
			estado.put("existUser", false);
			dao.newUser(email, passwd);
		}else if (dao.existUser(email) == -1){
			estado.put("statusCode", 500);
			estado.put("message", "Something wrong with the sql server");
		}else {
			estado.put("statusCode", 500);
			estado.put("message", "Something wrong with the server");
		}
		return estado;
	}

	@Override
	public Map<String, Object> existUserService(String email, String passwd) {
		estado = new HashMap<>();
		if(dao.userAutenticationExist(email, passwd) == 1) {
			estado.put("statusCode", 200);
			estado.put("existUser", true);
			return estado;
		}else if (dao.userAutenticationExist(email, passwd) == 0) {
			estado.put("statusCode", 200);
			estado.put("existUser", false);
			return estado;
		}else if (dao.userAutenticationExist(email, passwd) == -1) {
			estado.put("satusCode", 500);
			estado.put("message", "Something wrong with the sql server");
			return estado;
		}else {
			estado.put("statusCode", 500);
			estado.put("message", "Something wrong with the server");
			return estado;
		}
	}
}
