package com.example.layouts2

import android.R.attr.inset
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.nuevo_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            val horizontalPadding = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)

            v.setPadding(
                systemBars.left + horizontalPadding,
                systemBars.top + horizontalPadding,
                systemBars.right + horizontalPadding,
                systemBars.bottom + horizontalPadding
            )
            insets
        }
    }
}