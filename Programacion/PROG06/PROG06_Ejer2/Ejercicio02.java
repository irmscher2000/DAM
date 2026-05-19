// Eugen Moga  
// Prog06 Ejercicio 2

package PROG06_Ejer2;   
import java.util.Scanner;

public class Ejercicio02 {
    public static void main(String[] args){
        
        // Creo da variable sc para leer entrada del usuario.
        Scanner sc = new Scanner(System.in);
        
        // Pido al usuario que introduzca una frase.
        System.out.println("Introduce una frase de 10 palabras separadas por espacios:");
        String frase = sc.nextLine();
        
        // Declaro el Array y divido la frase con el metodo split.
        String[] palabras = frase.split(" ");
        
        // Hago la comprobacion si la frase tiene 10 palabras
        if (palabras.length != 10){
            System.out.println("Error: La frase debe contener 10 palabras");
        } else {
            
            // Muestro las palabras almacenadas en el array usando un bucle for.
            System.out.println("Las palabras introduccidas son: ");
            for(int i = 0; i < palabras.length; i++) {
                System.out.println("Palabra " + (i + 1) + ": " + palabras[i]);
            }
        }
        
        sc.close(); // Cierro el scanner
    }
}
