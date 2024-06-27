package service;

import java.util.List;
import java.util.Map;

public interface ListaCompraIntS {
	void addProductListaCompra(int cantidad, int usuario, String codigo_barra);
	Map<String, Object> mostrarProductsListaCompra(int usuario);
	void eliminarProductListaCompra(List<Integer> productos, int usuario);
}
