package com.example.lightdark

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lightdark.settings.Activity_settings
import com.example.lightdark.settings.ThemeSetup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var textInputEmail: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeSetup.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        editTextEmail = findViewById(R.id.editTextEmail)
        textInputEmail = findViewById(R.id.text_input_layout_email)

        // Se infla el menu para mostrar los iconos para buscar y compartir
        val bottomAppBar = findViewById<com.google.android.material.bottomappbar.BottomAppBar>(R.id.bottomBar)
        bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    // Se muestra el menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Activity_settings.start(this)
        return true
    }

    fun validate(view: View){
        if (TextUtils.isEmpty(editTextEmail.text)){
            textInputEmail.error = "Este campo es obligatorio"
            textInputEmail.isErrorEnabled = true
        } else {
            textInputEmail.error = null
            textInputEmail.isErrorEnabled = false
        }
    }
}