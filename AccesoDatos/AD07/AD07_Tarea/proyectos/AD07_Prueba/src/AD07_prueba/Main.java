// Eugen Moga
// AD07 Tarea Acceso a la base de datos

package AD07_prueba;

/**
 *
 * @author Moga
 */
public class Main {

    /**
    * Clase principal del programa.
    * Contiene el método main, punto de entrada de la aplicación.
    * Se encarga de probar el funcionamiento del componente MatriculaBean
    * a través de la clase AccedeBD.
    * 
    */
    public static void main(String[] args) {
        
        // Creo un objeto de la clase AccedeBD 
        AccedeBD gestion = new AccedeBD();
        
        gestion.listadoMatriculas();
        gestion.listadoMatriculasDNI();
        gestion.anadeMatricula();
        gestion.listadoMatriculasDNI();
    }
}
