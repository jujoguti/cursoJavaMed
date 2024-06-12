import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Infraestructura.adaptadores.ClienteImplementacion;
import Infraestructura.adaptadores.CuentaImplementacion;
import domain.models.Cliente;
import domain.models.Cuenta;
import domain.models.CuentaDeAhorro;
import domain.models.CuentaDeCheque;
import domain.models.Domicilio;
import domain.models.gateway.ServicioClientes;
import domain.models.gateway.ServicioCuentas;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numeroCliente;
        int numeroCuenta;
        double cantidad;
        int clienteId = 0;
        int numero = 0;
        ServicioCuentas servicioCuentas = new CuentaImplementacion();
        ServicioClientes servicioCliente = new ClienteImplementacion();
 
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Consultar cliente");
            System.out.println("4.Listado de clientes");
            System.out.println("5: Buscar cliente  por RFC");
            System.out.println("6. Agregar cuenta");
            System.out.println("7. Cancelar cuenta");
            System.out.println("8. abonar de una cuenta");
            System.out.println("9. retirar de una cuenta");
            System.out.println("10. Listado de cuentas");
            System.out.println("11. Salir");
 
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea
 
            switch (opcion) {
                case 1:
                   System.out.println("Ingrese el numero de identificacion\n");
                    numero = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("\nIngrese el nombre del cliente\n");
                    String nombre = scanner.nextLine();
                    
                    System.out.println("\nIngrese el Domicilio del cliente\n");
                    Domicilio domicilio = crearDomicilio();
                    
                    System.out.println("\nIngrese el RFC del Cliente\n");
                    String rfc = scanner.nextLine();
                    
                    System.out.println("\nIngrese el telefono del Cliente\n");
                    String telefono = scanner.nextLine();
                    
                    System.out.println("\nIngrese las cuentas del Cliente\n");
                    ArrayList<Cuenta> cuentasCliente = crearlistacuentas();
                    
                    System.out.println("\nIngrese Fecha de Nacimineto del Cliente\n");
                    String fechaNacimiento =  scanner.nextLine();
                    servicioCliente.agregarCliente( new Cliente(numero, nombre, domicilio, rfc, telefono, cuentasCliente, fechaNacimiento));
                    break;
                case 2:
                    System.out.println("Ingrese el numero de idenficacion del cliente a eliminar:");
                    numeroCliente = scanner.nextInt();
                    scanner.nextLine();
                    servicioCliente.eliminarCliente(numeroCliente);
                    break;
                case 3:
                    System.out.println("Ingrese el numero de identificacion del cliente a consultar:");
                    numeroCliente = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cliente = servicioCliente.consultarCliente(numeroCliente);
                    if (cliente != null) {
                        System.out.println("Cuenta encontrada: " + cliente);
                    } else {
                        System.out.println("Cuenta no encontrada");
                    }
                    break;
                case 4:
                    ArrayList<Cliente> listaCliente = (servicioCliente.obtenerClientes());
                    if (listaCliente != null) {
                        System.out.println("Clientes encontrados: " + listaCliente);
                    } else {
                        System.out.println("No hay clientes");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el RFC del cliente a consultar:");
                    String rfcFind = scanner.nextLine();
                    scanner.nextLine();
                    servicioCliente.buscarClientePorRFC(rfcFind);
                    break;
                case 6:
                    CuentaDeAhorro ctaAhorro;
                    CuentaDeCheque ctaCheque;
                   
                    System.out.println("\nQue tipo de cuneta quieres creaer?\n");
                    System.out.println("\n1: cuenta de ahorros.\n");
                    System.out.println("\n2: cuenta de cheque.\n");
                        
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\nIngrese numero de cuenta.\n");
                    int numeroCta = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nIngrese saldo de cuenta.\n");
                    double saldo = scanner.nextDouble();
                    scanner.nextLine();
                        if (tipo==1) {
                            System.out.println("\nIngrese la tasa de interes de la cuenta de ahorros. (ej; 0,5) \n");
                            double tasa = scanner.nextDouble();
                            scanner.nextLine();
                            ctaAhorro = new CuentaDeAhorro(numeroCta, LocalDate.now().toString(), saldo, "no aplica", tasa);
                            servicioCuentas.agregarCuenta(ctaAhorro);
                        }else if (tipo==2) {
                            System.out.println("\nIngrese costo mensual de la cuenta de cheque.\n");
                            double costo = scanner.nextDouble();
                            scanner.nextLine();
                            ctaCheque = new CuentaDeCheque(numeroCta, LocalDate.now().toString(), saldo, "no aplica", costo);
                            servicioCuentas.agregarCuenta(ctaCheque);
                        }
                        
                    break;
                case 7:
                    System.out.println("Ingrese el numero de cuenta que desea cancelar:");
                    numeroCuenta= scanner.nextInt();
                    scanner.nextLine();
                    servicioCuentas.cancelarCuenta(numeroCuenta);
                    break;
                case 8:
                    System.out.println("Ingrese el numero de cuenta que desea abonar:");
                    numeroCuenta= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la cantidad de desea abonar:");
                    cantidad= scanner.nextDouble();
                    scanner.nextLine();
                    servicioCuentas.abonarCuenta(numeroCuenta, cantidad);
                    break;
                case 9:
                    System.out.println("Ingrese el numero de cuenta que desea Retirar:");
                    numeroCuenta= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la cantidad de desea retirar:");
                    cantidad= scanner.nextDouble();
                    scanner.nextLine();
                    servicioCuentas.retirar(numeroCuenta, cantidad);
                    break;
                case 10:
                    System.out.println("Listado de todas las cuentas:\n");
                    ArrayList<Cuenta> list = servicioCuentas.obtenerCuentas();
                    for (Cuenta cuenta : list) {
                        System.out.println(cuenta+"\n");
                    }
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }
    
    @SuppressWarnings("resource")
    private static Domicilio crearDomicilio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la Calle del Domicilio");
        String calle = scanner.nextLine();
        System.out.println("Ingrese el numero del Domicilio");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la colonia del Domicilio");
        String colonia = scanner.nextLine();
        System.out.println("Ingrese el estado del Domicilio");
        String estado = scanner.nextLine();
        System.out.println("Ingrese el codigo postal del Domicilio");
        int codigoPostal = scanner.nextInt();
        scanner.nextLine();
        return new Domicilio(calle, numero, colonia, estado, codigoPostal);
    }
