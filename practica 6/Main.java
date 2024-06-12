import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
 
public class Main {
    public static void main(String[] args) {

    //Crear un Predicate que verifique si un número es par.
        int testNum = 41;
        Predicate <Integer> isPar= n->(n%2)==0;
        String resp = isPar.test(testNum) ? "PAR" : "IMPAR";
        System.out.println(isPar.test(testNum));
        System.out.println("Respuesta funcion predicate");
        System.out.println("el numero "+testNum+ " es: "+ resp );

    //Usar un Consumer para imprimir los nombres de una lista.

        ArrayList<String> listName = new ArrayList<>();
        listName.add("Juan");
        listName.add("Emily");
        listName.add("Jose");
        listName.add("Sofia");
        System.out.println("\nRespuesta funcion Consumer");
        Consumer <String> showList = l-> System.out.println("nombre: "+ l);
        listName.forEach(name -> showList.accept(name));

    //Transformar una lista de números a sus cuadrados usando Function.
        ArrayList<Integer> ListTest = new ArrayList<>();
        ListTest.add(2);
        ListTest.add(9);
        ListTest.add(23);
        ListTest.add(4);
        Function<Integer,Integer> cuadrados =  c-> c*c;
        System.out.println("\nRespuesta funcion Function");
        ListTest.forEach(num -> 
            System.out.println(num + " y su cuadradro es: " + cuadrados.apply(num))
        );
        

    //Generar una lista de números aleatorios usando Supplier.
        Supplier<StringBuilder> build=()-> new StringBuilder();
    //Convertir una lista de cadenas a mayúsculas usando UnaryOperator.
        
    }
}