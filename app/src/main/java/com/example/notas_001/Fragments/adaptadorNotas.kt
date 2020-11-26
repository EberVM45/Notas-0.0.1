package com.example.notas_001.Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notas_001.R
import com.example.notas_001.datos.Nota
import java.util.*

class adaptadorNotas (
        var contexto: Context,
        val listaNotas: ArrayList<Nota>
) : RecyclerView.Adapter<adaptadorNotas.ViewHolder>(){

    var inflador: LayoutInflater =
            contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var onClickListener: View.OnClickListener

    fun setOnclickListener(onclickListener: View.OnClickListener) {
        this.onClickListener = onclickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titulo: TextView
        var descripcion: TextView
        init {
            this.titulo = itemView.findViewById<View>(R.id.titulo_mostrar) as TextView
            this.descripcion = itemView.findViewById<View>(R.id.descripcion_mostrar) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflador.inflate(R.layout.item_recycle_notas, null)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note: Nota = listaNotas[position]
        holder.titulo.text = "Titulo: " + note.titulo
        holder.descripcion.text = "Descripcion: "+note.descripcion
    }

    override fun getItemCount(): Int {
      return listaNotas.size
    }
}