// Eugen Moga
// Programación Tema 07 

package prog07_ejer01;

// CuentaAhorro hereda de la clase Cuenta 
public class CuentaAhorro extends Cuenta {              // Subclase de Cuenta
    
    // Atributos de la clase CuentaAhorro
    private double interesVariable;
    private double saldoMinimo;
    
    // Constructor parametrizado 
    public CuentaAhorro(Persona cliente, long numeroCuenta, double interesVariable, double saldoMinimo){
        super(cliente, numeroCuenta);
        this.interesVariable = interesVariable;
        this.saldoMinimo = saldoMinimo;
    }
    
    // Método para cambiar el interes variable
    public void cambiarInteres(double nuevoInteres){
        this.interesVariable = nuevoInteres;
    }
    
    // Método toString 
    public String toString(){
        return super.toString() + ", Interes variable: " + interesVariable + "%" + ", saldo minimo: " + saldoMinimo;
    }

    // Método para retirar dinero
    @Override
    public void retirar(double cantidad) {
        if (cantidad == 0){
            return;
        }
        if (cantidad > 0 && (getSaldo() - cantidad) >= saldoMinimo){
            setSaldo(getSaldo() - cantidad);
        } else {
            System.out.println("No se puede retirar esa cantidad. El saldo no puede ser inferior a " + saldoMinimo);
        }
    }

    // Método para actualizar saldo 
    @Override
    public void actualizarSaldo() {
        setSaldo(getSaldo() + (getSaldo() * interesVariable / 100));
    }
}
