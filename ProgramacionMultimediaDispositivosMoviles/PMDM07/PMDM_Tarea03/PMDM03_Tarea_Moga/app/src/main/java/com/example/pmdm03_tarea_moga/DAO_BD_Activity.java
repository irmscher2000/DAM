package com.example.pmdm03_tarea_moga;

import android.content.Intent;
import android.database.Cursor; // Para recorrer resultados de consultas SQL
import android.database.sqlite.SQLiteDatabase; // Para operaciones con la BD
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;  // Para adaptar datos a ListView
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Actividad para gestionar y visualizar el historial de resultados almacenados en la BD
public class DAO_BD_Activity extends AppCompatActivity {

    // DECLARACIÓN DE VARIABLES DE INTERFAZ
    EditText campoNombre; // Campo para ingresar nombre a buscar/borrar
    ListView lvResultados;  // Lista para mostrar resultados
    BDEntidad helper;  // Helper para acceso a la base de datos

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la pantalla de gestión de BD
        setContentView(R.layout.bd_resultado);

        // INICIALIZACIÓN DE VISTAS
        campoNombre = findViewById(R.id.campoNombre);
        lvResultados = findViewById(R.id.listaResultados);
        // Inicializa el helper de base de datos (misma instancia que en ResultadoActivity)
        helper = new BDEntidad(this, "resultados.db", null, 1);

        //  OBTENCIÓN DE REFERENCIAS A BOTONES
        Button botonBorrar = findViewById(R.id.botonBorrar);
        Button botonListarTodo = findViewById(R.id.botonListarTodo);

        // BOTÓN "INSERTAR" (SOLO INFORMATIVO)
        // Este botón muestra un mensaje informativo ya que en esta actividad
        // no se permiten inserciones (solo se insertan desde ResultadoActivity)
        Button botonInsertar = findViewById(R.id.botonInsertar);
        botonInsertar.setOnClickListener(v -> {
            Toast.makeText(this, "No se pueden insertar resultados en esta actividad, los datos se guardan en el historial de resultados", Toast.LENGTH_SHORT).show();
        });

        // BOTÓN "VOLVER" A RESULTADOACTIVITY
        Button volver = findViewById(R.id.volver);
        volver.setOnClickListener(v -> {
            Intent intent = new Intent(DAO_BD_Activity.this, ResultadoActivity.class);
            finish();
        });

        // BOTÓN "LISTAR TODO"
        // Muestra todos los registros de la base de datos
        botonListarTodo.setOnClickListener(v -> listarResultados());

        // BOTÓN "CONSULTAR POR NOMBRE"
        Button botonConsultar = findViewById(R.id.botonConsultar);
        botonConsultar.setOnClickListener(v -> {
            // Obtiene el nombre ingresado por el usuario
            String nombreABuscar = campoNombre.getText().toString().trim();

            if (nombreABuscar.isEmpty()) {
                Toast.makeText(this, "Debes introducir un nombre de usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            consultarPorNombre(nombreABuscar);
        });

        // BOTÓN "BORRAR POR NOMBRE"
        botonBorrar.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debes introducir un nombre de usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtiene conexión de escritura a la BD
            SQLiteDatabase db = helper.getWritableDatabase();

            // Ejecuta la operación DELETE con parámetros para prevenir SQL injection
            // "nombre = ?" es una consulta parametrizada (segura)
            // new String[]{nombre} reemplaza el ? por el valor del nombre
            int cantidad = db.delete("resultados", "nombre = ?",  new String[]{nombre});
            db.close();
            campoNombre.setText("");
            if (cantidad > 0) {
                Toast.makeText(this, "Borrado con éxito", Toast.LENGTH_SHORT).show();
                listarResultados();
            } else {
                Toast.makeText(this, "No se ha encontrado el usuario", Toast.LENGTH_SHORT).show();
            }
        });

        // Gestion de clic en elementos de ListView
        lvResultados.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Obtiene el registro seleccionado en la posición 'position'
                String itemSeleccionado = (String) parent.getItemAtPosition(position);

                // Extrae el nombre del usuario del registro
                String[] lineas = itemSeleccionado.split("\n");
                String nombreUsuario = lineas[0].replace("Usuario: ", "").trim();

