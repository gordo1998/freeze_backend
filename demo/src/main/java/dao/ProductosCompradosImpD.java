package dao;

import java.io.IOException;
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
	public int anyProductComprado(String codigobarra, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.ANY_PRODUCT_COMPRADOS);){
			
			statement.setString(1, codigobarra);
			statement.setInt(2, usuario);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}else {
				throw new IOException();
			}
		} catch(SQLException | IOException e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void newProductComprado(int cantidad, int usuario, int producto) {
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
	
	public int cantidadProductComprado(int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.CANTIDAD_PRODUCTO_COMPRADOS)){
			
			statement.setInt(1, usuario);
			statement.setInt(2, producto);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getInt(1);
			}else {
				throw new IOException();
			}
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void updateProductComprado(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.UPDATE_PRODUCT_COMPRADOS)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, producto);
			statement.setInt(3, usuario);
			
			statement.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	

	

}
