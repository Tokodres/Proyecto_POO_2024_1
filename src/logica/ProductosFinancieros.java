package logica;

public class ProductosFinancieros {
	protected String Tipo;
	protected Cliente cliente;
	
	
	public ProductosFinancieros(String tipo, Cliente cliente) {
		super();
		Tipo = tipo;
		this.cliente = cliente;
	}
	
	public String getTipo() {
		return Tipo;
	}
	
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	



	
	
}
