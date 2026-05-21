package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Pregunta1Activity extends AppCompatActivity {

    // VARIABLES DE CONFIGURACIÓN
    private int respuestaCorrecta = 2; // La opción correcta es C) Nilo (posición 2 en el array)
    private int puntuacionAcumulada = 0;
    private String nombreUsuario;
    private boolean modoPractica;
    private boolean respuestaValidada = false;
    private boolean modoRevision = false;

    // VARIABLES PARA MULTIMEDIA
    ImageView imageView; // Muestra la imagen estática inicial
    VideoView videoView; // Reproduce el video
    ImageButton botonReproducirVideo; // Botón para iniciar la reproducción
    MediaController mediaController; // Controla reproducción (pausa, avance, etc.)

    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establece el layout de la pregunta 1
        setContentView(R.layout.pregunta1);

        // OBTENCIÓN DE DATOS DE LA ACTIVIDAD ANTERIOR
        Intent intentRecibido = getIntent();
        nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);
        puntuacionAcumulada = intentRecibido.getIntExtra("puntuacion", 0);
        modoRevision = intentRecibido.getBooleanExtra("modo_revision", false);

        // INICIALIZACIÓN DE VISTAS
        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.video);
        botonReproducirVideo = findViewById(R.id.boton_reproducir_video);

        // Configurar MediaController para controlar la reproducción del video
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // CONFIGURACIÓN DEL SPINNER DE RESPUESTAS DESPLEGABLES
        Spinner spinnerRespuestas = findViewById(R.id.spinner_respuestas);
        String[] opcionesRespuestas = {"A) Yangtsé", "B) Amazonas", "C) Nilo", "D) Misisipi"};

        // Adaptador para mostrar las opciones en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesRespuestas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRespuestas.setAdapter(adapter);

        // LÓGICA ESPECIAL PARA MODO REVISIÓN
        if (modoRevision) {
            // Obtener la respuesta que dio el usuario anteriormente
            int respuestaUsuario = intentRecibido.getIntExtra("respuesta_pregunta1", 0);
            spinnerRespuestas.setSelection(respuestaUsuario); // Seleccionar su respuesta
            spinnerRespuestas.setEnabled(false); // Deshabilitar el Spinner en modo revisión

            // Cambiar color según si acertó o no
            if (respuestaUsuario == respuestaCorrecta) {
                spinnerRespuestas.setBackgroundResource(R.drawable.spinner_correcto);
            } else {
                spinnerRespuestas.setBackgroundResource(R.drawable.spinner_incorrecto);
            }
        }

        // CONFIGURACIÓN DEL BOTÓN PARA REPRODUCIR VIDEO
        botonReproducirVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ocultar imagen estática y botón de reproducción
                imageView.setVisibility(View.GONE);
                botonReproducirVideo.setVisibility(View.GONE);

                // Mostrar video
                videoView.setVisibility(View.VISIBLE);

                // Establecer ruta del video
                String rutaVideo = "android.resource://" + getPackageName() + "/" + R.raw.video_rio;
                videoView.setVideoURI(Uri.parse(rutaVideo));

                // Listener que se ejecuta cuando el video está listo para reproducirse
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoView.start();
                        }
                });

                // Listener que se ejecuta cuando el video termina
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // Restaurar estado inicial: ocultar video, mostrar imagen y botón
                        videoView.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        botonReproducirVideo.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        // BOTON ATRAS a (PreguntasActivity.xml)
        Button botonAtras = findViewById(R.id.atras);
        botonAtras.setOnClickListener( v -> {

            // En modo revisión, volver a resultados
            if (modoRevision) {
                Intent intent = new Intent(Pregunta1Activity.this, ResultadoActivity.class);
                intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
                intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
                intent.putExtra("puntuacion", puntuacionAcumulada);
                intent.putExtra("total_preguntas", 3);
                // Reenviar respuestas de todas las preguntas para mantener consistencia
                intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
                intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
                intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));

                startActivity(intent);

            } else {
                // En modo normal: volver a la pantalla de selección de preguntas
                Intent intent = new Intent(Pregunta1Activity.this, PreguntasActivity.class);
                startActivity(intent);
            }
            finish();
        });

        // BOTÓN "SIGUIENTE" (NAVEGACIÓN Y VALIDACIÓN)
        Button botonSiguiente = findViewById(R.id.siguiente);
        botonSiguiente.setOnClickListener(v ->  {

            // Obtener la respuesta seleccionada por el usuario
            int posicionSeleccionada = spinnerRespuestas.getSelectedItemPosition();

            // VALIDACIÓN DE RESPUESTA (solo en modo normal)
            if (!modoRevision && !respuestaValidada){
                if (posicionSeleccionada == respuestaCorrecta){
                    puntuacionAcumulada += 1;
                }
                respuestaValidada = true;
            }

            // PREPARAR INTENT PARA SIGUIENTE ACTIVIDAD
            Intent intent = new Intent(Pregunta1Activity.this, Pregunta2Activity.class);
            intent.putExtra("nombre_usuario", nombreUsuario);
            intent.putExtra("modo_practica", modoPractica);
            intent.putExtra("puntuacion", puntuacionAcumulada);


            // Guardar respuesta para revisión
            if (!modoRevision) {
                // Modo normal: guardar la respuesta actual
                intent.putExtra("respuesta_pregunta1", posicionSeleccionada);
            } else {
                // Modo revisión: reenviar todas las respuestas guardadas
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
