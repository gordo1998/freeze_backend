package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import connection.ConnectionMySQL;
import utils.UtilsRequests;

@Repository
public class ListaCompraImpD implements ListaCompraIntD {

	@Override
	public int anyProductLista(String codigoBarra, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.ANY_PRODUCT_INVENTARIO);){
			
			statement.setString(1, codigoBarra);
			statement.setInt(2, usuario);
			
			ResultSet result = statement.executeQuery();
			
			return result.getInt(1);
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Map<String, Object> getProduct(String codigoBarra) {
		Map<String, Object> mapResult = new HashMap<>();
		
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.EXIST_PRODUCT_LISTA_COMPRA);){
			
			statement.setString(1, codigoBarra);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {	
				mapResult.put("statusCode", 200);
				mapResult.put("resultado", result);
			}else {
				throw new IOException();
			}
			
		}catch(SQLException | IOException e) {
			e.printStackTrace();
			
			mapResult.put("statusCode", 500);
			mapResult.put("message", "Something wrong with Server: " + e);
		}
		return mapResult;
	}

	@Override
	public int cantidadProductoLista(int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.CANTIDAD_PRODUCTO_LISTA_COMPRA)){
			statement.setInt(1, usuario);
			statement.setInt(2, producto);
			ResultSet result = statement.executeQuery();
			return result.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void newProductLista(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.INSERT_PRODUCT_LISTA_COMPRA)){
				
			statement.setInt(1, cantidad);
			statement.setInt(2, usuario);
			statement.setInt(3, producto);
			
			statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public void updateProductLista(int cantidad, int usuario, int porducto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.UPDATE_PRODUCT_LISTA_COMPRA)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, usuario);
			statement.setInt(3, porducto);
			
			statement.executeUpdate();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Map<String, Object> getProducts(int usuario) {
		Map<String, Object> mapResult = new HashMap<>();
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.SELECT_ALL_PRODUCT_LISTA)){
			
			statement.setInt(1, usuario);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				mapResult.put("statusCode", 200);
				mapResult.put("resultado", result);
			}else {
				throw new IOException();
			}
			
		}catch(SQLException | IOException i) {
			mapResult.put("statusCode", 500);
			mapResult.put("message", "Something wrong with the server: " + i);
		}
		return mapResult;
	}


	@Override
	public void deleteProduct(int producto, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.DELETE_PRODUCT_LISTA_COMPRA)){
			
			statement.setInt(1, producto);
			statement.setInt(2, usuario);
			
			statement.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProducts(List<Integer> productos, int usuario) {
		for(Integer producto: productos) {
			try(Connection connection = ConnectionMySQL.getConnection();
				PreparedStatement statement = connection.prepareStatement(UtilsRequests.DELETE_PRODUCT_LISTA_COMPRA)){
				
				statement.setInt(1, producto);
				statement.setInt(2, usuario);
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
