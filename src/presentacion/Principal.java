package presentacion;

import java.util.Scanner;

import logica.Banco;
import logica.Cliente;
import logica.Cuentas;
import logica.ProductosFinancieros;
import logica.TarjetaCredito;

public class Principal {
	private Banco banco;

	public Principal() {
		this.banco = new Banco();
		this.menu();

	}

	public static void main(String[] args) {
		new Principal();
	}

	private void menu() {
		Scanner sc = new Scanner(System.in);
		int op = 0;
		do {
			try {
				System.out.println("Seleccione:");
				System.out.println("0. Salir");
				System.out.println("1. Crear Cliente");
				System.out.println("2. Crear Cuenta");
				System.out.println("3. Imprimir");
				System.out.println("4. Almacenar en archivo de texto plano");
				System.out.println("5. Cargar desde archivo de texto plano");
				System.out.println("6. Realizar un movimiento en un Cuenta");
				System.out.println("7. Crear Creditos");
				System.out.println("8. Consignar Credito");
				System.out.println("9. Crear tarjeta Credio");
				System.out.println("10. Transferir con tarjeta de Credito");
				op = sc.nextInt();
				if(op == 0) {
					System.exit(0);
				}else if(op == 1) {
					this.crearCliente(sc);				
				}else if(op == 2) {
					this.creaCuenta(sc);
				}else if(op == 3) {
					this.menuimpresion(sc);
				}else if(op == 4) {
					this.almacenar(sc);
				}else if(op == 5) {
					this.cargar(sc);
				}else if(op == 6) {
					this.menuCuentas1(sc);
				}else if(op == 7) {
					this.menuCreacionCreditos(sc);;
				}else if(op == 8) {
					this.menuConsignarCreditos(sc);
				}else if(op == 9) {
					this.crearTarjetaCredito(sc);
				}else if(op == 10) {
					this.pagarTarjetaCredito(sc);
				}
				else if(op >= 12 || op <0) {
					throw new Exception("Opcion Invalida");
				}
			} catch (Exception e) {
				System.out.println("Error:" +e.getMessage());
			}
		}while(op != 0);

		sc.close();
	}

	private void cargar(Scanner sc) throws Exception {
		int opc = 0;
		System.out.println("seleccione:");
		System.out.println("0. Salir");
		System.out.println("1. Cargar Cliente");
		System.out.println("2. Cargar Cuenta");
		System.out.println("3. Cargar Creditos");
		System.out.println("4. Cargar Tarjetas de Credito");
		if(opc >=0 && opc <=4 ) {
			opc = sc.nextInt();
			this.banco.cargar(opc);
		}else {
			throw new Exception("Opcion Invalida");
		}	
	}

	private void almacenar(Scanner sc) throws Exception {
		int opc = 0;
		System.out.println("seleccione:");
		System.out.println("0. Salir");
		System.out.println("1. Almecenar Clientes");
		System.out.println("2. Almecenar Cuentas");
		System.out.println("3. Almecenar Creditoss");
		System.out.println("4. Almecenar Tarjetas de Credito");
		if(opc >=0 && opc <=4 ) {
			opc = sc.nextInt();
			this.banco.almacenar(opc);
		}else {
			throw new Exception("Opcion Invalida");
		}		
	}

	private void menuimpresion(Scanner sc) throws Exception {
		int opc = 0;
		System.out.println("seleccione:");
		System.out.println("0. Salir");
		System.out.println("1. Imprimir Clientes");
		System.out.println("2. Imprimir Cuentas");
		System.out.println("3. Imprimir Creditoss");
		System.out.println("4. Imprimir Tarjetas de Credito");
		if(opc >=0 && opc <=4 ) {
			opc = sc.nextInt();
			this.imprimir(opc);
		}else {
			throw new Exception("Opcion Invalida");
		}
	}

	private void imprimir(int opc) {
		if(opc == 1) {
			System.out.println("id\tNombre\tApellido\t");
			for(Integer id : this.banco.getClientes().keySet()) {
				Cliente cliente = this.banco.getClientes().get(id);
				System.out.println(cliente);
			}
		}else if(opc == 2) {
			System.out.println("Num\tSaldo\tTipo\tCliente");
			for(Cuentas cs : this.banco.getCuentas()) {
				System.out.println(cs);
			}
		}else if(opc == 3) {
			System.out.println("Num\tValor Credito\tValor Pagado\tTipo\tCliente");
			for(ProductosFinancieros pf : this.banco.getCreditos()) {
				System.out.println(pf);
			}
		}else if(opc == 4) {
			System.out.println("Num\tCupo\tCupo Usado\tTipo\tCliente");
			for(TarjetaCredito tr : this.banco.getTarjetaCredito()) {
				System.out.println(tr);
			}	
		}	
	}

	private void creaCuenta(Scanner sc) throws Exception {
		System.out.println("Numero:");
		int numero = sc.nextInt();
		System.out.println("Saldo:");
		int saldo = sc.nextInt();
		System.out.println("Tipo:");
		int tipo = sc.nextInt();
		System.out.println("Id Cliente:");
		int idCliente = sc.nextInt();
		this.banco.crearCuenta(numero, saldo, tipo, idCliente);
	}

	private void crearCliente(Scanner sc) throws Exception {
		System.out.println("Id:");
		int id = sc.nextInt();
		System.out.println("nombre:");
		String nombre = sc.next();
		System.out.println("apellido:");
		String apellido = sc.next();
		System.out.println("Edad:");
		int edad = sc.nextInt();
		this.banco.crearCliente(id, nombre, apellido,edad);
	}

