package logica;

public class CreditoHipotecario extends Creditos implements MovimientosCreditos  {
	
	public CreditoHipotecario(String tipo, Cliente cliente, int valorCredito, int numero, int valorPagado) {
		super(tipo, cliente, valorCredito, numero, valorPagado);
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

	@Override
	public void consignarCredito(int valor) {
		this.valorPagado += valor;
	}
	
	@Override
	public String toString() {
		return this.Numero + "\t\t" + this.valorCredito + "\t\t" + this.valorPagado + "\t" + this.Tipo + "\t" + this.cliente;
	}
	
}
