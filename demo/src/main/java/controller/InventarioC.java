package controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.InventoryImpS;

@RestController
public class InventarioC {
	
	InventoryImpS inventario = new InventoryImpS();
	
	@PutMapping(value = "introducirProductos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void introducirProducto(@RequestBody Map<String, Object> productos) {
		inventario.addProductInventario((Integer)productos.get("cantidad"), (Integer)productos.get("usuario"), (String)productos.get("codigo_barras"));
	}
	
	@DeleteMapping(value = "deleteProducts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProducts(@RequestBody Map<String, Object> productos) {
		inventario.eliminarProduct((List<Integer>) productos.get("productos"), (Integer)productos.get("usuario"));
	}
	
	@GetMapping(value = "getProducts/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProductos(@PathVariable("usuario")int usuario){
		return inventario.mostrarProducts(usuario);
	}
}
