/*
 * AD Tema 2 Ejercicio 4
 */
package ejercicio4;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;


/**
 * Este programa visualiza las etiquetas de un fichero XML ingresado como parametro
 * al ejecutar la aplicación utilizando la tecnica DOM
 * 
 * @author Eugen Moga
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Se comprueba que se ha insertado un parametro
        if (args.length != 1){
            System.out.println("Tienes que poner un parametro.");
            System.out.println("Ejemplo: java -jar ejercicio4.jar <datos.xml>");
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
            // Se carga el archivo XML en memoria
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            
            // Se normaliza el documento
            doc.getDocumentElement().normalize();
            
            System.out.println("Etiquetas encontradas en el documento XML");
            System.out.println("----------------------------------------");
            
            
            // Se muestra la etiqueta raiz
            System.out.println("<" + doc.getDocumentElement().getNodeName() + "> (elemento raiz)");
   
            // Se muestran todas las etiquetas
            NodeList nList = doc.getElementsByTagName("departamento");
            
            for (int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    
                    System.out.println("Código departamento: " + eElement.getAttribute("código"));
                    System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Empleados: " + eElement.getElementsByTagName("empleados").item(0).getTextContent());
                    
                    System.out.println();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
