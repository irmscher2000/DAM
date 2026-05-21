<?php

// Se recogen los datos enviados desde el formulario
$nombre = $_POST['nombre'];
$email = $_POST['email'];

// Conectar con la base de datos
$conexion = new mysqli("localhost","root","admin123","usuariosdb");

// Se insertan los datos en la tabla usuarios
$conexion->query("INSERT INTO usuarios (nombre, email) VALUES ('$nombre','$email')");

// Se muestra el mensaje de confirmacion al usuario
echo "Datos guardados correctamente";

?>

