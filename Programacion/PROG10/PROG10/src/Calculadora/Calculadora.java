// Eugen Moga
// Programacion Tarea Tema 10

package Calculadora;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Clase Calculadora que estiende de la clase javafx.application.Application
    es la clase base para aplicaciones JavaFx
*/
public class Calculadora extends Application {
    
    /**
     * Método start es el punto de entrada para aplicaciones JavaFX
     * Este método se llama automaticamente cuando se inicia la aplicación
     * 
     * @param primaryStage Escenario principal de la aplicacion 
     * @throws IOException Si ocurre un error al cargar el arhivo FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // Cargar el archivo FXML que define la interfaz gráfica de la calculadora.
        // FXMLLoader.load carga el archivo Calculadora.fxml y devuelve un objeto Parent
        // que representa la jerarquía de nodos de la interfaz grafica.
        Parent root = FXMLLoader.load(getClass().getResource("Calculadora.fxml"));
        
        // Crear una nueva escena con la jerarquía de nodos cargada desde el FXML.
        Scene scene = new Scene(root);
        
        // Se establece el título de la ventana principal.
        primaryStage.setTitle("Calculadora");
        
        // Se asigna la escena al escenario principal
        primaryStage.setScene(scene);
        
        // Muestra la ventana principal
        primaryStage.show();
    }

    /**
     * Método main es el punto de entrada de la aplicación.
     * Llama a launch para iniciar la aplicacion JavaFX
     * 
     * @param args Argumentos de la linea de comandos (no se utiliza en este caso)
     */
    public static void main(String[] args) {
        
        // El método launch inicializa la aplicacion y llama automaticamente al método start
        launch(args);
    }
}
