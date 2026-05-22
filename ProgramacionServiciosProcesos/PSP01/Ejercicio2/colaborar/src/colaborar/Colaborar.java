/**
 * PSP TEMA 1 TAREA 1 Ejercicio 2 B
 * 
 * Esta clase lanza varias instancias del programa "lenguaje.jar",
 * indicándole cuántas palabras generar en cada instancia, de forma incremental.
 * 
 * Cada proceso generado escribe sus palabras aleatorias en un mismo fichero de salida,
 * colaborando en la construcción de un gran fichero de texto.
 * 
 * Ejemplo de ejecución:
 * 
 * <pre>
 * java -jar colaborar.jar 10 datos.txt
 * </pre>
 * 
 * Esto lanzará 10 procesos, el primero escribiendo 10 líneas, el segundo 20,
 * el tercero 30, y así sucesivamente hasta 100.
 */
package colaborar;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal del programa Colaborar.
 * 
 * Lanza varias instancias de la aplicación "lenguaje.jar" para que
 * trabajen de forma concurrente y escriban en un mismo fichero.
 * 
 * Uso:
 * java -jar colaborar.jar &lt;numeroProcesos&gt; &lt;nombreFichero&gt;
 * Ejemplo:
 * java -jar colaborar.jar 10 datos.txt
 * 
 * @author Eugen Moga
 * @version 1.0
 */
public class Colaborar {

    /**
     * Método principal de la aplicación Colaborar.
     *
     * Recibe como argumentos:
     * 
     * args[0]: número de procesos que se lanzarán
     * args[1]: nombre del fichero de salida
     * 
     * Cada proceso lanza "lenguaje.jar" con un número creciente de palabras a generar.
     *  
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        
        // Se verifica que se han pasado exactamente 2 parámetros al programa
        if (args.length == 2){
            try{
                
                // Convierte el primer argumento a número entero (número de procesos)
                int numProcesos = Integer.parseInt(args[0]);
                
                // Se guarda el segundo argumento como nombre del fichero de salida
                String nombreFichero = args[1];
                
                // Bucle que lanza al menos 10 instancias de lenguaje.jar
                for (int i =1; i <= 10; i++){
                    
                    // Se muestra el número del proceso que se está lanzando
                    System.out.println("Proceso nº: " + i);
                    
                    // Se construye el comando a ejecutar:
                    // java -jar lenguaje.jar (numeroDePalabras) (nombreFichero)"
                    // Ejemplo: java -jar lenguaje.jar 20 datos.txt
                    String comando = "java -jar lenguaje.jar " + (i * 10) + " " + nombreFichero;
                    // Se muestra el comando que se va a ejecutar (útil para depuración)
                    System.out.println("Comando nº: " + comando);
                    
                    // Se ejecuta el comando en un nuevo proceso del sistema
                    Runtime.getRuntime().exec(comando);
                }
            }catch (SecurityException ex){
                // Si hay restricciones de seguridad en la ejecución de procesos
                System.out.println("Problema de seguridad: " + ex.getMessage());
            }catch (IOException ex){
                // Si ocurre un error al intentar ejecutar el comando
                Logger.getLogger(Colaborar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            // Si no se pasan los parámetros requeridos, se informa al usuario
            System.out.println("Debes introducir dos parametro.");
        }
    }
}
