package logica;


import java.util.ArrayList;
import java.util.HashMap;

import persistencia.ArchivoPlano;


public class Banco {
	private ArrayList<ProductosFinancieros> cuentas;
	private HashMap<Integer, Cliente> clientes;


	public ArrayList<ProductosFinancieros> getCuentas() {
		return cuentas;
	}

	public HashMap<Integer, Cliente> getClientes() {
		return clientes;
	}
	public Banco() {
		this.cuentas = new ArrayList<ProductosFinancieros>();
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

		if (!this.clientes.containsKey(idCliente)) {
			throw new Exception ("Cliente No existente");
		}else if(this.exiteCuenta(numero, tipo)){
			throw new Exception ("La cuenta ya existe");
		} else if(this.existeTipoCuenta(idCliente, tipo)) {
			throw new Exception ("El cliente ya tiene una cuenta de tipo : " + tipo);
		}else {
			ProductosFinancieros pf = null;
			if(tipo == 1) {
				pf = new CuentaAhorros("Ahorros",this.clientes.get(idCliente),numero, saldo);
			}else if(tipo == 2) {
				pf = new CuentaCorriente("Corriente",this.clientes.get(idCliente),numero, saldo);
			}
			this.clientes.get(idCliente).getProductoFinanciero().put(numero, pf);
			this.cuentas.add(pf);
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
		for(ProductosFinancieros cuenta : this.cuentas) {
			if(tipo ==1) {
				if(((CuentaAhorros) cuenta).getNumero() == numero) {
					return true;
				}
			}else if(tipo ==2) {
				if(((CuentaCorriente) cuenta).getNumero() == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public void almacenar(int n) {
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
			for(ProductosFinancieros cuenta : this.cuentas) {
				if(cuenta.Tipo.equalsIgnoreCase("Ahorros")) {
					lineasCuentas.add(((CuentaAhorros) cuenta).getNumero() + "\t" +((CuentaAhorros) cuenta).getSaldo() + "\t" +cuenta.getTipo() +"\t" +cuenta.getCliente().getId());
				}
				else if(cuenta.Tipo.equalsIgnoreCase("Corriente")) {
					lineasCuentas.add(((CuentaCorriente) cuenta).getNumero() + "\t" +((CuentaCorriente) cuenta).getSaldo() + "\t" +cuenta.getTipo() +"\t" +cuenta.getCliente().getId());
				}	
			}
			ArchivoPlano.almacenar("cuentas.csv", lineasCuentas);


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
				ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentas.csv");
				for(String linea : lineasCuentas) {
					String datos[] = linea.split(";");

					if (!this.clientes.containsKey(Integer.parseInt(datos[3]))) {
						throw new Exception ("Cliente No existente");
					}else if(this.exiteCuenta(Integer.parseInt(datos[0]), 1)){
						throw new Exception ("La cuenta ya existe");
					} else if(this.existeTipoCuenta(Integer.parseInt(datos[3]), 1)) {
						throw new Exception ("El cliente ya tiene una cuenta de tipo : Ahorros ");
					}else {
						ProductosFinancieros pf = null;
						if(datos[2].equalsIgnoreCase("Ahorros")) {
							pf = new CuentaAhorros(datos[2],this.clientes.get(Integer.parseInt(datos[3])),Integer.parseInt(datos[0]), Integer.parseInt(datos[2]));
							this.clientes.get(Integer.parseInt(datos[3])).getProductoFinanciero().put(Integer.parseInt(datos[0]), pf);
						}
						else if(datos[2].equalsIgnoreCase("Corriente")) {
							pf = new CuentaCorriente(datos[2],this.clientes.get(Integer.parseInt(datos[3])),Integer.parseInt(datos[0]), Integer.parseInt(datos[2]));
							this.clientes.get(Integer.parseInt(datos[3])).getProductoFinanciero().put(Integer.parseInt(datos[0]), pf);
						}	
						this.cuentas.add((CuentaAhorros) pf);
					}

				}
			}
		}
	}


	public void consignar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {
			for(ProductosFinancieros cuenta : this.cuentas) {
				Cuentas cuentass = null;
				if(TipoCuenta ==1) {
					if(((CuentaAhorros) cuenta).getNumero() == numero) {	
						cuentass = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero,((CuentaAhorros) cuenta).getSaldo());
					}
				}else if (TipoCuenta ==2) {
					if(((CuentaCorriente) cuenta).getNumero() == numero) {	
						cuentass = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero,((CuentaCorriente) cuenta).getSaldo());
					}
				}
				cuentass.consignar(valor);

			}
		}

	}
	public void retirar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {
			for(ProductosFinancieros cuenta : this.cuentas) {
				Cuentas cuentass = null;
				if(TipoCuenta ==1) {
					if(((CuentaAhorros) cuenta).getNumero() == numero) {	
						cuentass = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero,((CuentaAhorros) cuenta).getSaldo());
					}
				}else if (TipoCuenta ==2) {
					if(((CuentaCorriente) cuenta).getNumero() == numero) {	
						cuentass = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero,((CuentaCorriente) cuenta).getSaldo());
					}
				}
				cuentass.retirar(valor, false);
			}
		}
	}

	public void transferir(int numero, int idCliente, int valor, int TipoCuenta,int numeroTransferir,int idClienteTransferir,int TipoCuentaTransferir) throws Exception {
		Cuentas cuentas = null;
		Cuentas cuentaTransferir = null;
		this.exiteCuenta(numero, 1);
		this.exiteCuenta(numeroTransferir, TipoCuentaTransferir);
		if(this.exiteCuenta(numero, TipoCuenta) && this.exiteCuenta(numeroTransferir, TipoCuentaTransferir)) {
			for(ProductosFinancieros cuenta : this.cuentas) {
				if(TipoCuenta == 1) {
					if(((CuentaAhorros) cuenta).getNumero() == numero) {	
						cuentas = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero ,((CuentaAhorros) cuenta).getSaldo());
					}
					if(TipoCuenta == 1) {
						for(ProductosFinancieros cuentass : this.cuentas) {
							cuentaTransferir = new CuentaAhorros("Ahorros", this.clientes.get(idClienteTransferir), numeroTransferir, ((CuentaAhorros) cuentass).getSaldo() );
						}
					}
					else if(TipoCuenta == 2) {
						for(ProductosFinancieros cuentass : this.cuentas) {
							cuentaTransferir = new CuentaCorriente ("Corriente", this.clientes.get(idClienteTransferir), numeroTransferir, ((CuentaCorriente) cuentass).getSaldo() );
						}
					}
					cuentas.Transferir(cuentaTransferir, valor);
				}
				if(TipoCuenta == 2) {
					if(((CuentaCorriente) cuenta).getNumero() == numero) {	
						cuentas = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero ,((CuentaCorriente) cuenta).getSaldo());
					}
					if(TipoCuenta == 1) {
						for(ProductosFinancieros cuentass : this.cuentas) {
							cuentaTransferir = new CuentaAhorros("Ahorros", this.clientes.get(idClienteTransferir), numeroTransferir, ((CuentaAhorros) cuentass).getSaldo() );
						}
					}
					else if(TipoCuenta == 2) {
						for(ProductosFinancieros cuentass : this.cuentas) {
							cuentaTransferir = new CuentaCorriente ("Corriente", this.clientes.get(idClienteTransferir), numeroTransferir, ((CuentaCorriente) cuentass).getSaldo() );
						}
					}
					cuentas.Transferir(cuentaTransferir, valor);
				}


			}

		}
	}
}


