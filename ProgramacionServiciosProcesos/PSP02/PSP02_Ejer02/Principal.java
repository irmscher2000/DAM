/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 2
 * 
 */
package Ejercicio2;

/**
 * Clase principal que inicia la simulación del problema de los filósofos comensales.
 * Crea una mesa con 5 palillos y 5 filósofos que compiten por ellos.
 * 
 */
public class Principal {

    /**
     * Método principal que inicia la aplicación.
     * Crea los recursos compartidos y lanza los hilos de los filósofos.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        
        // Se crear la mesa con 5 palillos (para 5 filósofos)
        Mesa m = new Mesa (5);
        
        // Se crear e inicia 5 filósofos
        for (int i = 1; i <= 5; i++){
            Filosofo f = new Filosofo(m, i);
            f.start();
        }   
    } 
}
