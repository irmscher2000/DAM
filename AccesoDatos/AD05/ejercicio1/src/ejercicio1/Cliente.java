
package ejercicio1;

/**
 *
 * @author Moga
 */
public class Cliente {
    
    private Integer numeroCliente;
    private String nombreCliente;
    private Integer limiteCredito;
    private Oficina oficina;

    public Cliente(Integer numeroCliente, String nombreCliente, Integer limiteCredito, Oficina oficina) {
        this.numeroCliente = numeroCliente;
        this.nombreCliente = nombreCliente;
        this.limiteCredito = limiteCredito;
        this.oficina = oficina;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Integer limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public String toString() {
        return "Cliente{" + "numeroCliente=" + numeroCliente + ", nombreCliente=" + nombreCliente + ", limiteCredito=" + limiteCredito + ", oficina=" + oficina + '}';
    }
    
}
