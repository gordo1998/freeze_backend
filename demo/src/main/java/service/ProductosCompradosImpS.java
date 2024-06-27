package service;

import java.util.Map;

import org.springframework.stereotype.Service;

import dao.ProductosCompradosImpD;
import dao.ProductosEscaneadosImpD;

@Service
public class ProductosCompradosImpS implements ProductosCompradosIntS{
	
	ProductosCompradosImpD pComprados = new ProductosCompradosImpD();
	ProductosEscaneadosImpD pEscaneados = new ProductosEscaneadosImpD();
	
	@Override
	public void addProductComprado(int cantidad, int usuario, String codigo_barra) {
		int existProduct = pComprados.anyProductComprado(codigo_barra, usuario);
		System.out.println(existProduct);
	
		if(existProduct == 0) {
			int productNuevo = pEscaneados.returnProductScanner(codigo_barra);
			pComprados.newProductComprado(cantidad, usuario, productNuevo);
			
		}else {
			int productNuevo = pEscaneados.returnProductScanner(codigo_barra);
			int cantidadProducto = pComprados.cantidadProductComprado(usuario, productNuevo);
			pComprados.updateProductComprado((cantidadProducto + cantidad), usuario, productNuevo);
		}
		
	}

}
