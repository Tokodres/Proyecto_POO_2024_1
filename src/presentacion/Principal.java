package presentacion;

import java.util.Scanner;

import logica.Banco;
import logica.Cliente;
import logica.CuentaAhorros;
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
				System.out.println("8. ");
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
		int opc1 = 0;
		System.out.println("seleccione:");
		System.out.println("1. Almecenar Cliente");
		System.out.println("2. Almecenar Cuenta");
		int opc = sc.nextInt();
		
		if(opc == 2) {
			System.out.println("1. Almecenar Ahorros");
			System.out.println("2. Almecenar Corriente");
			opc1 = sc.nextInt();
		}
		this.banco.almacenar(opc, opc1);		
	}

	private void imprimirCuentas() {
		System.out.println("Num\tSaldo\tTipo\tCliente");
		for(ProductosFinancieros pf : this.banco.getCuentaAhorros()) {
			System.out.println(pf);
		}
		
		for(ProductosFinancieros pf : this.banco.getCuentaCorriente()) {
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
		ProductosFinancieros pf;
		System.out.println("Seleccione si su ciuenta es de :");
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
			System.out.println("Por favor ingrese el numero de cuenta a la que desea consignar: ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea consignar: ");
			int valor = sc.nextInt();
			this.banco.consignar(Numero, idCliente, valor,1);
		}else if(opc == 2) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea retirar ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea retirar: ");
			int valor = sc.nextInt();
			this.banco.retirar(Numero, idCliente, valor, 1);
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
			this.banco.retirar(Numero, idCliente, valor, 1);
			
		}else if(opc == 2) {
			System.out.println("Por favor ingrese el numero de cuenta en la que desea retirar ");
			int Numero = sc.nextInt();
			System.out.println("Por favor ingrese el id del cliente de la cuenta: ");
			int idCliente = sc.nextInt();
			System.out.println("Por favor ingrese el valor que desea retirar: ");
			int valor = sc.nextInt();
			this.banco.retirar(Numero, idCliente, valor, 2);
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


}
