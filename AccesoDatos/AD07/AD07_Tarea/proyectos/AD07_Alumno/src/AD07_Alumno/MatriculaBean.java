// Eugen Moga
// AD07 Tarea MatriculaBean

package AD07_Alumno;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
import java.util.EventListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Moga
 */
public class MatriculaBean {
    
    // Propiedades del bean
    private PropertyChangeSupport propertySupport;

    /**
     * Método constructor por defecto del componente
     */
    public MatriculaBean() {
        // Inicializo soporte de eventos
        propertySupport = new PropertyChangeSupport(this);
    }

    private String dni;

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    private String asignatura;

    /**
     * Get the value of asignatura
     *
     * @return the value of asignatura
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Set the value of asignatura
     *
     * @param asignatura new value of asignatura
     */
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    private String cursoEscolar;

    /**
     * Get the value of cursoEscolar
     *
     * @return the value of cursoEscolar
     */
    public String getCursoEscolar() {
        return cursoEscolar;
    }

    /**
     * Set the value of cursoEscolar
     *
     * @param cursoEscolar new value of cursoEscolar
     */
    public void setCursoEscolar(String cursoEscolar) {
        this.cursoEscolar = cursoEscolar;
    }
    
    private int creditos;

    /**
     * Get the value of creditos
     *
     * @return the value of creditos
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * Set the value of creditos
     *
     * @param creditos new value of creditos
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    private int calificacion;

    /**
     * Get the value of calificacion
     *
     * @return the value of calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * Set the value of calificacion
     *
     * @param calificacion new value of calificacion
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Clase Matricula
     */
    private class Matricula {

        private final String dni;
        private final String asignatura;
        private final String cursoEscolar;
        private final int creditos;
        private final int calificacion;

        /**
         * Método constructor de matrículas por defecto
         */
        public Matricula() {
            this.dni = "";
            this.asignatura = "";
            this.cursoEscolar = "";
            this.creditos = -1;
            this.calificacion = -1;
        }

        /**
         * Método constructor de matrículas parametrizado
         *
         * @param nDNI Número de dni
         * @param nAsignatura Nombre de la asignatura
         * @param nCursoEscolar Curso
         * @param nCreditos Numero de creditos en funcion de las horas semanales
         * @param nCalificacion Nota final
         */
        public Matricula(String nDNI, String nAsignatura, String nCursoEscolar, int nCreditos, int nCalificacion) {
            this.dni = nDNI;
            this.asignatura = nAsignatura;
            this.cursoEscolar = nCursoEscolar;
            this.creditos = nCreditos;
            this.calificacion = nCalificacion;
        }
    }

    /**
     * Vector auxiliar para cargar la información de la base de datos.
     */
    private Vector<Matricula> matriculas = new Vector<>();

    /**
     * Método que devuelve el tamaño de la lista de matrículas.
     *
     * @return Tamaño de la lista
     */
    public int size() {
        return matriculas.size();
    }

    /**
     * Método que recarga en las propiedades del componente la matrícula que se
     * encuentra en la fila indicada del vector.
     *
     * @param i Número de fila
     */
    public void seleccionarRegistro(int i) {
        Matricula matricula;

        if (i < matriculas.size()) {   //Comprueba que existe esa fila y carga sus datos
            matricula = matriculas.get(i);
            this.dni = matricula.dni;
            this.asignatura = matricula.asignatura;
            this.cursoEscolar = matricula.cursoEscolar;
            this.creditos = matricula.creditos;
            this.calificacion = matricula.calificacion;
        }
    }

    /**
     * Método que recarga la estructura del vector con las matriculas referentes
     * al dni indicado.
     * Si no se indica ninguna matrícula, se cargan todas las que existen en la
     * base de datos.
     *
     * @param nDni Número de dni a consultar
     */
    public void recargarDNI(String nDni) {
        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;
        matriculas.clear(); // Limpia el vector
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/RRHH?useSSL=false", "accesodatos", "A1b2c3d4.");
            if (nDni.isBlank() || nDni.isEmpty()) {                 //Si no se indica un número de dni
                receptor.capturarRecarga(new recargaEvent(this));   //Genera un evento
                pstmt = con.prepareStatement("select * from matriculas");
            } else {    //Si se indica un número de dni
                receptor.capturarRecargaDNI(new recargaDniEvent(this));   //Genera un evento
                pstmt = con.prepareStatement("select * from matriculas where dni = ?");
                pstmt.setString(1, nDni);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) { //Copia los datos de cada matricula
                Matricula matricula = new MatriculaBean.Matricula(
                        rs.getString("DNI"),
                        rs.getString("Asignatura"),
                        rs.getString("CursoEscolar"),
                        rs.getInt("Creditos"),
                        rs.getInt("Calificacion"));
                matriculas.add(matricula);  //Añade la matrícula al vector
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que añade una matricula a la base de datos con la información
     * almacenada en las propiedades del componente.
     */
    public void addMatricula() {
        Connection con;
        PreparedStatement pstmt;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/RRHH?useSSL=false", "accesodatos", "A1b2c3d4.");
            receptor.capturarBDModificada(new bdModificadaEvent(this)); //Genera un evento
            pstmt = con.prepareStatement("insert into matriculas values (?,?,?,?,?)");
            pstmt.setString(1, this.dni);
            pstmt.setString(2, this.asignatura);
            pstmt.setString(3, this.cursoEscolar);
            pstmt.setInt(4, (int) this.creditos);
            pstmt.setInt(5, (int) this.calificacion);
            pstmt.executeUpdate();  //Añade la matrícula a la base de datos
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    /**
     * Evento de modificación de la base de datos
     */
    public class bdModificadaEvent extends java.util.EventObject {

        public bdModificadaEvent(Object source) {
            super(source);
        }
    }

    /**
     * Evento de listado de matriculas completo
     */
    public class recargaEvent extends java.util.EventObject {

        public recargaEvent(Object source) {
            super(source);
        }
    }

    /**
     * Evento de listado de matriculas de un DNI
     */
    public class recargaDniEvent extends java.util.EventObject {

        public recargaDniEvent(Object source) {
            super(source);
        }
    }

    /**
     * Define las interface para los eventos
     */
    private interfaceListener receptor;
    
    public interface interfaceListener extends EventListener {

        public void capturarBDModificada(bdModificadaEvent ev);

        public void capturarRecarga(recargaEvent ev);

        public void capturarRecargaDNI(recargaDniEvent ev);

    }

    /**
     * Añade un listener
     *
     * @param receptor
     */
    public void addInterfaceListener(interfaceListener receptor) {
        this.receptor = receptor;
    }

    /**
     * Elimina un listener
     *
     * @param receptor
     */
    public void removeInterfaceListener(interfaceListener receptor) {
        this.receptor = null;
    }

    /**
     * *****************************************************
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
