package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import connection.ConnectionMySQL;
import utils.UtilsRequests;
@Repository
public class UsersImpD implements UsersIntD{
	@Override
	public int existUser(String email) {
		try (
			Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.EXISTUSER)) {
			
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}else {
				throw new Exception();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}catch(Exception i) {
			return -2;
		}
		
	}

	@Override
	public int userAutenticationExist(String email, String passwd) {
		try (Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.USERAUTENTICATION)){
			statement.setString(1, email);
			statement.setString(2, passwd);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}else {
				throw new Exception();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}catch(Exception i) {
			return -2;
		}
	}

	@Override
	public void newUser(String email, String passwd) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.NEWUSER)){
			statement.setString(1, email);
			statement.setString(2, passwd);
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
