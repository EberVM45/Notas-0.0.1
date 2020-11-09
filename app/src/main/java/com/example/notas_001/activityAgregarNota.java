package com.example.notas_001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notas_001.datos.Nota;
import com.example.notas_001.datos.daoNota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activityAgregarNota extends AppCompatActivity implements
        PopupMenu.OnMenuItemClickListener {

    private EditText title;
    private EditText description;
    private Button buttonAccept;
    public static int CAMERA_REQUEST = 123;
    private static int GALLERY_REQUEST = 124;
    private com.google.android.material.floatingactionbutton.FloatingActionButton floatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_nota);
        title = (EditText) findViewById(R.id.txtTittle);
        description = (EditText) findViewById(R.id.editText);
        buttonAccept = (Button) findViewById(R.id.btnAceptarNota);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButtonNote);
        floatingButton.setOnClickListener(item -> {
            PopupMenu popup = new PopupMenu(this, floatingButton);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemCamera:
                            addImageFromCamera();
                            return true;
                        case R.id.itemGaleria:
                            addFromGallery();
                            return true;
                    }
                    return true;
                }
            });
            popup.show();
        });
        buttonAccept.setOnClickListener((item) -> {
            addNote();
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("cosas") != null) {
                Toast.makeText(getApplicationContext(),
                        bundle.getString("cosas"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addNote() {
        try {
            new daoNota(getApplication()).insert(
                    new Nota(title.getText().toString(), description.getText().toString())
            );
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void addImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, activityAgregarNota.CAMERA_REQUEST);
    }

    private void addFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == activityAgregarNota.CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getBaseContext(), "Hola mundo", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getBaseContext(), "Hola mundo", Toast.LENGTH_LONG).show();
        return false;
    }
}