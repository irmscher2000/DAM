// Eugen Moga
// Programación Tema 07 

package prog07_ejer01;

// Cuenta corriente hereda de la clase Cuenta
public class CuentaCorriente extends Cuenta {           
    
    private static final double INTERES_FIJO = 1.5;      // Atributo para calcuar el interes fijo
    
    // Constructor parametrizado
    public CuentaCorriente(Persona cliente, long numeroCuenta){     
        super(cliente, numeroCuenta);
    }
    
    // Método toString
    @Override
    public String toString(){
        return super.toString() + ", Interes fijo: " + INTERES_FIJO + "%";
    }

    // Método para retirar saldo de la Interfaz ICuenta
    @Override
    public void retirar(double cantidad) {
        if (cantidad == 0){
            return;
        }
        if (cantidad > 0 && cantidad <= getSaldo()){
            setSaldo(getSaldo() - cantidad);
        }else {
            System.out.println("Error no puedes retirar mas dinero del que tienes en la cuenta: " + getSaldo());
        }
    }

    // Método para actualizar saldo calculado con el interes fijo
    @Override
    public void actualizarSaldo() {
        setSaldo(getSaldo() + (getSaldo() * INTERES_FIJO / 100));
    }
}