                String segundaLinea = lineas[1].trim();
                String[] partes = segundaLinea.split("\\|");

                if (partes.length < 3) {
                    Toast.makeText(DAO_BD_Activity.this, "Formato de nota incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Extraer nota
                String notaCompleta = partes[0].replace("Nota: ", "").trim();
                // Extraer modo
                String modo = partes[1].replace("Modo: ", "").trim();

                // Extraer fecha
                String fecha = partes[2].replace("Fecha: ", "").trim();

                // Separar nota
                String[] partesNota = notaCompleta.split("/");
                int aciertos = 0;
                int totalPreguntas = 3;

                try{
                    aciertos = Integer.parseInt(partesNota[0]);
                    totalPreguntas = Integer.parseInt(partesNota[1]);
                }catch (NumberFormatException e){
                    Toast.makeText(DAO_BD_Activity.this, "Formato de nota incorrecto", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Contar numero de intentos tiene un usuario
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM resultados WHERE nombre = ?", new String[]{nombreUsuario});

                int totalIntentos = 0;
                if (cursor.moveToFirst()) {
                    totalIntentos = cursor.getInt(0);
                }
                cursor.close();
                db.close();

                // Crear un Intent para abrir la actividad de detalle
                Intent intent = new Intent(DAO_BD_Activity.this, DetalleActivity.class);
                intent.putExtra("nombre_usuario", nombreUsuario);
                intent.putExtra("aciertos", aciertos);
                intent.putExtra("total_preguntas", totalPreguntas);
                intent.putExtra("modo", modo);
                intent.putExtra("fecha", fecha);
                intent.putExtra("total_intentos", totalIntentos);
                startActivity(intent);
            }
        });

    }



    // METODO PARA LISTAR TODOS LOS RESULTADOS
    private void listarResultados() {
        // Obtiene conexión de lectura a la BD
        SQLiteDatabase db = helper.getReadableDatabase();
        // ArrayList para almacenar los registros formateados
        ArrayList<String> lista = new ArrayList<>();

        // Ejecuta consulta SQL para obtener todos los registros
        // rawQuery ejecuta una consulta SQL directa
        Cursor fila = db.rawQuery("SELECT * FROM resultados", null);

        // Verifica si el cursor tiene resultados (moveToFirst va al primer registro)
        if (fila.moveToFirst()) {
            do {
                // Formatea cada registro como una cadena legible
                // Índices del cursor: 0=id, 1=nombre, 2=nota, 3=modo, 4=fecha
                String registro = "Usuario: " + fila.getString(1) +
                        "\nNota: " + fila.getString(2) +
                        " | Modo: " + fila.getString(3)+
                        " | Fecha: " + fila.getString(4);
                lista.add(registro);
            } while (fila.moveToNext());
        }
        db.close();

        // Crea un adaptador para mostrar la lista en el ListView
        // android.R.layout.simple_list_item_1 es un layout simple por defecto de Android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        // Asigna el adaptador al ListView
        lvResultados.setAdapter(adapter);
    }

    // METODO PARA CONSULTAR RESULTADOS POR NOMBRE
    private void consultarPorNombre(String nombre) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<String> listaFiltrada = new ArrayList<>();

        // Consulta SQL parametrizada para buscar por nombre
        // "SELECT * FROM resultados WHERE nombre = ?"
        // El ? será reemplazado por el valor del parámetro 'nombre'
        Cursor fila = db.rawQuery("SELECT * FROM resultados WHERE nombre = ?", new String[]{nombre});
        if (fila.moveToFirst()) {
            do {
                // Formatea cada registro encontrado
                String registro = "Usuario: " + fila.getString(1) +
                        "\nNota: " + fila.getString(2) +
                        " | Modo: " + fila.getString(3)+
                        " | Fecha: " + fila.getString(4);
                listaFiltrada.add(registro);
            } while (fila.moveToNext());
            Toast.makeText(this, "Se han encontrado " + listaFiltrada.size() + " registros", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existen registros con ese nombre", Toast.LENGTH_SHORT).show();
        }
        db.close();

        // Actualiza el ListView con los resultados filtrados
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFiltrada);
        lvResultados.setAdapter(adapter);
    }
}
