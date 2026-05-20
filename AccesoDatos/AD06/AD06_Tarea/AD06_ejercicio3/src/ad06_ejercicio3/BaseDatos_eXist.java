
// Eugen Moga
// Acceso a Datos Tema 6

package ad06_ejercicio3;

// Importo las clases necesarias de la API XML:DB
import org.xmldb.api.base.*;            // Clases básicas de acceso a bases de datos XML
import org.xmldb.api.modules.*;         // Servicios adicionales como consultas XPath
import org.xmldb.api.*;                 // Clases principales de la API XML:DB

/**
 * Clase para establecer la conexion con la base de datos
 * 
 * @author Moga
 */
public class BaseDatos_eXist {
    
    // Configuracion de conexion
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLECCION = "/db/Ejercicio03";
    private static final String USUARIO = "admin";
    private static final String PASS = "admin";
    
    // Objeto que representa la coleccion XML dentro de eXist
    private Collection coleccion;
    
    // Servicio que permite ejecutar consultas XPath
    private XPathQueryService servicio;
    
    // Constructor de la clase
    public BaseDatos_eXist(){
        
        // Inicialmente no hay conexión establecida
        this.coleccion = null;
        
        // El servicio de consultas tampoco está inicializado
        this.servicio = null;
    }
    
    // Inicializo el driver de eXist-db
    public void inicializarDriver() throws ClassNotFoundException, XMLDBException{
        System.out.println("Inicializando driver de eXist-db.");
        
        // Se crea una instancia del driver específico de eXist-db
        Database database = new org.exist.xmldb.DatabaseImpl();
    }
    
    // Establezco conexion con la base de datos 
    public void conectar() throws XMLDBException {
        System.out.println("Conectando a: " + URI);
        
        // Registrar el driver (IMPORTANTE)
        try {
            // Se carga dinámicamente la clase del driver
            Database database = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
            
            // Se registra el driver en el DatabaseManager para que Java pueda conectarse a eXist-db
            DatabaseManager.registerDatabase(database);
        } catch (Exception e) {
            // Si ocurre un error se lanza una excepción de base de datos XML
            throw new XMLDBException();
        }
        
        // Primero configurar las propiedades de conexión
        java.util.Properties props = new java.util.Properties();
        props.setProperty("user", USUARIO);
        props.setProperty("password", PASS);
        
        // Obtener la colección con autenticación
        coleccion = DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASS);
        
         // Si la colección no existe o no se puede acceder
        if (coleccion == null) {
            
            // Se lanza un error indicando que la colección no existe
            throw new XMLDBException(ErrorCodes.NO_SUCH_COLLECTION, 
                "No se puede acceder a la colección: " + COLECCION);
        }
        
        // Se obtiene el servicio para realizar consultas XPath
        servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
        System.out.println("Conectado correctamente con el usuario: " + USUARIO);
    }

    // Método para ejecutar consultas XPath
    public ResourceSet ejecutarConsulta(String consulta) throws XMLDBException{
        System.out.println("\nEjecutando consulta: " + consulta);
        
        // Ejecuta la consulta XPath sobre la colección
        ResourceSet resultado = servicio.query(consulta);
        
        // Devuelve el conjunto de resultados
        return resultado;
    }
    
    // Método para cerrar la conexión con la base de datos 
    public void cerrarConexion() throws XMLDBException{
        if (coleccion != null){
            coleccion.close();
            System.out.println("Conexion cerrada correctamente.");
        }
    }
    
}
