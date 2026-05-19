
package prog02_ejerc1;

// Eugen Moga
// PROG02 Ejercicio 1

public class PROG02_Ejerc1 {

    public static void main(String[] args) {
        
        // 1 Valor máximo no modificable 1000
        // Para expresar un numero entero que permanece constante utilizo final int
        final int num_max=1000;
        
        // 2 Si el nuevo estudiante tiene carnet de conducir o no
        // Utilizo boolean para representar valores verdadero verdadero falso 
        boolean tieneCarnet = true;
        
        // 3 El mes del año en formato numérico y como cadena
        // Utilizo byte ya que es suficiente para representar 12 meses y String para almacenar texto
        byte mesNumerico = 10;
        String mesCadena = "Octubre";
        
        // 4 El nombre y apellidos de una persona.
        // Utilizo String para almacenar texto
        String nombreCompleto = "Eugen Moga";
        
        // 5 Sexo: con dos valores posibles 'H' o 'M'.
        // Utilizo char para un solo carácter.
        char sexo = 'H';
        
        // 6 Milisegundos transcurridos desde el 01/01/1980 hasta nuestros días 
        // Utilizo long para almacenar numeros grandes
        // System.currentTimeMillis calcula los milisegundos desde 1970 hasta la fecha actual
        // java.util.GregorianCalendar 1980 crea el objeto calendar para representar el 1 de enero 1980
        // getTimeInMillis convierte esa fecha en milisegundos (desde 1970 hasta 1980)
        long milisegundosdesde1980 = System.currentTimeMillis() - new java.util.GregorianCalendar(1980, java.util.Calendar.JANUARY, 1).getTimeInMillis(); 
        
        // 7 Saldo de una cuenta bancaria.
        // Utilizo double para permitir decimales.
        double saldoBanco = 2500.35;
        
        // 8 Distancia en ‘km’ desde la Tierra a Marte.
        // Utilizo long para expresar numeros grandes
        long distanciaTierraMarte = 225000000;
        
        // Mostrar los valores en pantalla sin println
        System.out.print("Valor máximo no modificable: " + num_max);
        System.out.print("\nTiene carnet de conducir: " + tieneCarnet);
        System.out.print("\nMes en formato numérico: " + mesNumerico +" " + mesCadena);
        System.out.print("\nNombre completo: " + nombreCompleto);
        System.out.print("\nSexo: " + sexo);
        System.out.print("\nMilisegundos transcurridos desde 1 de Enero 1980: " + milisegundosdesde1980);
        System.out.print("\nSaldo en una cuenta bancaria: " + saldoBanco);
        System.out.print("\nDistancia desde la Tierra a Marte: " + distanciaTierraMarte);
    }
    
}
