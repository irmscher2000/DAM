package com.example.moga_eugen_pmdm_tarea4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    // Se declaran las constantes para identificar los permisos
    private static final int REQ_CAMARA = 1;
    private static final int REQ_UBICACION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Se agrega el fragmento de incio en el contenedor establecido
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, new FragmentInicial()).commit();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Se declaran los botones de la vista
        Button btnCamara = findViewById(R.id.botonCamara);
        Button btnUbicacion = findViewById(R.id.botonUbicacion);
        Button btnTiempo = findViewById(R.id.botonTiempo);

        // Se configura el boton camara para abrir el metodo pedirCamara
        btnCamara.setOnClickListener(v -> pedirCamara());
        btnUbicacion.setOnClickListener(v -> pedirUbicacion());
        btnTiempo.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentTiempo()).commit();
        });
    }
    // Metodo para comprobar y solicitar el permiso a la camara.
    private void pedirCamara(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            abrirCamara();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQ_CAMARA);
        }
    }

    // Metodo para comprobar y solicitar el permiso de la ubicacion
    private void pedirUbicacion(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            abrirUbicacion();
        } else {
            ActivityCompat.requestPermissions(this,
                    new  String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    }, REQ_UBICACION);
        }
    }

    // Se modifica el metodo onRequestPermissions para guardar la respuesta del usuario
    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(request, permissions, grantResults);

        // Se revisa si el resultado esta vacio
        if(grantResults.length == 0) return;

        // Se declara la variable booleana para guardar si el permiso se ha concedido o no
        boolean concedido = false;
        for (int resultado : grantResults){
            if (resultado == PackageManager.PERMISSION_GRANTED){
                concedido = true;
                break;
            }
        }

        // Bloque para tratar el permiso de la camara
        if(request == REQ_CAMARA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permiso a camara concedido", Toast.LENGTH_SHORT).show();
                abrirCamara();
            } else {
                Toast.makeText(this, "Permiso a camara denegado", Toast.LENGTH_SHORT).show();
            }
        }

        // Bloque para tratar el permiso de la ubicacion
        if (request == REQ_UBICACION){
            if (concedido){
                Toast.makeText(this, "Permiso a ubicacion concedido", Toast.LENGTH_SHORT).show();
                abrirUbicacion();
            }else {
                Toast.makeText(this, "Permiso a ubicacion denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Metodo abrir camara
    private void abrirCamara(){
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intentCamara);
    }

    // Metodo para abrir la ubicacion
    private void abrirUbicacion(){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentLocalizacion()).commit();
    }
}