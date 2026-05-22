package servidorftp;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Eugen Moga
 * PSP Tema 3 Tarea
 * Ejercicio 1: Lectura remota de ficheros mediante socket TCP
 */
public class ServidorFTP {
    
    /** 
     * @param args argumentos de linea de comandos
     *        args[0] -> Puerto donde escucha */
    public static void main(String[] args) {
        
        if(args.length != 1){
            System.out.println("Uso: java -jar ServidorFTP.jar <puerto>");
            return;
        }
        
        int puerto = Integer.parseInt(args[0]);
        
        // Creo el ServerSocket dentro de try catch para 
        // garantizar el cierre automatico del socket al finalizar
        try (ServerSocket skServidor = new ServerSocket(puerto)){
            
            System.out.println("Escucho en el puerto: " + puerto);
            
            Socket cliente = skServidor.accept();
            System.out.println("Cliente conectado");
            
            // Flujo de entrada para recibir datos del cliente
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            
            // Flujo de salida para enviar datos al cliente 
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            
            // Recibir ruta del fichero solicitado por el cliente
            String rutaFichero = entrada.readUTF();
            System.out.println("Solicitud de fichero: " + rutaFichero);
            
            // Se abre y se lee el fichero
            try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))){
                
                String linea;
                
                // Cada linea se envia al cliente mediante el socket
                while ((linea = br.readLine()) != null){
                    salida.writeUTF(linea);
                }
                salida.writeUTF("Fin"); // Fin de fichero
                
            }catch (Exception e){
                // Captura de error si no se puede leer el fichero
                salida.writeUTF("ERROR");
                salida.writeUTF("No se pudo leer el fichero");
            }
            
            // Se cierra el socket del cliente.
            cliente.close();
            
        }catch (Exception e){
            // Captura de errores generales del servidor
            System.out.println("Error servidor: " + e.getMessage());
        }
    }
}
