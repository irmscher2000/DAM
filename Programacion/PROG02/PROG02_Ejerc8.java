
package prog02_ejerc8;

// Importo el paquete Scanner
import java.util.Scanner;

public class PROG02_Ejerc8 {

    public static void main(String[] args) {
       
       // // Nombre el objeto "numero" de la clase Scanner
       Scanner numero = new Scanner(System.in);
        
       // Cons System.out.print imprimo por pantalla el texto para solicitar el numero de alumnos matriculados en Programacion
       System.out.println("Cuantos alumnos estan matriculados en Programación: ");
       int alumnos_PROG = numero.nextInt(); // Guaro la entrada en la variable alumnos_PROG de tipo entero
       
       System.out.println("Cuantos alumnos estan matriculados en Entornos de Desarrollo: ");
       int alumnos_ED = numero.nextInt();
       
       System.out.println("Cuantos alumnos estan matriculados en Bases de datos: ");
       int alumnos_BD = numero.nextInt();
       
       // Declaro la variable int total_Alumnos y calculo los alumnos totales que hay matriculados
       float total_Alumnos = alumnos_PROG + alumnos_ED + alumnos_BD;
       
       System.out.println("Total de alumnos matriculados: " + total_Alumnos);
       
       // Calculo los porcentajes de alumnos por modulo.
       float porcentaje_PROG = (alumnos_PROG *100) / total_Alumnos ;
       float porcentaje_ED = (alumnos_ED * 100 ) / total_Alumnos;
       float porcentaje_BD = (alumnos_BD *100 ) / total_Alumnos ;
       
       // Muestro los resultados con dos decimales utilizando printf y %.2f%%\n
       System.out.printf("Porcentaje de alumnos matriculados en Programación: %.2f%%\n", porcentaje_PROG);
       System.out.printf("Porcentaje de alumnos matriculados en Entornos de Desarrollo: %.2f%%\n", porcentaje_ED);
       System.out.printf("Porcentaje de alumnos matriculados en Bases de Datos: %.2f%%\n", porcentaje_BD);
       
       // Cierro la clase Scanner
       numero.close();
    }
    
}
