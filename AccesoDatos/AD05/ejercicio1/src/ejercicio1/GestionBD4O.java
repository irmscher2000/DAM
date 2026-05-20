
package ejercicio1;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar los metodos de la base de datos
 * @author Eugen Moga
 * 
 */
public class GestionBD4O {
    
    static Scanner teclado = new Scanner(System.in);
   
    // 1. Metodo para crear la base de datos
    public static void crearBaseDatos(ObjectContainer db){
        Oficina o1 = new Oficina(11, "Valencia", "Este", 4000, 5000);
        Oficina o2 = new Oficina(12, "Alicante", "Este", 5000, 4500);
        Oficina o3 = new Oficina(13, "Castellon", "Este", 2500, 3600);
        Oficina o4 = new Oficina(21, "Badajoz", "Oeste", 4250, 6000);
        Oficina o5 = new Oficina(22, "A Coruña", "Oeste", 1800, 2500);
        Oficina o6 = new Oficina(23, "Madrid", "Centro", null, null);
        Oficina o7 = new Oficina(24, "Madrid", "Centro", 1250, 900);
        Oficina o8 = new Oficina(26, "Pamplona", "Norte", null, null);
        Oficina o9 = new Oficina(28, "Valencia", "Este", 6000, 0);
            
        // Se almacena los objetos con el metodo store()
        db.store(o1);
        db.store(o2);
        db.store(o3);
        db.store(o4);
        db.store(o5);
        db.store(o6);
        db.store(o7);
        db.store(o8);
        db.store(o9);
        
        
        Cliente c1 = new Cliente(2101, "Luis Garcia Atón", 450, o1);
        Cliente c2 = new Cliente(2102, "Alvaro Rodriguez", 450, o2);
        Cliente c3 = new Cliente(2103, "Jaime Llorens", 500, o3);
        Cliente c4 = new Cliente(2104, "Antonio Canales", 50, o4);
        Cliente c5 = new Cliente(2105, "Juan Suarez", 250, o5);
        Cliente c6 = new Cliente(2106, "Julian López", 500, o6);
        Cliente c7 = new Cliente(2107, "Julia Antequera", 630, o8);
        Cliente c8 = new Cliente(2108, "Alberto Juanes", 420, o7);
        Cliente c9 = new Cliente(2109, "Cristóbal García", 520, o9);
        Cliente c10 = new Cliente(2110, "Maria Silca", 850, o6);
        Cliente c11 = new Cliente(2111, "Luisa Maron", 965, o1);
        Cliente c12 = new Cliente(2112, "Cristina Bulini", 520, o9);
        Cliente c13 = new Cliente(2113, "Vicente Martínez", 410, o3);
        Cliente c14 = new Cliente(2114, "Carlos Tena", 360, o4);
        Cliente c15 = new Cliente(2115, "Antonio Ponce", 563, o5);
        Cliente c16 = new Cliente(2116, "Salomon Bueno", 960, o6);
        Cliente c17 = new Cliente(2117, "Juan Malo", 320, o8);
        Cliente c18 = new Cliente(2118, "Vicente Ríos", 150, o1);
        Cliente c19 = new Cliente(2119, "José Marchante", 630, o1);
        Cliente c20 = new Cliente(2120, "José Libros", 400, o1);
        Cliente c21 = new Cliente(2121, "Juan Bolto", 520, o1);
        
        db.store(c1);
        db.store(c2);
        db.store(c3);
        db.store(c4);
        db.store(c5);
        db.store(c6);
        db.store(c7);
        db.store(c8);
        db.store(c9);
        db.store(c10);
        db.store(c11);
        db.store(c12);
        db.store(c13);
        db.store(c14);
        db.store(c15);
        db.store(c16);
        db.store(c17);
        db.store(c18);
        db.store(c19);
        db.store(c20);
        db.store(c21);
    }
    
    // 2. Metodo para añadir una oficina
    public static void anadirOficina(ObjectContainer db){
        System.out.print("Código: ");
        int codOficina = teclado.nextInt();
        teclado.nextLine();
        
        System.out.print("Ciudad: ");
        String ciudad = teclado.nextLine();
        
        System.out.print("Region: ");
        String region = teclado.nextLine();
        
        System.out.print("Objetivo: ");
        int obgetivo = teclado.nextInt();
        
        int ventas = 0;
        
        Oficina o = new Oficina(codOficina, ciudad, region, obgetivo, ventas);
        db.store(o);
        db.commit();
    }
    
    // 3. Metodo para añadir un cliente
    public static void anadirCliente(ObjectContainer db){
        System.out.print("Codigo: ");
        int numeroCliente = teclado.nextInt();
        teclado.nextLine();
        
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        
        System.out.print("Limite credito: ");
        int limite = teclado.nextInt();
        teclado.nextLine();
        
        System.out.print("Codigo oficina: ");
        int codOficina = teclado.nextInt();
        teclado.nextLine();
        
        // Busco la oficina pero el codigo
        Oficina buscarOficina = new Oficina(codOficina, null, null, null, null);
        
        // Guardo el resultado
        ObjectSet<Oficina> resultado = db.queryByExample(buscarOficina);
        
        // Se comprueba si ha encontrado la oficina en la base datos
        if(resultado.hasNext()){
            Oficina oficinaEncontrada = resultado.next();
            
            // Se añade el cliente 
            Cliente c = new Cliente(numeroCliente, nombre, limite, oficinaEncontrada);
            db.store(c);
            db.commit();
            System.out.println("Cliente guardado correctamente");
        }else{
            System.out.println("Error: La oficina " + codOficina + " no existe en la base de datos.");
        }
    }
    
