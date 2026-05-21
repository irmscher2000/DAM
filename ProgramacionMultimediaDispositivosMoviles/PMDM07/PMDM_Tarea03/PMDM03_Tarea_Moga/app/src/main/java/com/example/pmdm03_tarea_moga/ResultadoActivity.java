package com.example.pmdm03_tarea_moga;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Actividad que muestra los resultados finales del examen y permite diversas acciones
public class ResultadoActivity  extends AppCompatActivity{

    // VARIABLES PARA RESULTADOS
    private int puntuacion;
    private int totalPreguntas;
    private String nombreUsuario;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la pantalla de resultados
        setContentView(R.layout.pagina_resultado);

        // RECEPCIÓN DE DATOS DE ACTIVIDADES ANTERIORES
        // Obtiene los datos enviados desde Pregunta3Activity (o modo revisión)
        Intent intentRecibido = getIntent();
        String nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        boolean modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);
        puntuacion = intentRecibido.getIntExtra("puntuacion", 0);
        totalPreguntas = intentRecibido.getIntExtra("total_preguntas", 0);
        int puntuacionMaxima = totalPreguntas ;

        // INICIALIZACIÓN DE VISTAS
        TextView textoPuntuacion = findViewById(R.id.texto_puntuacion);
        TextView textoMensaje = findViewById(R.id.texto_mensaje);
        CardView cardResultado = findViewById(R.id.cardResultado);

        // MOSTRAR PUNTUACIÓN Y MENSAJE
        // Muestra la puntuación en formato "X/Y" (ej: "2/3")
        textoPuntuacion.setText("Tu puntuación: " + puntuacion + "/" + puntuacionMaxima);

        // Calcula el porcentaje de aciertos
        double porcentaje =  (puntuacion * 100) / puntuacionMaxima;


        // EVALUACIÓN DEL RESULTADO Y FEEDBACK VISUAL
        // Cambia el mensaje y color de la tarjeta según el porcentaje obtenido
        if(porcentaje >= 70){
            textoMensaje.setText("¡Excelente trabajo! Dominas muy bien los temas evaluados.");
            cardResultado.setCardBackgroundColor(ContextCompat.getColor(this, R.color.verde_fondo));

        } else if (porcentaje >= 50) {
            textoMensaje.setText("¡Buen trabajo! Revisa las preguntas para mejorar en los temas que fallaste.");
            cardResultado.setCardBackgroundColor(ContextCompat.getColor(this, R.color.amarillo_fondo));

        } else {
            textoMensaje.setText("Lo sentimos, debes mejorar tus conocimientos.");
            cardResultado.setCardBackgroundColor(ContextCompat.getColor(this, R.color.rojo_fondo));
        }

        // GUARDAR RESULTADO EN BASE DE DATOS
        // Solo guarda en la BD si NO está en modo revisión (para evitar duplicados)
        boolean modoRevision = intentRecibido.getBooleanExtra("modo_revision", false);
        if (!modoRevision) {
            guardarResultadoEnBD(nombreUsuario, puntuacion, modoPractica);
        }


        // CONFIGURACIÓN DE BOTONES

        // BOTÓN "REVISAR" - Permite revisar las preguntas una a una
        Button botonRevisar = findViewById(R.id.revisar);
        botonRevisar.setOnClickListener(v -> {
            // Inicia Pregunta1Activity en modo revisión
            Intent intent = new Intent(ResultadoActivity.this, Pregunta1Activity.class);
            intent.putExtra("modo_revision", true);
            intent.putExtra("nombre_usuario", intentRecibido.getStringExtra("nombre_usuario"));
            intent.putExtra("modo_practica", intentRecibido.getBooleanExtra("modo_practica", false));
            intent.putExtra("puntuacion", puntuacion);
            // Envía las respuestas para que se muestren en modo revisión
            intent.putExtra("respuesta_pregunta1", intentRecibido.getIntExtra("respuesta_pregunta1", 0));
            intent.putExtra("respuesta_pregunta2", intentRecibido.getIntExtra("respuesta_pregunta2", 0));
            intent.putExtra("respuesta_pregunta3", intentRecibido.getIntExtra("respuesta_pregunta3", 0));
            startActivity(intent);
        });


        // BOTÓN "VOLVER" - Regresa a la pantalla principal (login)
        Button botonVolver = findViewById(R.id.volver);
        botonVolver.setOnClickListener(v -> {
            // FLAG_ACTIVITY_CLEAR_TOP: Elimina todas las actividades intermedias del stack
            Intent intent = new Intent(ResultadoActivity.this, MainActivity.class   );
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        // BOTÓN "FINALIZAR" - Cierra completamente la aplicación
        Button botonFinalizar = findViewById(R.id.finalizar);
        botonFinalizar.setOnClickListener(v -> {
            // finishAffinity(): Cierra esta actividad y todas las actividades padre relacionadas
            finishAffinity();
        });

        // BOTÓN "VER HISTORIAL" - Muestra el historial de calificaciones guardadas
        Button botonBD = findViewById(R.id.botonBD);
        botonBD.setOnClickListener(v -> {
            Intent intent = new Intent(ResultadoActivity.this, DAO_BD_Activity.class);
            startActivity(intent);
        });

    }

    // METODO PARA GUARDAR RESULTADOS EN BASE DE DATOS SQLite
    private void guardarResultadoEnBD(String nombreUsuario, int nota, boolean modoPractica) {
        // 1. CREAR/CONECTAR CON LA BASE DE DATOS
        BDEntidad helper = new BDEntidad(this, "resultados.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        // 2. OBTENER FECHA Y HORA ACTUAL
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String fechaActual = sdf.format(new Date());

        // 3. PREPARAR DATOS PARA INSERTAR
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombreUsuario);
        valores.put("nota", nota + "/" + totalPreguntas);
        valores.put("modo", modoPractica ? "Práctica" : "Evaluación");
        valores.put("fecha", fechaActual);

        // 4. INSERTAR EN LA TABLA "resultados"
        long resultado = db.insert("resultados", null, valores);

        // 5. CERRAR CONEXIÓN
        db.close();

    }
}
