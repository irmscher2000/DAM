
// Eugen Moga
// PSP Tema 7 Servidor SSL

package psp07_servidorssl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

/**
 * SERVIDOR SSL - GENERADOR DE CLAVES 3DES
 * 
 * Este programa espera conexion cifrada de clientes, 
 * Genera una clave 3DES y la guarda en un fichero binario temporal
 * 
 * Cuando el cliente se conecta le envia la longitud del fichero y luego los bytes
 * 
 */
public class PSP07_ServidorSSL {
    
    public static void main(String[] args) throws Exception {
        
        // Cargo el keystore del servidor
        KeyStore ks = KeyStore.getInstance("JKS");
        try (FileInputStream fis = new FileInputStream("AlmacenSSL")){
            ks.load(fis, "123456".toCharArray());
        }
        
        
        // KeyManagerFactory envuelve el KeyStore para que lo pueda usar el SSLContext
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, "123456".toCharArray());
        
        // SSLContext es el motor TLS. Lo inicio con el certificado 
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), null, null);
        
        // Creo el socket seguro y espero la conexion del cliente
        SSLServerSocket servidor = 
                (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(5000);
        
        System.out.println(" Servidor escuchando en el puerto 5000 ...");
        
        // Creo el bucle infinito para atender un cliente y luego esperar al siguiente
        while (true){
            try (SSLSocket cliente = (SSLSocket) servidor.accept()) {
                System.out.println(" Cliente conectado.");
                atenderCliente(cliente);
            }catch (Exception e){
                System.out.println(" Error atendiendo el cliente: " + e.getMessage());
            }
        }
    }

    /**
     * Genero la clave 3DES, la guardo en un fichero.  
     * Envio la clave al cliente. 
     * Y elimino el fichero
     * 
     * @param cliente 
     */
    public static void atenderCliente(SSLSocket cliente) throws Exception{
        
        // Genero la clave
        // DESede es el nombre Java para 3DES
        KeyGenerator gen = KeyGenerator.getInstance("DESede");
        gen.init(168);
        SecretKey clave = gen.generateKey();
        System.out.println(" Clave 3DES generada con una longitud " + clave.getEncoded().length + " bytes");
        
        // Guardo la clave en un fichero binario 
        // Con getEncode devuelvo los bytes de la clave en formato binario 
        File fichero = new File("clave.key");
        try (FileOutputStream fos = new FileOutputStream(fichero)){
            fos.write(clave.getEncoded());
        }
        
        System.out.println(" Clave guardada en el fichero temporal");
        
        // Envio la longitud del fichero 
        DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
        salida.writeLong(fichero.length());
        salida.flush();
        System.out.println(" Longitud enviada: " +fichero.length() + " bytes.");
        
        // Envio el contenido del fichero
        try (  
            // Leo el fichero por bloques y lo vuelco al socket cifrado por TLS
            FileInputStream lectorFichero = new FileInputStream(fichero)) {
            byte[] buffer = new byte[1024];
            int leidos;
            while ((leidos = lectorFichero.read(buffer)) != -1){
                salida.write(buffer, 0, leidos);
            }
        }
        salida.flush();
        System.out.println(" Fichero enviado");
        
        
        // Borro el fichero 
        fichero.delete();
        System.out.println(" Fichero borrado");
    }
}
