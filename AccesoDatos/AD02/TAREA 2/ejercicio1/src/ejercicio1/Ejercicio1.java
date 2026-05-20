/*
 * AD02 Tarea 2 Ejercicio 1
 */

package ejercicio1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Este programa muestra información sobre el archivo o directorio que 
 * que se indica como argumento en la ejecución.
 * 
 * @author Eugen Moga
 * @version 1.0
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        
        // Se comprueba que se ha insertado un parametro
        if (args.length != 1){
            System.out.println("Tienes que poner un parametro.");
            System.out.println("Ejemplo: java -jar ejercicio1.jar <nombre fichero>");
            return;
        }
        
        // Se crea el objeto File con el parametro recibido
        String ruta = args[0];
        File f = new File(ruta);
        
        // Si el fichero no existe se muestra el mensaje
        if (!f.exists()){
            System.out.println("El fichero o directorio no existe.");
            return;
        }
        
        // Se muestra la información
        System.out.println("Nombre: " + f.getName());
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());
        System.out.println("Tamaño: " + f.length() + " bytes.");
        
        Path path = f.toPath();
        System.out.println("Permisos:");
        System.out.println("Leer = " + Files.isReadable(path));
        System.out.println("Escribir = " + Files.isWritable(path));
        
    }
    
}
