package com.example.notas_001;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notas_001.datos.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class activityAgregarNota extends AppCompatActivity implements
        PopupMenu.OnMenuItemClickListener {

    private EditText title;
    private EditText description;
    private Button buttonAccept;
    private ArrayList<RecursosNota> listaRecursos;
    public static int CAMERA_REQUEST = 123;
    private static int GALLERY_REQUEST = 124;
    private com.google.android.material.floatingactionbutton.FloatingActionButton floatingButton;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_nota);
        listaRecursos = new ArrayList<>();
        title = (EditText) findViewById(R.id.txtTaskTitle);
        description = (EditText) findViewById(R.id.txtTaskDescription);
        buttonAccept = (Button) findViewById(R.id.btn_add_note);
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
        buttonAccept.setOnClickListener(
                (item) -> {
                    try {
                        addNote();
                        addResourcesToDB();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addNote() {
        try {
            new daoNota(getApplication()).insert(
                    new Nota(title.getText().toString(),
                            description.getText().toString())
            );
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addResourcesToDB() {
        try {
            listaRecursos.forEach(
                    item -> {
                        new daoRecursosNota(getApplication())
                                .insert(item);
                    }
            );
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage() + " --",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void addImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, activityAgregarNota.CAMERA_REQUEST);
    }

    private void addFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Seleccione una imagen"),
                GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == activityAgregarNota.CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

        }
        if (requestCode == activityAgregarNota.GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = null;
            Uri selectedImage;

            String filePath = null;
            assert data != null;
            selectedImage = data.getData();
            String selectPath = selectedImage.getPath();
            if (selectPath != null) {
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    listaRecursos.add(new RecursosNota(selectedImage.toString(), "image"));
                    Toast.makeText(this, selectedImage.toString(), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getBaseContext(), "Hola mundo", Toast.LENGTH_LONG).show();
        return false;
    }
}