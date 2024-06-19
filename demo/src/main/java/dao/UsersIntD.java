package dao;

public interface UsersIntD {
	int existUser(String email);
	int userAutenticationExist(String email, String passwd);
	void newUser (String email, String passwd);
}
