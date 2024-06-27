package dao;

import java.util.List;
import java.util.Map;

public interface InventoryIntD {
	int anyProductInventario(String codigoBarra, int usuario);
	Map<String, Object> getProduct(String codigoBarra, int usuario);
	int cantidadProductInventario(int usuario, int producto);
	void newProductInventario(int cantidad, int usuario, int producto);
	void updateProductInventario(int cantidad, int usuario, int producto);
	Map<String, Object> getProducts(int usuario);
	void deleteProducts(List<Integer> productos, int usuario);
	
}
