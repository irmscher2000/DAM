/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 1
 * 
 */
package Ejercicio1;

/**
 * Clase principal del programa Ejercicio 1 Productor-Consumidor.
 * 
 * Se crea un buffer compartido de tamaño 6 y lanza un productor y un consumidor.
 * Ambos hilos producen y consumen 15 caracteres usando el buffer.
 * 
 * Se espera a que ambos hilos finalicen antes de terminar el programa.
 */
public class Principal {

     /**
     * Método principal que inicia la ejecución del programa.
     *
     * @throws InterruptedException si algún hilo es interrumpido durante la espera.
     */
    public static void main(String[] args) throws InterruptedException {
        
        BuferCompartido b = new BuferCompartido(6);
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        
        p.start();
        c.start();
        
        p.join();
        c.join();
        System.out.println("Termina el programa");
        
    }
    
}
