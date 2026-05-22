// Eugen Moga
// PSP Tema 4 Ejercicio 2

package psp04_ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Moga
 */
public class ServidorTienda {
    
    private static final int PUERTO = 8000;
    
    private static final double DESCUENTO = 0.25;

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
            System.out.println("\n    TIENDA   ");
            System.out.println("Esperando conexion en el puerto: " + PUERTO + "\n");
            
            // Se acepta la conexion
            clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado");
            
            // Se crea los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(clienteSocket.getOutputStream());
            
            // Se recibe el precio total sin descuento 
            double precio = flujo_entrada.readDouble();
            System.out.println("Precio sin descuento: " + precio);
            
            // Se calcula el descuento
            double descuento = precio * DESCUENTO;
            
            // Se envia la gancia al cliente 
            flujo_salida.writeDouble(descuento);
            System.out.println("Precio enviado al cliente.");
            
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
