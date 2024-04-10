package logica;


import java.util.ArrayList;
import java.util.HashMap;

import persistencia.ArchivoPlano;


public class Banco {
	private ArrayList<Cuentas> cuentas;
	private ArrayList<Creditos> creditos;
	private ArrayList<TarjetaCredito> tarjetaCredito;
	private HashMap<Integer, Cliente> clientes;



	public ArrayList<Cuentas> getCuentas() {
		return cuentas;
	}

	public HashMap<Integer, Cliente> getClientes() {
		return clientes;
	}
	
	public ArrayList<Creditos> getCreditos() {
		return creditos;
	}

	public ArrayList<TarjetaCredito> getTarjetaCredito() {
		return tarjetaCredito;
	}

	public Banco() {
		this.cuentas = new ArrayList<Cuentas>();
		this.creditos = new ArrayList<Creditos>();
		this.clientes = new HashMap<Integer, Cliente>();
		this.tarjetaCredito = new ArrayList<TarjetaCredito>();
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
			Cuentas cs;
			if(tipo == 1) {
				cs = new CuentaAhorros("Ahorros",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, cs);
				this.cuentas.add(cs);
			}else if(tipo == 2) {
				cs = new CuentaCorriente("Corriente",this.clientes.get(idCliente),numero, saldo);
				this.clientes.get(idCliente).getProductoFinanciero().put(numero, cs);
				this.cuentas.add(cs);
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
		for(Cuentas cuenta : this.cuentas) {
			if(tipo ==1) {
				if(cuenta.getNumero() == numero) {
					return true;
				}
			}else if(tipo ==2) {
				if(cuenta.getNumero() == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean existeCredito(int numeroCredito, int TipoCredito) {

		for(Creditos creditos: this.creditos) {
			if(TipoCredito == 1) {
				if(creditos.getNumero() == numeroCredito) {
					return true;
				}
			}else if(TipoCredito == 2) {
				if(creditos.getNumero() == numeroCredito) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean existeTarjetaCredito(int numeroTarjetaCredito, int TipoTarjeta) throws Exception {
		if(TipoTarjeta == 1) {
			for(TarjetaCredito tc: this.tarjetaCredito) {
				if(tc.getNumero() == numeroTarjetaCredito) {
					return true;
				}
			}
		}else {
			throw new Exception("No existe eae tip de Tarjeta");
		}

		return false;


	}

	public void almacenar(int n) {
		ArrayList<String> lineasClientes = new ArrayList<String>();
		ArrayList<String> lineasCuentas = new ArrayList<String>();
		ArrayList<String> lineasCreditos = new ArrayList<String>();
		ArrayList<String> lineasTarjetaCredito = new ArrayList<String>();
		
		if (n == 1) {
			for(Integer id : this.clientes.keySet()) {
				Cliente cliente = this.clientes.get(id);
				lineasClientes.add(cliente.getId() + ";" + cliente.getNombre() + ";" + cliente.getApellido() + ";" + cliente.getEdad());
			}
			ArchivoPlano.almacenar("clientes.csv", lineasClientes);
		}

		//TODO almacenar cuentas
		else if (n == 2) {
			for(Cuentas cuenta : this.cuentas) {
				if(cuenta.Tipo.equalsIgnoreCase("Ahorros")) {
					lineasCuentas.add(cuenta.getNumero() + ";" +cuenta.getSaldo() + ";" +cuenta.getTipo() +";" +cuenta.getCliente().getId());
				}
				else if(cuenta.Tipo.equalsIgnoreCase("Corriente")) {
					lineasCuentas.add(cuenta.getNumero() + ";" +cuenta.getSaldo() + ";" +cuenta.getTipo() +";" +cuenta.getCliente().getId());
				}	
			}
			ArchivoPlano.almacenar("cuentas.csv", lineasCuentas);
		}
		//TODO almacenar Creditos
		else if(n == 3) {
			for(Creditos creditos : this.creditos) {
				if(creditos.Tipo.equalsIgnoreCase("Credito Hipotecario")) {
					lineasCreditos.add(creditos.getNumero() + ";" + creditos.getCliente().getId() + ";" + creditos.getValorCredito() + ";" + creditos.getTipo() + ";" + creditos.getValorPagado());
				}
				else if(creditos.Tipo.equalsIgnoreCase("Credito Libre Inversion")) {
					lineasCreditos.add(creditos.getNumero() + ";" + creditos.getCliente().getId() + ";" + creditos.getValorCredito() + ";" + creditos.getTipo() + ";" + creditos.getValorPagado());
				}	
			}
			ArchivoPlano.almacenar("creditos.csv", lineasCreditos);


		}else if(n == 4) {
			for(ProductosFinancieros tarjetaCredito: this.tarjetaCredito) {
				if(tarjetaCredito.getTipo().equalsIgnoreCase("Tarjeta Credito")) {
					lineasTarjetaCredito.add(((TarjetaCredito) tarjetaCredito).getNumero() + ";" + tarjetaCredito.getCliente().getId() + ";" + ((TarjetaCredito) tarjetaCredito).getTipo() + ";" + ((TarjetaCredito) tarjetaCredito).getCupo() + ";" + ((TarjetaCredito) tarjetaCredito).getCupoUsado());
				}
			}
			ArchivoPlano.almacenar("tarjetaCredito.csv", lineasTarjetaCredito);
		}

	}


	public void cargar(int n) throws Exception {
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
			ArrayList<String> lineasCuentas = ArchivoPlano.cargar("cuentas.csv");
			for(String linea : lineasCuentas) {
				String datos[] = linea.split(";");
				if(datos[2].equalsIgnoreCase("Ahorros")) {
					this.crearCuenta(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),1,Integer.parseInt(datos[3]));	
				}else if(datos[2].equalsIgnoreCase("Corriente")) {
					this.crearCuenta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), 2, Integer.parseInt(datos[3]));
				}
			}
		}
		//TODO cargar creditos
		
		if(n == 3) {
			ArrayList<String> lineasCuentas = ArchivoPlano.cargar("creditos.csv");
			for(String linea : lineasCuentas) {
				String datos[] = linea.split(";");
				Creditos cr;
				if(datos[3].equalsIgnoreCase("Credito Hipotecario")) {
					cr = new CreditoHipotecario("Credito Hipotecario",this.clientes.get(Integer.parseInt(datos[1])),Integer.parseInt(datos[2]), Integer.parseInt(datos[0]),Integer.parseInt(datos[4]));
					this.clientes.get(Integer.parseInt(datos[1])).getProductoFinanciero().put(Integer.parseInt(datos[0]), cr);	
					this.creditos.add(cr);
				}else if(datos[3].equalsIgnoreCase("Credito Libre Inversion")) {
					cr = new CreditoLibreInversion("Credito Hipotecario",this.clientes.get(Integer.parseInt(datos[1])),Integer.parseInt(datos[2]), Integer.parseInt(datos[0]),Integer.parseInt(datos[4]));
					this.clientes.get(Integer.parseInt(datos[1])).getProductoFinanciero().put(Integer.parseInt(datos[0]), cr);	
					this.creditos.add(cr);
				}	
			}
		}else if(n == 4) {
			ArrayList<String> lineasCuentas = ArchivoPlano.cargar("tarjetaCredito.csv");
			for(String linea : lineasCuentas) {
				String datos[] = linea.split(";");
				if(datos[2].equalsIgnoreCase("Tarjeta Credito")) {
					if(this.existeTarjetaCredito(Integer.parseInt(datos[0]), 1)) {
						throw new Exception("La tarjeta ya existe existe la tarjeta");
					}else {
						TarjetaCredito tc = new TarjetaCredito(datos[2], this.clientes.get(Integer.parseInt(datos[1])), Integer.parseInt(datos[0]), 0, Integer.parseInt(datos[3]));
						this.clientes.get(Integer.parseInt(datos[1])).getProductoFinanciero().put(Integer.parseInt(datos[0]), tc);
						this.tarjetaCredito.add(tc);
					}
						
				}
			}
			
		}
	}


	public void consignar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {
			for(Cuentas cuenta : this.cuentas) {
				MovimientosCuentas cuentass = null;
				if(TipoCuenta ==1) {
					if(cuenta.getNumero() == numero) {	
						cuentass = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero,cuenta.getSaldo());
					}
				}else if (TipoCuenta ==2) {
					if(cuenta.getNumero() == numero) {	
						cuentass = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero,cuenta.getSaldo());
					}
				}
				cuentass.consignar(valor);

			}
		}

	}
	public void retirar(int numero, int idCliente, int valor, int TipoCuenta) throws Exception {
		if(this.exiteCuenta(numero, TipoCuenta)) {
			for(Cuentas cuenta : this.cuentas) {
				MovimientosCuentas cuentass = null;
				if(TipoCuenta ==1) {
					if(cuenta.getNumero() == numero) {	
						cuentass = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero,cuenta.getSaldo());
					}
				}else if (TipoCuenta ==2) {
					if(cuenta.getNumero() == numero) {	
						cuentass = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero,cuenta.getSaldo());
					}
				}
				cuentass.retirar(valor, false);
			}
		}
	}

	public void transferir(int numero, int idCliente, int valor, int TipoCuenta,int numeroTransferir,int idClienteTransferir,int TipoCuentaTransferir) throws Exception {
		MovimientosCuentas cuentas = null;
		MovimientosCuentas cuentaTransferir = null;
		this.exiteCuenta(numero, 1);
		this.exiteCuenta(numeroTransferir, TipoCuentaTransferir);
		if(this.exiteCuenta(numero, TipoCuenta) && this.exiteCuenta(numeroTransferir, TipoCuentaTransferir)) {
			for(Cuentas cuenta : this.cuentas) {
				if(TipoCuenta == 1) {
					if(cuenta.getNumero() == numero) {	
						cuentas = new CuentaAhorros("Ahorros", this.clientes.get(idCliente), numero ,cuenta.getSaldo());
					}
					if(TipoCuenta == 1) {
						for(Cuentas cuentass : this.cuentas) {
							cuentaTransferir = new CuentaAhorros("Ahorros", this.clientes.get(idClienteTransferir), numeroTransferir, cuentass.getSaldo() );
						}
					}
					else if(TipoCuenta == 2) {
						for(Cuentas cuentass : this.cuentas) {
							cuentaTransferir = new CuentaCorriente ("Corriente", this.clientes.get(idClienteTransferir), numeroTransferir, cuentass.getSaldo() );
						}
					}
					cuentas.Transferir(cuentaTransferir, valor);
				}
				if(TipoCuenta == 2) {
					if(cuenta.getNumero() == numero) {	
						cuentas = new CuentaCorriente("Corriente", this.clientes.get(idCliente), numero ,cuenta.getSaldo());
					}
					if(TipoCuenta == 1) {
						for(Cuentas cuentass : this.cuentas) {
							cuentaTransferir = new CuentaAhorros("Ahorros", this.clientes.get(idClienteTransferir), numeroTransferir, cuentass.getSaldo() );
						}
					}
					else if(TipoCuenta == 2) {
						for(Cuentas cuentass : this.cuentas) {
							cuentaTransferir = new CuentaCorriente ("Corriente", this.clientes.get(idClienteTransferir), numeroTransferir, cuentass.getSaldo() );
						}
					}
					cuentas.Transferir(cuentaTransferir, valor);
				}


			}

		}
	}

	public void crearCreditos(int idCliente, int numeroCuenta, int tipoCuenta, int numeroCredito, int valor, int TipoCredito) throws Exception {
		if(this.existeCredito(numeroCredito, 2) || this.existeCredito(numeroCredito, 1)) {
			throw new Exception("Ya cuenta con un credito este Ususario");
		}else{
			for(Cuentas cuentas: this.cuentas) {
				if(cuentas.getNumero() == numeroCuenta) {
					if(cuentas.getSaldo() >=  (valor * 0.2)) {						
						if(TipoCredito == 1) {
							Creditos cr = new CreditoHipotecario("Credito Hipotecario",this.clientes.get(idCliente),valor, numeroCredito,0);
							this.clientes.get(idCliente).getProductoFinanciero().put(numeroCredito, cr);	
							this.creditos.add(cr);
							this.consignar(numeroCuenta, idCliente, valor, tipoCuenta);
						}else if(TipoCredito == 2) {
							Creditos cr = new CreditoLibreInversion("Credito Libre Inversion",this.clientes.get(idCliente),valor, numeroCredito,0);
							this.clientes.get(idCliente).getProductoFinanciero().put(numeroCredito, cr);	
							this.creditos.add(cr);
							this.consignar(numeroCuenta, idCliente, valor, tipoCuenta);
							
						}
					}else {
						throw new Exception("Para sacar un credito debe tener almenos el 20% de el valor sobre el que desea pedir el credito.");
					}
				}			
			}	
		}	
	}

	public void consignarCreditos(int numeroCuenta, int idCliente,int tipoCuenta ,int numeroCredito, int valor, int tipoCredito) throws Exception {
		if(this.exiteCuenta(numeroCuenta, tipoCuenta)) {
			for(Creditos creditos: this.creditos) {
				if(tipoCredito == 1) {
					if(creditos.getNumero() == numeroCredito) {
						this.retirar(numeroCuenta, idCliente, valor, tipoCuenta);
						((CreditoHipotecario) creditos).consignarCredito(valor);
					}
				}else if(tipoCredito == 1) {
					if(creditos.getNumero() == numeroCredito) {
						this.retirar(numeroCuenta, idCliente, valor, tipoCuenta);
						((CreditoHipotecario) creditos).consignarCredito(valor);
					}
				}
				
				
				
			}
		}

	}

	public void CrearTarjetaCredito(int numeroTarjeta, int idCliente, int Cupo) throws Exception {
		if(this.existeTarjetaCredito(numeroTarjeta, 1)) {
			throw new Exception("Ya existe una Tarjeta de Credito con este Numero.");
		}
		else{
			TarjetaCredito tc = new TarjetaCredito("Tarjeta Credito", this.clientes.get(idCliente), numeroTarjeta, 0, Cupo);
			this.clientes.get(idCliente).getProductoFinanciero().put(numeroTarjeta, tc);
			this.tarjetaCredito.add(tc);
		}
	}


	public void PagarTarjetaCredito(int numeroTarjetaCredito, int idCLiente, int monto) throws Exception {
		if(!this.existeTarjetaCredito(numeroTarjetaCredito, 1)) {
			throw new Exception("No existe la tarjeta");
		}else {
			for(TarjetaCredito tc: this.tarjetaCredito) {
				if(tc.getNumero() == numeroTarjetaCredito) {
					tc.Comprar(monto);
				}
			}
		}
	}

}