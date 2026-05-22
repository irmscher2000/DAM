// Eugen Moga
// PSP Tema 4 Ejercicio 2

package psp04_ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTienda {

    static final String HOST = "localhost";
    static final int PUERTO = 8000;
    private static double precioTotal; 
    
    public static void main(String[] args) {
        
        // Representa la conexion con el servidor
        Socket socket = null;
        
        // Scanner para leer los datos del usuario por teclado
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("\n    CLIENTE     \n");
            
            // Conectar con el servidor
            socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado con la tienda");
            
            // Se crean los flujos de entrada salida
            DataInputStream flujo_entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(socket.getOutputStream());
            
            // Solicito dos precios al usuario
            for(int i = 0; i < 2; i++){
                double precio;    
                while (true) {                    
                    System.out.print("Ingresa el precio del producto " + (i+1) + ": ");
                    precio = sc.nextDouble();
                    
                    if (precio > 0) {
                        break;
                    }else{
                        System.out.println("El precio debe ser mayor que cero");
                    }
                }
                precioTotal += precio;
            }
            
            
            
            // Se envia el capital al servidor
            flujo_salida.writeDouble(precioTotal);
            
            // Recibo la respuesta
            double descuento = flujo_entrada.readDouble();
            
            System.out.println("\n DESCUENTO");
            System.out.printf("Precio sin descuento: €%.2f\n", precioTotal);
            System.out.printf("Descuento: €%.2f\n", descuento);
            System.out.printf("Total a pagar: €%.2f\n", (precioTotal-descuento));
            
            // Se cierra la conexion 
            flujo_entrada.close();
            flujo_salida.close();
            socket.close();
            sc.close();
            System.out.println("Conexion cerrada");
            
            
        } catch (UnknownHostException e) {
            System.out.println("No se puede encontrar el servidor: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error de comunicacion: " + e.getMessage());
        } finally{
            try {
                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
