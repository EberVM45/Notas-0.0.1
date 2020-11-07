package com.example.notas_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class activityAgregarNota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_nota);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            if(bundle.getString("cosas")!=null){
                    Toast.makeText(getApplicationContext(),
                        bundle.getString("cosas"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}