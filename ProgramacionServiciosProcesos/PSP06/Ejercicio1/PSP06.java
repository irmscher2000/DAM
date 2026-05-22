
// Eugen Moga
// PSP Tema 6 Ejercicio 1

package psp06;

import java.util.Scanner;

/**
 * Programa que valida el nombre de usuario y un codigo de producto
 * segun los criterios del enunciado.
 * 
 * @author Moga
 */
public class PSP06 {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Scanner para leer entradas del teclado
        Scanner sc = new Scanner(System.in);

        // APARTADO 1: Bucle de validación de usuario
        System.out.println("APARTADO1: Validación Nombre de usuario");
        System.out.println("(Introduce '*' para terminar el bucle para el nombre de usuario)\n");

        // Declaro la variable fuera del bucle para poder
        // reutilizarla en la condición del while
        String usuario;

        // El bucle se repite indefinidamente hasta que el usuario escriba '*'
        while (true) {

            System.out.print("Introduce el nombre de usuario: ");
            usuario = sc.nextLine(); 

            // Condición de salida: si es '*', terminamos el bucle
            if (usuario.equals("*")) {
                System.out.println("Fin de la entrada de usuarios.\n");
                break; 
            }

            // matches() devuelve true si la cadena cumple el patrón
            // Si NO cumple (!) muestro el error
            if (!usuario.matches("[a-z][a-z0-9]{3,7}")) {
                System.out.println("El nombre de usuario no es válido.");
                System.out.println("Recuerda: 4-8 caracteres, empieza por minúscula,");
                System.out.println("solo letras minúsculas y números.\n");
            } else {
                System.out.println("Usuario " + usuario + " válido.\n");
            }
        }

        // APARTADO 2: Bucle de validación de código de producto
        System.out.println("Validacion de codigo de producto");
        System.out.println("Formato esperado: ZZZ-CC-CODIG-ADVE");
        System.out.println("ZZZ  : 3 dígitos binarios (ej: 010)");
        System.out.println("CC   : categoría TM/OT/ON/OC/RI/ES/EN");
        System.out.println("CODIG: 5 dígitos numéricos");
        System.out.println("ADVE : letra B/C/D + 3 dígitos (ej: B290)");
        System.out.println("(Introduce '*' para terminar)\n");

        String producto;

        while (true) {

            System.out.print("Introduce el código de producto: ");
            producto = sc.nextLine();

            // Condición de salida
            if (producto.equals("*")) {
                System.out.println("Fin de la entrada de productos.");
                break;
            }

            if (!producto.matches("[01]{3}-(TM|OT|ON|OC|RI|ES|EN)-[0-9]{5}-[BCD][0-9]{3}")) {
                System.out.println("Error el código de producto no tiene el formato correcto.");
                System.out.println("Ejemplo válido: 010-TM-12345-B290\n");
            } else {
                System.out.println("Código " + producto + " valido.\n");
            }
        }

        // Cierro el Scanner al terminar (buena práctica)
        sc.close();
        System.out.println("\nPrograma finalizado.");
    }
}