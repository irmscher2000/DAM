
package clienteudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Eugen Moga
 * PSP Tema 3 Tarea
 * Ejercicio 2: Cliente UDP para el envio de datagramas
 */
public class ClienteUDP {

    /**
     * @param args argumentos de linea de comandos
     *        args[0] -> IP del servidor
     *        args[1] -> Puerto del servidor
     */
    public static void main(String[] args) {
        
        // Se comprueba el numero de argumentos recibidos
        // Tiene que ser dos IP y Puerto
        if (args.length != 2) {
            System.err.println("Uso: java -jar ClienteUDP.jar <ip_servidor> <puerto>");
            return;
        }
        
        // Se asignan los parametros introducidos a las variables
        String ipServidor = args[0];
        int puerto = Integer.parseInt(args[1]);
        
        // Creacion del socket UDP del cliente
        try (DatagramSocket socket = new DatagramSocket()){
            
            // Obtencion de la direccion IP del servidor
            InetAddress direccion = InetAddress.getByName(ipServidor);
            
            // Bucle para enviar 10000 mensajes numerados al servidor
            for(int i = 1; i <= 10000; i++){
                String mensaje = "Mensaje: " + i;
                byte[] cadena = mensaje.getBytes();
                
                // Se crea el datagrama con el mensaje, la IP y el puerto
                DatagramPacket paquete = 
                        new DatagramPacket(cadena, cadena.length, direccion, puerto);
                
                // Se envia el datagrama
                socket.send(paquete);
            }
            
            // Se envia FIN para indicar el final de la transmision
            byte[] fin = "FIN".getBytes();
            DatagramPacket paqueteFin = 
                    new DatagramPacket(fin, fin.length, direccion, puerto);
            
            socket.send(paqueteFin);
            
            System.out.println("Mensajes enviados correctamente.");
            
        } catch (Exception e) {
            // Se capturan errores relacionados con la comunicacion UDP
            System.out.println("Error cliente UDP: " + e.getMessage());
        }
    }
}
