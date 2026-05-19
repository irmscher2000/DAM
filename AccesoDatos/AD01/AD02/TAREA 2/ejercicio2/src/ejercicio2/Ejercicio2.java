/*
 * AD Tema 2 Ejercicio 2
 */
package ejercicio2;

import java.io.File;

/**
 * Este programa muetra el arbol del directorio que se indica 
 * como parametro al ejecutar el programa.
 * 
 * @author Eugen Moga
 * @version 1.0
 * 
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Se comprueba que se ha insertado un parametro
        if (args.length != 1){
            System.out.println("Tienes que poner un parametro.");
            System.out.println("Ejemplo: java -jar ejercicio1.jar <nombre directorio>");
            return;
        }
        
        // Se crea el objeto File con el parametro recibido
        String ruta = args[0];
        File f = new File(ruta);
        
        // Si el directorio no existe se muestra el mensaje
        if (!f.exists()){
            System.out.println("Error: El directorio " + ruta + " no existe.");
            return;
        }
        
        // Se muestra el árbol del directorio
        System.out.println("Árbol de directorio para: " + f.getAbsolutePath());
        mostrarArbol(f, " ");
    }
    
    // Método para  mostrar el árbol
    private static void mostrarArbol(File f,String prefijo){
        
        // Se obtienen todos los archivos y directorios del directorio actual
        File[] archivos =f.listFiles();
        
        // Si el directrios esta vacio el programa sale,
        if (archivos == null){
            return;
        }
        
        // Se recorre todos los archivos y directoris
        for (File archivo : archivos){
            System.out.println(prefijo + "|-- " + archivo.getName());
            
            if (archivo.isDirectory()){
                mostrarArbol(archivo, prefijo + "   ");
            }
        }
    }
    
}
