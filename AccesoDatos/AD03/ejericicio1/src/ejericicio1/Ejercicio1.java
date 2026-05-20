/*
 * AD03 Ejercicio 1
 */

package ejericicio1;

import java.sql.*;
import java.util.Scanner;

/**
 * Clase principal que gestiona la conexión a una base de datos Oracle 
 * y proporciona un menú para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) 
 * sobre la tabla 'departamento'.
 */
public class Ejercicio1 {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USUARIO = "accesodatos";
    private static final String PASS = "A1b2c3d4.";
    
    /**
     * Establece y devuelve una conexión activa a la base de datos Oracle.
     * * @return Un objeto Connection si la conexión es exitosa; null en caso de error.
     */
    private static Connection conectar(){
        try{
            System.out.println("Intentando conectar a: " + URL);
            return DriverManager.getConnection(URL, USUARIO, PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * Punto de entrada principal de la aplicación. 
     * Se configura el menú interactivo para gestionar los departamentos.
     * * @param args No hay argumentos en linea de comandos
     */
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        
        Connection con = conectar();
        
        if (con == null){
            System.out.println("No se puede conectar con la base de datos. ");
            return; // Termina la aplicación si la conexión falla.
        }
        
        int opcion;
        
        do{
            System.out.println("\nOpciones:");
            System.out.println("1. Listar la información de los departamentos.");
            System.out.println("2. Añadir un departamento.");
            System.out.println("3. Modificar el nombre de un departamento.");
            System.out.println("4. Modificar la ciudad de un departamento.");
            System.out.println("5. Borrar un departamento.");
            System.out.println("0. Salir");
            System.out.print("Seleccione la opción y pulse Enter: ");
            
            opcion = Integer.parseInt(sc.nextLine());
            
            switch (opcion){
                case 1 -> listarDepartamentos(con);
                case 2 -> agregarDepartamento(con, sc);
                case 3 -> modificarNombre(con, sc);
                case 4 -> modificarCiudad(con, sc);
                case 5 -> eliminarDepartamento(con, sc);
                case 0 -> System.out.println("Cerrando el programa...");
                default -> System.out.println("Opcion no válida.");
                
            }
        
        }while (opcion !=0);
        
        // Cierra la conexión a la base de datos al salir del bucle.
        try { con.close();} catch (SQLException ignored){}    
    }
    
    /**
     * Recupera y muestra en consola todos los registros de la tabla 'departamento'.
     * Se utiliza un Statement para ejecutar la consulta.
     * * @param con Conexión activa a la base de datos.
     */
    private static void listarDepartamentos(Connection con){
        String sql = "SELECT numero, nombre, ciudad FROM departamento ORDER BY numero";
        
        // Uso de try-with-resources para asegurar el cierre automático de Statement y ResultSet.
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            // Impresión del encabezado de la tabla con formato alineado.
            System.out.println("\n");
            System.out.printf("%-10s %-20s %-20s%n", "CODIGO", "NOMBRE", "CIUDAD");
            System.out.println("-----------------------------------------------");
            
            // Itera sobre los resultados y los imprime en formato tabulado.
            while (rs.next()){
                System.out.printf("%-10d %-20s %-20s%n",
                        rs.getInt("numero"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"));
            }
        } catch (SQLException e){
            System.out.println("Error al listar: " + e.getMessage());
        }
    }
    
    /**
     * Solicita datos al usuario y añade un nuevo registro a la tabla 'departamento'.
     * Utiliza PreparedStatement para prevenir inyección SQL.
     * * @param con Conexión activa a la base de datos.
     * @param sc Objeto Scanner para leer la entrada del usuario.
     */
    private static void agregarDepartamento(Connection con, Scanner sc){
        try {
            System.out.println("Ingresa el código: ");
            int codigo = Integer.parseInt(sc.nextLine());
            
            System.out.println("Ingresa el nombre: ");
            String nombre = sc.nextLine();
            
            System.out.println("Ingresa la ciudad: ");
            String ciudad = sc.nextLine();
            
            String sql = "INSERT INTO departamento (numero, nombre, ciudad) VALUES (?, ?, ?)";
            
            // Prepara la sentencia SQL con placeholders (?).
            PreparedStatement ps = con.prepareStatement(sql);
            
            // Asigna los valores a los placeholders.
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, ciudad);
            
            // Ejecuta la inserción.
            ps.executeUpdate();
            System.out.println("Departamente añadido correctamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al añadir departamento: " + e.getMessage());
        }
    }
    
    /**
     * Permite al usuario seleccionar un departamento por código y modificar su nombre.
     * Utiliza PreparedStatement para la actualización.
     * * @param con Conexión activa a la base de datos.
     * @param sc Objeto Scanner para leer la entrada del usuario.
     */
    private static void modificarNombre(Connection con, Scanner sc){
        try {
            System.out.println("Selecciona el código del departamento que deseas modificar el nombre: ");
            
            listarDepartamentos(con);  // Muestra la lista para que el usuario elija.
            
            System.out.println("Indica el código: ");
            int codigo = Integer.parseInt(sc.nextLine());
            
            System.out.println("Vas a modificar el departamento: " + codigo);
            System.out.println("Indica el nuevo nombre: ");
            String nuevoNombre = sc.nextLine();
            
            String sql = "UPDATE departamento SET nombre=? WHERE numero=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setInt(2, codigo);
            
            ps.executeUpdate();
            System.out.println("Nombre departamento actualizado");
            
        } catch (SQLException e){
            System.out.println("Error al actulizar: " + e.getMessage());
        }
    }
    
    /**
     * Permite al usuario seleccionar un departamento por código y modificar su ciudad.
     * Utiliza PreparedStatement para la actualización.
     * * @param con Conexión activa a la base de datos.
     * @param sc Objeto Scanner para leer la entrada del usuario.
     */
    private static void modificarCiudad(Connection con, Scanner sc){
        try {
            System.out.println("Selecciona el código del departamento que deseas modificar la ciudad: ");
            
            listarDepartamentos(con);
            
            System.out.println("Indica el código: ");
            int codigo = Integer.parseInt(sc.nextLine());
            
            System.out.println("Vas a modificar el departamento: " + codigo);
            System.out.println("Indica la nueva ciudad: ");
            String nuevaCiudad = sc.nextLine();
            
            
            String sql = "UPDATE departamento SET ciudad=? WHERE numero=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevaCiudad);
            ps.setInt(2, codigo);
            
            
            int filas = ps.executeUpdate();
            System.out.println("Nombre de la ciudad actualizado. Filas afectadas: " + filas);
        } catch (SQLException e) {
            System.out.println("Error al actualizar la ciudad. " + e.getMessage());
        }
    }
    
    /**
     * Permite al usuario seleccionar un departamento por código para eliminarlo de la base de datos.
     * Utiliza PreparedStatement para la eliminación.
     * * @param con Conexión activa a la base de datos.
     * @param sc Objeto Scanner para leer la entrada del usuario.
     */
    private static void eliminarDepartamento(Connection con, Scanner sc){
        try {
            System.out.println("Selecciona el código del departamento que deseas eliminar: ");
            
            listarDepartamentos(con);
            
            System.out.println("Indicar el código: ");
            int codigo = Integer.parseInt(sc.nextLine());
            
            String sql = "DELETE FROM departamento WHERE numero=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, codigo);
            
            ps.executeUpdate();
            
            System.out.println("Departamento  eliminado");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
