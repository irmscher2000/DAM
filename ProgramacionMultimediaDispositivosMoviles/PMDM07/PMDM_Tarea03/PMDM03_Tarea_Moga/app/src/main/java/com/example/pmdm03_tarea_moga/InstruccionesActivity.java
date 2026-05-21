package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class InstruccionesActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz
    private CardView cardAyuda;
    private ImageView iconoAyuda;
    private CardView cardConsejo;
    private String nombreUsuario;
    private boolean modoPractica;
    private boolean ayudaActivada = false; // Variable para controlar el boton ayuda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establece el layout de la pantalla de instrucciones
        setContentView(R.layout.pagina_instrucciones);

        // OBTENCIÓN DE DATOS DE LA ACTIVIDAD ANTERIOR
        // Recibe el Intent con los datos enviados desde MainActivity
        Intent intentRecibido = getIntent();
        nombreUsuario = intentRecibido.getStringExtra("nombre_usuario");
        modoPractica = intentRecibido.getBooleanExtra("modo_practica", false);

        // INICIALIZACIÓN DE VISTAS
        // Conecta las variables con los elementos del XML
        cardAyuda = findViewById(R.id.cardViewInformacion);
        iconoAyuda = findViewById(R.id.ic_help);

        // Configura la tarjeta de consejo (se crea dinámicamente)
        setupCardConsejo();

        // CONFIGURACIÓN BOTÓN "ANTERIOR"
        // Vuelve a MainActivity (que usa pagina_login.xml)
        androidx.appcompat.widget.AppCompatButton botonAnterior = findViewById(R.id.anterior);
        if (botonAnterior != null) {
            botonAnterior.setOnClickListener(new View.OnClickListener() {
                @Override
                // Cierra esta actividad, volviendo a la anterior (MainActivity)
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // CONFIGURACIÓN BOTÓN "SIGUIENTE"
        // Botón que avanza a la actividad de preguntas
        androidx.appcompat.widget.AppCompatButton botonSiguiente = findViewById(R.id.siguiente);
        if (botonSiguiente != null) {
            botonSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crea un Intent para ir a PreguntasActivity
                    Intent intent = new Intent(InstruccionesActivity.this, PreguntasActivity.class);
                    // Pasa los datos del usuario a la siguiente actividad
                    intent.putExtra("nombre_usuario", nombreUsuario);
                    intent.putExtra("modo_practica", modoPractica);
                    startActivity(intent);
                }
            });
        }


        // CONFIGURACIÓN DE LA TARJETA DE AYUDA INTERACTIVA
        // La tarjeta cambia de color y muestra/oculta el consejo al hacer clic
        if (cardAyuda != null && iconoAyuda != null) {
            cardAyuda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Alterna el estado de la ayuda (activado/desactivado)
                    ayudaActivada = !ayudaActivada;

                    if (ayudaActivada) {
                        // Estado PULSADO/ACTIVO (verde)
                        cardAyuda.setCardBackgroundColor(
                                ContextCompat.getColor(InstruccionesActivity.this, R.color.verde_boton)
                        );

                        // Muestra la tarjeta de consejo
                        mostrarConsejo();

                    } else {
                        // Estado NORMAL (amarillo)
                        cardAyuda.setCardBackgroundColor(
                                ContextCompat.getColor(InstruccionesActivity.this, R.color.amarillo)
                                );
                        ocultarConsejo();
                    }
                }
            });
        }
    }

    // Metodo que configura la tarjeta de consejo dinámicamente
    private void setupCardConsejo() {
        // INFLAR EL LAYOUT
        // Convierte el XML card_consejo.xml en una vista Java
        View cardView = getLayoutInflater().inflate(R.layout.card_consejo, null);
        // Obtiene la referencia a la CardView dentro del layout inflado
        cardConsejo = cardView.findViewById(R.id.cardConsejo);

        // AÑADIR AL LAYOUT PRINCIPAL
        // Obtiene el layout principal donde se añadirá la tarjeta
        ConstraintLayout mainLayout = findViewById(R.id.instrucciones);

        if (mainLayout != null) {
            // Crea parámetros de diseño para posicionar la tarjeta
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            // Configura las restricciones para centrar la tarjeta
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;

            // Aplica los parámetros y añade la vista al layout principal
            cardView.setLayoutParams(params);
            mainLayout.addView(cardView);
        }

        // CONFIGURAR BOTÓN DE CERRAR
        // Obtiene el botón de cerrar dentro de la tarjeta de consejo
        ImageView botonCerrar = cardView.findViewById(R.id.botonCerrar);
        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarConsejo();
                ayudaActivada = false;
                // Restaura el color original de la tarjeta de ayuda
                cardAyuda.setCardBackgroundColor(ContextCompat.getColor(InstruccionesActivity.this, R.color.amarillo));
            }
        });
    }


    // Metodo para mostrar la tarjeta de consejo
    private void mostrarConsejo() {
        if (cardConsejo != null) {
            cardConsejo.setVisibility(View.VISIBLE);
        }
    }

    // Metodo para ocultar la tarjeta de consejo
    private void ocultarConsejo() {
        if (cardConsejo != null) {
            cardConsejo.setVisibility(View.GONE);
        }
    }
}
