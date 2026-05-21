<?php
/**
 * Eugen Moga
 * Formulario de inicio de sesión para la agenda de contactos
 */

// Inicio sesion para manejar el estado de autenticación
session_start();

// Si el usuario ya ha iniciado sesión, lo redirijo a la agenda.php
if (isset($_SESSION['id'])){
    header("Location: agenda.php");
    exit();
}

// Incluyo el archivo de conexion para conectar con la base de datos
require "conexion.php";

// Variables para guardar el mensaje de error o exito
$mensaje = '';

// Proceso los datos del formulario de inicio de sesión
if ($_SERVER['REQUEST_METHOD'] === 'POST'){

    // Recojo los valores enviados por el formulario
    $email = trim($_POST['email']);
    $password = ($_POST['password']);

    // Validacion 1: Usuario y contraseña son obligatorios
    if ($email === '' || $password === ''){
        $mensaje = 'Introduce todos los datos.';

    }else{
        try{

            // Conecto con la base de datos
            $conexion = conectar("contactos", "root", "eugen");

            // Busco el usuario en la base de datos
            $stmt = $conexion->prepare("SELECT * FROM usuarios WHERE email = ?");
            $stmt->execute([$email]);
            $usuarioExistente = $stmt->fetch(PDO::FETCH_ASSOC);

            if (!$usuarioExistente){
                $mensaje = 'El email no se encuentra en la base de datos.';
            } else{
                // Verifico la contraseña con password_verify
                if (password_verify($password, $usuarioExistente['password_hash'])){
                    // Contraseña correcta, gaurdo datos en sesión 
                    $_SESSION['id'] = $usuarioExistente['id'];
                    $_SESSION['email'] = $usuarioExistente['email'];

                    // Redirijo a la agenda
                    header("Location: agenda.php");
                    exit();

                } else {
                    $mensaje = 'Contraseña incorrecta.';
                }
            }

        }catch (PDOException $e){
            $mensaje = 'Error en la conexión: ' . $e->getMessage();
        }
    }
}
?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
    </head>

    <body>

        <?php
            if ($mensaje !== ''){
                echo $mensaje;
            }
        ?>


        <h1>Login</h1>

        <h2>Introduce tu email y contraseña</h2>

        <form action="login.php" method="POST">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password">

            <input type="submit" value="Enviar">
        </form>


    </body>

</html>