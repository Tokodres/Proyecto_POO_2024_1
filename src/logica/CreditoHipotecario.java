package logica;

public class CreditoHipotecario extends ProductosFinancieros implements Creditos  {
	private int valorCredito;
	private int Numero;
	private int valorPagado;
	
	public CreditoHipotecario(String tipo, Cliente cliente, int valorCredito, int numero, int valorPagado) {
		super(tipo, cliente);
		this.valorCredito = valorCredito;
		Numero = numero;
		this.valorPagado = valorPagado;
	}

	public int getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(int valorCredito) {
		this.valorCredito = valorCredito;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public int getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(int valorPagado) {
		this.valorPagado = valorPagado;
	}

	@Override
	public void consignarCredito(int valor) {
		// TODO Auto-generated method stub
		
	}
	
	
}
