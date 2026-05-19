package PROG06_Ejer1;
// Eugen Moga  
// Prog06 Ejercicio 1

import java.util.Arrays;
public class Ejercicio01 {
    public static void main(String[] args) {
        
        // Declaro el array para que guarde 10 posiciones
        int[]num = new int[10];
        
        for (int i = 0; i < 10; i++){
        /**
         * Array num guarda cada posicion de i hasta 10 numeros
         * Math.random() genera un numero aleatorio entre 0 y 100
         * Multiplico por el numero maximo menos el numero minimo mas uno y le sumo 50
         */
        num[i] = (int) (Math.random() * (100 - 50 + 1)) + 50;
        }
        // Arrays original 
        System.out.println("Numeros generados: " + Arrays.toString(num));
        
        // Arrays ordenado en orden creciente
        Arrays.sort(num);
        System.out.println("Numeros en orden creciente: " + Arrays.toString(num));
        
    }
    
}
