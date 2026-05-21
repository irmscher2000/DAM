package com.example.pmdm03_tarea_moga;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Clase helper para gestionar la base de datos SQLite de la aplicación
// Extiende SQLiteOpenHelper, que proporciona métodos para crear/actualizar la BD
public class BDEntidad extends SQLiteOpenHelper {

    // DEFINICIÓN DE LA ESTRUCTURA DE LA TABLA
    // Sentencia SQL para crear la tabla "resultados"
    String crearTabla = "CREATE TABLE resultados (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nombre TEXT, " +
            "nota TEXT, " +
            "modo TEXT, " +
            "fecha TEXT)";

    // CONSTRUCTOR DE LA CLASE
    // Este constructor inicializa el helper de la base de datos
    // @param context: Contexto de la aplicación (necesario para acceso a BD)
    // @param name: Nombre del archivo de la base de datos (ej: "resultados.db")
    // @param factory: CursorFactory (puede ser null para usar el por defecto)
    // @param version: Versión de la base de datos (importante para actualizaciones)
    public BDEntidad(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        // Llama al constructor de la clase padre (SQLiteOpenHelper)
        super(context, name, factory, version);
    }

    // METODO onCreate: SE EJECUTA LA PRIMERA VEZ QUE SE CREA LA BD
    // Este metodo se llama automáticamente cuando la base de datos se crea por primera vez
    // @param db: Objeto SQLiteDatabase para ejecutar sentencias SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ejecuta la sentencia SQL para crear la tabla "resultados"
        db.execSQL(crearTabla);
    }

    // METODO onUpgrade: SE EJECUTA CUANDO SE ACTUALIZA LA VERSIÓN DE LA BD
    // Este metodo se llama cuando la versión de la base de datos cambia
    // Útil para migraciones de datos cuando se modifica la estructura de la BD
    // @param db: Objeto SQLiteDatabase para ejecutar sentencias SQL
    // @param oldVersion: Número de versión antigua de la BD
    // @param newVersion: Número de versión nueva de la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualizar, se borra la tabla vieja y se crea una nueva
        db.execSQL("DROP TABLE IF EXISTS resultados");
        db.execSQL(crearTabla);
    }
}
