/*
 * AD03 Ejercicio 1
 */

package ejercicio2;

import java.util.Scanner;
import java.sql.*;

/**
 * Clase principal que gestiona la información de empleados en la base de datos RRHH.
 * Proporciona un menú interactivo para listar, mostrar, modificar y borrar empleados.
 * 
 * @author Eugen Moga
 * @version 1.0
 */
public class ejercicio2 {

    /**
     * Método principal que ejecuta el programa de gestión de empleados.
     * Establece la conexión con la base de datos y presenta un menú de opciones
     * al usuario para realizar operaciones CRUD sobre los empleados.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     * @throws SQLException si ocurre un error durante las operaciones de base de datos
     */
    public static void main(String[] args) throws SQLException {
        
        Connection con = ConexionDB.conectar();
        
        Scanner sc = new Scanner(System.in);
        
        if (con == null){
            System.out.println("No se puede conectar a la base de datos.");
            return;
        }
        
        int opcion;
        
        do{
            System.out.println("\nOpciones: ");
            System.out.println("1. Listar la información de los empleados.");
            System.out.println("2. Mostrar la información de un empleado.");
            System.out.println("3. Modificar los datos de un empleado.");
            System.out.println("4. Borrar un empleado.");
            System.out.println("0. Salir.");
            System.out.print(" Seleccione la opción y pulse Enter.\n");
            
            opcion = Integer.parseInt(sc.nextLine());
            
            switch (opcion){
                case 1 -> listarEmpleados(con);
                case 2 -> mostrarEmpleado(con, sc);
                case 3 -> modificarEmpleado(con, sc);
                case 4 -> borrarEmpleado(con, sc);
                case 0 -> System.out.println("Cerrando el programa...");
                default -> System.out.println("Opcion no válida.");
            }
            
        }while (opcion !=0);
        
        sc.close();
        con.close();
        
    }
       
