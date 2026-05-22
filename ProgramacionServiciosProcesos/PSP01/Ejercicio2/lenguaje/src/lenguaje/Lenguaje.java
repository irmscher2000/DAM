/**
 * PSP TEMA 1 TAREA 1 Ejercicio 2 A
 */
package lenguaje;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Lenguaje genera cadenas de texto aleatorias y las escribe
 * en un fichero indicado por el usuario.
 * 
 * Esta aplicación está diseñada para ser ejecutada desde línea de comandos:
 * 
 * java -jar lenguaje.jar &lt;numeroLineas&gt; &lt;nombreFichero&gt;
 * 
 * Ejemplo:
 * java -jar lenguaje.jar 50 datos.txt
 * 
 * Cada instancia escribe cadenas aleatorias en el fichero, bloqueándolo
 * temporalmente con java.nio.channels.FileLock para permitir
 * la colaboración entre múltiples procesos sin conflictos de escritura.
 * 
 * @author Eugen Moga
 * @version 1.0
 */
public class Lenguaje {

    /**
     * Método principal de la aplicación.
     * 
     * Recibe dos parámetros: 
     * args[0] = número de líneas a generar
     * args[1] = nombre del fichero de salida
     * 
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        
        // Cadena de letras para formar las palabras aleatorias
        String letras = "abcdefghijklmnopqrstuvwxyz";
        String nombreFichero;
        
        // Objeto para manejar el bloqueo exclusivo del archivo
        FileLock bloqueo = null;
        
        // Comprueba que se han pasado exactamente 2 argumentos
        if (args.length == 2){
            try{
                
                // Se convierte el primer argumento a número entero (número de líneas)
                int numeroLineas = Integer.parseInt(args[0]);
                
                // Detecto el sistema operativo
                String osName = System.getProperty("os.name");
                
                // Si estamos en Windows, escapamos las barras invertidas en la ruta
                if (osName.toUpperCase().contains("WIN")){
                    nombreFichero = args[1].replace("\\", "\\\\");
                }else {
                    
                    // En otros sistemas (Linux/Mac), se usa la ruta directamente
                    nombreFichero = args[1];
                }
                
                // Se crea el objeto File para el archivo de salida
                File archivo = new File(nombreFichero);
                
                // Si el fichero no existe, se crea uno nuevo
                if(!archivo.exists()){
                    archivo.createNewFile();
                }
                
                // Se crea un RandomAccessFile en modo lectura/escritura síncrona
                RandomAccessFile raf = new RandomAccessFile(archivo,"rwd");
                
                // Se bloquea el archivo para evitar que otros procesos escriban al mismo tiempo
                bloqueo = raf.getChannel().lock();
                
                // Se posiciona el puntero al final del archivo para añadir texto
                raf.seek(archivo.length());
                
                // Se generan las líneas aleatorias
                for (int i = 0; i < numeroLineas; i++){
                    
                    // Se inicia la línea vacía
                    String linea = "";
                    
                    // Se genera un número aleatorio de caracteres (entre 1 y 10)
                    int numeroCaracteres = generarNumeroAleatorio(1, 10);
                    
                    // Se construye la palabra aleatoria
                    for(int j = 0; j < numeroCaracteres; j++){
                        linea += letras.charAt(generarNumeroAleatorio(0, letras.length() -1));
                    }
                    
                    // Se escribe la línea en el archivo con salto de línea
                    raf.writeChars(linea + "\n");
                }
                
                // Se libera el bloqueo para que otros procesos puedan escribir
                bloqueo.release();
                bloqueo = null;
                
                // Se cierra el archivo
                raf.close();
                
            }catch (IOException ex){
                // Si hay un error de E/S, se registra en el log
                Logger.getLogger(Lenguaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            // Si no se pasan los argumentos correctos, se muestra un mensaje de ayuda
            System.out.println("El programa necesita 2 parametros");
        }
    }
    
    /**
     * Genera un número aleatorio entero dentro de un rango específico.
     *
     * @param minimo valor mínimo (incluido)
     * @param maximo valor máximo (incluido)
     * @return número aleatorio entre {@code minimo} y {@code maximo}
     */
    public static int generarNumeroAleatorio(int minimo, int maximo){
        int num = (int) (Math.random() * (maximo - minimo +1) + (minimo));
        return num;
    }
    
}
