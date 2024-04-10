package logica;

public class Creditos extends ProductosFinancieros {
	protected int valorCredito;
	protected int Numero;
	protected int valorPagado;
	
	public Creditos(String tipo, Cliente cliente, int valorCredito, int numero, int valorPagado) {
		super(tipo, cliente);
		this.valorCredito = valorCredito;
		Numero = numero;
		this.valorPagado = valorPagado;
	}

	public int getValorCredito() {
		return valorCredito;
	}

	public int getNumero() {
		return Numero;
	}

	public int getValorPagado() {
		return valorPagado;
	}

}
