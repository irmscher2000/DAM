
// Eugen Moga
// Acceso a Datos Tema 6 Ejercicio 3

package ad06_ejercicio3;

// Importo las clases necesarias de la API XML:DB
import org.xmldb.api.base.Resource;             // Representa un recurso XML devuelto por una consulta
import org.xmldb.api.base.ResourceIterator;     // Permite recorrer los resultados de una consulta
import org.xmldb.api.base.ResourceSet;          // Conjunto de resultados devueltos por una consulta
import org.xmldb.api.base.XMLDBException;       // Excepción específica para errores de bases de datos XML

/**
 * Programa para consultar liboros almacenados en libros.xml
 * 
 * @author Moga
 */
public class AD06_ejercicio3 {

    /**
     *  Metodo principal del programa
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
        // Se crea un objeto de la propia clase para ejecutar el programa
        AD06_ejercicio3 programa = new AD06_ejercicio3();
        
        // Llamo al método que contiene la lógica principal
        programa.ejecutar();
        
    }
    
    // Metodo para ejecutar programa
    public void ejecutar() throws ClassNotFoundException{
        
        // Se crea un objeto de la clase que gestiona la conexión con la base de datos
        BaseDatos_eXist db = new BaseDatos_eXist();
        
        try {
            // Inicializa el driver necesario para conectarse a eXist-db
            db.inicializarDriver();
            
            // Establece la conexión con la base de datos
            db.conectar();
            
            // Consulta XPath que obtiene todos los títulos de los libros
            String consulta = "//libro/titulo";
            
            // Se ejecuta la consulta en la base de datos
            ResourceSet resoultado = db.ejecutarConsulta(consulta);
            
            System.out.println("     LISTADO DE LIBROS     ");
            
            // Se obtiene un iterador para recorrer los resultados
            ResourceIterator iterador = resoultado.getIterator();
            
            // Mientras existan más resultados
            while (iterador.hasMoreResources()){
                Resource recurso = iterador.nextResource();
                System.out.println(recurso.getContent().toString());
            }
            
        }catch (XMLDBException e){
            System.err.println("Error al conectar con eXist-db " + e.getMessage());
            e.printStackTrace();
        }
    }
}
