package com.example.demo;

import dao.UsersImpD;

public class pruebasJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsersImpD dao = new UsersImpD();
		System.out.println(dao.existUser("jordibarreda98@gmail.com"));
	}

}
