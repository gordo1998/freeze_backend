package com.example.demo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dao.InventoryImpD;
import dao.UsersImpD;
import service.UsersImpS;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		UsersImpD dao = new UsersImpD();
		UsersImpS serviceUser = new UsersImpS();
		InventoryImpD inventario = new InventoryImpD();
		
		//System.out.println(dao.existUser("jordibarreda98@gmail.com"));
		
		//dao.newUser("jordibarreda98@gmail.com", "1234");
		
		/*
		Map<String, Object> mapService = serviceUser.addUser("jordibarreda98@gmail.com", "1234");
		if((Integer)mapService.get("statusCode") == 200) {
			if((boolean)mapService.get("existUser") == true) {
				System.out.println("Ya existe el usuario!");
			}else if ((boolean)mapService.get("existUser") == false){
				System.out.println("Se ha agregado el usuario!");
			}
		}else {
			System.out.println((String)mapService.get("message"));
		}
		*/
		
		/*
		System.out.println(String.valueOf(inventario.anyProductInventario("867677t", 1)));
		int n = inventario.anyProductInventario("867677t", 1);
		if(n == 0) {
			
			System.out.println(String.valueOf(inventario.anyProductInventario("867677t", 1)));
			if((Integer)inventario.getProduct("867677t").get("statusCode") == 200) {
				
				Map<String, Object> result = (Map<String, Object>)inventario.getProduct("867677t").get("resultado");
				
				inventario.newProductInventario(2, 1, (Integer)result.get("idProducto"));
				
				System.out.println("El producto se ha añadido correctamente");
				
				
			}else {
				System.out.println((String)inventario.getProduct("867677t").get("message"));
			}			
		}else {
			System.out.println("El producto ya existe!");
		}
		
		*/
		
		/*
		try {
			int cantidad = inventario.cantidadProductInventario(1, 1);
			System.out.println("La cantidad del producto es: " + cantidad);
			inventario.updateProductInventario(cantidad + 1, 1, 1);
			System.out.println("Producto actualizado!");
		}catch(NullPointerException e) {
			System.out.println("El producto o el usuario no existen!");
		}
		
		*/
		
		Map<String, Object> productos = inventario.getProducts(1);
		if((Integer)productos.get("statusCode") == 200) {
			List<Map<String, Object>> product = (List<Map<String, Object>>) productos.get("resultado");
			for(Map<String, Object> p: product) {
				System.out.println("" + p.get("idProducto"));
				System.out.println("" + p.get("codigoBarra"));
				System.out.println("" + p.get("nombre"));
				System.out.println("" + p.get("cantidad"));
			}
			
			
			System.out.println();
			System.out.println();
	
		}
		
		
		
	
		
	}

}
