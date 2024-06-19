package dao;


public interface ProductosCompradosIntD {
	int anyProductComprados(String codigobarra, int usuario);
	void newProductInventario(int cantidad, int usuario, int producto);
	void updateProductInventario(int cantidad, int usuario, int producto);
	
}
