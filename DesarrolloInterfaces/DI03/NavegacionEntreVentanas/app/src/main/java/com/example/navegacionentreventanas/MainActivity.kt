package com.example.navegacionentreventanas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var btnAceptar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Se obtiene una referencia a los controles de la interfaz
        txtNombre = findViewById(R.id.txtNombre)
        btnAceptar = findViewById(R.id.btnAceptar)

        // Se implementa el evento click del botón
        btnAceptar.setOnClickListener {

            // Se crea el intent
            val  intent = Intent(this@MainActivity, SaludoActivity::class.java)

            // Se crea la informacion a pasar entre actividades
            val b = Bundle()
            b.putString("NOMBRE", txtNombre.text.toString())

            // Se añade la informacion al intent
            intent.putExtras(b)

            // Se inicia la nueva actividad
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lytContenedor)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


