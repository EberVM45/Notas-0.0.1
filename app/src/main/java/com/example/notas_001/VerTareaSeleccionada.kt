package com.example.notas_001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notas_001.datos.daoTarea

class VerTareaSeleccionada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_tarea_seleccionada)
        val id = intent.extras?.getInt("idTarea")
        val obj = id?.let { daoTarea(applicationContext).getOneById(it) }
        Toast.makeText(applicationContext,obj?.titulo,Toast.LENGTH_SHORT).show()
    }
}