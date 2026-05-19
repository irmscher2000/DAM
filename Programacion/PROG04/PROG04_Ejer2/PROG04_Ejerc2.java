package prog04_ejerc2;
// Importo todas las clases de java .io con en el * para utilizar el metodo readLine
import java.io.*;                                                                       
public class PROG04_Ejerc2{
    public static void main(String[] args) {
        
        int numInt = 5;                                                                 
        int numMax = 10;                                                                
        boolean configurado = false;                                                    
        // Para leer valores desde consola
        BufferedReader lectorBuffered = new BufferedReader(new InputStreamReader(System.in));
        // Controlo la ejecucion del bucle principal
        boolean ejecutado = true;                                                       
        // While para implementar el bucle infinito
        while (ejecutado) {
            // Se muestra por pantalla las opciones del menu                                                             
            System.out.println("Menú: ");                                               
            System.out.println("1. Configurar");
            System.out.println("2. Jugar");
            System.out.println("3. Salir");
            System.out.println("Elige una opción: ");
            // Lee el numero introducido por el usuario
            // Declara la variable opcion para gaurdar la opcion del usuario
            int opcion = leerNumero(lectorBuffered, "Error, debes Introducir un número válido");                                                              
            
            switch(opcion){ 
                // Opcion 1 configura el juego                                                            
                case 1:
                    System.out.print("Introduce el número de intentos permitidos: ");
                    numInt = leerNumero(lectorBuffered, "Error, debes introducir un número válido");  // Lee lo que se introduce por teclado y lo guarda en numInt
                    System.out.print("Introduce el número máximo generado: ");
                    numMax = leerNumero(lectorBuffered, "Error, debes introducir un número válido");  // Lee lo que se introduce por teclado y lo guarda en numMax
                    configurado = true;                                                 // Lo marco como configurado
                    System.out.println("Configuración guardada: intentos = " + numInt + ", numero máximo generado = " + numMax);
                    break;
                    
                case 2:
                    int intentos = numInt;                                              
                    
                    /* math.random genera un numero aleatorio decimal entre 0 y 1
                    se multiplica por numMax y se suma 1
                    math.floor toma el numero decimal y lo redondea al hacia abajo al numero mas cercano
                    int numOculto realiza un casting es decir el numero entero obtenido en un entero de tipo int
                    */
                    int numOculto = (int) Math.floor(Math.random()* (numMax + 1));
                    // Bandera para determinar si el usuario gana
                    boolean ganado = false;                                             
                    
                    System.out.println("¡Empieza el juego!");
                    System.out.println("Tienes " + intentos + " intentos para adivinar un numero entre 0 y " + numMax +".");
                    
                    while (intentos > 0){                                               
                        System.out.println("Introduce tu numero: ");
                        
                        // Lee la entrada del usuario y lo guarda en numeroUsuario, si se introduce otro caracterque no sea numero muestra el error
                        int numeroUsuario = leerNumero(lectorBuffered, "Error, debes Introducir un número válido.");
                        
                        if (numeroUsuario == numOculto){
                            System.out.println("¡Has ganado!");
                            System.out.println("¡Has Necesitado " + (numInt - intentos + 1) + " intentos");
                            ganado = true;
                            break;                                                      // Sale del bucle si el usuario gana
                        } else if (numeroUsuario > numOculto){                          // Comprueba si numeroUsuario es mayor que numOculto y le da la siguiente pista
                            System.out.println("El numero oculto es menor.");           
                        } else {
                            System.out.println("¡El número oculto es mayor.");
                        }
                        
                        intentos--;                                                     // Se reduce el numero de intentos
                        if (intentos > 0){                                              // Verifica si intentos es mayor que 0 y muestra por pantalla cuantos intentos le queda
                            System.out.println("Te quedan " + intentos + " intentos.");
                        }
                    }
                    
                    if (!ganado){                                                       // Se cambia el valor de la variable con "!" porque se han terminado los intentos y ha perdido 
                        System.out.println("Perdiste!. Intentos consumidos");
                        System.out.println("El numero oculto era: " + numOculto + ".");
                    }
                    break;
                    
                case 3:
                    System.out.println("Finalizando programa. ");
                    ejecutado = false;                                                  // Se cambia el valor de la variable para que salga del bucle
                    break;
            }
        }
    }
    public static int leerNumero(BufferedReader lectorBuffered, String error){          // Declaro la clase BufferedReader que es la que contiene el metodo readLine
        while (true){                                                                   // Mientras sea cierto ejecuta
            try{
                return Integer.parseInt(lectorBuffered.readLine());                               // Devuelve un numero de tipo entero que se ha introducido en el metodo readLine
            } catch (NumberFormatException e){                                          // Verifica si el numero introducido por teclado se puede convertir a entero
                System.out.println("NumberFormatException: " + error);
            } catch (Exception e){                                                      // Captura cualquier otra excepción, para manejar errores imprevistos
                System.out.println("Exception: Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }

}   
    

