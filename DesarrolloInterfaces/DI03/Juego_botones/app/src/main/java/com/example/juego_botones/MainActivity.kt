package com.example.juego_botones

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val numBotones = 10
    private lateinit var llBotonera: LinearLayout

    // Variable para guardar el numero del boton ganador
    private var botonGanador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        llBotonera = findViewById(R.id.llBotonera)

        // Se genera un numero aleatorio entre 0 y numBotones
        botonGanador = Random.nextInt(numBotones)


        // Se crean las propiedades de layout que tendran los botones
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.button_height) // Se define la altura del boton con un valor concreto
        )

        // Se crean los botones en bucle
        for (i in 0 until numBotones){
            val button = Button(this)

            // Se asignan las propiedades de layout al boton
            button.layoutParams = lp

            // Se asigna el texto al boton
            button.text = "Botón " + String.format("%02d", i)

            // Se asigna el Listener
            button.setOnClickListener{

                // Se verifica si el boton pulsado es el ganador
                if(i == botonGanador){
                    Toast.makeText(this,"ME ENCONTRASTE!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Sigue buscando", Toast.LENGTH_SHORT).show()
                }
            }

            llBotonera.addView(button)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Funcion privada para mostrar mensaje al pulsar un boton
    private fun buttonClickListener(index: Int) : View.OnClickListener{
        return View.OnClickListener{
            Toast.makeText(this@MainActivity,
                "Botón " + String.format("%02d", index) + " pulsado",
                Toast.LENGTH_SHORT).show()
        }
    }
}