
// Eugen Moga
// PROG11_Tarea

package com.prog11.bbdd;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Clase DAO para realizar las operaciones con la tabla Vehiculos
 * 
 */
public class VehiculosDAO {
   
    /**
     * Inserta un nuevo vehiculo en la base de datos 
     * @param matricula
     * @param marca
     * @param modelo
     * @param km
     * @param precio
     * @param dniPropietario DNI del propietario, puede ser null si el coche no esta vendido
     * @param conexion
     * @return 0 si tiene exito, -1 si da error
     */
    public static int insertarVehiculo(ConnectionDB conexion, String matricula, String marca, String modelo, int km, float precio, String dniPropietario){
        
        try{
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "INSERT INTO Vehiculos (Matricula, Marca, Modelo, km, precio, DNI_propietario)" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            
            st.setString(1, matricula);
            st.setString(2, marca);
            st.setString(3, modelo);
            st.setInt(4, km);
            st.setFloat(5, precio);
            
            // Manejo explicito del valor NULL para DNI_propietario
            if(dniPropietario == null || dniPropietario.trim().isEmpty()){
                st.setNull(6, java.sql.Types.VARCHAR);
            }else {
                st.setString(6, dniPropietario);
            }
            
            st.executeUpdate();
            
            st.close();
            conexion.closeConnection();
            return 0;
        }catch(SQLException e){
            System.out.println("Error al insertar vehiculo: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Método para actualizar el propietario de un vehiculo indicando su DNI
     * @param matricula 
     * @param dniPropietario DNI del nuevo propietario 
     * @param conexion
     * @return devuleve 0 si es correcto y -1 si obtine un error
     */
    public static int actualizarPropietarioVehiculo(ConnectionDB conexion, String matricula, String dniPropietario){
        
        try{
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "UPDATE vehiculos " + 
                    "SET DNI_propietario = ? " +
                    "WHERE matricula = ?");
            st.setString(1, dniPropietario);
            st.setString(2, matricula);
            
            int actualizado = st.executeUpdate();
            
            
            
            if (actualizado == 0){
                return -1;
            }
            
            st.close();
            conexion.closeConnection();
            return 0;
        }catch (SQLException e){
            System.out.println("Error al actualizar propietario del vehiculo: " + e.getMessage());
            return -1;
        }
    }
    
    
    /**
     * Metodo para eliminar un vehiculo indicando la matricula
     * @param conexion
     * @param matricula
     * @return 
     */
    public static int eliminarVehiculo(ConnectionDB conexion, String matricula){
        
        try{
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "DELETE FROM vehiculos " +
                    "WHERE matricula = ? ");
            st.setString(1, matricula);
            
            int borrado = st.executeUpdate();
            
            if(borrado == 0){
                System.out.println("No se encontro el vehiculo con matricula: " + matricula);
                return -1;
            }
            
            st.close();
            conexion.closeConnection();
            return 0;
        }catch (SQLException e){
            System.out.println("Error al eliminar el vehiculo. " + e.getMessage());
        }
        return -1;
    }
    
    
    /**
     * Metodo para ver todos los vehiculos del concesionario sin pasarle parametros
     * @param conexion 
     * @return 
     */
    public static ArrayList<String> recuperarTodosLosVehiculos(ConnectionDB conexion){
        
        try{
            ArrayList<String> vehiculos = new ArrayList<>();
            
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "SELECT v.matricula, v.marca, v.modelo, v.km, v.precio, v.DNI_propietario, p.nombre, p.DNI " + 
                    "FROM vehiculos v " +
                    "LEFT JOIN propietarios p ON v.DNI_propietario = p.DNI "
                    );
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                vehiculos.add("Matricula: " + rs.getString("matricula") +
                                ", marca: " + rs.getString("marca") +
                                ", modelo: " + rs.getString("modelo") +
                                ", Km: " + rs.getInt("km") +  
                                ", precio: " + rs.getFloat("precio") +
                                ", propietario: " + rs.getString("nombre"));
            }
            
            conexion.closeConnection();
            return vehiculos;
        }catch (SQLException e){
            System.out.println("Error al recuperar todos los vehiculos." + e.getMessage());
        }
        return null;
    }
    
    
    /**
     * Recupera vehiculos por marca 
     * @param conexion
     * @param marca
     * @return 
     */
    public static ArrayList<String> recuperarVehiculosPorMarca(ConnectionDB conexion, String marca){
        
        try{
            ArrayList<String> vehiculos = new ArrayList<>();
            
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "SELECT v.matricula, v.marca, v.modelo, v.km, v.precio, v.DNI_propietario, p.nombre, p.DNI " + 
                    "FROM vehiculos v " + 
                    "LEFT JOIN propietarios p ON v.DNI_propietario = p.DNI " +
                    "WHERE v.marca = ? "        
                    );
            
            st.setString(1, marca);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                vehiculos.add("Matricula: " + rs.getString("matricula") +
                                ", marca: " + rs.getString("marca") +
                                ", modelo: " + rs.getString("modelo") +
                                ", Km: " + rs.getInt("km") +  
                                ", precio: " + rs.getFloat("precio") +
                                ", propietario: " + rs.getString("nombre"));
            }
            
            conexion.closeConnection();
            return vehiculos;
        }catch (SQLException e){
            System.out.println("Error al recuperar todos los vehiculos." + e.getMessage());
        }
        return null;
    }
    
    
    public static ArrayList<String> recuperarVehiculos(ConnectionDB conexion){
        
        try{
            ArrayList<String> vehiculos = new ArrayList<>();
            
            conexion.openConnection();
            
            PreparedStatement st = conexion.getConnection().prepareStatement(
                    "SELECT v.matricula, v.marca, v.modelo, v.km, v.precio, v.DNI_propietario " + 
                    "FROM vehiculos v " 
                    );
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                vehiculos.add("Matricula: " + rs.getString("matricula") +
                                ", marca: " + rs.getString("marca") +
                                ", Km: " + rs.getInt("km") +  
                                ", precio: " + rs.getFloat("precio"));
            }
            
            conexion.closeConnection();
            return vehiculos;
        }catch (SQLException e){
            System.out.println("Error al recuperar todos los vehiculos." + e.getMessage());
        }
        return null;
    }
    
    
}
