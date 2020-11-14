package com.example.notas_001.datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQL(context: Context) : SQLiteOpenHelper(context, "notasTareas", null, 1) {
    override fun onCreate(database: SQLiteDatabase?) {
        val query_tarea: String? = "CREATE TABLE tareas (" +
                "${Tabla_tarea.campo_id } INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Tabla_tarea.campo_titulo} VARCHAR(30)," +
                "${Tabla_tarea.campo_descripcion} VARCHAR(100)," +
                "${Tabla_tarea.campo_fecha} VARCHAR(30));"
        database?.execSQL(query_tarea)

        val query_nota: String? = "CREATE TABLE ${Tabla_Nota.nombre_tabla} (" +
                "${Tabla_Nota.campo_id} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Tabla_Nota.campo_nombre} VARCHAR(30)," +
                "${Tabla_Nota.campo_descripcion} VARCHAR(200) );"
        database?.execSQL(query_nota)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Tabla_Nota.nombre_tabla}");
        db?.execSQL("DROP TABLE IF EXISTS ${Tabla_tarea.nombre_tabla}");
        onCreate(db);
    }

}