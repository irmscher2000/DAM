
package prog02_ejerc9;

// Eugen Moga
// PROG02 Ejercicio 9

// Importa la clase Scanner
import java.util.Scanner;

public class PROG02_Ejerc9 {

    public static void main(String[] args) {
        
        // Nombre el objeto "año" de la clase Scanner
        Scanner entrada = new Scanner(System.in);
        
        // Le pido al usuario que introduzca un año
        System.out.println("Escribe un año: ");
        int ano = entrada.nextInt();
        
        // "ano % 4 == 0" Comprueba si el año es divisible por 4
        // "ano % 100 != 0" Comprueba que no sea divisible por 100
        // "ano % 400 == 0" Comprueba que si es divisible por 100 tambien lo sea por 400.
        // La expresion completa devuelve verdadero si el año es bisiesto y falso si el año no es bisiesto. 
        boolean esbisiesto = (ano % 4 == 0) && (ano % 100 != 0 || ano % 400 == 0);
        
        // Imprimo el resultado
        System.out.println("El año " + ano + (esbisiesto ? " es bisiesto." : " no es bisiesto."));
    }
    
}
