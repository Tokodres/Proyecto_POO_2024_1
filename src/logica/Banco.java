package logica;


import java.util.ArrayList;
import java.util.HashMap;

import persistencia.ArchivoPlano;


public class Banco {
	private ArrayList<CuentaCorriente> cuentaCorriente;
	private ArrayList<CuentaAhorros> cuentaAhorros;
	private HashMap<Integer, Cliente> clientes;
	private ArrayList<CreditoLibreInversion> ceditoLibreInversion;

	public ArrayList<CuentaCorriente> getCuentaCorriente() {
		return cuentaCorriente;
	}

	public ArrayList<CuentaAhorros> getCuentaAhorros() {
		return cuentaAhorros;
	}

	public HashMap<Integer, Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<CreditoLibreInversion> getCeditoLibreInversion() {
		return ceditoLibreInversion;
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

		if (!this.clientes.containsKey(idCliente)) {
			throw new Exception ("Cliente No existente");
		}else if(this.exiteCuenta(numero, tipo)){
			throw new Exception ("La cuenta ya existe");
		} else if(this.existeTipoCuenta(idCliente, tipo)) {
			throw new Exception ("El cliente ya tiene una cuenta de tipo : " + tipo);
		}else {
			if(tipo == 1) {
				CuentaAhorros ca = new CuentaAhorros("Ahorros",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, ca);
				this.cuentaAhorros.add(ca);
			}else if(tipo == 2) {
				CuentaCorriente cc = new CuentaCorriente("Corriente",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, cc);
				this.cuentaCorriente.add(cc);
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

		if(tipo ==2) {
			for(CuentaCorriente cuenta : this.cuentaCorriente) {
				if(cuenta.getNumero() == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public void almacenar(int n,int tipoCuenta) {
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
			if(tipoCuenta == 1) {
				for(CuentaAhorros cuenta : this.cuentaAhorros) {
					if(cuenta.Tipo.equalsIgnoreCase("Ahorros")) {
						lineasCuentas.add(cuenta.getNumero() + "\t" +cuenta.getSaldo() + "\t" +cuenta.getTipo() +"\t" +cuenta.getCliente().getId());
					}
				}
				ArchivoPlano.almacenar("cuentasAhorros.csv", lineasCuentas);
			}else if(tipoCuenta == 2) {
				for(CuentaCorriente cuenta : this.cuentaCorriente) {
					if(cuenta.Tipo.equalsIgnoreCase("Corriente")) {
						lineasCuentas.add(cuenta.getNumero() + "\t" +cuenta.getSaldo() + "\t" +cuenta.getTipo() +"\t" +cuenta.getCliente().getId());
					}
				}
				ArchivoPlano.almacenar("cuentasCorriente.csv", lineasCuentas);

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
				ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentasAhorros.csv");
				for(String linea : lineasCuentas) {
					String datos[] = linea.split(";");
					this.crearCuenta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
				}
			}else if(TipoCuenta ==2) {
				ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentasCorriente.csv");
				for(String linea : lineasCuentas) {
					String datos[] = linea.split(";");
					this.crearCuenta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
				}
			}

		}
	}


	public void consignar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {

			if(TipoCuenta ==1) {
				for(CuentaAhorros cuenta : this.cuentaAhorros) {
					if(cuenta.getNumero() == numero) {	
						cuenta.consignar(valor);
					}
				}

			}

			if (TipoCuenta ==2) {
				for(CuentaCorriente cuenta : this.cuentaCorriente) {
					if(cuenta.getNumero() == numero) {	
						cuenta.consignar(valor);
					}
				}
			}
		}

	}



	public void retirar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {

			if(TipoCuenta ==1) {
				for(CuentaAhorros cuenta : this.cuentaAhorros) {
					if(cuenta.getNumero() == numero) {	
						cuenta.retirar(valor, false);
					}
				}
			}else if (TipoCuenta ==2) {
				for(CuentaCorriente cuenta : this.cuentaCorriente) {
					if(cuenta.getNumero() == numero) {	
						cuenta.retirar(valor, false);
					}
				}
			}
		}
	}

	public void transferir(int numero, int idCliente, int valor, int TipoCuenta,int numeroTransferir,int idClienteTransferir,int TipoCuentaTransferir) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta) && this.exiteCuenta(numeroTransferir, TipoCuentaTransferir)) {
			if(TipoCuenta == 1) {
				for(CuentaAhorros cuenta : this.cuentaAhorros) {
					if(cuenta.getNumero() == numero) {	
						if(TipoCuentaTransferir == 1) {
							for(CuentaAhorros cuentasTransferir : this.cuentaAhorros) {
								if(cuentasTransferir.getNumero() == numeroTransferir){
									cuenta.Transferir(cuentasTransferir, valor);
								}
							}
						}
						else if(TipoCuentaTransferir == 2) {
							for(CuentaCorriente cuentasTransferir : this.cuentaCorriente) {
								if(cuentasTransferir.getNumero() == numeroTransferir){
									cuenta.Transferir(cuentasTransferir, valor);
								}
							}
						}
					}
				}
			}else if(TipoCuenta == 2) {
				for(CuentaCorriente cuenta : this.cuentaCorriente) {	
					if(cuenta.getNumero() == numero) {	
						if(TipoCuentaTransferir == 1) {
							for(CuentaAhorros cuentasTransferir : this.cuentaAhorros) {
								if(cuentasTransferir.getNumero() == numeroTransferir){
									cuenta.Transferir(cuentasTransferir, valor);
								}
							}
						}
						else if(TipoCuentaTransferir == 2) {
							for(CuentaCorriente cuentasTransferir : this.cuentaCorriente) {
								if(cuentasTransferir.getNumero() == numeroTransferir){
									cuenta.Transferir(cuentasTransferir, valor);
								}
							}
						}
					}
				}					
			}
		}
	}

	
	public void crearCreditoLibreInversion(int numeroCuenta, int idCliente,int tipoCuenta ,int numeroCredito, int valor) {
		if(this.exiteCuenta(numeroCuenta, tipoCuenta)) {
			//Todo agregar condicion de el 70%
			CreditoLibreInversion cli = new CreditoLibreInversion("Credito Libre Inversion",this.clientes.get(idCliente),numeroCredito,valor,0);
			this.clientes.get(idCliente).getProductoFinanciero().put(numeroCredito, cli);
			this.ceditoLibreInversion.add(cli);
		}
	}


	public void consignarCreditoLibreInversion(int numeroCuenta, int idCliente,int tipoCuenta ,int numeroCredito, int valor) throws Exception {
		if(this.exiteCuenta(numeroCuenta, tipoCuenta)) {
			for(CreditoLibreInversion cli: this.ceditoLibreInversion) {
				if(cli.getNumero() == numeroCredito) {
					this.retirar(numeroCredito, idCliente, valor, tipoCuenta);
					cli.consignarCredito(valor);
				}
			}
		}

	}




}


