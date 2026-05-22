/**
 * PSP TEMA 1 TAREA 1 Ejercicio 1 A
 */

package ordenarnumeros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * La clase OrdenarNumeros es una aplicación de consola
 * que permite al usuario introducir una secuencia de números enteros
 * por teclado. Al finalizar la entrada de datos (introduciendo un cero),
 * la aplicación muestra la lista de números ingresados ordenados de mayor a menor.
 * 
 * Utilizo la clase java.util.Scanner para la entrada de datos
 * y manejo posibles errores de tipo de dato mediante
 * java.util.InputMismatchException
 *
 * @author Eugen Moga
 * 
 * @version 1.0
 */
public class OrdenarNumeros {

    /**
     * Punto de entrada principal para la aplicación.
     * 
     * Este método solicita números enteros al usuario hasta que se introduce un cero.
     * Al recibir el cero, ordena los números almacenados de forma descendente
     * y los imprime en consola.
     *
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        ArrayList<Integer> numeros = new ArrayList<>();
        
        System.out.println("Esta aplicación ordena de mayor a menor los números que introduzcas por teclado. Para finalizar introducir cero");
        System.out.println("Ingresa los números: ");
        
        
        while(sc.hasNext()){
            try{
                if (sc.hasNext()){
                    int numero = sc.nextInt();
                
                    if(numero == 0){
                        break;
                    }
                    numeros.add(numero);
                }else {
                    System.out.println("Error: " + sc.next() + " no es un número valido.");
                }
            }catch(InputMismatchException e){
                System.out.println("Error: Debes introducir solo números enteros.");
                // Limpio el búfer del Scanner para evitar un bucle infinito
                sc.next();
            }      
        }
        
        // Ordeno la lista en orden descendente (mayor a menor)
        Collections.sort(numeros, Collections.reverseOrder());
        System.out.println("\nNúmeros ordenados de mayor a menor: ");
        
        for(int n : numeros){
            System.out.println(n);
        }
        
        sc.close();
    }
    
}
