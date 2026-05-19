
package prog02_ejerc11;

// Eugen Moga   
// PROG02_Ejercicio 11

public class PROG02_Ejerc11 {

    public static void main(String[] args) {
        
        // Declaro las variables de tipo char para los caracteres B y K
        char caracter1 = 'B';
        char caracter2 = 'K';
        
        // Paso las variables de tipo char a tipo int 
        // Al asignar un valor char a un int se obtiene el codigo unicode automaticamente.
        int unicode1 = caracter1;
        int unicode2 = caracter2;
        
        // Muestro por pantalla el resultado 
        System.out.println("La letra " + caracter1 +", corresponde al valor " + unicode1);
        System.out.println("La letra " + caracter2 + ", corresponde al valor "+ unicode2);
        
    }
    
}
