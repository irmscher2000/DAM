// Eugen Moga
// PSP Tema 4 Ejercicio 1

package psp04_ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    
    private static final int PUERTO = 8000;
    
    private static final double INTERES = 0.02;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // ServerSocket que escucha conexiones entrantes
        ServerSocket servidorSocket = null;
        
        // Socket representa la conexion con un cliente 
        Socket clienteSocket = null;
        
        try {
            // El servidor escucha en el puerto 8000
            servidorSocket = new ServerSocket(PUERTO);
            System.out.println("\n    BANCO   ");
            System.out.println("Esperando conexion en el puerto: " + PUERTO + "\n");
            
            // Se acepta la conexion
            clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado");
            
            // Se crea los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(clienteSocket.getOutputStream());
            
            // Se recibe el capital a invertir del cliente 
            double capital = flujo_entrada.readDouble();
            System.out.println("Capital a invertir: " + capital);
            
            // Se calcula la ganancia
            double ganancia = capital * INTERES;
            
            // Se envia la gancia al cliente 
            flujo_salida.writeDouble(ganancia);
            System.out.println("Mensaje enviado al cliente.");
            
            // Cierro las conexiones
            flujo_entrada.close();
            flujo_salida.close();
            clienteSocket.close();
            System.out.println("Conexion cerrada");
            
            
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }finally{
            // Se cierra el servidor al finalizar
            try {
                if (servidorSocket != null && !servidorSocket.isClosed()) {
                    servidorSocket.close();
                    System.out.println("Servidor cerrado correctamente ");
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }
}
