package com.example.notas_001.Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notas_001.R
import com.example.notas_001.datos.Nota
import com.example.notas_001.datos.Tarea
import org.w3c.dom.Text
import java.util.ArrayList

class adaptadorTareas(
        var contexto: Context,
        val listaTareas: ArrayList<Tarea>
): RecyclerView.Adapter<adaptadorTareas.ViewHolder>() {

    var inflador: LayoutInflater =
            contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var onClickListener: View.OnClickListener

    fun setOnclickListener(onclickListener: View.OnClickListener) {
        this.onClickListener = onclickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titulo: TextView
        var descripcion: TextView
        var fecha: TextView
        init {
            this.titulo = itemView.findViewById<View>(R.id.titulo_tarea_mostrar) as TextView
            this.descripcion = itemView.findViewById<View>(R.id.descripcion_tarea_mostrar) as TextView
            this.fecha = itemView.findViewById(R.id.fecha_mostrar) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptadorTareas.ViewHolder {
        val view: View = inflador.inflate(R.layout.item_recycle_tareass, null)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Tarea = listaTareas[position]
        holder.titulo.text = "Título: ${task.titulo}"
        holder.descripcion.text = "Descripción: ${task.descripcion}"
        holder.fecha.text = "Fecha acordada: ${task.fecha}"
    }
    override fun getItemCount(): Int {
       return listaTareas.size
    }
}