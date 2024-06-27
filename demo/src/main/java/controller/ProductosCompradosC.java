package controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.ProductosCompradosImpS;

@RestController
public class ProductosCompradosC {
	
	ProductosCompradosImpS pCService = new ProductosCompradosImpS();
	
	@PutMapping(value = "introducirProductosComprados", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void introducirProducto(@RequestBody Map<String, Object> productos) {
		pCService.addProductComprado((Integer) productos.get("cantidad"), (Integer) productos.get("usuario"), (String) productos.get("codigo_barras"));
	}
	
}
