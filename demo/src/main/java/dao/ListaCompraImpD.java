package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import connection.ConnectionMySQL;
import utils.UtilsRequests;

@Repository
public class ListaCompraImpD implements ListaCompraIntD {

	@Override
	public int anyProductListaCompra(String codigoBarra, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.ANY_PRODUCT_LISTA_COMPRA);){
			
			statement.setString(1, codigoBarra);
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
	public Map<String, Object> getProductCompra(String codigoBarra, int usuario) {
		Map<String, Object> mapResult = new HashMap<>();
		
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.EXIST_PRODUCT_LISTA_COMPRA);){
			
			statement.setString(1, codigoBarra);
			statement.setInt(2, usuario);
			
			ResultSet result = statement.executeQuery();
			
			Map<String, Object> resultado = new HashMap<>();
			if (result.next()) {	
				resultado.put("codigoBarra", result.getString(1));
				resultado.put("idProducto", result.getInt(2));
				
				mapResult.put("statusCode", 200);
				mapResult.put("resultado", resultado);
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
	public int cantidadProductoListaCompra(int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.CANTIDAD_PRODUCTO_LISTA_COMPRA)){
			
			statement.setInt(1, usuario);
			statement.setInt(2, producto);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
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
	public void newProductListaCompra(int cantidad, int usuario, int producto) {
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
	public void updateProductListaCompra(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.UPDATE_PRODUCT_LISTA_COMPRA)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, producto);
			statement.setInt(3, usuario);
			
			statement.executeUpdate();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Map<String, Object> getProductsCompra(int usuario) {
		Map<String, Object> mapResult = new HashMap<>();
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.SELECT_ALL_PRODUCT_LISTA)){
			
			statement.setInt(1, usuario);
			
			ResultSet result = statement.executeQuery();
			
			List<Map<String, Object>> listProducts = new ArrayList<>();
			
			while(result.next()) {
				Map<String, Object> mapProduct = new HashMap<>();
				mapProduct.put("idProducto", result.getInt(4));//Es 4 porque la posición del id escaneado es la 4
				mapProduct.put("codigoBarra", result.getString(6));
				mapProduct.put("nombre", result.getString(7));
				mapProduct.put("cantidad", result.getInt(2));
				
				listProducts.add(mapProduct);
				
			}
			mapResult.put("statusCode", 200);
			mapResult.put("resultado", listProducts);
			
		}catch(SQLException i) {
			mapResult.put("statusCode", 500);
			mapResult.put("message", "Something wrong with the server: " + i);
		}
		return mapResult;
	}


	@Override
	public void deleteProductsCompra(List<Integer> productos, int usuario) {
		for(Integer producto: productos) {
			try(Connection connection = ConnectionMySQL.getConnection();
				PreparedStatement statement = connection.prepareStatement(UtilsRequests.DELETE_PRODUCT_LISTA_COMPRA)){
				
				statement.setInt(1, producto);
				statement.setInt(2, usuario);
				
				statement.executeUpdate();			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
