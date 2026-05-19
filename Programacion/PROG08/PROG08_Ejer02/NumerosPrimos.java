
// Eugen Moga
// PROG08 Ejercicio 2

package PROG08_Ejer02;

public class NumerosPrimos {
    
    // Método que comprueba si el numero es primo
    public static boolean esPrimo(int numero){
        if(numero < 2){
            return false;
        }
        for (int i = 2; i * i <= numero; i++){
            if(numero % i == 0){
                return false;
            }
        }
        return true;
    }
    
    
}
