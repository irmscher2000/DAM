package prog02_ejerc4;

// Importo la clase scanner para pedir datos por teclado
import java.util.Scanner;

public class PROG02_Ejerc4 {

    public static void main(String[] args) {
        // Inicio el objeto año para pedir datos al usuario
        Scanner año = new Scanner ( System.in);
        
        // Se pregunta al usuario por la edad
        System.out.print("¿Cuantos años tienes?: ");
        
        // Pedir al usuario que ingresa su edad
        int edad = año.nextInt();
        
        // Mostrar si esta jubilado o no con el operador ternario
        String mensaje = (edad >= 65) ? "Estas jubilado. " : "No estas jubilado. ";
        
        // Imprime el mensaje
        System.out.println(mensaje);
        
        //Cierro el objeto año.
        año.close();
        
    }
    
}
