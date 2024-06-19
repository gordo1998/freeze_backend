package dao;

import java.util.List;
import java.util.Map;

public interface ListaCompraIntD {
	int anyProductLista(String codigoBarra, int usuario);
	Map<String, Object> getProduct(String codigoBarra);
	int cantidadProductoLista(int usuario, int producto);
	void newProductLista(int cantidad, int usuario, int producto);
	void updateProductLista(int cantidad, int usuario, int porducto);
	Map<String, Object> getProducts(int usuario);
	void deleteProduct(int producto, int usuario);
	void deleteProducts(List<Integer> productos, int usuario);
}
