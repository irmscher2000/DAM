
package ejercicio2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileOutputStream;

/**
 * Cliente FTP
 * 
 * @author Moga
 */
public class Ejercicio2 {
    
    // Objeto FTPClient de Apache
    private static FTPClient clienteFTP;
    
    private static final int PUERTO_FTP = 2121;
    
    private static String usuario = "anonymous";
    
    private static String password = "";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length !=2){
            System.out.println("Error, debes introducir dos parametros");
            System.out.println("Uso: java ClienteFTP <servidor> <fichero>");
            return;
        }
        
        String servidor = args[0];
        String fichero = args[1];
        
        try {
            // Creacion del objeto cliente FTP
            clienteFTP = new FTPClient();
            
            // Creacion del cliente al servidor FTP
            clienteFTP.connect(servidor, PUERTO_FTP);
            
            // Compruebo si la conexion es satisfactoria
            if(!FTPReply.isPositiveCompletion(clienteFTP.getReplyCode())){
                System.out.println("No se pudo conectar al servidor FTP ");
                clienteFTP.disconnect();
                return;
            }
            
            // Inicio sesion con el usuario anonimo
            clienteFTP.login(usuario, password);
            
            // Configuracion
            clienteFTP.enterLocalPassiveMode();
            clienteFTP.setFileType(FTPClient.BINARY_FILE_TYPE);
            
            // Flujo de salida
            FileOutputStream salida = new FileOutputStream(fichero);
            
            boolean descargado = clienteFTP.retrieveFile(fichero, salida);
            salida.close();
            
            if (descargado){
                System.out.println("Fichero descargado correctamente");
            }else {
                System.out.println("Error el fichero no existe en el servidor");
            }
            
            clienteFTP.logout();
            clienteFTP.disconnect();
                
        } catch (Exception e) {
            // Error del socket
            System.out.println(e.toString());
        }
    }
}
