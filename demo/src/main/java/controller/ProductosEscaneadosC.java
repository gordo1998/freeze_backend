package controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.ProductosEscaneadosImpS;

@RestController
public class ProductosEscaneadosC {
	
	ProductosEscaneadosImpS pEscaneadoService = new ProductosEscaneadosImpS();
	
	@GetMapping(value = "existProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> existProductScaned(@RequestBody Map<String, String> producto){
		return pEscaneadoService.existProduct(producto.get("codigo_barras"));
	}
	
	
	@PostMapping(value = "addProductScanned", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addProduct(@RequestBody Map<String, Object> producto) {
		pEscaneadoService.addProduct((String) producto.get("codigo_barras"), (String) producto.get("nombre"));
	}
	
}
