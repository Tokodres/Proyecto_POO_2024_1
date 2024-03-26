package logica;

import java.util.HashMap;

public class Cliente {
	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private HashMap<Integer, ProductosFinancieros> productoFinanciero;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}







	public HashMap<Integer, ProductosFinancieros> getProductoFinanciero() {
		return productoFinanciero;
	}



	public void setProductoFinanciero(HashMap<Integer, ProductosFinancieros> productoFinanciero) {
		this.productoFinanciero = productoFinanciero;
	}



	public Cliente(int id, String nombre, String apellido, int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.productoFinanciero = new HashMap<Integer, ProductosFinancieros>();
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	@Override
	public String toString() {
		return this.id + "\t" + this.nombre + "\t" + this.apellido;
	}
	
	
	
}
