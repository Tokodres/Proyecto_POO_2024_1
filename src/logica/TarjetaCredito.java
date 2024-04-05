package logica;

public class TarjetaCredito extends ProductosFinancieros {
	private int Numero;
	private int CupoUsado;
	private int Cupo;

	public TarjetaCredito(String tipo, Cliente cliente, int numero, int cupoUsado, int cupo) {
		super(tipo, cliente);
		Numero = numero;
		CupoUsado = cupoUsado;
		Cupo = cupo;
	}
	
	public int getNumero() {
		return Numero;
	}

	public int getCupoUsado() {
		return CupoUsado;
	}

	public int getCupo() {
		return Cupo;
	}

	public void Comprar(int valor) {
		if((CupoUsado + valor)<= Cupo) {
			this.CupoUsado += valor;			
		}
	}	
	public void CuotaManejo() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
