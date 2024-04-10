package logica;

public interface MovimientosCuentas {
	
	public abstract void consignar(int valor);
	public abstract void retirar(int valor, boolean Transferir) throws Exception;
	public abstract void Transferir(MovimientosCuentas cs,int valor) throws Exception;
	
}
