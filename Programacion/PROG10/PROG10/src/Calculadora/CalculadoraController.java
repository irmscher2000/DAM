// Eugen Moga
// Programacion Tarea Tema 10

package Calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controlador de la calculadora
 * Esta clase maneja la logica de la interfaz gráfica de la calculadora
 */
public class CalculadoraController implements Initializable {

    @FXML
    private Label pantalla;      // Etiqueta que muestra los numeros y los resultados
    
    // Estilos CSS para despues de reiniciar la calculadora 
    private static final String ESTILO_NORMAL = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 48px;";
    private static final String ESTILO_ERROR = "-fx-background-color: #FFCCCB; -fx-text-fill: red; -fx-font-size: 30px;";
    
    // Variables de estado
    private boolean esDigito = false;               // Indico si se ha ingresado un digito
    private boolean coma = false;                   // Indico si se ha ingresado una coma decimal
    private boolean esNuevoOperador = true;         // Indico si se debe reiniciar la pantalla para un nuevo operador
    private double primerOperando, segundoOperando; // Almaceno los operandos    
    private char operadorActual = ' ';              // Almaceno el operador actual (+, -, *, /) 

    /**
     * Inicializo el controlador
     * Este método lo llamo automaticamente despues de cargar el archivo Calculadora.FXML
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pantalla.setText("0");       // Inicializo la pantalla en 0
        pantalla.setStyle(ESTILO_NORMAL);
    }    

    // Métodos para registrar los clics en los botones numéricos
    @FXML
    private void clicSiete(ActionEvent event) {
        digitoPantalla("7");
    }

    @FXML
    private void clicOcho(ActionEvent event) {
        digitoPantalla("8");
    }

    @FXML
    private void clicNueve(ActionEvent event) {
        digitoPantalla("9");
    }

    @FXML
    private void clicCuatro(ActionEvent event) {
        digitoPantalla("4");
    }

    @FXML
    private void clicCinco(ActionEvent event) {
        digitoPantalla("5");
    }

    @FXML
    private void clicSeis(ActionEvent event) {
        digitoPantalla("6");
    }

    @FXML
    private void clicUno(ActionEvent event) {
        digitoPantalla("1");
    }

    @FXML
    private void clicDos(ActionEvent event) {
        digitoPantalla("2");
    }

    @FXML
    private void clicTres(ActionEvent event) {
        digitoPantalla("3");
    }

    @FXML
    private void clicCero(ActionEvent event) {
        digitoPantalla("0");
    }

    @FXML
    private void clicComa(ActionEvent event) {
        if(!coma ){
            String valorActual = pantalla.getText();
            pantalla.setText(valorActual + ",");       
            coma = true;
        }
    }
    
    @FXML
    private void clicBorrar(ActionEvent event) {
        reiniciarCalculadora();
    }

    // Calculo la potencia obteniendo el numero que hay en pantalla y cambiando la compa por el punto
    // porque en java el punto separa los decimales y no la coma.
    // despues de obtener el resultado vuelvo a cambiar el punto por la coma para que se imprima por pantalla
    @FXML
    private void clicPotencia(ActionEvent event) {
        if (esDigito){
            segundoOperando = Double.parseDouble(pantalla.getText().replace(",", "."));
            primerOperando = Math.pow(segundoOperando, 2);      // calculo el cuadrado
            pantalla.setText(String.valueOf(primerOperando).replace(".", ","));
            esNuevoOperador = true;
            esDigito = true;
            coma = false;
        }
    }

    // Calculo de raiz cuadrada.
    @FXML
    private void clicRaizCuadrada(ActionEvent event) {
        if (esDigito){
            segundoOperando = Double.parseDouble(pantalla.getText().replace(",", "."));
            primerOperando = Math.sqrt(segundoOperando);   // Calculo la raiz cuadrada
            pantalla.setText(String.valueOf(primerOperando).replace(".", ","));
            esNuevoOperador = true;
            esDigito = true;
            coma = false;
        }
    }

    // Metodos que evaluan los operadores simples sumar, restar, multiplicar y dividir
    @FXML
    private void clicDividir(ActionEvent event) {
        evaluarOperador('÷');
    }

    @FXML
    private void clicMultiplicar(ActionEvent event) {
        evaluarOperador('x');
    }

    @FXML
    private void clicRestar(ActionEvent event) {
        evaluarOperador('-');
    }

    @FXML
    private void clicSumar(ActionEvent event) {
        evaluarOperador('+');
    }

    @FXML
    private void clicIgual(ActionEvent event) {
        evaluarOperador('=');
    }
    
    // Método para calcular signo mas - menos
    @FXML
    private void clicValor(ActionEvent event) {
        String valorActual = pantalla.getText();
        if (valorActual.charAt(0) == '-'){
            pantalla.setText(valorActual.substring(1));     // Elimino el signo negativo
        }else {
            pantalla.setText("-" + valorActual);            // Agrego el signo negativo
        }
    }
    
    /**
     * Agrega un dígito a la pantalla.
     * @param numero El dígito a agregar.
     */
    private void digitoPantalla(String numero){
        if(esNuevoOperador){
            pantalla.setText("0");
            esNuevoOperador = false;
            coma = false;
        }
        
        String valorActual = pantalla.getText();
        if(valorActual.equals("0") && !numero.equals(",")){
            valorActual = "";
        }
        pantalla.setText(valorActual + numero);
        esDigito = true;
    }
    
    /**
     * Evalúa la operación con el operador especificado.
     * @param operador El operador a evaluar (+, -, *, /, =).
     */
    private void evaluarOperador(char operador){
        if (esDigito){
            segundoOperando = Double.parseDouble(pantalla.getText().replace(",", "."));
            if (!esNuevoOperador){
                switch(operadorActual){
                case '+':
                    primerOperando = primerOperando + segundoOperando;
                    break;
                case '-':
                    primerOperando = primerOperando - segundoOperando;
                    break;
                case 'x':
                    primerOperando = primerOperando * segundoOperando;
                    break;
                case '÷':
                    if (segundoOperando != 0){
                        primerOperando = primerOperando / segundoOperando;
                    } else {
                        mostrarError();
                        return;
                    }
                    break;
                default:
                    primerOperando = segundoOperando;
                    break;
            }
            pantalla.setText(String.valueOf(primerOperando).replace(".", ","));
            }else {
                primerOperando = segundoOperando;
            }
            esNuevoOperador = true;
            esDigito = true;
            coma = false;
        }
        operadorActual = operador;
    }
    
    // Muestro el mensaje de error en pantalla
    private void mostrarError(){
        pantalla.setText("Error, pulse C para borrar ");
        pantalla.setStyle(ESTILO_ERROR); // Muestro el texto en rojo del error
    }
    
    // Reinicio la calculadora a su estado inicial
    private void reiniciarCalculadora(){
        pantalla.setText("0");
        pantalla.setStyle(ESTILO_NORMAL);
        esDigito = false;
        coma = false;
        esNuevoOperador = true;
        primerOperando = 0;
        segundoOperando = 0;
        operadorActual = ' ';
    }
}
