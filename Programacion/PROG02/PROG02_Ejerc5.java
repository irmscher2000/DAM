package prog02_ejerc5;

// Importo el paquete Scanner:
import java.util.Scanner;

public class PROG02_Ejerc5 {
    public static void main(String[] args) {
        
        // Primero creo el objeto Scanner numero para leer la entrada del usuario.
        Scanner numero = new Scanner(System.in);
        
        // Imprimo por pantalla la pregunta para solicitar los segundos
        System.out.print("Cuantos segundos quieres calcular: ");
        
        // Guardo la entrada del usuario.
        int segundosTotales = numero.nextInt();
        
        // Inicio la variable dias y divido los segundosTotales entre 86400 segundos que tiene un dia para averiguar los dias enteros
        int dias = segundosTotales / 86400;
        
        // Inicio la variable horas y le descuento los dias completos a segundosTotales.
        // El operador % lo que hace es dividir segundosTotales entre 86400 y el resultado se divide entre 3600 segundos que tiene una hora.
        // En definitiva una vez que resta los dias completos calcula las horas enteras que quedan.
        int horas = (segundosTotales % 86400) / 3600;
        
        // Inicio la variable minutos, se descuenta los dias completos y las horas completas.
        // El resultado se divide entre 60 segundos que tiene un minuto para calcular los minutos enteros que quedan
        int minutos = ((segundosTotales % 86400) % 3600) / 60;
        
        // Inicio la variable segundos y a segundosTotales se descuenta los dias completos, las horas completas, y los minutos completos.
        // El resultado que queda son los segundos que sobran.
        int segundos = ((segundosTotales % 86400) % 3600) % 60;
        
        // Imprimo por pantalla el resultado
        System.out.println("El resultado equivale a " + dias + " días, " + horas + " horas, " + minutos + " minutos y " + segundos + " segundos.");
        
        // Cierro el objeto Scanner
        numero.close();
    }
    
}