private static ArrayList<Cuenta> crearlistacuentas() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cuenta> lista = new ArrayList<>();
        CuentaDeAhorro ctaAhorro;
        CuentaDeCheque ctaCheque;
        ServicioCuentas servicioCuentas = new CuentaImplementacion();
        String continuar = "SI";
        do{
            System.out.println("\nQue tipo de cuneta quieres creaer?\n");
            System.out.println("\n1: cuenta de ahorros.\n");
            System.out.println("\n2: cuenta de cheque.\n");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\nIngrese numero de cuenta.\n");
            int numero = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nIngrese saldo de cuenta.\n");
            double saldo = scanner.nextDouble();
            scanner.nextLine();
            if (tipo==1) {
                System.out.println("\nIngrese la tasa de interes de la cuenta de ahorros. (ej; 0,5) \n");
                double tasa = scanner.nextDouble();
                scanner.nextLine();
                ctaAhorro = new CuentaDeAhorro(numero, LocalDate.now().toString(), saldo, "no aplica", tasa);
                servicioCuentas.agregarCuenta(ctaAhorro);
                lista.add(ctaAhorro);
                System.out.println("\nEscribe SI para  agregar otra cuenta de lo contrario presiona cualquier letra\n");
                continuar = scanner.nextLine();
            }else if (tipo==2) {
                System.out.println("\nIngrese costo mensual de la cuenta de cheque.\n");
                double costo = scanner.nextDouble();
                scanner.nextLine();
                ctaCheque = new CuentaDeCheque(numero, LocalDate.now().toString(), saldo, "no aplica", costo);
                servicioCuentas.agregarCuenta(ctaCheque);
                lista.add(ctaCheque);
                System.out.println("\nEscribe SI para  agregar otra cuenta de lo contrario presiona cualquier letra\n");
                continuar = scanner.nextLine();
            }
            
        }while(continuar.toUpperCase()=="SI");
        
        return lista;
    }
}