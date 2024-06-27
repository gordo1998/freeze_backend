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

import service.ListaCompraImpS;

@RestController
public class ListaCompraC {
	
	ListaCompraImpS lCService = new ListaCompraImpS();
	
	@PutMapping(value = "introducirProductosLCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void introducirProducto(@RequestBody Map<String, Object> productos) {
		lCService.addProductListaCompra((Integer)productos.get("cantidad"), (Integer)productos.get("usuario"), (String)productos.get("codigo_barras"));
	}
	
	@DeleteMapping(value = "deleteProductsLCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProducts(@RequestBody Map<String, Object> productos) {
		lCService.eliminarProductListaCompra((List<Integer>) productos.get("productos"), (Integer)productos.get("usuario"));
	}
	
	@GetMapping(value = "getProductsLCompra/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProductos(@PathVariable("usuario")int usuario){
		return lCService.mostrarProductsListaCompra(usuario);
	}
}
