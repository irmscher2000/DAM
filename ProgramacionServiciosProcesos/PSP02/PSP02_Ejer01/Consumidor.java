/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 1
 * 
 */
package Ejercicio1;

/**
 * Hilo consumidor que extrae caracteres del buffer compartido.
 * 
 * Consume 15 caracteres, esperando si el buffer está vacío.
 */
public class Consumidor extends Thread {
    
    /** Buffer desde el que se consumen caracteres. */
    private final BuferCompartido bufer;
    
    /** Número de caracteres consumidos. */
    private int consumido;
    
    /** Límite total de caracteres a consumir. */
    private final int LIMITE = 15;
    
    /**
     * Constructor del consumidor.
     *
     * @param bufer El buffer compartido del que se extraen caracteres.
     */
    public Consumidor(BuferCompartido bufer){
        this.consumido = 0;
        this.bufer = bufer;
    }
    
    /**
     * Método que ejecuta el hilo consumidor.
     * Consume caracteres hasta alcanzar el límite establecido.
     */
    public void run(){
        while(consumido < LIMITE){
            try {
                char c = bufer.consumir();
                consumido++;
                System.out.println("Recogido el carácter " + c + " del buffer");
                sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}
