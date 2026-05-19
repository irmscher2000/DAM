
// Eugen Moga
// PROG08 Ejercicio 1

package PROG08_Ejer01;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {
    
    private static Map<String, Libro> biblioteca = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    
    // Método para agregar Libro
    public void agregarLibro(){
        System.out.println("Título del libro: ");
        String titulo = sc.nextLine();
        
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        
        LocalDate fechaPublicacion = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Valido la fecha de publicación
        while(true){
            try{
                System.out.println("Fecha de publicacion: dd/MM/yyyy ");
                String fechaString = sc.nextLine();
                fechaPublicacion = LocalDate.parse(fechaString, formato);
                break;      // Sale del bucle si la fecha es valida.
            }catch(DateTimeParseException e){
                System.out.println("Error: Formato de fecha incorrecto. Usa dd/MM/yyyy (ejemplo: 06/02/2025)");
            }
            
        }
        
        System.out.println("Genero literario: ");
        String generoLiterario = sc.nextLine();
        
        System.out.println("Número de paginas: ");
        int numPaginas = sc.nextInt();
        sc.nextLine();  // Consumir la nueva linea despues de nextInt
        
        System.out.println("Editorial: ");
        String editorial = sc.nextLine();
        
        // Despues de capturar los datos introducidos por el usuario, se crea el objeto Libro
        Libro libro = new Libro(titulo, autor, fechaPublicacion, generoLiterario, numPaginas, editorial); 
        biblioteca.put(titulo, libro);      // Se guarda en HashMap. titulo es la clave y libro es el valor.
        System.out.println("Libro agregado correctamente!");
    }
    
    // Método para buscar Libro
    public void buscarLibro(){
        System.out.println("¿Que libro deseas buscar?");
        String palabraClave = sc.nextLine().toLowerCase();
        
        boolean encontrado = false;
        
        System.out.println("Libros disponibles: ");
        for (Map.Entry<String, Libro> entry: biblioteca.entrySet()){
            if(entry.getKey().toLowerCase().contains(palabraClave)){    // Verifica si el titulo contiene la palabra clave
                System.out.println(entry.getValue());       // Imprime el libro encontrado
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No tenemos coincidencias con tu busqueda.");
        }
    }
    
    // Métodos para mostrar libros
    public void mostrarLibros(){
        if(biblioteca.isEmpty()){
            System.out.println("No hay libros disponibles en la biblioteca.");
        }else {
            int i = 1;
            System.out.println("Libros disponibles en la biblioteca:");
            for(Libro libro: biblioteca.values()){
                System.out.println("Libro nº " + i +": " + libro);
                i++;
            }
        }
    }
    
}
