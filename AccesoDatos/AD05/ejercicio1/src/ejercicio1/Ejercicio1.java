
package ejercicio1;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Eugen Moga
 * AD 05 Ejercicio 1
 */
public class Ejercicio1 {

    public static int mostrarMenu(){
        int opcion=-1;
        do {
            System.out.printf("\nOpciones");
            System.out.printf("\n\t1. Crear la base de datos.");
            System.out.printf("\n\t2. Añadir una Oficina.");
            System.out.printf("\n\t3. Añadir un Cliente.");
            System.out.printf("\n\t4. Modificar datos de una oficina.");
            System.out.printf("\n\t5. Modificar el límite de un cliente.");
            System.out.printf("\n\t6. Eliminar un cliente.");
            System.out.printf("\n\t7. Eliminar los clientes de una oficina.");
            System.out.printf("\n\t8. Listar los clientes de una oficina.");
            System.out.printf("\n\t9. Listar todos los clientes.");
            System.out.printf("\n\t10. Listar las oficinas cuyo objetivo este entre dos datos leidos por teclado.");
            System.out.printf("\n\t11. Listar todas las oficinas.");
            System.out.printf("\n\t0. Salir.");
            System.out.printf("\nSeleccione la opción y pulse Enter:  \n");
            Scanner teclado = new Scanner(System.in);
            opcion = teclado.nextInt();
            
        } while(opcion<0 || opcion > 11);
        return opcion;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Base datos fisica es el fiechero miBaseDatos.db4o
        // que se almacena en  la carpeta raiz del proyecto
        ObjectContainer db = Db4oEmbedded.openFile("miBaseDatos.db4o");
        
        int opcion =-1, codigo;
        
        
        while (opcion !=0){
            opcion = mostrarMenu();
            switch(opcion){
                case 1 -> { 
                    // Comprueba la conexion actual y si esta abierta la cierro
                    if(db != null){db.close();}
                    
                    // Borro el archivo fisico si existe
                    File fichero = new File("miBaseDatos.db4o");
                    if(fichero.exists()){fichero.delete();}
                    
                    // Vuelvo a abrir la conexion y creo el fichero vacio
                    db =Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"miBaseDatos.db4o");
                    
                    // Llamo al metodo para crear las tablas 
                    GestionBD4O.crearBaseDatos(db);
                }
                    
                case 2 -> GestionBD4O.anadirOficina(db);
                case 3 -> GestionBD4O.anadirCliente(db);
                case 4 -> GestionBD4O.modificarDatosOficina(db);
                case 5 -> GestionBD4O.modificarLimiteCliente(db);
                case 6 -> GestionBD4O.eliminarCliente(db);
                case 7 -> GestionBD4O.eliminarClientesOficina(db);
                case 8 -> GestionBD4O.listarClientesOficina(db);
                case 9 -> GestionBD4O.listarClientes(db);
                case 10-> GestionBD4O.oficinasEntreObjetivo(db);
                case 11-> GestionBD4O.listarTodasOficinas(db);
            }
        }
        
        // Cierre db
        if (db != null && !db.ext().isClosed()){
            db.close();
        }
    }
}
