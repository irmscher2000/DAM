package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PreguntasActivity extends AppCompatActivity {

    private String nombreUsuario;
    private boolean modoPractica;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_preguntas);

        Intent intentRecibido = getIntent();
        nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);

        Log.d("DEBUG_APP", "PreguntasActivity - nombreUsuario: " + nombreUsuario);
        Log.d("DEBUG_APP", "PreguntasActivity - modoPractica: " + modoPractica);

        // BOTÓN VOLVER (a login/main)
        Button botonVolver = findViewById(R.id.volver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreguntasActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // BOTÓN PREGUNTA1 (a pregunta1.xml)
        Button botonPregunta1 = findViewById(R.id.pregunta1);
        botonPregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreguntasActivity.this, Pregunta1Activity.class);
                intent.putExtra("nombre_usuario", nombreUsuario);
                intent.putExtra("modo_practica", modoPractica);
                intent.putExtra("puntuacion", 0);
                startActivity(intent);
                finish();
            }

        });

        // BOTÓN PREGUNTA2 (a pregunta2.xml)
        Button botonPregunta2 = findViewById(R.id.pregunta2);
        botonPregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreguntasActivity.this, Pregunta2Activity.class);
                intent.putExtra("nombre_usuario", nombreUsuario);
                intent.putExtra("modo_practica", modoPractica);
                intent.putExtra("puntuacion", 0);
                startActivity(intent);
                finish();
            }
        });

        // BOTÓN PREGUNTA3 (a pregunta3.xml)
        Button botonPregunta3 = findViewById(R.id.pregunta3);
        botonPregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreguntasActivity.this, Pregunta3Activity.class);
                intent.putExtra("nombre_usuario", nombreUsuario);
                intent.putExtra("modo_practica", modoPractica);
                intent.putExtra("puntuacion", 0);
                startActivity(intent);
                finish();
            }
        });

    }
}
