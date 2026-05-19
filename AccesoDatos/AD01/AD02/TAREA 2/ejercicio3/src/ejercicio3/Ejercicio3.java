/*
 * AD Tema 2 Ejercicio 3
 */
package ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * Este programa lee los datos de un documento txt que se introduce como parametro
 * y genera un fichero de datos en formato XML
 * 
 * @author Eugen Moga
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // Se comprueba que se ha insertad correctamente los parametros para ejecutar el archivo
        if (args.length != 2){
            System.out.println("Tienes que poner dos parametros.");
            System.out.println("Ejemplo: java -jar ejercicio1.jar <entrada.txt> <salida.xml>");
            return;
        }
        
        File entrada = new File(args[0]);
        File salida = new File(args[1]);
        
        // Se comprueba que el archivo de entrada existe 
        if (!entrada.exists()){
            System.out.println("Error: el archivo de entrada no existe");
            return;
        }
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            
            // Se crea el documento XML en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Elemento raiz
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("empresa");
            doc.appendChild(rootElement);
            
            // Salta el encabezado para ignorar la primera linea (Codigo, Departameto, Numero) del documento txt
            br.readLine();
            
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.trim().isEmpty()) continue;
                
                String[] datos = linea.split("\\s+");
                if (datos.length >= 3){
                    
                    String codigo = datos[0];
                    String nombre = datos[1];
                    String empleados = datos[2];
                    
                    // Departamento
                    Element departamento = doc.createElement("departamento");
                    rootElement.appendChild(departamento);
                    
                    Attr attrCodigo = doc.createAttribute("código");
                    attrCodigo.setValue(codigo);
                    departamento.setAttributeNode(attrCodigo);
                    
                    // Nombre
                    Element eNombre = doc.createElement("nombre");
                    eNombre.setTextContent(nombre);
                    departamento.appendChild(eNombre);
                    
                    // Empleados
                    Element eEmpleados = doc.createElement("empleados");
                    eEmpleados.setTextContent(empleados);
                    departamento.appendChild(eEmpleados);
                    
                }
            }
            br.close();
            
            // Se guarda el XML en fichero
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            
            // Dar formato indentado
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(salida);
            transformer.transform(source, result);
            
            System.out.println("XML generado correctamente: " + salida.getAbsolutePath());
            
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }catch (TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
