/*
 * PSP TEMA 1 TAREA 1 Ejercicio 1 B
 */
package aleatorios;

import java.util.Random;

/**
 * Aleatorios es una aplicación de línea de comandos
 * diseñada para generar una cantidad específica de números enteros
 * aleatorios en funcion del parametro especificado.
 * El programa espera un único argumento numérico que indica cuántos
 * números aleatorios deben ser generados. Los números generados están
 * en el rango de 0 a 100, ambos inclusive.
 *
 * @author Eugen Moga
 * @version 1.0
 */
public class Aleatorios {

    /**
     * Punto de entrada principal para la aplicación.
     * Este método procesa el argumento pasado por la línea de comandos,
     * valida que sea un número entero positivo y luego genera e imprime
     * la cantidad solicitada de números aleatorios en el rango [0, 100].
     *
     * @param args Un array de cadenas que debe contener exactamente un elemento,
     * el cual representa la cantidad de números aleatorios a generar.
     * Ejemplo: "5".
     * 
     * @throws NumberFormatException Si el argumento proporcionado no puede
     * ser parseado como un entero (ej: "hola").
     */
    public static void main(String[] args) {
        // Compruebo que se ha introducido un argumento.
        if (args.length != 1){
            System.out.println("Error, tienes que introducir un número.");
            System.out.println("Ejemplo: java -jar aleatorios.jar 5");
            return;
        }
        
        try{
            // Convierto el primer argumento a un número entero.
            int cantidad = Integer.parseInt(args[0]);
            
            // Compruebo que el argumento introducido sea mayor que cero.
            if (cantidad <= 0){
                System.out.println("Error: La cantidad tiene que ser un número entero positivo");
                return;
            }
            
            // Inicializo el generador de números aleatorios.
            Random r = new Random();
            
            // Bucle para generar e imprimir la cantidad de números solicitada.
            for (int i = 0; i < cantidad; i++){
                int numeroAleatorio = r.nextInt(101);
                System.out.println(numeroAleatorio);
            }
            
        }catch (NumberFormatException e){
            System.out.println("El argumento tiene que ser un número entero positivo.");
            return;
        }
    
    }
}
