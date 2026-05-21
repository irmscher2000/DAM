package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        // Recibir datos
        String nombreUsuario = getIntent().getStringExtra("nombre_usuario");
        int aciertos = getIntent().getIntExtra("aciertos", 0);
        int totalPreguntas = getIntent().getIntExtra("total_preguntas", 0);
        String modo = getIntent().getStringExtra("modo");
        String fecha = getIntent().getStringExtra("fecha");
        int totalIntentos = getIntent().getIntExtra("total_intentos", 0);



        // Inicializar vistas
        TextView tvNombreUsuario = findViewById(R.id.tvNombre);
        TextView tvNota = findViewById(R.id.tvNota);
        TextView tvModo = findViewById(R.id.tvModo);
        TextView tvFecha = findViewById(R.id.tvFecha);
        TextView tvPorcentaje = findViewById(R.id.tvPorcentaje);
        TextView tvIntentos = findViewById(R.id.tvIntentos);




        // Mostrar nombre y puntuacion
        tvNombreUsuario.setText("Usuario: " + nombreUsuario);
        tvNota.setText("Nota: " + aciertos + "/" + totalPreguntas);
        tvModo.setText("Modo: " + modo);
        tvFecha.setText("Fecha: " + fecha);

        // Calcular porcentaje
        double porcentaje = (double) aciertos / totalPreguntas * 100;
        tvPorcentaje.setText("Porcentaje: " + String.format("%.2f", porcentaje) + "%");

        // Mostrar numero de intentos
        tvIntentos.setText("Intentos: " + totalIntentos);


        // Boton Volver
        Button botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(v ->  {
            Intent intent = new Intent(DetalleActivity.this, DAO_BD_Activity.class);
            startActivity(intent);
            finish();
        });

    }
}
