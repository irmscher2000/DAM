package prog04_ejerc3;
import java.util.Scanner;                                       
import java.util.InputMismatchException;                        
public class PROG04_Ejerc3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Variable para almacenar el número de filas                   
        int numeroFilas = 0;                                    
         // Bucle infinito que depende de condiciones internas para terminar la ejecución
        while(true) {                                           
            try {
                System.out.print("Ingresa un número entero positivo para las filas del triángulo: ");
                numeroFilas = sc.nextInt();                     
                // Sale del bucle si el número es válido
                if (numeroFilas > 0) {
                    break;                                      
                } else {
                    System.out.println("Error, el numero debe ser positivo");
                }
            // Error que aparece si el usuario ingresa algo que no es un número entero    
            } catch (InputMismatchException e){                 
                System.out.println("Error, debes ingresar un número entero. ");
                sc.nextLine();
            // Captura cualquier otra excepción                                      
            } catch (Exception e) {                             
                System.out.println("Error inesperado: " + e.getMessage());                
            }
        }        
        System.out.println("Triángulo de asteriscos");
        /* Bucle for
        Se declara la variable fila de tipo entero y se inicia con el valor 1
        Comprueba que fila es menor o igual a numeroFilas 
        fila se inclemata en 1
        */
        for (int fila = 1; fila <= numeroFilas; fila++){
            // Se calcula la cantidad de espacios para centrar la fila
            int espacios = numeroFilas - fila;
            // Se calcula 2 asteriscos multiplicado por el numero de fila -1                  
            int asteriscos = 2 * fila - 1;                      
            
            /* Imprimo espacios
            Declaro la variable imprimir y la inicio con el valor 0
            Compruebo que imprimir es menor que espacio 
            Incremento imprimir en 1
            */
            for (int imprimir = 0; imprimir < espacios; imprimir++) {
                System.out.print(" ");
            }
            
            /* Imprimo asteriscos
            Hace la misma función que el bucle anterio
            Pero esta vez imprime los asteriscos
            */
            for (int imprimir = 0; imprimir < asteriscos; imprimir ++){
                System.out.print("*");
            }
            // Salto de linea al final de cada linea
            System.out.println(" ");                            
        }   
        sc.close();                                             
    }    
}
