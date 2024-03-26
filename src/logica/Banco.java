package logica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import persistencia.ArchivoPlano;


public class Banco {
	private ArrayList<CuentaAhorros> cuentaAhorros;
	private ArrayList<CuentaCorriente> cuentaCorriente;
	private HashMap<Integer, Cliente> clientes;




	public ArrayList<CuentaAhorros> getCuentaAhorros() {
		return cuentaAhorros;
	}

	public ArrayList<CuentaCorriente> getCuentaCorriente() {
		return cuentaCorriente;
	}

	public HashMap<Integer, Cliente> getClientes() {
		return clientes;
	}







	public Banco() {
		this.cuentaAhorros = new ArrayList<CuentaAhorros>();
		this.cuentaCorriente = new ArrayList<CuentaCorriente>();
		this.clientes = new HashMap<Integer, Cliente>();
	}

	public void crearCliente(int id , String nombre, String apellido, int edad) throws Exception{
		if(this.clientes.containsKey(id)){
			throw new Exception("cliente repetido");
		}else {
			Cliente cliente = new Cliente(id, nombre, apellido, edad);
			this.clientes.put(id, cliente);
		}
	}
	public void crearCuenta(int numero, int saldo, int tipo, int idCliente) throws Exception {

		if(tipo == 1) {
			if (!this.clientes.containsKey(idCliente)) {
				throw new Exception ("Cliente No existente");
			}else if(this.exiteCuenta(numero, tipo)){
				throw new Exception ("La cuenta ya existe");
			} else if(this.existeTipoCuenta(idCliente, tipo)) {
				throw new Exception ("El cliente ya tiene una cuenta de tipo : " + tipo);
			}else {
				ProductosFinancieros pf = new CuentaAhorros("Ahorros",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, pf);
				this.cuentaAhorros.add((CuentaAhorros) pf);
			}
		}
		else if(tipo == 2) {
			if (!this.clientes.containsKey(idCliente)) {
				throw new Exception ("Cliente No existente");
			}else if(this.exiteCuenta(numero, tipo)){
				throw new Exception ("La cuenta ya existe");
			} else if(this.existeTipoCuenta(idCliente, tipo)) {
				throw new Exception ("El cliente ya tiene una cuenta de tipo : " + tipo);
			}else {
				ProductosFinancieros pf = new CuentaCorriente("Corriente",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, pf);
				this.cuentaCorriente.add(  (CuentaCorriente) pf);
			}
		}
	}

	private boolean existeTipoCuenta(int idCliente, int tipo) {
		for(Integer numero : this.clientes.get(idCliente).getProductoFinanciero().keySet()) {
			if(this.clientes.get(idCliente).getProductoFinanciero().get(numero).getTipo().equals(tipo)) {
				return true;
			}
		}
		return false;
	}

	public boolean exiteCuenta(int numero, int tipo) {
		if(tipo ==1) {
			for(CuentaAhorros cuenta : this.cuentaAhorros) {
				if(cuenta.getNumero() == numero) {
					return true;
				}
			}
		}
		else if(tipo ==2) {
			for(CuentaCorriente cuenta : this.cuentaCorriente) {
				if(cuenta.getNumero() == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public void almacenar(int n, int TipoCuenta) {
		ArrayList<String> lineasClientes = new ArrayList<String>();
		ArrayList<String> lineasCuentas = new ArrayList<String>();

		if (n == 1) {
			for(Integer id : this.clientes.keySet()) {
				Cliente cliente = this.clientes.get(id);
				lineasClientes.add(cliente.getId() + ";" + cliente.getNombre() + ";" + cliente.getApellido() + ";" + cliente.getEdad());
			}
			ArchivoPlano.almacenar("clientes.csv", lineasClientes);
		}

		//TODO almacenar cuentas
		if (n == 2) {

			if(TipoCuenta == 1) {
				for(CuentaAhorros cuenta : this.cuentaAhorros) {
					lineasCuentas.add(cuenta.getNumero() + "/t" +cuenta.getSaldo() + "/t" +cuenta.getTipo() +"/t" +cuenta.getCliente().getId());	
				}
				ArchivoPlano.almacenar("cuentaAhorros.csv", lineasCuentas);

			}else if (TipoCuenta == 2) {
				for(CuentaCorriente cuenta : this.cuentaCorriente) {
					lineasCuentas.add(cuenta.getNumero() + "/t" +cuenta.getSaldo() + "/t" +cuenta.getTipo() +"/t" +cuenta.getCliente().getId());	
				}
				ArchivoPlano.almacenar("cuentaCorriente.csv", lineasCuentas);
			}
		}
	}


	public void cargar(int n,int TipoCuenta) throws Exception {



		if(n == 1) {
			ArrayList<String> lineasClientes = ArchivoPlano.cargar("clientes.csv");
			for(String linea : lineasClientes) {
				String datos[] = linea.split(";");
				Cliente c = new Cliente(Integer.parseInt(datos[0]), datos[1], datos[2],Integer.parseInt(datos[3]));
				this.clientes.put(Integer.parseInt(datos[0]), c);
			}	
		}
		//TODO cargar cuentas

		if(n == 2) {
			if(TipoCuenta == 1) {
				ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentaAhorros.csv");
				for(String linea : lineasCuentas) {
					String datos[] = linea.split(";");

					if (!this.clientes.containsKey(Integer.parseInt(datos[3]))) {
						throw new Exception ("Cliente No existente");
					}else if(this.exiteCuenta(Integer.parseInt(datos[0]), 1)){
						throw new Exception ("La cuenta ya existe");
					} else if(this.existeTipoCuenta(Integer.parseInt(datos[3]), 1)) {
						throw new Exception ("El cliente ya tiene una cuenta de tipo : Ahorros ");
					}else {
						ProductosFinancieros pf = new CuentaAhorros("Ahorros",this.clientes.get(Integer.parseInt(datos[3])),Integer.parseInt(datos[0]), Integer.parseInt(datos[2]));
						this.clientes.get(Integer.parseInt(datos[3])).getProductoFinanciero().put(Integer.parseInt(datos[0]), pf);
						this.cuentaAhorros.add((CuentaAhorros) pf);
					}

				}





			}
			if(TipoCuenta == 1) {
				ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentaCorriente.csv");
				for(String linea : lineasCuentas) {
					String datos[] = linea.split(";");

					if (!this.clientes.containsKey(Integer.parseInt(datos[3]))) {
						throw new Exception ("Cliente No existente");
					}else if(this.exiteCuenta(Integer.parseInt(datos[0]), 1)){
						throw new Exception ("La cuenta ya existe");
					} else if(this.existeTipoCuenta(Integer.parseInt(datos[3]), 1)) {
						throw new Exception ("El cliente ya tiene una cuenta de tipo : Corriente ");
					}else {
						ProductosFinancieros pf = new CuentaCorriente("Corriente",this.clientes.get(Integer.parseInt(datos[3])),Integer.parseInt(datos[0]), Integer.parseInt(datos[2]));
						this.clientes.get(Integer.parseInt(datos[3])).getProductoFinanciero().put(Integer.parseInt(datos[0]), pf);
						this.cuentaCorriente.add((CuentaCorriente) pf);
					}

				}





			}






		}
	}
}


