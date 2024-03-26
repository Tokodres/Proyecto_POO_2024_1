package logica;

public interface Cuentas {
	
	public abstract void consignar(int valor);
	public abstract void retirar(int valor, boolean Transferir) throws Exception;
	public abstract void Transferir(ProductosFinancieros pf,int valor) throws Exception;
	
}
