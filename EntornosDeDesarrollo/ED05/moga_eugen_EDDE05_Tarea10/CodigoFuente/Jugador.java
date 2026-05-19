public abstract class Jugador extends Persona {

    private int numeroDorsal;

    private boolean esTitular;

    public Jugador(String nombre, String apellidos, int edad, int numeroDorsal, boolean esTitular) {
        super(nombre, apellidos, edad);
        this.numeroDorsal = numeroDorsal;
        this.esTitular = esTitular;
    }

    public int getNumeroDorsal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNumeroDorsal(int numeroDorsal) {
    }

    public boolean getEsTitular() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEsTitular(boolean esTitular) {
    }

    public void mostrarInformacion() {
    }
}
