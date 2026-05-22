// Eugen Moga
// PSP Tema 5 Ejercicio 2 Servidor FTP

package servidorftp;

import java.io.File;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 *
 * @author Moga
 */
public class ServidorFTP {

    /**
     * Esta clase la utilizo para generar un servidor FTP
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            // Carpeta para compartir por FTP
            File carpetaFTP = new File("ftp");
            carpetaFTP.mkdirs();
            
            // Configuracion del servidor
            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            factory.setPort(2121);
            serverFactory.addListener("default", factory.createListener());
            
            // Gestor de usuarios
            PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
            userManagerFactory.setFile(new File("usuarios.properties"));
            
            // Se crea el usuario
            BaseUser anonymous = new BaseUser();
            anonymous.setName("anonymous");
            anonymous.setHomeDirectory(carpetaFTP.getAbsolutePath());
            anonymous.setEnabled(true);
            
            // Guardo el usuario en el gestor de usuarios del servidor
            serverFactory.getUserManager().save(anonymous);
            
            // Crear y arrancar servidor FTP
            FtpServer server = serverFactory.createServer();
            server.start();
            
            System.out.println("Servidor FTP iniciado en el puerto 2121");
            
        } catch (Exception e) {
            System.out.println("Error al iniciar le servidor FTP: " + e.getMessage());
        }
    }
}
