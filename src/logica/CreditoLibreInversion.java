package logica;

public class CreditoLibreInversion extends ProductosFinancieros implements Creditos {
	private int Numero;
	private int ValorCredito;
	private int valorPagado;
	final float recaudo = 0.5f;
	
	public CreditoLibreInversion(String tipo, Cliente cliente, int numero, int valorCredito, int valorPagado) {
		super(tipo, cliente);
		Numero = numero;
		ValorCredito = valorCredito;
		this.valorPagado = valorPagado;
	}
	
	public int getNumero() {
		return Numero;
	}

	public int getValorCredito() {
		return ValorCredito;
	}

	public int getValorPagado() {
		return valorPagado;
	}

	public float getRecaudo() {
		return recaudo;
	}

	@Override
	public void consignarCredito(int valor) {
		this.valorPagado += valor;
		
	}
	
	
	
	
	
	
	
}
