package utils;

public class UtilsRequests {
	//SENTENCIAS PARA LA TABLA DE USUARIOS
	public static final String EXISTUSER = "SELECT count(*) FROM users WHERE email = ?";
	public static final String USERAUTENTICATION = "SELECT count(*) FROM users WHERE email = ? AND  passwd = ?";
	public static final String NEWUSER = "INSERT INTO users (email, passwd) VALUES(?, ?)";
	
	//SENTENCIAS PARA LA TABLA DE INVENTARIO
	public static final String ANY_PRODUCT_INVENTARIO = "select count(*) from inventario inner join productos_escaneados on inventario.producto = productos_escaneados.id where codigo_barra = ? and usuario = ?";
	public static final String EXIST_PRODUCT_INVENTARIO = "select codigo_barra, productos_escaneados.id from inventario inner join productos_escaneados on inventario.producto = productos_escaneados.id where codigo_barra = ? and usuario = ?";
	public static final String CANTIDAD_PRODUCTO_INVENTARIO = "Select cantidad from inventario where usuario = ? and producto = ?";
	public static final String INSERT_PRODUCT_INVENTARIO = "Insert into inventario (cantidad, usuario, producto) values (?, ?, ?)";
	public static final String UPDATE_PRODUCT_INVENTARIO = "Update inventario set cantidad = ? where producto = ? and usuario = ?";
	public static final String DELETE_PRODUCT_INVENTARIO = "Delete from inventario where producto = ? and usuario = ?";
	public static final String SELECT_ALL_PRODUCT_INVENTARIO = "select * from inventario inner join productos_escaneados on inventario.producto = productos_escaneados.id where usuario = ?";
	
	//SENTENCIAS PARA LA TABLA DE LISTA DE LA COMPRA
	public static final String ANY_PRODUCT_LISTA_COMPRA = "select count(*) from lista_compra inner join productos_escaneados on lista_compra.producto = productos_escaneados.id where codigo_barra = ? and usuario = ?";
	public static final String EXIST_PRODUCT_LISTA_COMPRA = "select codigo_barra, productos_escaneados.id from lista_compra inner join productos_escaneados on lista_compra.producto = productos_escaneados.id where codigo_barra = ? and usuario = ?";
	public static final String CANTIDAD_PRODUCTO_LISTA_COMPRA = "Select cantidad from lista_compra where usuario = ? and producto = ?";
	public static final String INSERT_PRODUCT_LISTA_COMPRA = "Insert into lista_compra (cantidad, usuario, producto) values (?, ?, ?)";
	public static final String UPDATE_PRODUCT_LISTA_COMPRA = "Update lista_compra set cantidad = ? where producto = ? and usuario = ?";
	public static final String DELETE_PRODUCT_LISTA_COMPRA = "Delete from lista_compra where producto = ? and usuario = ?";
	public static final String SELECT_ALL_PRODUCT_LISTA = "select * from lista_compra inner join productos_escaneados on lista_compra.producto = productos_escaneados.id where usuario = ?";

	
	//SENTENCIAS PARA LA TABLA DE PRODUCTOS COMPRADOS
	public static final String ANY_PRODUCT_COMPRADOS = "select count(*) from productos_comprados inner join productos_escaneados on productos_comprados.producto = productos_escaneados.id where codigo_barra = ? and usuario = ?";
	public static final String EXIST_PRODUCT_COMPRADOS = "select codigo_barra, productos_escaneados.id from productos_comprados inner join productos_escaneados on productos_comprados.producto = productos_escaneados.id where codigo_barra = ?";
	public static final String CANTIDAD_PRODUCTO_COMPRADOS = "Select cantidad from productos_comprados where usuario = ? and producto = ?";
	public static final String INSERT_PRODUCT_COMPRADOS = "Insert into productos_comprados (cantidad, usuario, producto) values (?, ?, ?)";
	public static final String UPDATE_PRODUCT_COMPRADOS = "Update productos_comprados set cantidad = ? where producto = ? and usuario = ?";
	
	//SENTENCIAS PARA LA TABLA DE PRODUCTOS ESCANEADOS
	public static final String EXISTPRODUCT = "Select * from productos_escaneados where codigo_barra = ?";//Para validar que exista el producto
	public static final String NEWPRODUCT = "Insert into productos_escaneados (codigo_barra, nombre) values(?, ?)";//Si no existe se inserta
	
}
