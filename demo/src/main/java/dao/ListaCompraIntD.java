package dao;

import java.util.List;
import java.util.Map;

public interface ListaCompraIntD {
	int anyProductListaCompra(String codigoBarra, int usuario);
	Map<String, Object> getProductCompra(String codigoBarra, int usuario);
	int cantidadProductoListaCompra(int usuario, int producto);
	void newProductListaCompra(int cantidad, int usuario, int producto);
	void updateProductListaCompra(int cantidad, int usuario, int producto);
	Map<String, Object> getProductsCompra(int usuario);
	void deleteProductsCompra(List<Integer> productos, int usuario);
}
