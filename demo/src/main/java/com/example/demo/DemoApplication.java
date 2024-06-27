package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import dao.InventoryImpD;
import dao.UsersImpD;
import service.InventoryImpS;
import service.UsersImpS;

@ComponentScan(basePackages = {"controller"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	
		
	}

}
