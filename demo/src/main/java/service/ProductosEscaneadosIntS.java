package service;

import java.util.Map;

public interface ProductosEscaneadosIntS {
	
	Map<String, Object> existProduct(String codigoBarra);
	void addProduct(String codigoBarra, String nombre);
	
}
