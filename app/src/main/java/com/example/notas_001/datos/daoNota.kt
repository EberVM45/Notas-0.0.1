package com.example.notas_001.datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class daoNota(
        val contexto: Context
) {
    private val database: AdminSQL = AdminSQL(contexto)
    var base: SQLiteDatabase = database.writableDatabase

    fun insert(note: Nota): Boolean {
        val query: String =
                "INSERT INTO ${Tabla_Nota.nombre_tabla} (${Tabla_Nota.campo_nombre}," +
                        "${Tabla_Nota.campo_descripcion} ) VALUES (" +
                        "'${note.titulo}','${note.descripcion}');"
        return try {
            base.execSQL(query)
            Toast.makeText(contexto, "Agreado correctamente", Toast.LENGTH_LONG).show()
            true
        } catch (e: Exception) {
            Toast.makeText(contexto, e.message, Toast.LENGTH_LONG).show()
            false
        }
    }
}