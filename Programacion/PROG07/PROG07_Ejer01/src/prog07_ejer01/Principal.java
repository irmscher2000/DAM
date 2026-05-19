// Eugen Moga
// Programación Tema 07 

package prog07_ejer01;

public class Principal {

    public static void main(String[] args) {
        
        Persona cliente1 = new Persona("Eugen", "Moga", "12345678R");
        Persona cliente2 = new Persona("Eugenio", "Mola", "12312312R");
        
        Cuenta cuentaCorriente = new CuentaCorriente(cliente1, 1234123412341234L);
        Cuenta cuentaAhorro = new CuentaAhorro(cliente2, 1234567891234234L, 3.5, 1500);
        
        // Operaciones con la cuenta corriente
        cuentaCorriente.ingresar(1000);
        cuentaCorriente.retirar(100);
        cuentaCorriente.actualizarSaldo();
        System.out.println(cuentaCorriente);
        
        // Operaciones con la cuenta de ahorros
        cuentaAhorro.ingresar(3000);
        cuentaAhorro.retirar(200);
        cuentaAhorro.actualizarSaldo();
        System.out.println(cuentaAhorro);
        
    }
    
}
