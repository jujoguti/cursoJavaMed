/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practica10;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

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

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<ArrayList<String[]>> futureCuenta = executorService.submit(new leerArchivoCallable());

        try {
            ArrayList<String[]> ArchivoCuentas = futureCuenta.get();

            executorService.shutdown();

            ArchivoCuentas.forEach(datos ->{
                int idCliente = Integer.parseInt(datos[4]);
                LocalDate fechaApertura = convertirFecha(datos[1]);
                clientes.forEach(cliente -> {
                    if (idCliente==cliente.getId()) {

                        if (datos[5].equals("CA")) {
                            cliente.agregarCuenta(new CuentaDeAhorro(datos[0], Double.parseDouble(datos[2]), fechaApertura,
                                    convertirFecha("08-06-2024"), Double.parseDouble(datos[3])));

                        } else if (datos[5].equals("CC")) {
                            cliente.agregarCuenta(new CuentaDeCheque(datos[0], Double.parseDouble(datos[2]), fechaApertura,
                                    convertirFecha("08-06-2024"), Double.parseDouble(datos[3])));
                        }

                    }
                });

            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    private static LocalDate convertirFecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fecha,formato);
    }

    private static class leerArchivoCallable implements Callable<ArrayList<String[]>> {

        @Override
        public ArrayList<String[]> call() throws Exception {
            String ruta = "C:\\Users\\jujoguti\\Documents\\cursos banco\\2024\\Java Midle\\cursoJavaMed\\practica-8\\app\\src\\main\\resources\\cuentas.txt";
            ArrayList<String[]> listCuenta = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
                String linea = null;
                while ((linea=reader.readLine())!= null) {
                    //quito los espacio a la linea del archivo
                    linea =  linea.replaceAll("\\s","");
                    //obtengo el tipo de cuenta
                    String tipo = linea.substring(0,2);
                    //obtengo los datos de la cuenta y lo asigno a un arreglo de string separados por ,
                    String[] datos =  linea.substring(3,linea.length()-1).concat(","+tipo).split(",");

                    listCuenta.add(datos);
                }
                System.out.println("--- fin de archivo ---");
            }catch(IOException exception){
                System.out.println(exception);
            }
            return listCuenta;
        }
    }
}
