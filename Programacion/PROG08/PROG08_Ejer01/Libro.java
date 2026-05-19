
// Eugen Moga
// PROG08 Ejercicio 1

package PROG08_Ejer01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Moga
 */
public class Libro {
    
    // Atributos del Libro
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private String generoLiterario;
    private int numPaginas;
    private String editorial;
    
    // Constructor
    public Libro (String titulo, String autor, LocalDate fechaPublicacion, String generoLiterario, int numPaginas, String editorial){
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.generoLiterario = generoLiterario;
        this.numPaginas = numPaginas;
        this.editorial = editorial;
    }
    
    // Getters y Setters
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public LocalDate getFechaPublicacion(){
        return fechaPublicacion;
    }
    public void setFechaPublicacion(LocalDate fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public String getGeneroLiterario(){
        return generoLiterario;
    }
    public void setGeneroLiterario(String generoLiterario){
        this.generoLiterario = generoLiterario;
    }
    
    public int getNumPaginas(){
        return numPaginas;
    }
    public void setNumPaginas(int numPaginas){
        this.numPaginas = numPaginas;
    }
    
    public String getEditorial(){
        return editorial;
    }
    public void setEditorial(String editorial){
        this.editorial = editorial;
    }
    
        @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Título: " + titulo + ", " + 
                "Autor: " + autor +", " + 
                "Fecha publicación: " + fechaPublicacion.format(formato) + ", " + 
                "Genero: " + generoLiterario + ", " +
                "Páginas: " + numPaginas + ", " + 
                "Editorial: " + editorial;
    }
}