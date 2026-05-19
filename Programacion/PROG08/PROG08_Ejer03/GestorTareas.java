
// Eugen Moga
// PROG08 Ejercicio 3

package PROG08_Ejer03;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class GestorTareas {
    
    // ArrayLista para ver tareas pendientes
    private ArrayList<String>tareasPendientes = new ArrayList<>();
    
    // LinkedList para ver tareas completadas
    private LinkedList<String>tareasCompletadas = new LinkedList<>();
    
    // Scanner para leer la entrada del usuario
    private Scanner sc = new Scanner(System.in);
    
    // 1. Método para agregar tarea
    public void agregarTarea(){
        System.out.println("Ingresa tarea nueva: ");
        String tarea = sc.nextLine();
        tareasPendientes.add(tarea);
        System.out.println("Tarea agregada correctamente.\n");
    }
    
    // 2. Metodo para marcar tareas como completadas
    public void marcarTareaCompletada(){
        consultarEstadoTareas();
        System.out.print("Ingresa el número de la tarea que has completado: ");
        while(true){
            try{
                int indice = sc.nextInt() - 1;
                sc.nextLine(); 
                if (indice >= 0 && indice < tareasPendientes.size()){
                    String tarea = tareasPendientes.remove(indice);
                    tareasCompletadas.addFirst(tarea);
                    System.out.println("Tarea marcada como completada.");
                    break;
                }else {
                    System.out.println("Número invalido. Introduce un numero en 1 y " + tareasPendientes.size());
                }
            }catch(InputMismatchException e){
                System.out.println("Error: Debes introducir un numero válido.");
                sc.nextLine();      // Limpiar buffer
            }
        }
        
        
    }
    
    // 3. Método para consultar estado de tareas.
    public void consultarEstadoTareas(){
        System.out.println("Tareas pendientes: ");
        for (int i = 0; i < tareasPendientes.size(); i++){
            System.out.println((i + 1) + ". " + tareasPendientes.get(i));
        }
        System.out.println("\nTareas completadas: ");
        for (String tarea : tareasCompletadas){
            System.out.println(tarea);
        }
    }
    
    // 4. Método para eliminar varias tareas a la vez
    public void eliminarMultiplesTareas(){
        consultarEstadoTareas();
        System.out.println("Ingresa el rango de tareas a eliminar (Inicio y fin) separados por espacio");
        // Manejo de excepciones
        while(true){
            try{
                int inicio = sc.nextInt() - 1;
                int fin = sc.nextInt();
                sc.nextLine();
                
                if(inicio >= 0 && fin <= tareasPendientes.size() && inicio < fin){
                    tareasPendientes.subList(inicio, fin).clear();
                    System.out.println("Tareas eliminadas correctamente.");
                    break;
                }else {
                    System.out.println("Rango invalido. Introduce un numero entre 1 y " + tareasPendientes.size());
                }
            } catch(InputMismatchException e){
                System.out.println("Error: solo puedes ingresar números");
                sc.nextLine();  // Limpiar buffer
            }
        }
    }
    
    // 5. Recuperar la ultima tarea completada
    public void recuperarUltimaTareaCompletada(){
        if(!tareasCompletadas.isEmpty()){
            String tareaRecuperada = tareasCompletadas.poll();
            tareasPendientes.add(tareaRecuperada);
            System.out.println("La última tarea completada fue restaurada a tareas pendientes. ");
        } else {
            System.out.println("No hay tareas completadas para recuperar. ");
        }
    }
    
    // 6. Modificar una tarea existente.
    public void modificarTarea(){
        consultarEstadoTareas();
        System.out.println("Ingresa el nombre de la tarea que deseas modificar: ");
        String nombreTarea = sc.nextLine();
        
        // Obtengo el indice de la tarea con indexOf
        int indice = tareasPendientes.indexOf(nombreTarea);
   
        if(indice != -1){       
            System.out.println("Cambia el nombre de la tarea: ");
            String nuevaTarea = sc.nextLine();
            tareasPendientes.set(indice, nuevaTarea);
            System.out.println("Tarea modificada correctamente. ");
        } else {
            System.out.println("La tarea " + nombreTarea + " no existe en la lista de tareas pendientes.");
        }        
    }
}
