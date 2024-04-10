package logica;

public  class CuentaAhorros extends Cuentas implements MovimientosCuentas{
	
	public CuentaAhorros(String tipo, Cliente cliente, int numero, int saldo) {
		super(tipo, cliente, numero, saldo);
	}
	

	@Override
	public void consignar(int valor) {
		this.saldo =+ valor;
	}

	@Override
	public void retirar(int valor, boolean Transferir) throws Exception {
		if(valor <= this.saldo) {
			this.saldo -= valor;			
		}else {
			throw new Exception("Saldo insuficiente");
		}
	}

	@Override
	public void Transferir(MovimientosCuentas cs, int valor) throws Exception {
		this.retirar(valor,true);
		cs.consignar(valor);
		
	}
	@Override
	public String toString() {
		return this.numero + "\t" + this.saldo + "\t" + this.Tipo + "\t" + this.cliente;
	}





	

}
