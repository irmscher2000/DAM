package com.prog03.figuras; 
// Declaro la clase Triangulo
public class Triangulo {
    // Declare atributos para la base y la altura de un triángulo.
    private double base;       
    private double altura;      
    // Declare atributos para los ángulos: anguloIzq y anguloDer
    private int anguloIzq; 
    private int anguloDer; 
    // Declare un constructor vacío que inicialice los atributos a 0.
    public Triangulo() {    // Declaro un constructor vacio
        this.base = 0;      // Inicializo a 0 los atributos
        this.altura = 0;
        this.anguloIzq = 0;
        this.anguloDer = 0;
    }
    
    // Declara un constructor que inicialice base, altura, anguloIzq y anguloDer.
    public Triangulo(double base, double altura, int anguloIzq, int anguloDer) {
        this.base = base;                   
        this.altura = altura;               
        this.anguloIzq = anguloIzq;         
        this.anguloDer = anguloDer;         
    }
    
    // Métodos para actualizar y obtener el valor de cada atributo
    public void setBase(double base){       
        this.base = base;
    }
    public double getBase(){                
        return base;                        
    }
    public void setAltura(double altura){   
        this.altura = altura;
    }
    public double getAltura(){              
        return altura;                      
    }
    public void setAnguloIzq(int anguloIzq){
        this.anguloIzq = anguloIzq;
    }
    public double getAnguloIzq(){           
        return anguloIzq;                   
    }
    public void setAnguloDer(int anguloDer){
        this.anguloDer = anguloDer;
    }
    public double getAnguloDer(){           
        return anguloDer;                   
    }
    // float getArea(): devuelve el área del triángulo.
    public float getArea(){
        return (float) ((base * altura) / 2); 
    }
    // float getAnguloSup(): devuelve el ángulo superior.
    public float getAnguloSup(){               
        return (float) (180 - (anguloIzq + anguloDer)); 
    }
    
    // String toString(): devuelve una cadena conteniendo su área (con dos números decimales), su altura y el ángulo superior
    public String toString(){
        return String.format("Área: %.2f, Altura: %.2f, Ángulo Superior: %.2f", getArea(), altura, getAnguloSup()); 
    }
    
    // boolean isEquilatero(): devuelve un booleano indicando si el triángulo es equilátero.
    public boolean isEquilatero(){
        return anguloIzq == 60 && anguloDer == 60 && getAnguloSup() == 60;      
    }    
}
