package logica;

public class Cuentas extends ProductosFinancieros{
	protected int numero;
	protected int saldo;
	
	public Cuentas(String tipo, Cliente cliente, int numero, int saldo) {
		super(tipo, cliente);
		this.numero = numero;
		this.saldo = saldo;
	}
	public int getNumero() {
		return numero;
	}
	public int getSaldo() {
		return saldo;
	}
	
}
