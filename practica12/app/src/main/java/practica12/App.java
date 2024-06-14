/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practica12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        Future<ArrayList<Object>> futureCuenta = executorService.submit(new leerArchivoCallable());

        try {
            ArrayList<Object> cuentasBD = futureCuenta.get();
            System.out.println("despues "+cuentasBD);
            executorService.shutdown();

            cuentasBD.forEach(datos ->{
                int idCliente = datos[5]("cliente");
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

    private static class leerArchivoCallable implements Callable<ArrayList<Object>> {

        private static final String URL = "jdbc:postgresql://localhost:5433/practica12";
        private static final String USER = "postgres";
        private static final String PASSWORD= "postgres";

        @Override
        public ArrayList<Object> call() throws Exception {

            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "Select numero,fecha,saldo,interes,cliente,tipoCuenta from cuentas";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Object> listCuenta = new ArrayList<>();
            while (resultSet.next()){
                listCuenta.add(resultSet);
            }

            return listCuenta;
        }
    }
}