    // 4. Metodo para modificar datos de una oficina
    public static void modificarDatosOficina(ObjectContainer db){
        System.out.print("Indica el codigo de la oficina que deseas modificar: ");
        int codOficina = teclado.nextInt();
        teclado.nextLine();
        
        Oficina buscarOficina = new Oficina(codOficina, null, null, null, null);
        ObjectSet<Oficina> resultado = db.queryByExample(buscarOficina);
    
        System.out.println("Datos de la oficina");
        System.out.println(resultado);
        
        System.out.println("Indica los nuevos datos: ");
        
        if(!resultado.isEmpty()){
            Oficina o = resultado.next();
            
            System.out.print("Nueva ciudad: ");
            o.setCiudad(teclado.nextLine());
            
            System.out.print("Nueva region: ");
            o.setRegion(teclado.nextLine());
            
            System.out.print("Nuevo objetivo: ");
            o.setObjetivo(teclado.nextInt());
            
            db.store(o);
            db.commit();
        }
    }
    
    // 5. Modificar limite de un cliente 
    public static void modificarLimiteCliente(ObjectContainer db){
        System.out.print("Indica el codigo del cliente: ");
        int codCliente = teclado.nextInt();
        
        Cliente buscarCliente = new Cliente(codCliente, null, null, null);
        ObjectSet<Cliente> resultado = db.queryByExample(buscarCliente);
        
        System.out.println("Datos del cliente: ");
        System.out.println(resultado);
        
        if(!resultado.isEmpty()){
            Cliente c = resultado.next();
            
            System.out.print("Indica el nuevo limite: ");
            c.setLimiteCredito(teclado.nextInt());
            
            db.store(c);
            db.commit();
        } else {
            System.out.println("El cliente no existe.");
        }
    }
    
    // 6. Metodo para eliminar un cliente
    public static void eliminarCliente(ObjectContainer db){
        System.out.print("Indica el codigo del cliente: ");
        int codCliente = teclado.nextInt();
        
        Cliente buscarCliente = new Cliente(codCliente, null, null, null);
        ObjectSet<Cliente> resultado = db.queryByExample(buscarCliente);
        
        if(!resultado.isEmpty()){
            Cliente c = resultado.next();
            
            db.delete(c);
            db.commit();
            
            System.out.println("Eliminado correctamente: " + c );
        }
    }
    
    // 7. Metodo para eliminar los clientes de una oficina
    public static void eliminarClientesOficina(ObjectContainer db){
        
        System.out.println("Indica el codigo de la oficina para eliminar sus clientes: ");
        int codOficina = teclado.nextInt();
        teclado.nextLine();
        
        int contador = 0;
        for (Cliente c : db.query(Cliente.class)){
            if(c.getOficina().getCodOficina() == codOficina){
                db.delete(c);
                contador++;
            }
        }
        db.commit();
        System.out.println("Clientes eliminados: " + contador);
    }
    
    // 8. Metodo para listar los clientes de una oficina
    public static void listarClientesOficina(ObjectContainer db){
        System.out.println("Indica el codigo de la Oficina para listar sus clientes: ");
        int codOficina = teclado.nextInt();
        teclado.nextLine();
        
        for (Cliente c : db.query(Cliente.class)){
            if(c.getOficina().getCodOficina() == codOficina){
                System.out.println(c.toString());
            }
        }
    }
    
    // 9. Metodo para listar todos los clientes
    public static void listarClientes(ObjectContainer db){
        ArrayList<Cliente> lista = new ArrayList<>(db.query(Cliente.class));
        
        if(lista.isEmpty()){
            System.out.println("No hay clientes registrados en la base de datos");
        } else {
            
            // Se ordena la lista antes de imprimirla
            lista.sort((c1, c2) -> {
                Integer cod1 = c1.getOficina().getCodOficina();
                Integer cod2 = c2.getOficina().getCodOficina();
                return cod1.compareTo(cod2);
            });
            
            for (Cliente c : lista){
                System.out.println(c);
            }
        }
    }
    
    // 10. Metodo que muestra oficinas dentro de un intervalo introducido por el usuario
    public static void oficinasEntreObjetivo(ObjectContainer db){
        System.out.println("A continuación indica un intervalo.");
        System.out.print("Desde: ");
        int minimo = teclado.nextInt();
        
        System.out.print("Hasta: ");
        int maximo = teclado.nextInt();
        
        for (Oficina o : db.query(Oficina.class)){
            if (o.getObjetivo() >= minimo && o.getObjetivo() <= maximo){
                System.out.println(o);
            }
        }
        
    }
    
    // 11. Listar todas las oficinas
    public static void listarTodasOficinas(ObjectContainer db){
        ArrayList<Oficina> lista = new ArrayList<>(db.query(Oficina.class));
        
        if(lista.isEmpty()){
            System.out.println("No hay oficinas registradas en la base de datos.");
        }else {
            System.out.println("Todas las oficinas: ");
            for(Oficina o : lista){
                System.out.println(o.toString());
            }
        }
    }
    
}
