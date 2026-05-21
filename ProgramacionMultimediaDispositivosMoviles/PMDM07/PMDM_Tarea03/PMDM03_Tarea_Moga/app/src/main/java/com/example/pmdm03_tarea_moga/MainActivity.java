package com.example.pmdm03_tarea_moga;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

// Clase principal de la aplicación que maneja la pantalla de login
public class MainActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la UI
    private Button botonInstrucciones;
    private Button botonComenzar;
    private EditText campoNombre;
    private SwitchMaterial switchModo;

    // Constantes para almacenar/recuperar preferencias del usuario SharedPreferences
    private static final String PREFS_NAME = "MisPreferencias"; // Nombre del archivo
    private static final String MODO_KEY = "modo_practica";     // Clave para guardar





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Establecer el layout de la pantalla de login
        setContentView(R.layout.pagina_login);

        // INICIALIZACIÓN DE VISTAS
        // Conectar las variables Java con los elementos del XML
        campoNombre = findViewById(R.id.campoNombre);
        botonComenzar = findViewById(R.id.comenzar);
        botonInstrucciones = findViewById(R.id.instrucciones);
        switchModo = findViewById(R.id.switchModo);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        // GESTIÓN DE PREFERENCIAS (SHAREDPREFERENCES)
        // Obtener acceso al almacenamiento persistente de preferencias
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        //  Leer el valor guardado. Si no existe nada, ponemos 'false' (Modo Evaluación)
        boolean modoPracticaActivado = prefs.getBoolean(MODO_KEY, false);

        // Aplicar el valor al switch y el texto correspondiente
        switchModo.setChecked(modoPracticaActivado);
        if (modoPracticaActivado) {
            switchModo.setText("Modo Práctica");  // Texto cuando está activado
        } else {
            switchModo.setText("Modo Evaluación");  // Texto cuando está desactivado
        }

        // Guardar cambios cuando el usuario mueve el switch
        switchModo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Actualizar texto del switch segun su estado
                if (isChecked) {
                    switchModo.setText("Modo Práctica");
                } else {
                    switchModo.setText("Modo Evaluación");
                }

                // Guardar el nuevo estado en SharedPreferences para persistencia
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(MODO_KEY, isChecked);
                editor.apply();  // Guardar cambios de forma asíncrona
            }
        });

        //  LÓGICA BOTÓN COMENZAR
        botonComenzar.setOnClickListener(v -> {
            // Validar que el usuario haya ingresado un nombre
            if (validarUsuario()) {
                // Crear intent para ir a la actividad de preguntas
                Intent intent = new Intent(MainActivity.this, PreguntasActivity.class);
                enviarDatosExtra(intent);
                startActivity(intent);
            }
        });


        //  LÓGICA BOTÓN INSTRUCCIONES
        botonInstrucciones.setOnClickListener(v -> {
            // Validar que el usuario haya ingresado un nombre
            if (validarUsuario()) {
                // Crear intent para ir a la actividad de instrucciones
                Intent intent = new Intent(MainActivity.this, InstruccionesActivity.class);
                enviarDatosExtra(intent);
                startActivity(intent);
            }
        });
    }

    // Metodo para empaquetar los datos que se enviarán a otras actividades
    private void enviarDatosExtra(Intent intent) {
        intent.putExtra("nombre_usuario", campoNombre.getText().toString().trim());
        intent.putExtra("modo_practica", switchModo.isChecked());
    }

    // Metodo para validar que el usuario haya ingresado un nombre
    private boolean validarUsuario() {
        String nombreUsuario = campoNombre.getText().toString().trim();
        if (nombreUsuario.isEmpty()) {
            Toast.makeText(this, "Debes introducir un nombre de usuario", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
