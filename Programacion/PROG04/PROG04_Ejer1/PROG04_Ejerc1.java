package prog04_ejerc1;
import java.util.Scanner;
// Esta clase maneja excepciones de entrada inválida                       
import java.util.InputMismatchException;        
public class PROG04_Ejerc1 {    
    public static void main(String[] args) {  
        Scanner entrada = new Scanner(System.in);                               
        try {
            System.out.print("Ingrese un número entero menor que 50: ");      
            int N = entrada.nextInt();                                          
            // Verifica si el numero es menor que 50
            if (N > 0 && N <= 50){                                              
                //Bucle for: Imprime los números del 1 al N en orden ascendente.
                System.out.println("Secuencia con bucle for ascendente: ");
                for (int i = 1; i <= N; i++){                                   
                    System.out.print(i + " ");                                  
                }
            }else {
                System.out.println("Error el número debe ser mayor que 0 y menor que 50.");   
                return;     // Termina el programa
                }
            System.out.println("Secuencia con bucle while: ");
            int contador = N;                                                   
            // Condicion para seguir el bucle
            // Bucle while: Imprime los números del N al 1 en orden descendente.
            while (contador >= 1){                                              
                System.out.print(contador + " ");                               
                contador--;                                                     
            }                                   
            System.out.println("Secuencia con bucle do-while: ");
              // Inicio el numero en 1
            int numero = 1;
            // Bucle do-while: Imprime los números pares desde 1 hasta N                                                   
            do {
                // Verifico si el numero es par
                if(numero % 2 == 0){                                            
                    System.out.print(numero + " ");                             
                }
                numero++;                                                       
            // Comprueba que numero sea menor o igual a N para continuar el bucle
            }while (numero <= N);                                               
        // Captura la excepción si el usuario ingresa un numero que no sea entero                                                          
        }catch (InputMismatchException error){                                  
            System.out.println("Error, debes insertar un numero entero valido. " + error);
        }catch (Exception error){
            // Captura cualquier otra execpción genérica
            System.out.println("Ocurrio un error inesperado: " + error.getMessage());   
        }finally {
            entrada.close();                                                        
        }
    }  
}
