package service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.ListaCompraImpD;
import dao.ProductosEscaneadosImpD;

@Service
public class ListaCompraImpS implements ListaCompraIntS{

	ListaCompraImpD listaCompra = new ListaCompraImpD();
	ProductosEscaneadosImpD pEscaneados = new ProductosEscaneadosImpD();
	
	@Override
	public void addProductListaCompra(int cantidad, int usuario, String codigo_barra) {
		int existProduct = listaCompra.anyProductListaCompra(codigo_barra, usuario);
		System.out.println(existProduct);
	
		if(existProduct == 0) {
			int productNuevo = pEscaneados.returnProductScanner(codigo_barra);
			listaCompra.newProductListaCompra(cantidad, usuario, productNuevo);
			
		}else {
			Map<String, Object> product = listaCompra.getProductCompra(codigo_barra, usuario);
			Map<String, Object> contenidoElemento = (Map<String, Object>)product.get("resultado");
			int cantidadProducto = listaCompra.cantidadProductoListaCompra(usuario,(Integer) contenidoElemento.get("idProducto"));
			System.out.println(cantidadProducto);
			listaCompra.updateProductListaCompra((cantidadProducto + cantidad), usuario, (Integer) contenidoElemento.get("idProducto"));
		}
		
	}

	@Override
	public Map<String, Object> mostrarProductsListaCompra(int usuario) {
		Map<String, Object> productos = listaCompra.getProductsCompra(usuario);
		return productos;
	}

	@Override
	public void eliminarProductListaCompra(List<Integer> productos, int usuario) {
		listaCompra.deleteProductsCompra(productos, usuario);
	}

}
