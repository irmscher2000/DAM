public class Tecnico extends Persona {

    private int aniosExperiencia;

    private boolean esNacional;

    public Tecnico(String nombre, String apellidos, int edad, int aniosExperiencia, boolean esNacional) {
        super(nombre, apellidos, edad);
        this.aniosExperiencia = aniosExperiencia;
        this.esNacional = esNacional;
    }

    public int getAniosExperiencia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAniosExperiencia(int aniosExperiencia) {
    }

    public boolean getEsNacional() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEsNacional(boolean esNacional) {
    }

    public void mostrarInformacion() {
    }
}
