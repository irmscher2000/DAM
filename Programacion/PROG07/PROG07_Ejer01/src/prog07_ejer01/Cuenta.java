// Eugen Moga
// Programación Tema 07 
package prog07_ejer01;

public abstract class Cuenta implements ICuenta {   // Clase abstracta Cuenta que implementa la interfaz de ICuenta
    private long numeroCuenta;                      // Atributo de la clase Cuenta
    private double saldo;                           // Atributo de la clase Cuenta
    private Persona cliente;                        // Atributo de la clase Persona
    
    // Constructor parametrizado que recibe un cliente y un número de cuenta
    public Cuenta(Persona cliente, long numeroCuenta){
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        
    }
    
    // GETTER Y SETTERS atributo numeroCuenta
    public long getNumeroCuenta(){
        return numeroCuenta;
    }
    public void setNumeroCuenta(long numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }
    
    // GETTER Y SETTERS atributo saldo
    public double getSaldo(){
        return saldo;
    }
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    // GETTER Y SETTERS atributo cliente
    public Persona getCliente(){
        return cliente;
    }
    public void setCliente(Persona cliente){
        this.cliente = cliente;
    }
    
    // Método toString con información relativa a la cuenta 
    @Override
    public String toString(){
        return String.format("INFORMACIÓN: El número de cuenta:%d,  contiene un saldo de: %.2f, y pertenece al cliente: %s", numeroCuenta, saldo, cliente);
    }
    
    // Método para ingresar de la Interfaz ICuenta
    public void ingresar(double cantidad){
        if (cantidad > 0 ) {
            saldo += cantidad;
        }
    }

}
