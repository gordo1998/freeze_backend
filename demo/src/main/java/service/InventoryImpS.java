package service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import controller.InventarioC;
import dao.InventoryImpD;
import dao.ProductosEscaneadosImpD;

@Service
public class InventoryImpS implements InventoryIntS{
	InventoryImpD inventario = new InventoryImpD();
	ProductosEscaneadosImpD escaner = new ProductosEscaneadosImpD();
	

	@Override
	public void addProductInventario(int cantidad, int usuario, String codigo_barra) {
		int existProduct = inventario.anyProductInventario(codigo_barra, usuario);
		System.out.println(existProduct);
	
		if(existProduct == 0) {
			int productNuevo = escaner.returnProductScanner(codigo_barra);
			inventario.newProductInventario(cantidad, usuario, productNuevo);
			
		}else {
			Map<String, Object> product = inventario.getProduct(codigo_barra, usuario);
			Map<String, Object> contenidoElemento = (Map<String, Object>)product.get("resultado");
			int cantidadProducto = inventario.cantidadProductInventario(usuario,(Integer) contenidoElemento.get("idProducto"));
			System.out.println(cantidadProducto);
			inventario.updateProductInventario((cantidadProducto + cantidad), usuario, (Integer) contenidoElemento.get("idProducto"));
		}
		
	}

	@Override
	public Map<String, Object> mostrarProducts(int usuario) {
		Map<String, Object> productos = inventario.getProducts(usuario);
		return productos;
	}

	@Override
	public void eliminarProduct(List<Integer> productos, int usuario) {
		inventario.deleteProducts(productos, usuario);
	}
	
	
}
