package service;

import java.util.HashMap;
import java.util.Map;

import dao.ProductosEscaneadosImpD;

public class ProductosEscaneadosImpS implements ProductosEscaneadosIntS{
	
	ProductosEscaneadosImpD pEscaneado = new ProductosEscaneadosImpD();

	@Override
	public Map<String, Object> existProduct(String codigoBarra) {
		Map<String, Object> product = new HashMap<>();
		if(pEscaneado.anyProductEscaneado(codigoBarra) == 0) {
			product.put("statusCode", 200);
			product.put("exist", false);
		}else if(pEscaneado.anyProductEscaneado(codigoBarra) >= 1){
			product.put("statusCode", 200);
			product.put("exist", true);
		}else {
			product.put("statusCode", 500);
			product.put("message", "Ha ocurrido un error con el servidor.");
		}
		return product;
	}
	
	@Override
	public void addProduct(String codigoBarra, String nombre) {
		pEscaneado.addProductEscaneado(codigoBarra, nombre);
	}

}
