package logica;

public interface Cuentas {
	
	public abstract void consignar(int valor);
	public abstract void retirar(int valor) throws Exception;
	public abstract void Transferir(Cuentas cuenta,int valor) throws Exception;
	
}
