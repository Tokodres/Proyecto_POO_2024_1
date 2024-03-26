package logica;

public class CuentaCorriente extends ProductosFinancieros implements Cuentas {
	
	public final float interes = 0.5f;
	private int numero;
	private int saldo;
	
	
	
	
	
	
	
	
	
	
	
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
	public CuentaCorriente(String tipo, Cliente cliente, int numero, int saldo) {
		super(tipo, cliente);
		this.numero = numero;
		this.saldo = saldo;
	}
	@Override
	public void consignar(int valor) {
		this.saldo =+ (int)(valor - (valor * interes));
	}

	@Override
	public void retirar(int valor, boolean Transferir) throws Exception {
		if(Transferir) {
			if(valor <= this.saldo){
				this.saldo -= valor ;			
			}else {
				throw new Exception("Saldo insuficiente");
			}
			
		}else {
			if((valor +(valor * interes)) <= this.saldo) {
				this.saldo -= (valor +(valor * interes));			
			}else {
				throw new Exception("Saldo insuficiente");
			}
		}
	}

	@Override
	public void Transferir(ProductosFinancieros pf, int valor) throws Exception {
		
		
		this.retirar(valor,true);
		
	}
	
	@Override
	public String toString() {
		return this.numero + "\t" + this.saldo + "\t" + this.Tipo + "\t" + this.cliente;
	}
	
	
}
