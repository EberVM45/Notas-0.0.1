package com.example.notas_001

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class verNotaSeleccionada : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var buttonAccept: Button
    private lateinit var floatingButtonVisualizar: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_nota_seleccionada)
        initializeComponents()
        val id: Int = intent.extras!!.getInt("idNota")
    }

    private fun initializeComponents() {
        title = findViewById(R.id.txtTaskTitleView)
        description = findViewById(R.id.txtTaskDescriptionView)
        buttonAccept = findViewById(R.id.btn_accept_view_note)
        buttonAccept.setOnClickListener {

        }
        floatingButtonVisualizar = findViewById(R.id.floatingButtonNoteView)
        floatingButtonVisualizar.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, floatingButtonVisualizar)
            popupMenu.menuInflater.inflate(R.menu.popup_menu_visualizar,
                    popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_ver_imagenes -> {
                        Toast.makeText(applicationContext, "Ver imagenes", Toast.LENGTH_SHORT).show()
                        return@OnMenuItemClickListener true
                    }
                }
                true
            })
            popupMenu.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}