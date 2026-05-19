// Eugen Moga  
// Prog06 Ejercicio 1

package PROG06_Ejer3;
import java.util.Scanner;

public class Ejercicio03 {
    public static void main(String[] args){
        
        // Ejercicio 3.1
        // Creo el Array de tipo string para mejorar la vista de la tabla
        String[] columnaTablaG = {"Comprados", "Precio Compra", "Total Gastos"};
        
        // Array que guarda el numero de muebles comprados.
        int [] mueblesComprados = {30, 50, 200};     // Sofás, mesas, sillas.
        
        // Array que guarda los precios de compra por mueble.
        int [] precioCompra = {120, 60, 15};         // Sofás, mesas, sillas.
        
        // Array que guarda los precios de venta por mueble.
        int [] precioVenta = {250, 100, 25};        // Sofaás mesas, sillas.
        
        // Creo un array 3x3 para almacenar los datos.
        int [][] arrayGastos = new int[3][3];
        
        /**
         * Asigno valores al Array 3x3
         */
        for (int i = 0; i < 3; i++){
            arrayGastos[i][0] = mueblesComprados[i];
            arrayGastos[i][1] = precioCompra[i];
            arrayGastos[i][2] = mueblesComprados[i] * precioCompra[i];
        }
        
        // Imprimo el Array por pantalla.
        System.out.println("\nTabla de gastos ");
        System.out.printf("%-16s %-16s %-15s %-15s%n", "Mueble", "Sofás", "Mesas", "Sillas"); // Formato para alinear los datos
        System.out.println("------------------------------------------------------------");
        for(int i = 0; i < 3; i++){
            if (i == 0){
                System.out.printf("%-16s %-16d %-16d %-16d%n", // Formato para alinear los datos e imprimir la primera fila
                    columnaTablaG[i],
                    arrayGastos[0][i],
                    arrayGastos[1][i],
                    arrayGastos[2][i]);
            } else {
                System.out.printf("%-15s€ %-15d€ %-15d€ %-15d%n", // Formato para alinear los datos e imprimir con el simbolo de €
                    columnaTablaG[i],
                    arrayGastos[0][i],
                    arrayGastos[1][i],
                    arrayGastos[2][i]);
            }
        }
        System.out.println("------------------------------------------------------------");
        
        // Ejercicio 3.2
        // Creo da variable sc para leer entrada del usuario.
        Scanner sc = new Scanner(System.in);
        
        String[] columnaTablaI = {"Vendidos", "Precio Venta", "Total Ingresos"};
        int[] mueblesVendidos = new int [3];
        int[][] arrayIngresos = new int [3][3];
        
        System.out.println("\nVamos a calcular las ventas de este mes para ver los ingresos.");
        System.out.println("\n¿Cuantos sofás se han vendido? ");
        mueblesVendidos[0] = sc.nextInt();
        
        System.out.println("\n¿Cuantas mesas se han vendido? ");
        mueblesVendidos[1] = sc.nextInt();
        
        System.out.println("\n¿Cuantas sillas se han vendido? ");
        mueblesVendidos[2] = sc.nextInt();
        
        for (int i = 0; i <3; i++){
            arrayIngresos[i][0] = mueblesVendidos[i];
            arrayIngresos[i][1] = precioVenta[i];
            arrayIngresos[i][2] = mueblesVendidos[i] * precioVenta[i];
        }
        
        // Imprimo el Array por pantalla.
        System.out.println("\nTabla de ingresos ");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Mueble", "Sofás", "Mesas", "Sillas"); // Formato para alinear los datos
        System.out.println("-------------------------------------------------------------");
        for(int i = 0; i < 3; i++){
            if (i == 0){
                System.out.printf("%-16s %-16d %-16d %-16d%n", // Formato para alinear los datos e imprimir la primera fila
                    columnaTablaI[i],
                    arrayIngresos[0][i],
                    arrayIngresos[1][i],
                    arrayIngresos[2][i]);
            } else {
                System.out.printf("%-15s€ %-15d€ %-15d€ %-15d%n", // Formato para alinear los datos e imprimir con el simbolo de €
                    columnaTablaI[i],
                    arrayIngresos[0][i],
                    arrayIngresos[1][i],
                    arrayIngresos[2][i]);
            }
        }
        System.out.println("-------------------------------------------------------------");
        
        // Ejercicio 3.3
        // Stock
        // Array para la cabecera de la tabla
        String[] nombreStock = {"Muebles en Stock", "Beneficio"};
        int[][] arrayStock = new int[2][4];
        
        // Calculo muebles en stock muebles comprados menos muebles vendidos
        for(int i = 0; i < 3; i++){
            // Calcular los muebles en stock fila 0
            arrayStock[0][i] = mueblesComprados[i] - mueblesVendidos[i];
            
            // Calcular los beneficios fila 1
            arrayStock[1][i] = arrayIngresos[i][2] - arrayGastos[i][2];
        }
        
        // Calculo totales columna 3 en ambas filas
        for(int i = 0; i < 2; i++){
            arrayStock[i][3] = arrayStock[i][0] + arrayStock[i][1] + arrayStock[i][2];
        }
        
        System.out.println("\nTabla de Stock Mensual ");
        System.out.printf("%-20s %-10s %-11s %-11s %-11s%n", "Muebles", "Sofás", "Mesas", "Sillas", "Total"); // Formato para alinear los datos
        System.out.println("-----------------------------------------------------------------");
        //Bucle for para imprimir filas
        for(int i = 0; i < 2; i++){
            if (i == 0){
                System.out.printf("%-21s %-11d %-11d %-11d %-11d%n", // Formato para alinear los datos e imprimir la primera fila
                    nombreStock[i],
                    arrayStock[i][0],
                    arrayStock[i][1],
                    arrayStock[i][2],
                    arrayStock[i][3]);
            } else {
                System.out.printf("%-20s€ %-10d€ %-10d€ %-10d€ %-10d%n", // Formato para alinear los datos e imprimir con el simbolo de €
                    nombreStock[i],
                    arrayStock[i][0],
                    arrayStock[i][1],
                    arrayStock[i][2],
                    arrayStock[i][3]);
            }
        }
        System.out.println("-----------------------------------------------------------------");
    }
}


