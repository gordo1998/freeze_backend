package service;

import java.util.List;
import java.util.Map;

public interface InventoryIntS {
	void addProductInventario(int cantidad, int usuario, String codigo_barra);
	Map<String, Object> mostrarProducts(int usuario);
	void eliminarProduct(List<Integer> productos, int usuario);
	
}
