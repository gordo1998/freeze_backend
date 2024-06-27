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
public class InventoryImpD implements InventoryIntD {
	//DEVUELVE LA CANTIDAD DE UN PRODUCTO ESPECIFICADO, DEBE DEVOLVER 1 O 0
	@Override
	public int anyProductInventario(String codigoBarra, int usuario) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.ANY_PRODUCT_INVENTARIO);){
			
			statement.setString(1, codigoBarra);
			statement.setInt(2, usuario);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}else {
				throw new IOException();
			}
		} catch(SQLException | IOException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	//DEVUELVE UN MAP QUE CONTIENE EL CODIGO DE ESTADO Y EL ID EL PRODUCTO. 
	//SI EL PRODUCTO NO EXISTE EN EL INVENTARIO DARÁ UN ERROR.
	@Override
	public Map<String, Object> getProduct(String codigoBarra, int usuario) {
		Map<String, Object> mapResult = new HashMap<>();
		
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.EXIST_PRODUCT_INVENTARIO);){
			
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
	
	//DEVUELVE LA CANTIDAD DE UN PRODUCTO
	@Override
	public int cantidadProductInventario(int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.CANTIDAD_PRODUCTO_INVENTARIO)){
			
			statement.setInt(1, usuario);
			statement.setInt(2, producto);
			
			ResultSet result = statement.executeQuery();
			
			return result.next() ? result.getInt(1) : null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//INSERTA UN NUEVO PRODUCTO. SE LE HA DE PASAR LA CANTIDAD, EL ID DE UDUARIO
	// Y EL ID DEL PRODUCTO.
	@Override
	public void newProductInventario(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.INSERT_PRODUCT_INVENTARIO)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, usuario);
			statement.setInt(3, producto);
			
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//ATCUALIZA LAS CANTIDADES DE UN PRODUCTO QUE YA EXISTE EN EL INVENTARIO.
	//SE LE HA DE PASAR EL ID DE USUARIO Y EL ID DEL PRODUCTO.
	@Override
	public void updateProductInventario(int cantidad, int usuario, int producto) {
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.UPDATE_PRODUCT_INVENTARIO)){
			
			statement.setInt(1, cantidad);
			statement.setInt(2, producto);
			statement.setInt(3, usuario);
			
			statement.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//DEVUELVE UN MAPA QUE CONTIENE UNA LISTA DE PRODUCTOS CON EL CODIGO 
	//DE BARRA Y CONTIENE EL ID DEL 
	//PRODUCTO MÁS EL NOMBRE, MÁS LAS CANTIDADES.
	//SI LOS PRODUCTOS NO EXISTEN EN EL INVENTARIO DARÁ ERROR.
	@Override
	public Map<String, Object> getProducts(int usuario) {
		Map<String, Object> mapResult = new HashMap<>();
		try(Connection connection = ConnectionMySQL.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtilsRequests.SELECT_ALL_PRODUCT_INVENTARIO)){
			
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
	public void deleteProducts(List<Integer> productos, int usuario) {
		for(Integer producto: productos) {
			try(Connection connection = ConnectionMySQL.getConnection();
				PreparedStatement statement = connection.prepareStatement(UtilsRequests.DELETE_PRODUCT_INVENTARIO)){
				
				statement.setInt(1, producto);
				statement.setInt(2, usuario);
				
				statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}



}
