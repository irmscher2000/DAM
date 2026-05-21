<?php

// Se establece la conexion con la base de datos
$conexion = new mysqli("localhost","root","admin123","nombresdb");

// Se obtiene los datos de la base de datos 
$resultado = $conexion->query("SELECT nombre FROM nombres");

// Se muestran los datos. Fetch_assoc() convierte cada fila en un array
while($fila=$resultado->Fetch_assoc()){
    echo $fila['nombre'], "<br>";
}

?>