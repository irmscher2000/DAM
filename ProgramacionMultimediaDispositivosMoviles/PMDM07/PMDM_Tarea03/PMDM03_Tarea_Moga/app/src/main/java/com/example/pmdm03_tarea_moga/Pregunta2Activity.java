package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Pregunta2Activity extends AppCompatActivity {

    // VARIABLES DE CONFIGURACIÓN
    private int respuestaCorrecta = 2; // La opción correcta es C) Madrid (posición 2 en el array)
    private int puntuacionAcumulada = 0;
    private String nombreUsuario;
    private boolean modoPractica;
    private boolean respuestaValidada = false;
    private boolean modoRevision = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la pregunta 2
        setContentView(R.layout.pregunta2);

        // OBTENCIÓN DE DATOS DE LA ACTIVIDAD ANTERIOR
        // Recibe los datos enviados desde Pregunta1Activity
        Intent intentRecibido = getIntent();
        nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);
        puntuacionAcumulada = intentRecibido.getIntExtra("puntuacion", 0);
        modoRevision = intentRecibido.getBooleanExtra("modo_revision", false);

        // CONFIGURACIÓN DEL SPINNER (LISTA DESPLEGABLE) DE RESPUESTAS
        Spinner spinnerRespuestas = findViewById(R.id.spinner_respuestas);
        // Opciones de las respuestas para la pregunata 2
        String[] opcionesRespuestas = {"A) Barcelona", "B) Sevilla ", "C) Madrid ", "D) Valencia "};

        // Adaptador para mostrar las opciones en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesRespuestas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRespuestas.setAdapter(adapter);

        // LÓGICA ESPECIAL PARA MODO REVISIÓN
        if (modoRevision) {
            // Obtener la respuesta que dio el usuario anteriormente para esta pregunta
            int respuestaUsuario = intentRecibido.getIntExtra("respuesta_pregunta2", 0);
            spinnerRespuestas.setSelection(respuestaUsuario);  // Seleccionar su respuesta anterior
            spinnerRespuestas.setEnabled(false); // Deshabilitar el Spinner en modo revisión

            // Cambiar color del fondo según si acertó o no
            if (respuestaUsuario == respuestaCorrecta) {
                spinnerRespuestas.setBackgroundResource(R.drawable.spinner_correcto);
            } else {
                spinnerRespuestas.setBackgroundResource(R.drawable.spinner_incorrecto);
            }
        }

        // BOTÓN "ATRÁS" (NAVEGACIÓN A PREGUNTA ANTERIOR)
        Button botonAtras = findViewById(R.id.atras);
        botonAtras.setOnClickListener( v ->  {

            // Crear Intent para volver a Pregunta1Activity
            Intent intent = new Intent(Pregunta2Activity.this, Pregunta1Activity.class);
            // Reenviar datos básicos necesarios
            intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
            intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
            intent.putExtra("puntuacion", puntuacionAcumulada);

            // Si está en modo revisión, enviar información adicional
            if (modoRevision){
                intent.putExtra("modo_revision", true);  // Indicar que sigue en modo revisión
                // Reenviar todas las respuestas para mantener consistencia
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));
            }

            startActivity(intent);
            finish();
        });

        // BOTÓN "SIGUIENTE" (NAVEGACIÓN Y VALIDACIÓN)
        Button botonSiguiente = findViewById(R.id.siguiente);
        botonSiguiente.setOnClickListener(v ->  {
            // Obtener la respuesta seleccionada por el usuario en esta pregunta
            int posicionSeleccionada = spinnerRespuestas.getSelectedItemPosition();

            // VALIDACIÓN DE RESPUESTA (solo en modo normal)
            if (!modoRevision && !respuestaValidada){
                // Si la respuesta es correcta, sumar 1 punto a la puntuación acumulada
                if (posicionSeleccionada == respuestaCorrecta){
                    puntuacionAcumulada += 1;
                }
                respuestaValidada = true;  // Marcar como validada para evitar duplicados
            }

            // PREPARAR INTENT PARA SIGUIENTE ACTIVIDAD (Pregunta3)
            Intent intent = new Intent(Pregunta2Activity.this, Pregunta3Activity.class);
            // Enviar datos básicos
            intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
            intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
            intent.putExtra("puntuacion", puntuacionAcumulada);

            // ENVÍO DE DATOS SEGÚN MODO
            if (!modoRevision) {
                // MODO NORMAL: Enviar respuestas de preguntas 1 y 2
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", posicionSeleccionada);
            } else {
                // MODO REVISIÓN: Reenviar todas las respuestas guardadas
                intent.putExtra("nombre_usuario", nombreUsuario);
                intent.putExtra("modo_practica", modoPractica);
                intent.putExtra("modo_revision", true);
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));
            }

            startActivity(intent);
            finish();
        });
    }
}

