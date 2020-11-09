package com.example.notas_001.datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQL(context: Context) : SQLiteOpenHelper(context, "notasTareas", null, 1){
    override fun onCreate(database: SQLiteDatabase?) {
        val query_nota: String? = "CREATE TABLE ${Tabla_Nota.nombre_tabla} (" +
                "${Tabla_Nota.campo_id} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Tabla_Nota.campo_nombre} VARCHAR(30)," +
                "${Tabla_Nota.campo_descripcion} VARCHAR(200) );"
        database?.execSQL(query_nota)
        val query_Tarea: String? = "CREATE TABLE ${Table_Tarea.nombre_tabla} (" +
                "${Table_Tarea.campo_id} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Table_Tarea.campo_nombre} VARCHAR(30)," +
                "${Table_Tarea.campo_descripcion} VARCHAR(200) ),"+
                "${Table_Tarea.campo_FechaNotificacion} VARCHAR(200) ),"+
                "${Table_Tarea.campo_HoraNotificacion} VARCHAR(200) );"
                database?.execSQL(query_Tarea)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Tabla_Nota.nombre_tabla}");
        db?.execSQL("DROP TABLE IF EXISTS ${Table_Tarea.nombre_tabla}");
        onCreate(db);
    }

}