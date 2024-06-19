package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import connection.ConnectionMySQL;
import utils.UtilsRequests;

@Repository
public class ProductosEscaneadosImpD implements ProductosEscaneadosIntD{

	@Override
	public int anyProductEscaneado(String codigoBarra) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.EXISTPRODUCT)){
			
			statement.setString(1, codigoBarra);
			
			ResultSet result = statement.executeQuery();
			
			return result.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void addProductEscaneado(String codigoBarra, String nombre) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.NEWPRODUCT)){
			
			statement.setString(1, codigoBarra);
			statement.setString(2, nombre);
			
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
