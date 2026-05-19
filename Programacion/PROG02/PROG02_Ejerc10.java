
package prog02_ejerc10;

// Eugen Moga
// PROG02 Ejercicio 10

public class PROG02_Ejerc10 {

    public static void main(String[] args) {
        
        // Declaro las variables numero1 y numero2 de tipo entero con sus respectivos valores
        int numero1 = 2;
        int numero2 = 7;
        
        // Hago la division y el resultado lo guardo en una variable de tipo doble para obtener un resultado con decimales preciso
        double resultado = (double) numero2 / numero1; 
        
        // Imprimo el resultado por pantalla con printf
        // %.2f%n  el simbolo % indica el inicio de un especificador de formato. 
        // .2 Indica el numero de decimales despues del punto
        // f indica que se imprimira un numero de punto flotante de tipo float o double.
        System.out.printf("El resultado de la division es: %.2f%n", resultado);
        
    }
    
}
