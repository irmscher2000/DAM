public abstract class JugadorCampo extends Jugador {

    private int asistencias;

    private int golesAnotados;

    public JugadorCampo(String nombre, String apellidos, int edad, int numeroDorsal, boolean esTitular, int asistencias, int golesAnotados) {
        super(nombre, apellidos, edad, numeroDorsal, esTitular);
        this.asistencias = asistencias;
        this.golesAnotados = golesAnotados;
    }

    public int getAsistencias() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsistencias(int asistencias) {
    }

    public int getGolesAnotados() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGolesAnotados(int golesAnotados) {
    }

    public void mostrarInformacion() {
    }
}
