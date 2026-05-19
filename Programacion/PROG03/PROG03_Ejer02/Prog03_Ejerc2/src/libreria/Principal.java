package libreria;
// Importo la clase Date y Calendar para la gestion de fechas
import java.util.Date;
import java.util.Calendar;
// import java.text.SimpleDateFormat;
public class Principal {
    public static void main(String[] args){ // Metodo main o principal
    
        // Creo un objeto de la clase Libro usando el primer constructor
        Libro objLibro1 = new Libro("Nacidos de la Bruma", Libro.enumGenero.FANTASIA);
        
        // Actualizo los atributos autor y fecha publicacion
        objLibro1.setAutor("Brandon Sanderson");
        
        // Con Calendar establezco la fecha de publicacion
        Calendar cal1 = Calendar.getInstance(); // Se crea la instancia cal1
        cal1.set(2006, Calendar.AUGUST,17);     // A cal1 se le asigna el valor 17/AGOSTO/2006
        objLibro1.setFechaPublicacion(cal1.getTime());  // cal1 se guarda en fechaPublicacion
        
        // Muestra el Libro por pantalla en formato largo.
        System.out.println("Primer libro, inicializado con el primer constructor");
        System.out.println(objLibro1.toString());
        
        // Comprueba si el libro es clasico o no 
        System.out.println(objLibro1.isClasico() ? "Es un clásico" : "No es un clásico" );
        
        System.out.println(); // Deja una linea en blanco
        
        
        // Con Calendar establezco la fecha de publicacion
        Calendar cal2 = Calendar.getInstance(); // Se crea la instancia cal2
        cal2.set(1965, Calendar.AUGUST,1);     // A cal1 se le asigna el valor 1/AGOSTO/1965
        Date fechaDune = cal2.getTime();  // cal2 se guarda en fechaDune
        
        // Inicializo el segundo libro con el segundo constructor
        Libro objLibro2 = new Libro("Dune", "Frank Herbert", Libro.enumGenero.CIENCIA_FICCION, fechaDune);
        
        // Muestro el segundo libro en formato largo
        System.out.println("Segundo libro, inicializado con el segundo constructor");
        System.out.println(objLibro2.toString());
        
        // Comprueba si el libro es clasico o no 
        System.out.println(objLibro2.isClasico() ? "Es un clásico" : "No es un clásico" );   
    }   
}