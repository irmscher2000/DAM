
package ejercicio5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Manejador personalizado para eventos SAX que procesa el archivo XML
 * y extrae la información de los departamentos.
 * 
 * @author Eugen Moga
 */
public class Handler extends DefaultHandler {
    
    // StringBuilder para acumular el contenido textual de los elementos
    private StringBuilder value;
    
    // Variable para almacenar el codigo del departamento actual
    private String codigo;
    
    /**
     * Constructor de la clase Handler.
     * Inicializa el StringBuilder para almacenar valores.
     */
    public Handler(){
        this.value = new StringBuilder();
    }
    
    /**
     * Método invocado cuando se encuentra una etiqueta de apertura en el XML.
     * 
     * @param uri Namespace URI (no utilizado en este caso)
     * @param localName Nombre local (no utilizado)
     * @param qName Nombre de la etiqueta (ej: "departamento", "nombre", etc.)
     * @param attributes Lista de atributos de la etiqueta
     * @throws SAXException Si ocurre un error durante el parsing
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        // Se limpia el StringBuilder para comenzar a acumular nuevo contenido
        this.value.setLength(0);
        
        // Se limpia el StringBuilder para comenzar a acumular nuevo contenido
        if (qName.equals("departamento")){
            codigo = attributes.getValue("código");
            System.out.println("Código departamento: " + codigo);
        }
    }
    
    /**
     * Método invocado cuando se encuentra contenido textual dentro de las etiquetas.
     * 
     * @param ch Array de caracteres con el contenido
     * @param start Posición inicial en el array
     * @param length Longitud del contenido a leer
     * @throws SAXException Si ocurre un error durante el parsing
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        
        // Se acumula el contenido textual en el StringBuilder
        this.value.append(ch, start, length);
    }
    
    /**
     * Método invocado cuando se encuentra una etiqueta de cierre en el XML.
     * 
     * @param uri Namespace URI (no utilizado)
     * @param localName Nombre local (no utilizado)
     * @param qName Nombre de la etiqueta que se cierra
     * @throws SAXException Si ocurre un error durante el parsing
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        switch(qName){
            case "empresa":
                System.out.println("\n");
                break;
            case "nombre":
                System.out.println("Nombre: " + this.value.toString().trim());
                break;
            case "empleados":
                System.out.println("Empleados: " + this.value.toString().trim());
                System.out.println("\n");
                break;           
        }
        // Nota: No hay case para "departamento" porque su información
        // se maneja en startElement con el atributo código
    } 
}
