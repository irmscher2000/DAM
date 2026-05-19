/*
 * AD Tema 2 Ejercicio 6
 */
package ejercicio6;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.InputMismatchException;

/**
 * Este programa maneja el documento xml introducido por parametro para 
 * consultar, agregar, modificar o eliminar informacion sobre los departamentos 
 * de una empresa
 * 
 * @author Eugen Moga
 */
public class Ejercicio6 {

    private static String archivoXML;
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Método principal que muestra el menú y gestiona las opciones
     * 
     * @param args Argumentos de línea de comandos: archivo XML
     */
    public static void main(String[] args) throws TransformerException, SAXException, IOException, ParserConfigurationException {
        
        // Se comprueba que se ha insertado un parametro
        if (args.length != 1){
            System.out.println("Tienes que poner un archivo XML como parametro.");
            System.out.println("Ejemplo: java -jar ejercicio6.jar <datos.xml>");
            return;
        }
        
        // Se asocia el archivo xml que se ha introducido como parametro
        archivoXML = args[0];
        
        // Se muestra el error en caso de que el archivo XML no exista
        File file = new File(archivoXML);
        if (!file.exists()){
            System.out.println("Error: El archivo XML no existe. ");
            return;
        }
        
        int opcion;
        do{
            try{
                mostrarMenu();
                System.out.println("Selecciona la opción deseada: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion){
                    case 1:
                        añadirDepartamento();
                        break;
                    case 2:
                        consultarDepartamento();
                        break;
                    case 3:
                        modificarDepartamento();
                        break;
                    case 4:
                        eliminarDepartamento();
                        break;
                    case 0:
                        System.out.println("Cerrando el programa...");
                        break;
                    default:
                        System.out.println("Error: Opcion no valida, selecciona una opción del menu.");
                }
                System.out.println();
            }catch (InputMismatchException e){
                System.out.println("Ingresa un número valido.");
                sc.nextLine();
                opcion = -1;
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
                opcion = -1;
            }
            
        }while (opcion != 0);
        
        sc.close();    
    }
    
    /**
     * Muestra el menú principal de opciones
     */
    private static void mostrarMenu(){
        System.out.println("----------------------------------------");
        System.out.println("    Gestión Departamentos ");
        System.out.println("----------------------------------------");
        System.out.println(" 1. Añadir un nuevo departamento.");
        System.out.println(" 2. Consultar los datos de un departamento.");
        System.out.println(" 3. Modificar los datos de un departamento.");
        System.out.println(" 4. Eliminar un departamento.");
        System.out.println(" 0. Salir y cerrar el programa.");
    }
    
    /**
     * Añade un nuevo departamento al archivo XML
     * Genera automáticamente el código del departamento
     */
    private static void añadirDepartamento() throws TransformerException, SAXException, IOException, ParserConfigurationException{
        try{
            // Se carga el documento XML
            Document doc = cargarDocumento();
            
            // Se genera nuevo codigo, (codigo actual + 1)
            int nuevoCodigo = generarNuevoCodigo(doc);
            
            // Se piden datos al usuario
            System.out.println("Ingresa el nombre del Departamento:");
            String nombre = sc.nextLine();
            
            System.out.println("Ingresa el numero de empleados:");
            String empleados = sc.nextLine();
            
            // Se crea nuevo elemento departamento
            Element nuevoDepartamento = doc.createElement("departamento");
            nuevoDepartamento.setAttribute("código", String.valueOf(nuevoCodigo));
            
            // Se crea el elemento nombre
            Element eNombre = doc.createElement("nombre");
            eNombre.appendChild(doc.createTextNode(nombre));
            nuevoDepartamento.appendChild(eNombre);
            
            // Se crea el elemento empleados
            Element eEmpleados = doc.createElement("empleados");
            eEmpleados.appendChild(doc.createTextNode(empleados));
            nuevoDepartamento.appendChild(eEmpleados);
            
            // Se añade al elemento raiz
            doc.getDocumentElement().appendChild(nuevoDepartamento);
            
            // Se guardan los cambios
            guardarDocumento(doc);
            
            System.out.println("El departamento con código: " + nuevoCodigo + " se ha añadido correctamente.");
            
        }catch (Exception e){
            System.out.println("Error al añadir departamento: " + e.getMessage());
        }
    }
    
