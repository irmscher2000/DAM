public class Portero extends Jugador {

    private int golesRecibidos;

    public Portero(String nombre, String apellidos, int edad, int numeroDorsal, boolean esTitular, int golesRecibidos) {
        super(nombre, apellidos, edad, numeroDorsal, esTitular);
        this.golesRecibidos = golesRecibidos;
    }

    public int getGolesRecibidos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGolesRecibidos(int golesRecibidos) {
    }

    public void mostrarInformacion() {
    }
}
