<?php

/**
 * Eugen Moga 
 * Archivo para establecer la conexion con la base de datos usando PDO
 */

/**
 * Función que establece la conexion con la base de datos
 */
function conectar($database, $user, $pass){
    try{
        // Cadena de conexion DSN 
        $dsn = "mysql:host=localhost;dbname=$database;charset=utf8mb4";

        // Creo la conexion
        $conexion = new PDO($dsn, $user, $pass);

        return $conexion;

    }catch (PDOException $e){
        die("Error de conexión a la base de datos: " .$e->getMessage());
    }
}
?>
