
package servidorudp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Eugen Moga
 * 
 * PSP Tema 3 Tarea
 * Ejercicio 2: Receptor de datagramas con socket UDP
 */
public class ServidorUDP {

    public static void main(String[] args) {
        
        // Sin argumentos, comprueba que no se ingresa ningun argumento
        if (args.length != 0){
            System.err.println("Uso: java -jar ServidorUDP.jar");
            return;
            
        }
        
        // Se indica el puerto donde el servidor UDP permanecera a la escucha
        final int PUERTO = 1500;
        
        // Se crea el socket UDP y se indica el puerto en el que escucha
        // Se crea BufferedWriter para almacenar los mensajes recibidos
        // Utilizo try-catch para asegurar el cierre de recursos
        try (DatagramSocket sSocket = new DatagramSocket(PUERTO);
            BufferedWriter bw = new BufferedWriter(new FileWriter("mensaje.txt"))){
            
            System.out.println("Esperando mensajes en el puerto " + PUERTO);
            
            // Buffer de bytes para almacendar datos recibidos
            byte [] cadena = new byte[1024];
            
            // Bucle infinito que reitera hasta que recibe el mensaje "FIN"
            while (true) {            
                
                // Se crea el datagrama que recibe la informacion 
                DatagramPacket paquete = new DatagramPacket(cadena, cadena.length);
                
                // El servidor queda bloqueado hasta recibir un datagrama
                sSocket.receive(paquete);
                
                // Convierte el contenido del datagrama a string
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                
                // Si recibe la cadena FIN se finaliza la ejecucion.
                if (mensaje.equals("FIN")){
                    System.out.println("Mensaje FIN recibido. Cerrando servidor.");
                    break;
                }
                
                // Se escribe el mensaje recibido en el fichero
                bw.write(mensaje);
                bw.newLine();
            }
            System.out.println("Mensajes guardados correctamente.");
            
        }catch (Exception e){
            // Se capturan errores relacionados con el sockets
            System.out.println("Error servidor UDP: " + e.getMessage());
        }
    }
}
