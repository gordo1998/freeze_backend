package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import connection.ConnectionMySQL;
import utils.UtilsRequests;

@Repository
public class ProductosCompradosImpD implements ProductosCompradosIntD{
	
	@Override
	public int anyProductComprados(String codigobarra, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.ANY_PRODUCT_COMPRADOS);){
			
			statement.setString(1, codigobarra);
			statement.setInt(2, usuario);
			
			ResultSet result = statement.executeQuery();
			
			return result.getInt(1);
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void newProductInventario(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.INSERT_PRODUCT_COMPRADOS)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, usuario);
			statement.setInt(3, producto);
			
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductInventario(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.UPDATE_PRODUCT_COMPRADOS)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, usuario);
			statement.setInt(3, producto);
			
			statement.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	

	

}
