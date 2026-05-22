// Eugen Moga
// PSP Tema 5 Ejercicio 1

package ejercicio1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

public class Ejercicio1 {

    static final String URL_WEB_SIN_PDF = "https://es.wikipedia.org/wiki/Programaci%C3%B3n_orientada_a_objetos"; // Enlace sin archivos pdf
    static final String URL_WEB_CON_PDF = "https://commons.wikimedia.org/wiki/File:An_introduction_to_object-oriented_programming_-_(by)_Michael_L._Nelson._(IA_introductiontoob00nels).pdf";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        // Creo el array para guardar los enlaces PDF encontrados
        ArrayList<String> listaPDF = new ArrayList<>();
        
        try {
            // Creo el objeto URL
            URL url = new URL(URL_WEB_CON_PDF);
            
            // Abro la conexion
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            
            // Se necesita indicar user agent para poder abrir la conexion
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            // Leo el html de la pagina
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            
            String linea;
            
            // Busco enlaces que contengan .pdf
            while ((linea = lector.readLine()) != null){
                if (linea.contains(".pdf")) {
                    
                    int inicio = linea.indexOf("href=\"");
                    if (inicio != -1){
                        inicio += 6;    // Salto href=
                        int fin = linea.indexOf("\"", inicio);
                        
                        String enlace = linea.substring(inicio, fin);
                        
                        // Enlace relativo lo convierto en absoluto
                        if(!enlace.startsWith("http")){
                            enlace = "https://es.wikipedia.org" + enlace;
                        }
                        
                        // Me aseguro que el enlace termina en .pdf
                        if (enlace.endsWith(".pdf")) {
                            listaPDF.add(enlace);
                        }
                    }
                }
            }
            lector.close();
            
            // Si no hay pdf muestro el error
            if(listaPDF.isEmpty()){
                System.out.println("Error la pagina no contiene archivos pdf");
                return;
            }
            
            // Descargo cada pdf encontrado
            for(String pdf : listaPDF){
                descargarPDF(pdf);
            }
            
            System.out.println("Descarga finalizada");
            
        } catch (Exception e) {
            System.out.println("Error al acceder a la pagina web");
        }
    }
    
    /* 
    Metodo para descargar un archivo PDF desde una URL
    */
    private static void descargarPDF(String urlPDF){
        
        try {
            URL url = new URL(urlPDF);
            
            // Nombre del archivo PDF
            String nombreArchivo = urlPDF.substring(urlPDF.lastIndexOf("/") + 1);
            
            // Flujo de entrada desde internet
            InputStream entrada = url.openStream();
            
            // Flujo de salida al archivo local
            FileOutputStream salida = new FileOutputStream(nombreArchivo);
            
            byte[] buffer = new byte[1024];
            int bytesLeidos;
            
            // Se lee y se escribe el archiov
            while ((bytesLeidos = entrada.read(buffer)) != -1){
                salida.write(buffer, 0, bytesLeidos);
            }
            
            entrada.close();
            salida.close();
            
            System.out.println("PDF descargado: " + nombreArchivo);
            
        } catch (Exception e) {
            System.out.println("Error al descargar el PDF: " + urlPDF);
        }
    }
    
}
