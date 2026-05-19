// Eugen Moga
// Programación Tema 07 
package prog07_ejer01;

public class Persona {
    private String nombre;          // Atributos clase Persona
    private String apellidos;
    private String DNI;
    
    public Persona(String nombre, String apellidos, String DNI){ // Constructor clase Persona 
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.DNI = DNI; 
    }
    
    // Método toString
    @Override
    public String toString(){
        return nombre + ", " + apellidos + ", DNI " + DNI; 
    }
    
}
