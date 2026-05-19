
// Eugen Moga
// PROG08 Ejercicio 2

package PROG08_Ejer02;

import java.util.Scanner;
import java.util.TreeSet;

public class Principal{

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int N;
        
        // Capturo errores en caso de que el usuario ingrese algo que no sea un número entero positivo
        while(true){
            try{
                System.out.println("Ingresa un número entero positivo: ");
                N = sc.nextInt();
                if(N > 0){
                    break;
                }
                System.out.println("Error: El número debe ser mayor que 0. ");
            } catch (Exception e){
                System.out.println("Error: Debes ingresar un número entero.");
                sc.nextLine();  // Limpio el buffer del scanner
            }
        }
        
        // Uso TreeSet para almacenar y ordenar automaticamente los numeros primos
        TreeSet<Integer> primos = new TreeSet<>();
        
        // Busco numeros primos hasta N
        for (int i = 2; i <= N; i++){
            if(NumerosPrimos.esPrimo(i)){
                primos.add(i);
            }
        }
        
        // Imprimo los números primos
        System.out.println("Conjunto de números primos menores o iguales a " + N + ":");
        System.out.println(primos);
    }
    
}
