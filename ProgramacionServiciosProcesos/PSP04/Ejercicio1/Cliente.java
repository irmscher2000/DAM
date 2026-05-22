// Eugen Moga
// PSP Tema 4 Ejercicio 1
package psp04_ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    
    static final String HOST = "localhost";
    static final int PUERTO = 8000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Representa la conexion con el servidor
        Socket socket = null;
        
        // Scanner para leer los datos del usuario por teclado
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("\n    CLIENTE     \n");
            
            // Conectar con el servidor
            socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado con el banco");
            
            // Se crean los flujos de entrada salida
            DataInputStream flujo_entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(socket.getOutputStream());
            
            double capitalInvertido;
            
            while (true) {
                System.out.print("Ingresa el capital a invertir: ");
                capitalInvertido = sc.nextDouble();
                
                // Valido que el capital sea positivo
                if (capitalInvertido > 0) {
                    break;
                } else{
                    System.out.println("El capital debe ser mayor que cero");
                }
            }
            
            
            // Se envia el capital al servidor
            flujo_salida.writeDouble(capitalInvertido);
            
            // Recibo la respuesta
            double ganancia = flujo_entrada.readDouble();
            
            System.out.println("\n RESULTADO \n");
            System.out.printf("Capital invertido: €%.2f\n", capitalInvertido);
            System.out.printf("Ganancia mensual: €%.2f\n", ganancia);
            System.out.printf("Total despues de 1 mes: €%.2f\n", (capitalInvertido+ganancia));
            
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
