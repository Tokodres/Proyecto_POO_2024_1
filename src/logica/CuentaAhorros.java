package logica;

public  class CuentaAhorros extends ProductosFinancieros implements Cuentas{

	
	private int numero;
	private int saldo;
	
	
	

	public CuentaAhorros(String tipo, Cliente cliente, int numero, int saldo) {
		super(tipo, cliente);
		this.numero = numero;
		this.saldo = saldo;
	}
	
	
	
	
	

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	@Override
	public void consignar(int valor) {
		this.saldo =+ valor;
	}

	@Override
	public void retirar(int valor) throws Exception {
		if(valor <= this.saldo) {
			this.saldo -= valor;			
		}else {
			throw new Exception("Saldo insuficiente");
		}
	}

	@Override
	public void Transferir(Cuentas cuentaDestino, int valor) throws Exception {
		this.retirar(valor);
		cuentaDestino.consignar(valor);
		
	}






	

}