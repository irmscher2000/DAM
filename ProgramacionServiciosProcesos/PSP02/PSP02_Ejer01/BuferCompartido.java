/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 1
 * 
 */
package Ejercicio1;

/**
 * Clase que implementa un buffer compartido para el problema productor-consumidor.
 * 
 * El buffer actúa como una pila (LIFO): el último carácter en entrar será el primero en salir.
 * Usa sincronización con wait() y notifyAll() para gestionar el acceso concurrente.
 */
public class BuferCompartido {
    
    /** Array donde se almacenan los caracteres. */
    private final char[] bufer;
    
    /**
     * Índice del siguiente hueco libre.
     * Representa el número de elementos almacenados.
     */
    private int siguiente;
    
    /**
     * Constructor del buffer compartido.
     *
     * @param capacidad El tamaño máximo del buffer.
     */
    public BuferCompartido(int capacidad){
        this.bufer = new char[capacidad];
        this.siguiente = 0;
    }
    
    /**
     * Método sincronizado que consume un carácter del buffer.
     * Si está vacío, el consumidor espera.
     *
     * @return El carácter consumido.
     */
    public synchronized char consumir(){
        
        while(this.siguiente == 0){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        
        this.siguiente--;
        
        char c = this.bufer[this.siguiente];
        
        notifyAll();
        
        return c;
        
    }
    
    /**
     * Método sincronizado que introduce un carácter en el buffer.
     * Si está lleno, el productor espera.
     *
     * @param c Carácter a introducir.
     */
    public synchronized void producir(char c){
        
        while(this.siguiente == this.bufer.length){
            try {
                wait();
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        
        this.bufer[this.siguiente] = c;
        
        this.siguiente++;
        
        notifyAll();
        
    }
    
}
