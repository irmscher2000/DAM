
package clienteftp;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Eugen Moga
 * 
 * PSP Tema 3 Tarea
 * Ejercicio 1: Cliente para la lectura remota de ficheros mediante sockets TCP
 */
public class ClienteFTP {

    /**
     * @param args argumentos de linea de comandos
     *        args[0] -> IP del servidor
     *        args[1] -> Puerto del servidor
     */
    public static void main(String[] args) {
        
        if (args.length != 2){
            System.out.println("Uso: java -jar ClienteFTP.jar <ip_servidor> <puerto> ");
            return;
        }
        
        // Se obtienen los parametros de conexion
        String ip = args[0];
        int puerto = Integer.parseInt(args[1]);
        
        // Creacion del socket del cliente y conexion con el servidor
        // dentro de un bloque try catch para asegurar el cierre automatico
        try(Socket sCliente = new Socket(ip, puerto)){
            
            // Flujo de entrada y de salida
            DataInputStream entrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(sCliente.getOutputStream());
            
            Scanner teclado = new Scanner(System.in);
            
            // Se indica la ruta del fichero
            System.out.println("Introduce la ruta completa del fichero: ");
            String ruta = teclado.nextLine();
            
            salida.writeUTF(ruta);
            
            // Se crea el fichero local para guardar el contenido recibido
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("fichero_recibido.txt"))){
                while (true){
                    String linea = entrada.readUTF();
                    
                    if (linea.equals("Fin")){
                        break;
                    }
                    
                    if (linea.equals("ERROR")){
                        System.out.println("Error al leer el fichero en el servidor ");
                        System.out.println(entrada.readUTF());
                        return;
                    }
                    
                    // Se escribe la linea recibida en el fichero local
                    bw.write(linea);
                    bw.newLine();
                }
            }
            
            System.out.println("Fichero recibido correctamente");
            
        }catch(Exception e){
            // Se captura errores de conexion.
            System.err.println("Error cliente: " + e.getMessage());
        }
    }
}
