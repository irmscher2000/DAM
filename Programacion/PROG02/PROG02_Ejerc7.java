package prog02_ejerc7;

// Importo la clase Scanner para leer datos desde teclado
import java.util.Scanner;

public class PROG02_Ejerc7 {
    public static void main(String[] args) {
        
        // // Primero creo el objeto Scanner numero para leer la entrada del usuario.
        Scanner numero = new Scanner(System.in);
        
        System.out.println("Este programa resuelve la siguiente ecuación C1x + C2 * C3 = 0 ");
        
        // Creo las variables C1, C2, C3
        float C1,C2,C3;
        
        // Solicito al usuario que introduzca un valor para C1
        System.out.print("Que valor quieres que tenga C1: ");
        C1 = numero.nextInt();
        
        // Solicito al usuario que introduzca un valor para C2
        System.out.print("Que valor quieres que tenga C2: ");
        C2 = numero.nextInt();
        
        // Solicito al usuario que introduzca un valor para C3
        System.out.print("Que valor quieres que tenga C3: ");
        C3 = numero.nextInt();
        
        // Declaro la variable X y resuelvo la ecuacion 
        // Resultado C1 * X + C2 * C3 = 0
        double x = -(C2 * C3) / C1;
        
        // Imprimo por pantalla el resultado utilizando 
        // El primer % indica que en esa posición se va a escribir un valor.
        // .2 indica el número de decimales.
        // La f indica que el número es de tipo float o double.
        // %n indica un salto de línea.
        System.out.printf("El valor de x es: %.2f%n", x);
        
    }
    
}
