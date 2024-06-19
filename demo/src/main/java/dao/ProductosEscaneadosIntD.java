package dao;

public interface ProductosEscaneadosIntD {
	int anyProductEscaneado(String codigoBarra);
	void addProductEscaneado(String codigoBarra, String nombre);
}
