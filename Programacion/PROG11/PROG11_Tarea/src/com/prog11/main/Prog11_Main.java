
// Eugen Moga
// PROG11_Tarea

package com.prog11.main;

import com.prog11.bbdd.ConnectionDB;
import com.prog11.bbdd.PropietariosDAO;
import com.prog11.bbdd.VehiculosDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Prog11_Main {

    public static void main(String[] args) throws SQLException {
        
        ArrayList<String> datos = new ArrayList<>();
        ConnectionDB conexion = new ConnectionDB();
        
        // Inserto usuario
        System.out.println("\nInsertar varios vehículos y propietarios.");
        if(PropietariosDAO.insertarPropietario(conexion, "12345678D", "Fran") == 0){
            System.out.println("Propietario Fran insertado correctamente");
        } else{
            System.out.println("Propietario  Fran no se ha insertado correctamente");
        }
        
        // Inserto usuario
        if(PropietariosDAO.insertarPropietario(conexion, "12345678E", "Raul") == 0){
            System.out.println("Propietario Raul insertado correctamente");
        } else{
            System.out.println("Propietario  Raul no se ha insertado correctamente");
        }
        
        // Inserto vehiculo
        if(VehiculosDAO.insertarVehiculo(conexion, "ABC1119", "Audi", "A4", 60500, 12300, "12345678E") == 0){
            System.out.println("Vehiculo con matricula ABC1119 se ha insertado correctamente.");
        }else {
            System.out.println("Vehiculo con matricula ABC1119 no se ha insertado correctamente.");
        }
        
        // Inserto vehiculo
        if(VehiculosDAO.insertarVehiculo(conexion, "ABC1120", "Audi", "Q5", 40500, 22900, "") == 0){
            System.out.println("Vehiculo con matricula ABC1120 se ha insertado correctamente.");
        }else {
            System.out.println("Vehiculo con matricula ABC1120 no se ha insertado correctamente.");
        }
        
        
        System.out.println("\nListar todos los vehículos.");
        datos = VehiculosDAO.recuperarTodosLosVehiculos(conexion);
        
        for (String dato : datos){
            System.out.println(dato);
        }
        
        
        System.out.println("\nActualizar propietario de un vehículo.");
        if(VehiculosDAO.actualizarPropietarioVehiculo(conexion, "ABC1111", "12345678B") == 0){
            System.out.println("Vehiculo con matricula ABC1111 ha cambiado de propietario ahora es del propietario DNI 12345678B");
        }else {
            System.out.println("Vehiculo con matricula ABC1111 no se ha cambiado de propietario ");
        }
        
        
        System.out.println("\nListar todos los vehículos.");
        datos = VehiculosDAO.recuperarTodosLosVehiculos(conexion);
        
        for (String dato : datos){
            System.out.println(dato);
        }    
        
        
        System.out.println("\nEliminar un vehículo que exista.");
        if(VehiculosDAO.eliminarVehiculo(conexion, "ABC1111") == 0){
            System.out.println("Vehiculo con matricula ABC1111 se ha eliminado. ");
        } else {
            System.out.println("Vehiculo con matricula ABC1111 no se ha eliminado. ");
        }
        
        
        System.out.println("\nEliminar un vehículo que no exista.");
        if(VehiculosDAO.eliminarVehiculo(conexion, "AAA1111") == 0){
            System.out.println("Vehiculo con matricula AAA1111 se ha eliminado. ");
        } else {
            System.out.println("Vehiculo con matricula AAA1111 no existe. ");
        }
        
        
        System.out.println("\nListar todos los vehículos.");
        datos = VehiculosDAO.recuperarTodosLosVehiculos(conexion);
        
        for (String dato : datos){
            System.out.println(dato);
        }
        
        
        System.out.println("\nListar los vehículos de una marca.");
        datos = VehiculosDAO.recuperarVehiculosPorMarca(conexion, "Toyota");
        
        for (String dato : datos){
            System.out.println(dato);
        }
        
        
        System.out.println("\nListar todos los vehículos de un propietario.");
        datos = PropietariosDAO.recuperarVehiculosPropietario(conexion, "12345678A");
        for (String dato : datos){
            System.out.println(dato);
        }
        
        
        System.out.println("\nEliminar un propietario con vehículos.");
        if(PropietariosDAO.eliminarPropietario(conexion, "12345678A") != 0){
            System.out.println("Se ha eliminado el propietario con dni 12345678A");
        } else {
            System.out.println("No se ha eliminado el propietario con dni 12345678A");
        }
        
        
        System.out.println("\nEliminar un propietario sin vehículos.");
        if(PropietariosDAO.eliminarPropietario(conexion, "12345678D") != 0){
            System.out.println("Se ha eliminado el propietario con DNI 12345678D");
        } 
    }
    
}
