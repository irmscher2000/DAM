package com.example.navegacionentreventanas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SaludoActivity : AppCompatActivity() {

    private lateinit var txtSaludo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saludo)

        // Se localiza los controles
        txtSaludo = findViewById(R.id.txtSaludo)

        // Se recupera la informacion pasada en el intent
        val bundle = intent.extras

        // Se construye el mensaje a mostrar
        txtSaludo.text = "Hola  ${bundle?.getString("NOMBRE")}"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lyyContenedorSaludo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}