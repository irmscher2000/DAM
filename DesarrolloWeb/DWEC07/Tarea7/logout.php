<?php
/**
 * Eugen Moga
 * Cierre de sesión del usuario y redirección a la página de login.php
 */

// Inicio sesión 
session_start();

// Destruyo la sesión para cerrar la sesión del usuario
session_destroy();

// Redirijo al usuario a la página de login
header("Location: login.php");
exit();
?>