    /**
     * Lista todos los empleados de la base de datos ordenados por nombre.
     * Muestra la información en formato de tabla con las columnas:
     * número, nombre, empleo y sueldo.
     * 
     * @param con conexión activa a la base de datos
     */
    private static void listarEmpleados(Connection con){
        
        String sql = "SELECT numero, nombre, empleo, sueldo FROM empleado ORDER BY nombre";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            System.out.printf("%-8s %-12s %-12s %-10s%n", "NUM", "NOMBRE", "EMPLEO", "SUELDO");
            System.out.println("------------------------------------------");
            
            while (rs.next()){
                System.out.printf("%-8d %-12s %-12s %-10.2f%n",
                        rs.getInt("numero"),
                        rs.getString("nombre"),
                        rs.getString("empleo"),
                        rs.getFloat("sueldo"));
            } 
        } catch (SQLException e){
            System.out.println("Error al listar empleados.");
            e.printStackTrace();
        }
    }
    
    /**
     * Muestra la información completa de un empleado específico.
     * Solicita al usuario el número de empleado y muestra todos sus datos
     * en formato registro (un dato por línea).
     * 
     * @param con conexión activa a la base de datos
     * @param sc objeto Scanner para leer la entrada del usuario
     * @throws SQLException si ocurre un error durante la consulta
     */
    private static void mostrarEmpleado(Connection con, Scanner sc) throws SQLException{
        
        System.out.print("Número de empleado: ");
        int numero = Integer.parseInt(sc.nextLine());
        
        String sql = "SELECT * FROM empleado WHERE numero=?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                System.out.println("Número: " + rs.getInt("numero"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Empleo: " + rs.getString("empleo"));
                System.out.println("Jefe: " + rs.getInt("jefe"));
                System.out.println("Fecha entrada: " + rs.getDate("fechaEntrada"));
                System.out.println("Sueldo: " + rs.getInt("sueldo"));
                System.out.println("Complemento: " + rs.getFloat("complemento"));
                System.out.println("Departamento: " + rs.getInt("numeroDepartamento"));
            } else {
                System.out.println("Empleado no encontrado");
            }
            rs.close();
        }
        
    }
    
    /**
     * Modifica los datos de un empleado existente.
     * Solicita el número de empleado y permite modificar cada campo.
     * Si el usuario pulsa Enter sin introducir un valor, el campo mantiene su valor actual.
     * 
     * @param con conexión activa a la base de datos
     * @param sc objeto Scanner para leer la entrada del usuario
     * @throws SQLException si ocurre un error durante la actualización
     */
    public static void modificarEmpleado(Connection con, Scanner sc) throws SQLException{
        
        System.out.print("Número de empleado: ");
        int numero = Integer.parseInt(sc.nextLine());
        
        String select = "SELECT * FROM empleado WHERE numero=?";
        
        String update = """
                UPDATE empleado SET
                nombre=?, empleo=?, jefe=?, fechaEntrada=?, 
                sueldo=?, complemento=?, numeroDepartamento=?
                WHERE numero=?
                        """;
        
        try (PreparedStatement psSelect = con.prepareStatement(select)){
            psSelect.setInt(1, numero);
            ResultSet rs = psSelect.executeQuery();
            
            if (!rs.next()){
                System.out.println("Empleado no encontrado.");
                return;
            }
            
            System.out.print("Nombre (" + rs.getString("nombre") + "): ");
            String nombre = sc.nextLine();
            if (nombre.isEmpty()) nombre = rs.getString("nombre");
            
            System.out.print("Empleo (" + rs.getString("empleo") + "): ");
            String empleo = sc.nextLine();
            if (empleo.isEmpty()) empleo = rs.getString("empleo");
            
            System.out.print("Jefe (" + rs.getInt("jefe") + "): ");
            String jefeStr = sc.nextLine();
            int jefe = jefeStr.isEmpty() ? rs.getInt("jefe") : Integer.parseInt(jefeStr);
            
            System.out.print("Fecha entrada (" + rs.getDate("fechaEntrada") + "): ");
            String fechaStr = sc.nextLine();
            Date fecha = fechaStr.isEmpty() ? rs.getDate("fechaEntrada") : Date.valueOf(fechaStr);
            
            System.out.print("Sueldo (" + rs.getFloat("sueldo") + "): ");
            String sueldoStr = sc.nextLine();
            float sueldo = sueldoStr.isEmpty() ? rs.getFloat("sueldo") : Float.parseFloat(sueldoStr);
            
            System.out.print("Complemento (" + rs.getFloat("complemento") + "): ");
            String complementoStr = sc.nextLine();
            float complemento = complementoStr.isEmpty() ? rs.getFloat("complemento") : Float.parseFloat(complementoStr);
            
            System.out.print("Departamento (" + rs.getInt("numeroDepartamento") + "): ");
            String departamentoStr = sc.nextLine();
            int departamento = departamentoStr.isEmpty() ? rs.getInt("numeroDepartamento") : Integer.parseInt(departamentoStr);
            
            PreparedStatement psUpdate = con.prepareStatement(update);
            psUpdate.setString(1, nombre);
            psUpdate.setString(2, empleo);
            psUpdate.setInt(3, jefe);
            psUpdate.setDate(4, fecha);
            psUpdate.setFloat(5, sueldo);
            psUpdate.setFloat(6, complemento);
            psUpdate.setInt(7, departamento);
            psUpdate.setInt(8, numero);
            
            psUpdate.executeUpdate();
            System.out.println("Empleado actualizado correctamente.");
            
            rs.close();
            psUpdate.close();
        }
    }
    
    /**
     * Elimina un empleado de la base de datos.
     * Solicita el número de empleado y procede a borrarlo de la tabla.
     * Informa si el empleado fue eliminado o si no se encontró.
     * 
     * @param con conexión activa a la base de datos
     * @param sc objeto Scanner para leer la entrada del usuario
     * @throws SQLException si ocurre un error durante la eliminación
     */
    private static void borrarEmpleado(Connection con, Scanner sc) throws SQLException{
        System.out.print("Número de empleado a borrar: ");
        
        int numero = Integer.parseInt(sc.nextLine());
        
        String sql = "DELETE FROM empleado WHERE numero=?";
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, numero);
            
            int filas = ps.executeUpdate();
            
            if (filas > 0){
                System.out.print("Empleado eliminado.");
            } else {
                System.out.print("Empleado no encontrado");
            }
                
        }
    }
}
