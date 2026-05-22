/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 2
 * 
 */
package Ejercicio2;

import java.util.concurrent.Semaphore;

/**
 * Clase que representa la mesa donde los filósofos comparten los palillos.
 * Gestiona la asignación y liberación de palillos usando semáforos para
 * evitar condiciones de carrera y deadlocks.
 * 
 */
public class Mesa {
    
    private final Semaphore[] palillos;
    
    /**
     * Constructor de la clase Mesa.
     * Inicializa los semáforos para cada palillo con un permiso disponible.
     * 
     * @param nPalillos Número total de palillos en la mesa
     */
    public Mesa (int nPalillos){
        this.palillos = new Semaphore[nPalillos];
        for (int i = 0; i < nPalillos; i++){
            // Cada palillo se inicializa con 1 permiso (disponible).
            this.palillos[i] = new Semaphore(1);
        }
    }
    
    /**
     * Obtiene el índice del palillo izquierdo para un filósofo dado.
     * 
     * @param i Índice del filósofo (0 a N-1)
     * @return Índice del palillo izquierdo
     */
    public int palilloIzq(int i){
        return i;
    }
    
    /**
     * Obtiene el índice del palillo derecho para un filósofo dado.
     * Implementa la disposición circular de los palillos alrededor de la mesa.
     * 
     * @param i Índice del filósofo (0-based)
     * @return Índice del palillo derecho
     */
    public int palilloDcho(int i){
        if (i == 0){
            return this.palillos.length - 1;
        }else{
            return i - 1;
        }
    }
    
    /**
     * Intenta coger los palillos izquierdo y derecho.
     * Implementa la **estrategia asimétrica** para prevenir el Deadlock:
     * - Filósofos pares (0, 2, 4...) cogen IZQUIERDA -> DERECHA.
     * - Filósofos impares (1, 3...) cogen DERECHA -> IZQUIERDA.
     * @param filosofo El índice del filósofo que intenta comer.
     * @throws InterruptedException Si el hilo es interrumpido mientras espera.
     */
    public void cogerPalillos(int filosofo) throws InterruptedException{
        
        // Rompiendo la simetría: un subconjunto de filósofos cambia el orden
        if (filosofo % 2 == 0){
            
            // Filósofos pares (0, 2, 4...): orden IZQ -> DCHO
            this.palillos[this.palilloIzq(filosofo)].acquire();
            this.palillos[this.palilloDcho(filosofo)].acquire();
        }else {
            
            // Filósofos impares (1, 3...): orden DCHO -> IZQ
            this.palillos[this.palilloDcho(filosofo)].acquire();
            this.palillos[this.palilloIzq(filosofo)].acquire();
        }
        
    }
    
    /**
     * Libera ambos palillos utilizados por un filósofo.
     * Los palillos quedan disponibles para otros filósofos.
     * 
     * @param filosofo Índice del filósofo que libera los palillos
     */
    public void dejarPalillos(int filosofo){
        this.palillos[this.palilloIzq(filosofo)].release();
        this.palillos[this.palilloDcho(filosofo)].release();
    }  
}
