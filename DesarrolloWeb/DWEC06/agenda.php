<?php
// Inicio la sesion para poder guardar los datos 
session_start();


// Compruebo si el array contactos existe y si no lo creo
if(!isset($_SESSION['contactos'])){
    $_SESSION['contactos'] = [];
}

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
        $existe = array_key_exists($nombre, $_SESSION['contactos']);

        // Validacion 2: El nombre existe y hay correo > Actualizar
        if ($existe && $email !== ''){
            $_SESSION['contactos'][$nombre] = $email;
            $mensaje = 'Contacto actualizado correctamente.';

            // Validacion 3: El nombre existe y el correo esta vacio > Eliminar
        }elseif ($existe && $email === ''){
            unset($_SESSION['contactos'][$nombre]);
            $mensaje = 'Contacto eliminado correctamente.';

            // Validacion 4: El nombre no existe y hay correo > Añadir
        }elseif (!$existe && $email !== ''){
            $_SESSION['contactos'][$nombre] = $email;
            $mensaje = 'Contacto añadido correctamente.';
        }
    }
}
?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Agenda de Contactos</title>
    </head>
    <body>
        <h1>Agenda de Contactos</h1>

        <h2>Contactos</h2>

        <?php
            if ($mensaje !== ''){
                echo $mensaje;
            }
        ?>

        <?php
            // Compruebo si el array contactos esta vacio
            if (empty($_SESSION['contactos'])){
                echo '<p> No hay contactos en la agenda. </p>';
            }else {
                // Si hay contactos recorro el array y los muestro
                foreach($_SESSION['contactos'] as $nombre => $email){
                    echo '<p>' . $nombre .'-' . $email . '</p>';
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

