package com.example.notas_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class activityAgregarTarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_tarea);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            if(bundle.getString("msj")!=null){
                    Toast.makeText(getApplicationContext(),
                        bundle.getString("msj"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}