// Eugen Moga
// AD07 Tarea Acceso a la base de datos 

package AD07_prueba;

import AD07_Alumno.MatriculaBean;

/**
 * Clase que actúa como cliente del componente MatriculaBean.
 * Implementa la interfaz de eventos para reaccionar a cambios en la BD.
 */
public class AccedeBD implements MatriculaBean.interfaceListener{
    
    // Creo una instancia del bean que gestiona las matrículas
    MatriculaBean matriculas;

    public AccedeBD() {
        matriculas = new MatriculaBean();
        matriculas.addInterfaceListener(this);
    }
    
    // Método que lista todas las matriculas de la base de datos 
    public void listadoMatriculas(){
        matriculas.recargarDNI("");
        for (int i = 0; i < matriculas.size(); i++){
            matriculas.seleccionarRegistro(i);
            System.out.println("Matricula: " + (i + 1));
            System.out.println("\tDNI: " + matriculas.getDni());
            System.out.println("\tAsignatura: " + matriculas.getAsignatura());
            System.out.println("\tCurso Escolar: " + matriculas.getCursoEscolar());
            System.out.println("\tCreditos: " + matriculas.getCreditos());
            System.out.println("\tCalificación: " + matriculas.getCalificacion() + "\n");
        }
    }
    
    //  Método que lista las matrículas de un DNI especificado
    public void listadoMatriculasDNI(){
        matriculas.recargarDNI("X4579462R");
        for (int i = 0; i < matriculas.size(); i++){
            matriculas.seleccionarRegistro(i);
            System.out.println("Matricula: " + (i + 1));
            System.out.println("\tDNI: " + matriculas.getDni());
            System.out.println("\tAsignatura: " + matriculas.getAsignatura());
            System.out.println("\tCurso Escolar: " + matriculas.getCursoEscolar());
            System.out.println("\tCreditos: " + matriculas.getCreditos());
            System.out.println("\tCalificación: " + matriculas.getCalificacion() + "\n");
        }
    }
    
    // Método para añadir una matrícula nueva
    public void anadeMatricula(){
        matriculas.setDni("X4579462R");
        matriculas.setAsignatura("Desarrollo Interfaces");
        matriculas.setCursoEscolar("25/26");
        matriculas.setCreditos(6);
        matriculas.setCalificacion(9);
        System.out.println("Matricula nueva: ");
        System.out.println("\tDNI: " + matriculas.getDni());
        System.out.println("\tAsignatura: " + matriculas.getAsignatura());
        System.out.println("\tCurso Escolar: " + matriculas.getCursoEscolar());
        System.out.println("\tCreditos: " + matriculas.getCreditos());
        System.out.println("\tCalificación: " + matriculas.getCalificacion() + "\n");
        matriculas.addMatricula();
    }
    

    @Override
    public void capturarBDModificada(MatriculaBean.bdModificadaEvent me) {
        System.out.println("\nSe ha añadido una matricula a la base de datos\n");
    }

    @Override
    public void capturarRecarga(MatriculaBean.recargaEvent r) {
         System.out.println("\nSe han recargado todas las matriculas\n");
    }

    @Override
    public void capturarRecargaDNI(MatriculaBean.recargaDniEvent de) {
        System.out.println("\nSe han recargado las matriculas de un dni\n");
    }
}
