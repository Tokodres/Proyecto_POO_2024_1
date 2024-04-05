package presentacion;

import java.util.Scanner;

import logica.Banco;
import logica.Cliente;
import logica.ProductosFinancieros;

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
				System.out.println("3. Imprimir Clientes");
				System.out.println("4. Imprimir Cuentas");
				System.out.println("5. Almacenar en archivo de texto plano");
				System.out.println("6. Cargar desde archivo de texto plano");
				System.out.println("7. Realizar un movimiento en un Cuenta");
				System.out.println("8. Crear Creditos");
				System.out.println("9. Consignar Credito Libre Inversion");
				System.out.println("10. Consignar Credito Hipotecario");
				System.out.println("11. Crear tarjeta Credio");
				op = sc.nextInt();
				if(op == 1) {
					this.crearCliente(sc);				
				}else if(op == 2) {
					this.creaCuenta(sc);
				}else if(op == 3) {
					this.imprimirClientes();
				}else if(op == 4) {
					this.imprimirCuentas();
				}else if(op == 5) {
					this.almacenar(sc);
				}else if(op == 6) {
					this.cargar(sc);
				}else if(op == 7) {
					this.menuCuentas1(sc);
				}else if(op == 8) {
					this.menuCreditos(sc);
				}else if(op == 9) {
					this.consignarCreditoLibreInversion(sc);
				}else if(op == 10) {
					this.consignarCreditoHipotecario(sc);
				}else if(op == 11) {
					this.crearTarjetaCredito(sc);
				}else if(op == 12) {
					this.pagarTarjetaCredito(sc);
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}while(op != 0);

		sc.close();

	}

	private void cargar(Scanner sc) throws Exception {
		int opc1 = 0;
		System.out.println("seleccione:");
		System.out.println("1. Cargar Cliente");
		System.out.println("2. Cargar Cuenta");
		int opc = sc.nextInt();
		if(opc == 2) {
			System.out.println("1. Almecenar Ahorros");
			System.out.println("2. Almecenar Corriente");
			opc1 = sc.nextInt();
		}
		this.banco.cargar(opc, opc1);
	}

	private void almacenar(Scanner sc) {
		System.out.println("seleccione:");
		System.out.println("1. Almecenar Cliente");
		System.out.println("2. Almecenar Cuenta");
		int opc = sc.nextInt();
		this.banco.almacenar(opc);		
	}

	private void imprimirCuentas() {
		System.out.println("Num\tSaldo\tTipo\tCliente");
		for(ProductosFinancieros pf : this.banco.getCuentas()) {
			System.out.println(pf);
		}
	}

	private void imprimirClientes() {

		System.out.println("id\tNombre\tApellido\t");
		for(Integer id : this.banco.getClientes().keySet()) {
			Cliente cliente = this.banco.getClientes().get(id);
			System.out.println(cliente);
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
		System.out.println("Seleccione si su cuenta es de :");
		System.out.println("1. Volver");
		System.out.println("1. Ahorros");
		System.out.println("2. Corriente");
		int opc = sc.nextInt();
		if(opc == 1) {
			this.menuCuentasAhorros(sc);
		}else if(opc ==2) {
			this.menuCuentasCorrientes(sc);
		}
		
	}
	private void menuCuentasAhorros(Scanner sc) throws Exception {
		System.out.println("Seleccione:");
		System.out.println("0. Volver");
		System.out.println("1. Consignar");
		System.out.println("2. Retirar");
		System.out.println("3. Transferir");
		int opc = sc.nextInt();
		if(opc == 1) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea consignar: ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea consignar: ");
			int valor = sc.nextInt();
			this.banco.consignar(Numero, idCliente, valor, 1);
			
		}else if(opc == 2) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea retirar: ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea retirar: ");
			int valor = sc.nextInt();
			this.banco.retirar(Numero, idCliente, valor, 1);
			
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
			this.banco.transferir(Numero, idCliente, valor, 1,NumeroT,idClienteT,TipoT);
		}
		
	}
	
	private void menuCuentasCorrientes(Scanner sc) throws Exception {
		System.out.println("Seleccione:");
		System.out.println("0. Volver");
		System.out.println("1. Consignar");
		System.out.println("2. Retirar");
		System.out.println("3. Transferir");
		int opc = sc.nextInt();
		if(opc == 1) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea consignar: ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea consignar: ");
			int valor = sc.nextInt();
			this.banco.consignar(Numero, idCliente, valor, 2);
			
		}else if(opc == 2) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea retirar: ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea retirar: ");
			int valor = sc.nextInt();
			this.banco.retirar(Numero, idCliente, valor, 2);
			
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
			this.banco.transferir(Numero, idCliente, valor, 2,NumeroT,idClienteT,TipoT);
		}
	
	}
	private void menuCreditos(Scanner sc) throws Exception {
		System.out.println("Seleccione si su cuenta es de :");
		System.out.println("1. Volver");
		System.out.println("1. Credito Hipotecario");
		System.out.println("2. Credito Libre Inversion");
		int opc = sc.nextInt();
		if(opc == 1) {
			this.crearCreditoHipotecario(sc);
		}else if(opc ==2) {
			this.crearCreditoLibreInversion(sc);
		}
	}
		
	private void crearCreditoLibreInversion(Scanner sc) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor ingrese el Tipo de la Cuenta: ");
		System.out.println("1.Ahorros ");
		System.out.println("2.Corriente");
		int tipoCuenta = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor de el credito: ");
		int valor = sc.nextInt();
		
		this.banco.crearCreditoLibreInversion(numeroCuenta, idCliente, tipoCuenta, numeroCredito, valor);
	}
	
	private void consignarCreditoLibreInversion(Scanner sc) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor ingrese el Tipo de la Cuenta: ");
		System.out.println("1.Ahorros ");
		System.out.println("2.Corriente");
		int tipoCuenta = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor a consignar: ");
		int valor = sc.nextInt();
		this.banco.consignarCreditoLibreInversion(numeroCuenta, idCliente, tipoCuenta, numeroCredito, valor);
			
	}
	
	private void crearCreditoHipotecario(Scanner sc) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor ingrese el Tipo de la Cuenta: ");
		System.out.println("1.Ahorros ");
		System.out.println("2.Corriente");
		int tipoCuenta = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor de el credito: ");
		int valor = sc.nextInt();
		
		this.banco.crearCreditoHipotecario(numeroCuenta, idCliente, tipoCuenta, numeroCredito, valor);
	}
	private void consignarCreditoHipotecario(Scanner sc) throws Exception {
		System.out.println("Por favor el numero de la Cuenta: ");
		int numeroCuenta = sc.nextInt();
		System.out.println("Por favor el id de Cliente de la Cuenta: ");
		int idCliente = sc.nextInt();
		System.out.println("Por favor ingrese el Tipo de la Cuenta: ");
		System.out.println("1.Ahorros ");
		System.out.println("2.Corriente");
		int tipoCuenta = sc.nextInt();
		System.out.println("Por favor el numero de el Credito: ");
		int numeroCredito = sc.nextInt();
		System.out.println("Por favor el valor a consignar: ");
		int valor = sc.nextInt();
		this.banco.consignarCreditoHipotecario(numeroCuenta, idCliente, tipoCuenta, numeroCredito, valor);
			
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
