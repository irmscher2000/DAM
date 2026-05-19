
// Eugen Moga
// PROG11_Tarea

package com.prog11.bbdd;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropietariosDAO {
    
    /**
     * Inserta un nuevo propietari en la base de datos
     * @param dni DNI del propietario a insertar
     * @param nombre Nombre del propietario
     * @param conexion Conexion activa con la BBDD
     * @return 0 si tiene exito, -1 si da error
     */
    public static int insertarPropietario(ConnectionDB conexion, String dni, String nombre){
        
        try{
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                "INSERT INTO propietarios (DNI, nombre)" + 
                " VALUES (?, ?)");
            
            st.setString(1, dni);
            st.setString(2, nombre);
            
            st.executeUpdate();
            
            st.close();
            conexion.closeConnection();
            return 0;
        }catch (SQLException e){
            System.out.println("Error al insertar propietario: " + e.getMessage());
        }
        return -1;
    }
    
    /**
     *
     * Recupera los vehiculos de un propietario
     * @param dni DNI del propietario
     * @param conexion Conexion activa con la base de datos
     * @return ArrayList de vehiculos o null si hay un error
     */
    public static ArrayList<String> recuperarVehiculosPropietario(ConnectionDB conexion, String dni){
        
        try{
            ArrayList<String> vehiculos = new ArrayList<>();
        
            conexion.openConnection();
        
            PreparedStatement st = conexion.getConnection().prepareStatement(
                "SELECT v.matricula, v.marca, v.km, v.precio, v.DNI_propietario " + 
                "FROM vehiculos v JOIN propietarios p ON v.DNI_propietario = p.DNI " +  
                " WHERE p.DNI = ? ");
        st.setString(1, dni);
        ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                vehiculos.add(
                    "Matricula: " + rs.getString("matricula") +
                    ", Marca: " + rs.getString("marca") +
                    ", Km: " + rs.getInt("km") +
                    ", Precio: " + rs.getFloat("precio") +
                    ", DNI propietario: " + rs.getString("DNI_propietario")); 
            }
            
            rs.close();
            st.close();
            conexion.closeConnection();
            return vehiculos;   
        }catch(SQLException e){
            System.out.println("Error al obtener vehiculos del propietario: " + e.getMessage());
        }
        return null;
    }
    
    
    /**
     * Elimina el propietario de la base de datos
     * @param dni DNI del propietario que se desea eliminar
     * @param conexion Conexion activa con la BBDD
     * @return  Número de registros aliminados
     */
    public static int eliminarPropietario(ConnectionDB conexion, String dni){

            String actualizarSql = "UPDATE vehiculos SET DNI_propietario = NULL WHERE DNI_propietario = ? ";
            try{
                conexion.openConnection();
                
                // Primero pongo el propietario en null en los vehiculos para poder eliminar el propietario
                try(PreparedStatement actualizarSt = conexion.getConnection().prepareStatement(actualizarSql)){
                    actualizarSt.setString(1, dni);
                    actualizarSt.executeUpdate();
                }
            
            // Elimino el propietario
            String sql = "DELETE FROM propietarios WHERE DNI = ? ";
                try (PreparedStatement st = conexion.getConnection().prepareStatement(sql)) {
                st.setString(1, dni);
                int eliminados = st.executeUpdate();
                return eliminados;
                }

            }catch (SQLException e){
                System.out.println("Error al eliminar el propietario: " + e.getMessage());
                return 0;
            } finally {
                try {
                    conexion.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(PropietariosDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }   
}
