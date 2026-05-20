/*
 * AD Tema 2 Ejercicio 5
 */
package ejercicio5;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.io.File;

/**
 * Este programa visualiza todas las etiquetas de un fichero XML 
 * ingresado como parametro al ejecutar la aplicacion utilizando las tecnicas SAX
 * 
 * @author Eugen Moga
 */
public class Ejercicio5 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
       
        // Se comprueba que se ha insertado un parametro
        if (args.length != 1){
            System.out.println("Tienes que poner un parametro.");
            System.out.println("Ejemplo: java -jar ejercicio5.jar <datos.xml>");
            return;
        }
        
        // Se asocia el archivo xml que se ha introducido como parametro
        File archivoXML = new File(args[0]);
        
        // Se muestra el error en caso de que el archivo XML no exista
        if (!archivoXML.exists()){
            System.out.println("Error: El archivo XML no existe. ");
            return;
        }
        
        try {
            System.out.println("\n");
            System.out.println("---------------------------");
            System.out.println("Contenido del documento XML");
            System.out.println("---------------------------");
            
            // Se crea el parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            // Se crea el manejador SAX
            Handler handler = new Handler();
            saxParser.parse(archivoXML, handler);
        }catch (Exception e){
            e.printStackTrace();
        } 
    }    
}
