package logica;

public class CreditoLibreInversion extends Creditos implements MovimientosCreditos {
	final float recaudo = 0.5f;
	
	public CreditoLibreInversion(String tipo, Cliente cliente, int valorCredito, int numero, int valorPagado) {
		super(tipo, cliente, valorCredito, numero, valorPagado);
	}

	public int getNumero() {
		return Numero;
	}

	public int getValorCredito() {
		return this.valorCredito;
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
	
	@Override
	public String toString() {
		return this.Numero + "\t" + this.valorCredito + "\t" + this.valorPagado + "\t" + this.Tipo + "\t" + this.cliente;
	}
	
	
	
	
	
	
	
}
