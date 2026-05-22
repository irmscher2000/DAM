/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 1
 * 
 */
package Ejercicio1;

/**
 * Hilo productor que introduce caracteres en el buffer compartido.
 * 
 * Produce 15 caracteres aleatorios del alfabeto.
 */
public class Productor extends Thread {
    
    /** Buffer compartido donde se almacenan los caracteres. */
    private BuferCompartido bufer;
    
    /** Cadena de caracteres posibles a producir. */
    private final String caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    
    /** Contador de caracteres producidos. */
    private int producido;
    
    /** Límite total de caracteres a producir. */
    private final int LIMITE = 15;
    
    /**
     * Constructor del productor.
     *
     * @param bufer El buffer compartido donde introducir caracteres.
     */
    public Productor(BuferCompartido bufer){
        this.producido = 0;
        this.bufer = bufer;
    }
    
    /**
     * Método que ejecuta el hilo productor.
     * Produce caracteres hasta alcanzar el límite.
     */
    public void run(){
        while(producido < LIMITE){
            try {
                char c = caracteres.charAt((int)(Math.random() * caracteres.length()));
                bufer.producir(c);
                producido++;
                System.out.println("Depositado el carácter " + c + " del buffer");
                sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}
