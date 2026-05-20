// Eugen Moga
// AD07 Tarea AlumnoBean

package AD07_Alumno;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;

public class AlumnoBean implements Serializable{
    
    // Propieades del Bean 
    private PropertyChangeSupport propertySupport;
    
    public AlumnoBean(){
        // Inicializo soporte de eventos
        propertySupport = new PropertyChangeSupport(this);
        
        // Cargo los datos desde la base de datos
        recargarFilas();
    }
    
    // Getter y Setter
    protected String DNI;
    public String getDNI(){
        return DNI;
    }
    
    public void setDNI(String DNI){
        this.DNI = DNI;
    }
    
    protected String nombre;
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    protected String apellidos;
    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    
    protected String direccion;
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    protected Date fechaNac;
    public Date getFechaNac(){
        return fechaNac;
    }
    public void setFechaNac(Date fechaNac){
        this.fechaNac = fechaNac;
    }
    
    // Clase auxiliar para crear un vector privado de alumnos
    private class Alumno {
    
        String DNI;
        String nombre;
        String apellidos;
        String direccion;
        Date fechaNac;

        // Constructor vacio
        public Alumno(){
        }

        // Constructor con parametros
        public Alumno(String nDNI, String nNombre, String nApellidos, String nDireccion, Date nFechaNac){
            this.DNI = nDNI;
            this.nombre = nNombre;
            this.apellidos = nApellidos;
            this.direccion = nDireccion;
            this.fechaNac = nFechaNac;
        }
    }
    
    // Utilizo un vector auxiliar para cargar la informacion de la tabla
    // sin necesidad de estar conectados constantemente
    private Vector Alumnos = new Vector();
    
    // Actualiza el contenido de la tabla en el vector de alumnos
    private void recargarFilas(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/RRHH?useSSL=false", "accesodatos", "A1b2c3d4.");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from alumnos");
            while (rs.next()){
                Alumno a = new Alumno(rs.getString("DNI"),
                                      rs.getString("Nombre"),
                                      rs.getString("Apellidos"),
                                      rs.getString("Direccion"),
                                      rs.getDate("FechaNac"));
                Alumnos.add(a);
            }
            Alumno a = new Alumno();
            a = (Alumno) Alumnos.elementAt(1);
            this.DNI = a.DNI;
            this.nombre = a.nombre;
            this.apellidos = a.apellidos;
            this.direccion = a.direccion;
            this.fechaNac = a.fechaNac;
            rs.close();
            con.close();
        }catch (SQLException ex){
            this.DNI = "";
            this.nombre = "";
            this.apellidos = "";
            this.direccion = "";
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Numero de la fila a cargar en las propiedades del componente
    public void seleccionarFila(int i){
        if (i <= Alumnos.size()){
            Alumno a = new Alumno();
            a = (Alumno) Alumnos.elementAt(i);
            this.DNI = a.DNI;
            this.nombre = a.nombre;
            this.apellidos = a.apellidos;
            this.direccion = a.direccion;
            this.fechaNac = a.fechaNac;
        }else {
            this.DNI = "";
            this.nombre = "";
            this.apellidos = "";
            this.direccion = "";
        }
    }
    
    
    // DNI a buscar y se cargar en las propiedades del componente
    public void seleccionarDNI(String nDNI){
        Alumno a = new Alumno();
        int i=0;
        
        this.DNI = "";
        this.nombre = "";
        this.apellidos = "";
        this.direccion = "";
        while (this.DNI.equals("") && i <= Alumnos.size()){
            a = (Alumno)Alumnos.elementAt(i);
            if (a.DNI.equals(nDNI)){
                this.DNI = DNI;
                this.nombre = a.nombre;
                this.apellidos = a.apellidos;
                this.direccion = a.direccion;
                this.fechaNac = a.fechaNac;
            }
            i++;
        }
    }
}
