/*
 * AD03 Ejercicio 1
 */

package ejercicio2;

import java.sql.*;
/**
 * Clase utilitaria para gestionar la conexión con la base de datos MySQL.
 * Proporciona métodos estáticos para establecer la conexión con la base de datos RRHH.
 * 
 * <p>Esta clase utiliza el patrón de configuración centralizada con constantes
 * para los parámetros de conexión (URL, usuario y contraseña).</p>
 * 
 * @author Eugen Moga
 * @version 1.0
 * @since 2025
 */
public class ConexionDB {
    
     /**
     * URL de conexión a la base de datos MySQL.
     * Incluye el host (127.0.0.1), puerto (3306), nombre de la base de datos (RRHH)
     * y desactiva SSL con el parámetro useSSL=false.
     */
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/RRHH?useSSL=false";
    private static final String USUARIO = "accesodatos";
    private static final String PASS = "A1b2c3d4.";
    
    /**
     * Establece y devuelve una conexión con la base de datos MySQL.
     * 
     * <p>Este método intenta crear una conexión utilizando los parámetros
     * definidos en las constantes de la clase. Si la conexión se establece
     * correctamente, muestra un mensaje de confirmación en la consola.</p>
     * 
     * <p>En caso de error durante la conexión, imprime la traza del error
     * y devuelve null.</p>
     * 
     * @return Connection objeto de conexión a la base de datos si tiene éxito,
     *         null si ocurre algún error durante la conexión
     * 
     * @see java.sql.DriverManager#getConnection(String, String, String)
     * @see java.sql.Connection
     * 
     * @throws SQLException si ocurre un error de acceso a la base de datos,
     *         aunque esta excepción es capturada internamente
     */
    public static Connection conectar(){
        Connection con = null;
    
    try{
        con = DriverManager.getConnection(URL, USUARIO, PASS);
        System.out.println("Conectado correctamente con la base de datos.");
        
    } catch (SQLException e){
        e.printStackTrace();
    }
    return con;
    } 
}
