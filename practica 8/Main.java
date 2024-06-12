import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
     // Crear 3 clientes en la clase principal
     Cliente cliente1 = new Cliente(1, "Cliente 1", new Date());
     Cliente cliente2 = new Cliente(2, "Cliente 2", new Date());
     Cliente cliente3 = new Cliente(3, "Cliente 3", new Date());

     // HashMap para almacenar los clientes
     HashMap<Integer, Cliente> clientes = new HashMap<>();
     clientes.put(1, cliente1);
     clientes.put(2, cliente2);
     clientes.put(3, cliente3);

     // Imprimir información de los clientes
     for (Cliente cliente : clientes.values()) {
        System.out.println("antes -> "+cliente);
    }

     leerArchivoCuentas(clientes);

     // Imprimir información de los clientes y sus cuentas
     for (Cliente cliente : clientes.values()) {
        System.out.println(cliente);
    }
    }

    public static void leerArchivoCuentas(HashMap<Integer, Cliente> clientes) {
        try (BufferedReader br = new BufferedReader(new FileReader("cuenta.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int idCliente = Integer.parseInt(datos[5]); // Suponiendo que el ID del cliente está en el índice 5

                String numero = datos[0];
                String tipo = datos[1];
                String fechaApertura = datos[2];
                double saldo = Double.parseDouble(datos[3]);
                double tasaInteres = tipo.equalsIgnoreCase("ahorros") ? Double.parseDouble(datos[4]) : 0.0;
                double costoMensual = tipo.equalsIgnoreCase("cheque") ? Double.parseDouble(datos[4]) : 0.0;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaNacimiento = sdf.parse(datos[6]); // Suponiendo que la fecha de nacimiento está en el índice 6

                Cliente cliente = clientes.getOrDefault(idCliente, new Cliente(idCliente, "Cliente " + idCliente, fechaNacimiento));
                Cuenta cuenta = new Cuenta(numero, tipo, fechaApertura, saldo, tasaInteres, costoMensual);
                cliente.agregarCuenta(cuenta);
                clientes.put(idCliente, cliente);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}