package com.example.notas_001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notas_001.datos.Nota;
import com.example.notas_001.datos.daoNota;

public class activityAgregarNota extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button buttonAccept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_nota);
        title = (EditText)findViewById(R.id.txtTittle);
        description = (EditText)findViewById(R.id.editText);
        buttonAccept = (Button)findViewById(R.id.btnAceptarNota);
        buttonAccept.setOnClickListener((item) -> {
           addNote();
        });
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            if(bundle.getString("cosas")!=null){
                    Toast.makeText(getApplicationContext(),
                        bundle.getString("cosas"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void addNote(){
        try {
            new daoNota(getApplication()).insert(
                    new Nota(title.getText().toString(),description.getText().toString())
            );
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}