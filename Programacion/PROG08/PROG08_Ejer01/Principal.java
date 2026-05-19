
// Eugen Moga
// PROG08 Ejercicio 1

package PROG08_Ejer01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // Instancia de Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        int opcion = 0;
        
        do {
            System.out.println("\n******************************");
            System.out.println("Menú de la biblioteca: ");
            System.out.println("1. Agregar libro ");
            System.out.println("2. Buscar libro ");
            System.out.println("3. Mostrar todos los libros ");
            System.out.println("4. Salir ");
            System.out.println("******************************\n");
            
            while(true){
                System.out.println("Seleccione una opción: ");
                try{
                    opcion = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("Error: Debe ingresar un número válido.");
                    sc.nextLine();       // Limpio la entrada incorrecta
                }
            }

            switch(opcion){
                case 1:
                    biblioteca.agregarLibro();
                    break;
                case 2:
                    biblioteca.buscarLibro();
                    break;
                case 3:
                    biblioteca.mostrarLibros();
                    break;
                case 4: 
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción no valida. Seleccione una opción del menú.");
                
            } 
            
        }while (opcion != 4);
    sc.close();
    }
}
