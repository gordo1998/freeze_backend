package dao;


public interface ProductosCompradosIntD {
	int anyProductComprado(String codigobarra, int usuario);
	
	void newProductComprado(int cantidad, int usuario, int producto);
	void updateProductComprado(int cantidad, int usuario, int producto);
	
}
