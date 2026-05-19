package libreria;
import java.text.SimpleDateFormat;
import java.util.Date; 
// Clase
public class Libro {
    // Declaro un tipo enumerado 
    public enum enumGenero{
        FICCION, CIENCIA_FICCION, FANTASIA, HISTORIA, ROMANTICA, NOVELA_NEGRA;
    }
    
    // Atributos de la clase Libro
    private String titulo;          
    private String autor;           
    private enumGenero genero;      
    private Date fechaPublicacion;  
    
    // Con este constructor inicializo el título y el género con los valores dados
    public Libro(String titulo, enumGenero genero){
        this.titulo = titulo;       
        this.genero = genero;       
        this.autor = "";            
        this.fechaPublicacion = null;
    }
    
    // Con este constructor inicializo el título, autor, género y fecha de publicacion con los valores dados
    public Libro(String titulo, String autor, enumGenero genero, Date fechaPublicacion){
        this.titulo = titulo;           
        this.autor = autor;             
        this.genero = genero;           
        this.fechaPublicacion = fechaPublicacion;    
    }
    
    // GETTER Método para obtener el título del libro
    public String getTitulo(){
        return titulo;
    }
    // SETTER Método para establecer el título del libro
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    // Método para obtener el autor del libro
    public String getAutor(){
        return autor;
    }
    // Método para establecer el autor del libro
    public void setAutor(String autor){
        this.autor = autor;
    }
    // Método para obtener el genero del libro
    public enumGenero getGenero(){
        return genero;
    }
    // Método para establecer el genero del libro
    public void setGenero(enumGenero genero){
        this.genero = genero;
    }
    // Método para obtener la fecha de publicación del libro
    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }
    // Método para establecer la fecha de publicación del libro
    public void setFechaPublicacion(Date fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
    }
    // Metodo para obtener si el libro es clasico o no
    public boolean isClasico(){
        // Establece la fecha limite del 1 de enero del año 2000
        // "100" hace referencia a 100 años desde 1900, "0" a los meses y "1" al dia
        Date fechaLimite = new Date(100, 0, 1);  
        // compara si la fecha de publicacion es anterior a la fecha limite
        return fechaPublicacion != null && fechaPublicacion.before(fechaLimite);
    }

    // Metodo que devuelve una cadena con la fecha en formato largo 
    public String toString(){
        // Crea un formateador de fechas con el patron "dd/MM/yyyy"
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
        // A fechaPublicacion la formatea y la guarda en fechaFormateada
        String fechaFormateada = (fechaPublicacion != null) ? formato.format(fechaPublicacion) : "Fecha desconocida"; 
        // Devuelve la cadena con los datos y la fecha formateada
        return String.format("Titulo: %s, Autor: %s, Género: %s, Fecha publicación: %s", titulo, autor, genero, fechaFormateada);
    }
}
