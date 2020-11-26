package com.example.notas_001.datos

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import java.util.*

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
    fun getOneById(id:Int): Nota ?{
        base = database.readableDatabase
        var cursor: Cursor? = null
        try {
            val query = "SELECT * FROM ${Tabla_Nota.nombre_tabla} " +
                    "WHERE ${Tabla_Nota.campo_id} = '${id}';"
            cursor = base.rawQuery(query, null)

            return Nota(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2))
        }catch (e: Exception){
            Toast.makeText(contexto,e.message, Toast.LENGTH_SHORT).show()
            return null
        }finally {
            cursor?.close()
        }
    }
    fun getAll(): ArrayList<Nota>?{
        base = database.readableDatabase
        val list: ArrayList<Nota> = ArrayList()
        try {
            val query: String = "SELECT * FROM ${Tabla_Nota.nombre_tabla};"
            val cursor: Cursor = base.rawQuery(query,null)
            while(cursor.moveToNext()){
                list.add(Nota(cursor.getInt(0),cursor.getString(1),
                cursor.getString(2)))
            }
            cursor.close()
        }catch (e: Exception){

        }
        return list
    }
    fun getLastId(): Int{
        base = database.readableDatabase
        try{
           val query = "SELECT * FROM ${Tabla_Nota.nombre_tabla}"
            val cursor: Cursor = base.rawQuery(query,null)
            cursor.moveToLast()
            val id = cursor.getInt(0)
            cursor.close()
            return id
        }catch (e: SQLException){
            Toast.makeText(contexto,e.message,Toast.LENGTH_SHORT).show()
            return 0
        }
    }
}