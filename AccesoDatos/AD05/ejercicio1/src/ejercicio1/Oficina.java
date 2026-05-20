
package ejercicio1;

/**
 *
 * @author Eugen Moga
 */
public class Oficina {
    
    private Integer codOficina;
    private String ciudad;
    private String region;
    private Integer objetivo;
    private Integer ventas;

    public Oficina(Integer codOficina, String ciudad, String region, Integer objetivo, Integer ventas) {
        this.codOficina = codOficina;
        this.ciudad = ciudad;
        this.region = region;
        this.objetivo = objetivo;
        this.ventas = ventas;
    }

    public Integer getCodOficina() {
        return codOficina;
    }

    public void setCodOficina(Integer codOficina) {
        this.codOficina = codOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "Oficina{" + "codOficina=" + codOficina + ", ciudad=" + ciudad + ", region=" + region + ", objetivo=" + objetivo + ", ventas=" + ventas + '}';
    }
    
}
