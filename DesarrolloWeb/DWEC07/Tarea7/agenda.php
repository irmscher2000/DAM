<?php
// Inicio la sesion para poder guardar los datos 
session_start();


// Verificar que el usuario ha iniciado sesión, si no lo ha hecho, redirigirlo a login.php
if (!isset($_SESSION['id'])) {
    header("Location: login.php");
    exit();
}

// Conecto con la base de datos
require "conexion.php";
$conexion = conectar("contactos", "root", "eugen");

// Variables para guardar el mensaje que se mostrara al usuario
$mensaje = '';

// Proceso los datos del formulario
if ($_SERVER['REQUEST_METHOD'] === 'POST'){

    // Recojo los valores enviados por el formulario
    $nombre = trim($_POST['nombre']);
    $email = trim($_POST['email']);

    // Validacion 1: Nombre es obligatorio
    if ($nombre === ''){
        $mensaje = 'Error: El nombre es obligatorio.';

    }else{

        // Compruebo si el nombre existe
        $stmt = $conexion->prepare("SELECT * FROM contactos WHERE Nombre = ?");
        $stmt->execute([$nombre]);
        $contactoExistente = $stmt->fetch(PDO::FETCH_ASSOC);

        // Validacion 2: El nombre existe y hay correo > Actualizar
        if ($contactoExistente && $email !== ''){
            $stmt = $conexion->prepare("UPDATE contactos SET Email = ? WHERE Nombre = ?");
            $stmt->execute([$email,$nombre]);
            $mensaje = "Contacto actualizado correctamente.";

            // Validacion 3: El nombre existe y el correo esta vacio > Eliminar
        }elseif($contactoExistente && $email === ''){
            $stmt = $conexion->prepare("DELETE FROM contactos WHERE Nombre = ? ");
            $stmt->execute([$nombre]);
            $mensaje = 'Contacto eliminado correctamente.';

            // Validacion 4: El nombre no existe y hay correo > Añadir
        }elseif (!$contactoExistente && $email !== ''){
            $stmt = $conexion->prepare("INSERT INTO contactos (Nombre, Email) VALUES (?, ?)");
            $stmt->execute([$nombre, $email]);
            $mensaje = 'Contacto añadido correctamente.';
        }
    }
}

// Obtengo todos los contactos de la base de datos para mostrarlos
$stmt = $conexion->prepare("SELECT * FROM contactos ORDER BY Nombre");
$stmt->execute();
$contactos = $stmt->fetchAll(PDO::FETCH_ASSOC);

?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Agenda de Contactos</title>
    </head>
    <body>
        <h1>Agenda de Contactos</h1>

        <!-- Enlace para cerrar sesión -->
        <p><a href="logout.php">Cerrar sesión</a></p>

        <h2>Contactos</h2>

        <?php
            if ($mensaje !== ''){
                echo $mensaje;
            }
        ?>

        <?php
            // Muestro la lista de contacots
            if (empty($contactos)){
                echo '<p> No hay contactos en la agenda. </p>';
            }else {
                // Si hay contactos en la base de datos los muestro
                foreach($contactos as $contacto){
                    echo '<p>' . $contacto['Nombre'] .'-' . $contacto['Email'] . '</p>';
                }
            }
        ?>

        <h2>Nuevo Contacto</h2>

        <form action="agenda.php" method="POST">
            <label for="nombre">Nombre: </label>
            <input type="text" id="nombre" name="nombre">

            <label for="email">Email:</label>
            <input type="text" id="email" name="email">

            <input type="submit" value="Enviar">
        </form>

    </body>

</html>

