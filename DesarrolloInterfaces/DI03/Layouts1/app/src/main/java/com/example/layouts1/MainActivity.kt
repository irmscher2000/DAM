package com.example.layouts1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.linear_layout)

        val boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener {
            setContentView(R.layout.frame_layout)

        }
    }
}