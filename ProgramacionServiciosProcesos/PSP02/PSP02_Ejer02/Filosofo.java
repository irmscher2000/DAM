/**
 * 
 * @author Eugen Moga
 * PSP Tema 02 Ejercicio 2
 * 
 */
package Ejercicio2;
/**
 * Clase que representa a un filósofo en el problema de los filósofos comensales.
 * Cada filósofo alterna entre estados de pensar y comer, necesitando dos palillos
 * para poder comer. Extiende la clase Thread para ejecución concurrente.
 * 
 */
public class Filosofo extends Thread {
    private Mesa mesa;
    private int filosofo;
    private int indiceFilosofo;
    
    /**
     * Constructor de la clase Filosofo.
     * 
     * @param m Mesa compartida donde se encuentran los palillos
     * @param filosofo Número identificador del filósofo (1-based)
     */
    public Filosofo (Mesa m, int filosofo){
        this.filosofo = filosofo;
        this.indiceFilosofo = filosofo -1;
        this.mesa = m;
    }
    
    /**
     * Método principal de ejecución del hilo.
     * El filósofo alterna indefinidamente entre pensar y comer.
     */
    public void run(){
        while(true){
            try {
                this.pensando();
                System.out.println("Filosofo " + this.filosofo + " Hambriento");
                mesa.cogerPalillos(this.indiceFilosofo);
                this.comiendo();
                System.out.println("Filosofo " + this.filosofo + " Termina de comer, Libres palillos:" 
                        + (this.mesa.palilloIzq(this.indiceFilosofo) + 1) + ", " + (this.mesa.palilloDcho(this.indiceFilosofo) + 1));
                mesa.dejarPalillos(this.indiceFilosofo);    
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Simula la acción de pensar del filósofo.
     * El filósofo permanece en estado de pensamiento durante 2 segundos.
     * 
     * @throws InterruptedException Si el hilo es interrumpido durante el sueño
     */
    public void pensando() throws InterruptedException{
        System.out.println("Filosofo " + this.filosofo + " Pensando");
        Thread.sleep(2000);
    }
    
    /**
     * Simula la acción de comer del filósofo.
     * El filósofo permanece en estado de comida durante 2 segundos.
     * Requiere tener ambos palillos para ejecutar esta acción.
     * 
     * @throws InterruptedException Si el hilo es interrumpido durante el sueño
     */
    public void comiendo() throws InterruptedException {
        System.out.println("Filosofo " + this.filosofo + " Comiendo");
        Thread.sleep(2000);
    }
}
