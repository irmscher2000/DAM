package com.prog03.Principal;
import com.prog03.figuras.Triangulo;        
import java.util.Scanner;                   
public class principal {
    public static void main(String[] args) {         
        // Creo el objeto Scanner para leer la entrada del usuario
        Scanner dato = new Scanner(System.in); 
        
        // Imprimo por pantalla
        System.out.println("*****************************");
        System.out.println("Calcula el area del triangulo");
        
        System.out.print("Base: ");
        double base = dato.nextDouble(); // Guarda el dato introducido en base
        
        System.out.print("Altura: ");
        double altura = dato.nextDouble(); // Guarda el dato introducido en altura
        
        // Crea el Tiangulo 1 con los datos introducidos por el usuario
        Triangulo triangulo1 = new Triangulo(base, altura, 0, 0);   

        // Imprime la información completa del triángulo 1 usando toString()
        System.out.println("Información del Triángulo 1:");
        System.out.println(triangulo1.toString());
        System.out.println("*****************************");
        
        // Calcula e imprime el area del triangulo1
        // %.2f\n formatea el numero de salida que tenga 2 decimales y un salto de linea
        System.out.printf("El área del triangulo es: %.2f\n", triangulo1.getArea());  
        System.out.println("*****************************");
        
        System.out.println("*****************************");
        System.out.println("Comprueba si es equilatero: ");
        
        // Solicita al usuario el angulo Izquierdo
        System.out.print("Cuantos grados tiene el Angulo Izquierdo: ");
        int anguloIzq = dato.nextInt(); // Guarda el dato introducido en anguloIzq
        
        // Solicita al usuario el angulo derecho
        System.out.print("Cuantos grados tiene el Angulo Derecho: ");
        int anguloDer = dato.nextInt(); // Guarda el dato introducido en anguloIzq
        
        // Crea el Tiangulo 2 con los datos introducidos por el usuario
        Triangulo triangulo2 = new Triangulo(0, 0, anguloIzq, anguloDer);
        
        // Comprueba si es equilatero e imprime por pantalla el resultado 
        System.out.println("¿Es equilatero? " + triangulo2.isEquilatero());
        System.out.println("*****************************");
        
    }
    
}
