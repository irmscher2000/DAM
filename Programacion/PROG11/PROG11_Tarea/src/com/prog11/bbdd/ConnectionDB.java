
// Eugen Moga
// PROG11_Tarea

package com.prog11.bbdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


// Clase donde gestiono la conexion con la base de datos 
public class ConnectionDB {
    
    // Variables y datos para establecer la conexion 
    private static final String URL = "jdbc:mariadb://localhost:3306/Concesionario";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    private Connection conexion;
    
    public void openConnection() throws SQLException{
        this.conexion = DriverManager.getConnection(URL, USER, PASS);
    }
    
    public void closeConnection() throws SQLException{
        this.conexion.close();
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
}