    /**
     * Consulta los datos de un departamento por su código
     */
    private static void consultarDepartamento(){
        try{
            Document doc = cargarDocumento();
            NodeList departamentos = doc.getElementsByTagName("departamento");
            
            // He preferido listar primero los departamentos para que se pueda ver que departamentos hay disponibles 
            System.out.println("--- Departamentos disponibles---");
            for (int i = 0; i < departamentos.getLength(); i++){
                Element dept = (Element) departamentos.item(i);
                String codigo = dept.getAttribute("código");
                String nombre = dept.getElementsByTagName("nombre").item(0).getTextContent();
                
                System.out.println(" " + codigo + " - " + nombre);
            }
            System.out.println("------------------------------");
            
            System.out.println("Introduce el código del departamento que quieres consultar: ");
            String codigo = sc.nextLine();
            
            
            
            boolean encontrado = false;
            for (int i =0; i < departamentos.getLength(); i++){
                Element dept = (Element) departamentos.item(i);
                if (dept.getAttribute("código").equals(codigo)){
                    String nombre = dept.getElementsByTagName("nombre").item(0).getTextContent();
                    String empleados = dept.getElementsByTagName("empleados").item(0).getTextContent();
                    
                    System.out.println("\n--- Datos del Departamento ---");
                    System.out.println("Código: " + codigo);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Empleados: " + empleados);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado){
                System.out.println("No se ha encontrado ningun departamento con el código: " + codigo);
            }
        }catch (Exception e){
            System.out.println("Error al consultar departamento: " + e.getMessage());
        }
    }
    
    /**
     * Modifica el número de empleados de un departamento
     */
    private static void modificarDepartamento(){
        try{
            System.out.println("Introduce el código del departamento que quieres modificar: ");
            String codigo = sc.nextLine();
            
            Document doc = cargarDocumento();
            NodeList departamentos = doc.getElementsByTagName("departamento");
            
            boolean encontrado = false;
            for (int i = 0; i < departamentos.getLength(); i++){
                Element dept = (Element) departamentos.item(i);
                if (dept.getAttribute("código").equals(codigo)){
                    String nombre = dept.getElementsByTagName("nombre").item(0).getTextContent();
                    String empleadosActual = dept.getElementsByTagName("empleados").item(0).getTextContent();
                    
                    System.out.println("Departamento: " + nombre);
                    System.out.println("Empleados actuales: " + empleadosActual);
                    
                    // Se pide el nuevo número de empleados
                    System.out.println("Ingresa el nuevo número de empleados");
                    String nuevosEmpleados = sc.nextLine();
                    
                    // Se actualiza el valor
                    dept.getElementsByTagName("empleados").item(0).setTextContent(nuevosEmpleados);
                    
                    // Se guadan los cambios
                    guardarDocumento(doc);
                    
                    System.out.println("Departamento actualziado correctamente. ");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado){
                System.out.println("No se encontro ningun departamento con el código: " + codigo);
            }
        }catch (Exception e){
            System.out.println("Error al modificar el departamento: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un departamento del archivo XML
     */
    private static void eliminarDepartamento(){
        try{
            System.out.println("Introduce el código del departamento a eliminar: ");
            String codigo = sc.nextLine();
            
            Document doc = cargarDocumento();
            NodeList departamentos = doc.getElementsByTagName("departamento");
            
            boolean encontrado = false;
            for (int i = 0; i < departamentos.getLength(); i++){
                Element dept = (Element) departamentos.item(i);
                if (dept.getAttribute("código").equals(codigo)){
                    
                    // Se muestra la informacion antes de eliminar
                    String nombre = dept.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("Confirma que deseas eliminar el departamento: " + nombre + "(s/n)");
                    
                    String confirmacion = sc.nextLine();
                    
                    if (confirmacion.equalsIgnoreCase("s")){
                        // Se elimina el nodo
                        doc.getDocumentElement().removeChild(dept);
                        guardarDocumento(doc);
                        System.out.println("El departamento " + nombre + " se ha eliminado correctamente.");
                    }else {
                        System.out.println("Eliminación cancelada.");
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado){
                System.out.println("No se ha encontrado ningun departamento con el código " + codigo);
            }
        }catch (Exception e){
            System.out.println("Error al eliminar el departamento " + e.getMessage());
        }
    }
    
    
    /**
     * Carga el documento XML desde el archivo
     * 
     * @return Documento XML cargado
     * @throws Exception Si hay error al cargar el documento
     */
    private static Document cargarDocumento() throws SAXException, IOException, ParserConfigurationException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(archivoXML));
        doc.getDocumentElement().normalize();
        return doc;
    }
    
    /**
     * Guarda el documento XML en el archivo
     * 
     * @param doc Documento XML a guardar
     * @throws Exception Si hay error al guardar
     */
    private static void guardarDocumento(Document doc) throws TransformerException{
        
        // Se limpian los espacion en blanco antes de guardar
        doc.normalizeDocument();
        eliminarEspacios(doc.getDocumentElement());
        
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(archivoXML));
        transformer.transform(source, result);      
    }
    
    /**
     * Genera un nuevo código de departamento (máximo actual + 1)
     * 
     * @param doc Documento XML
     * @return Nuevo código de departamento
     */
    private static int generarNuevoCodigo(Document doc){
        NodeList departamentos = doc.getElementsByTagName("departamento");
        int maxCodigo = 0;
        
        for (int i = 0; i < departamentos.getLength(); i++){
            Element dept = (Element) departamentos.item(i);
            int codigo = Integer.parseInt(dept.getAttribute("código"));
            if (codigo > maxCodigo){
                maxCodigo = codigo;
            }
        }
        return maxCodigo + 1;
    }
    
    /**
     * Metodo para eliminar los espacion en blanco que se genera cada vez que se ejecuta el 
     * archivo xml.
     */
    private static void eliminarEspacios(Element element){
        NodeList hijos = element.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++){
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE){
                if (hijo.getTextContent().trim().isEmpty()){
                    element.removeChild(hijo);
                    i--;
                }
            }else if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                eliminarEspacios((Element) hijo);
            }
        }
    }
}
