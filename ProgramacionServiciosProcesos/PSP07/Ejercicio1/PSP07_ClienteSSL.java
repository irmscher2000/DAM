// Eugen Moga
// PSP Tema 7 Cliente SSL

package psp07_clientessl;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/**
 * CLIENTE TLS - RECEPTOR DE CLAVES 3DES
 * 
 * Configuración usada:
 *   TrustStore: TrustSSL  (alias: clave_TSsl, contraseña: 123456)
 *   Servidor:   localhost:5000
 */
public class PSP07_ClienteSSL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        // Cargo el Truststore del cliente 
        // Fichero TrustSSL
        // Contiene el certificado del servidor para verificar que es legitimo.
        KeyStore ks = KeyStore.getInstance("JKS");
        try (FileInputStream fis = new FileInputStream("TrustSSL")){
            ks.load(fis, "123456".toCharArray());
        }
        
        
        // TrustManagerFactory envuelve el TrustStore para que lo use El SSLContext
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);
        
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, tmf.getTrustManagers(), null);
        
        // Conectar al servidor
        SSLSocket socket = 
                (SSLSocket) ctx.getSocketFactory().createSocket("localhost", 5000);
        System.out.println(" Conectado al servidor por TLS.");
        
        // Leeo la longitud del fichero
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        long longitud = entrada.readLong();
        System.out.println(" El servidor envia " + longitud + " bytes.");
        
        // Leeo todos los datos 
        // readFully garantiza que se leen todos los datos esperados 
        byte[] datos = new byte[(int) longitud];
        entrada.readFully(datos);
        System.out.println(" Datos recibidos correctamente.");
        
        // Guardo el fichero en local 
        FileOutputStream fos = new FileOutputStream("clave_recibida.key");
        fos.write(datos);
        fos.close();
        System.out.println(" La clave se ha guardado (clave_recibida.key)");
        
        socket.close();
    }
}