	private void menuCuentas1(Scanner sc) throws Exception {
		int opc = 0;
		System.out.println("Seleccione:");
		System.out.println("0. Volver");
		System.out.println("1. Ahorros");
		System.out.println("2. Corriente");
		if(opc >= 0 && opc <=2) {
			opc = sc.nextInt();
			this.menuMovimientosCuentas(sc, opc);		
		}else {
			throw new Exception("Opcion Invalida");
		}
	}
	private void menuMovimientosCuentas(Scanner sc, int tipoCuenta) throws Exception {
		int opc = 0;
		System.out.println("Seleccione:");
		System.out.println("0. Volver");
		System.out.println("1. Consignar");
		System.out.println("2. Retirar");
		System.out.println("3. Transferir");
		if(opc >= 0 && opc <=3) {
			opc = sc.nextInt();
			if(opc == 1) {
				System.out.println("Por favor ingrese el numero de cuenta en la que desea consignar: ");
				int Numero = sc.nextInt();
				System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
				int idCliente = sc.nextInt();
				System.out.println("Por favor ingrese el valor que desea consignar: ");
				int valor = sc.nextInt();
				if(tipoCuenta == 1) {
					this.banco.consignar(Numero, idCliente, valor, 1);
				}else if(tipoCuenta == 2) {
					this.banco.consignar(Numero, idCliente, valor, 1);
				}	
			}else if(opc == 2) {
				System.out.println("Por favor ingrese el numero de cuenta en la que desea retirar: ");
				int Numero = sc.nextInt();
				System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
				int idCliente = sc.nextInt();
				System.out.println("Por favor ingrese el valor que desea retirar: ");
				int valor = sc.nextInt();
				if(tipoCuenta == 1) {
					this.banco.retirar(Numero, idCliente, valor, 1);
				}else if(tipoCuenta == 2) {
					this.banco.retirar(Numero, idCliente, valor, 2);
				}	
				
			}else if(opc == 3) {
				System.out.println("Por favor ingrese el numero de su cuenta: ");
				int Numero = sc.nextInt();
				System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
				int idCliente = sc.nextInt();
				System.out.println("Por favor ingrese el numero de cuenta en el que desea transferir el dinero: ");
				int NumeroT = sc.nextInt();
				System.out.println("Por favor ingrese el tipo de la cuenta: ");
				System.out.println("1.Ahorros.");
				System.out.println("2.Corriente.");
				int TipoT = sc.nextInt();
				System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
				int idClienteT = sc.nextInt();
				System.out.println("Por favor ingrese el valor que desea transferir: ");
				int valor = sc.nextInt();
				if(tipoCuenta == 1) {
					this.banco.transferir(Numero, idCliente, valor, 1,NumeroT,idClienteT,TipoT);
				}else if(tipoCuenta == 2) {
					this.banco.transferir(Numero, idCliente, valor, 2,NumeroT,idClienteT,TipoT);
				}
			}			
		}else {
			throw new Exception("Opcion Invalida");
		}	
	}
	
	private void menuCreacionCreditos(Scanner sc) throws Exception {
		int opc = 0;
		System.out.println("Seleccione tipo Credito :");
		System.out.println("0. Volver");
		System.out.println("1. Credito Hipotecario");
		System.out.println("2. Credito Libre Inversion");
		if(opc >= 0 && opc <=2) {
			opc = sc.nextInt();
			this.crearCreditos(sc, opc);		
		}else {
			throw new Exception("Opcion Invalida");
		}
	
	}
	
	private void menuConsignarCreditos(Scanner sc) throws Exception {
		System.out.println("Seleccione tipo Credito :");
		System.out.println("0. Volver");
		System.out.println("1. Credito Hipotecario");
		System.out.println("2. Credito Libre Inversion");
		int opc = sc.nextInt();
		this.consignarCreditos(sc, opc);
	}
	
	private void crearCreditos(Scanner sc, int tipoCredito) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor de el credito: ");
		int valor = sc.nextInt();
		this.banco.crearCreditos(idCliente, numeroCuenta, 1, numeroCredito, valor, tipoCredito);
	}
	
	private void consignarCreditos(Scanner sc, int tipoCredito) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor a consignar: ");
		int valor = sc.nextInt();
		this.banco.consignarCreditos(numeroCuenta, idCliente, 1, numeroCredito, valor, tipoCredito);
			
	}
	
	private void crearTarjetaCredito(Scanner sc) throws Exception {
		System.out.println("Por favor el numero de la Tarjeta de Credito: ");
		int numeroTarjetaCredito = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Tarjeta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor el Cupo de la Tarjeta: ");
		int Cupo = sc.nextInt();
		this.banco.CrearTarjetaCredito(numeroTarjetaCredito, idCliente, Cupo);
	}

	private void pagarTarjetaCredito(Scanner sc) throws Exception {
		System.out.println("Numero de tarjeta de credito con la que desea pagar: ");
		int numeroTarjetaCredito = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Tarjeta de credito: ");
		int idCliente = sc.nextInt();
		System.out.println("Ingrese el monto que desea pagar: ");
		int monto = sc.nextInt();
		this.banco.PagarTarjetaCredito(numeroTarjetaCredito, idCliente, monto);
	}
	
}
