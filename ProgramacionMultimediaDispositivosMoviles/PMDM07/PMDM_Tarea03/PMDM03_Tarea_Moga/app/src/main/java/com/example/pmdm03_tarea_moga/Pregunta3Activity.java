package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pregunta3Activity extends AppCompatActivity {

    // VARIABLES DE CONFIGURACIÓN
    private int respuestaCorrecta = R.id.respuesta2; // La opción correcta es B) Miguel de Cervantes
    private int puntuacionAcumulada = 0;
    private String nombreUsuario;
    private boolean modoPractica;
    private boolean respuestaValidada = false;
    private boolean modoRevision = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la pregunta 3 (sobre Don Quijote)
        setContentView(R.layout.pregunta3);

        // OBTENCIÓN DE DATOS DE LA ACTIVIDAD ANTERIOR
        // Recibe los datos enviados desde Pregunta2Activity
        Intent intentRecibido = getIntent();
        nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);
        puntuacionAcumulada = intentRecibido.getIntExtra("puntuacion", 0);  // Puntuación acumulada desde preguntas 1 y 2
        modoRevision = intentRecibido.getBooleanExtra("modo_revision", false);

        // CONFIGURACIÓN DEL RADIOGROUP (SELECCIÓN ÚNICA)
        RadioGroup radioGroupRespuestas = findViewById(R.id.respuestasGroup);

        // LÓGICA ESPECIAL PARA MODO REVISIÓN
        if (modoRevision) {
            // Obtener la respuesta que dio el usuario anteriormente para esta pregunta
            int respuestaUsuario = intentRecibido.getIntExtra("respuesta_pregunta3", 0);

            // Verificar que existe una respuesta
            if (respuestaUsuario != 0){
                radioGroupRespuestas.check(respuestaUsuario);

                // Deshabilitar todos los RadioButtons para evitar modificaciones
                for(int i = 0; i < radioGroupRespuestas.getChildCount(); i++) {
                    radioGroupRespuestas.getChildAt(i).setEnabled(false);
                }

                // Cambiar color del texto del RadioButton seleccionado según si acertó o no
                RadioButton rbSeleccionado = findViewById(respuestaUsuario);
                if (rbSeleccionado != null ) {
                    if (respuestaUsuario == respuestaCorrecta){
                        rbSeleccionado.setTextColor(getResources().getColor(R.color.verde_boton));
                    }else {
                        rbSeleccionado.setTextColor(getResources().getColor(R.color.rojo_fondo));
                    }
                }
            }
        }

        // BOTÓN "ATRÁS" (NAVEGACIÓN A PREGUNTA ANTERIOR)
        Button botonAtras = findViewById(R.id.atras);
        botonAtras.setOnClickListener(v -> {
            // Crear Intent para volver a Pregunta2Activity
            Intent intent = new Intent(Pregunta3Activity.this, Pregunta2Activity.class);
            // Reenviar datos básicos necesarios
            intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
            intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
            intent.putExtra("puntuacion", puntuacionAcumulada);

            // Si está en modo revisión, enviar información adicional
            if (modoRevision) {
                intent.putExtra("modo_revision", true);
                // Reenviar todas las respuestas para mantener consistencia
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));
            }
            startActivity(intent);
            finish();
        });

        // BOTÓN "SIGUIENTE" (NAVEGACIÓN, VALIDACIÓN Y FINALIZACIÓN)
        Button botonSiguiente = findViewById(R.id.siguiente);
        botonSiguiente.setOnClickListener(v -> {

            // Obtener el ID del RadioButton seleccionado por el usuario
            int idSeleccionado = radioGroupRespuestas.getCheckedRadioButtonId();

            // LÓGICA PARA MODO NORMAL (NO REVISIÓN)
            if (!modoRevision){
                // VALIDACIÓN: Verificar que se haya seleccionado una respuesta
                if (idSeleccionado == -1){  // -1 significa que no hay selección
                    Toast.makeText(Pregunta3Activity.this, "Debes seleccionar una respuesta", Toast.LENGTH_SHORT).show();
                    return;
                }

                // VALIDACIÓN DE RESPUESTA (solo si no se ha validado antes)
                if (!respuestaValidada ){
                    // Si la respuesta es correcta, sumar 1 punto a la puntuación acumulada
                    if (idSeleccionado == respuestaCorrecta){
                        puntuacionAcumulada += 1;
                    }
                    respuestaValidada = (true);
                }

                // PREPARAR INTENT PARA ACTIVIDAD DE RESULTADOS
                Intent intent = new Intent(Pregunta3Activity.this, ResultadoActivity.class);
                // Enviar datos básicos del usuario
                intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
                intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
                intent.putExtra("puntuacion", puntuacionAcumulada);
                intent.putExtra("total_preguntas", 3);
                // Enviar respuestas de todas las preguntas para poder mostrarlas en resultados
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", idSeleccionado);
                startActivity(intent);
                finish();

            }else {

                // LÓGICA PARA MODO REVISIÓN
                // En modo revisión, simplemente volver a la pantalla de resultados
                Intent intent = new Intent(Pregunta3Activity.this, ResultadoActivity.class);

                // Reenviar todos los datos originales (sin cambios)
                intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
                intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
                intent.putExtra("modo_revision", true);
                intent.putExtra("puntuacion", puntuacionAcumulada);
                intent.putExtra("total_preguntas", 3);
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));
                startActivity(intent);
                finish();
            }
        });

        // BOTÓN "INFORMACIÓN" (ENLACE EXTERNO) Intent IMPLICITO
        // Botón que abre información adicional en el navegador web
        Button botonInformacion = findViewById(R.id.informacion);
        botonInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es.wikipedia.org/wiki/Don_Quijote_de_la_Mancha"));
                startActivity(intent);
            }
        });
    }
}
