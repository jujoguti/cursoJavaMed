package practica9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        String file = "C:\\Users\\jujoguti\\Documents\\cursos banco\\2024\\Java Midle\\cursoJavaMed\\practica-8\\app\\src\\main\\resources\\cuentas.txt";
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(1, "cliente 1",convertirFecha("02-10-1997"));
        Cliente cliente2= new Cliente(2, "cliente 2",convertirFecha("23-12-1994"));
        Cliente cliente3= new Cliente(3, "cliente 3",convertirFecha("18-02-2000"));

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        clientes.forEach(cl->System.out.println(cl));
        ArrayList<Cliente> clientesCuentas = uploadAccount(clientes,file);
        System.out.println("despues de leer el archivo");
        clientesCuentas.forEach(cl->System.out.println(cl));
    }

    public static ArrayList<Cliente> uploadAccount(ArrayList<Cliente> clientes, String ruta){
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea = null;
            while ((linea=reader.readLine())!= null) {
                linea =  linea.replaceAll("\\s","");
                String tipo = linea.substring(0,2);
                String[] datos =  linea.substring(3,linea.length()-1).split(",");
                int idCliente = Integer.parseInt(datos[4]);
                LocalDate fechaApertura = convertirFecha(datos[1]);
                clientes.forEach(cliente -> {
                    if (cliente.getId()==idCliente){
                        Cuenta cuenta = null;
                        if (tipo.equals("CA")){
                            cuenta =  new CuentaDeAhorro(datos[0],Double.parseDouble(datos[2]),fechaApertura,
                                    convertirFecha("08-06-2024"), Double.parseDouble(datos[3]));

                        }else if (tipo.equals("CC")){
                            cuenta =  new CuentaDeCheque(datos[0],Double.parseDouble(datos[2]),fechaApertura,
                                    convertirFecha("08-06-2024"), Double.parseDouble(datos[3]));
                        }
                        cliente.agregarCuenta(cuenta);
                    }
                });


            }
            System.out.println("--- fin de archivo ---");
        }catch(IOException exception){
            System.out.println(exception);
        }

        return clientes;
    }

    private static LocalDate convertirFecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fecha,formato);
    }
}
