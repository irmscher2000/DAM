
// Eugen Moga
// PROG08 Ejercicio 3

package PROG08_Ejer03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        int opcion;
        
        do{
            System.out.println("\nGestor de tareas");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Consultar estado de tareas");
            System.out.println("4. Eliminar varias tareas");
            System.out.println("5. Recuperar ultima tarea completada");
            System.out.println("6. Modificar tarea existente");
            System.out.println("7. Salir\n");
            System.out.println("Seleccione una opción: ");
            
            // Manejo de excepciones por si no se ingresa un numero.
            while(true){
                try{
                    opcion = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("Error: Debes ingresar un número válido");
                    sc.nextLine();
                }
            }
            
            // Menu
            switch(opcion){
                case 1:
                    gestor.agregarTarea();
                    break;
                case 2:
                    gestor.marcarTareaCompletada();
                    break;
                case 3:
                    gestor.consultarEstadoTareas();
                    break;
                case 4:
                    gestor.eliminarMultiplesTareas();
                    break;
                case 5:
                    gestor.recuperarUltimaTareaCompletada();
                    break;
                case 6:
                    gestor.modificarTarea();
                    break;
                case 7:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion no valida. Introduce una opcion del menu.");
            }
        } while (opcion !=7);
        sc.close();
    }
    
}
