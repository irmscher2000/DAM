
package prog02_ejerc6;

// Eugen Moga    
// PROG02_Ejercicio 6

public class PROG02_Ejerc6 {

    // Defino el tipo enum para las razas de perro
    public enum RazaPerro {
        Mastín, Terrier, Samoyedo, Pekines, Husky, Galgo
    }

    public static void main(String[] args) {

        // Creo la variable var1 de tipo enumerado y le asigno un valor.
        RazaPerro var1 = RazaPerro.Galgo;

        // Creo la variable var2 de tipo enumerado y le asigno un valor.
        RazaPerro var2 = RazaPerro.Terrier;

        // Muestra por pantalla el valor obtenido de comparar ambas variables.
        System.out.println("Comparando las dos variables: " + var1.equals(var2));

        // Averiguar posicion que ocupa un determinado valor en el enumerado
        System.out.println("La posicion de la variable 1 es: " + var1.ordinal());
        System.out.println("La posicion de la variable 2 es: " + var2.ordinal());

        // Mostrar la cantidad de valores que contiene el tipo enumerado
        System.out.println("El tipo enumerado contiene " + RazaPerro.values().length + " valores: ");

        // Mostrar todos los valores del tipo enumerado.
        for (RazaPerro raza : RazaPerro.values()) {
            System.out.println(raza);
        }
    }

}
