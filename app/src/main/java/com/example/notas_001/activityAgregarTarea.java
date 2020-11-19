package com.example.notas_001;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notas_001.datos.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class activityAgregarTarea extends AppCompatActivity {

    private ImageView imgCalendar, imgTime;
    private EditText description;
    private EditText title;
    private String fechaActual = "";
    private String fecha_seleccionada = "";
    private String hora_seleccionada;
    private Button btnCancelar;
    private Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_agregar_tarea);
        initializingComponents();
        imgCalendar.setOnClickListener((item) -> {
            showDatePickerDialog();
        });
        btnCancelar.setOnClickListener(
                (item) -> {
                    String fecha_final = fecha_seleccionada + " " + hora_seleccionada;
                    description.setText(fecha_final + "\n" + fechaActual);
                    description.setText(description.getText().toString() + "\n" + (fecha_final.equals(fechaActual)));
                }
        );
        actualTime();
        imgTime.setOnClickListener((item) -> {
            showTimePicked();
        });
        btnAccept.setOnClickListener(
                (item) -> {
                    addNoteToDataBase();
                }
        );
    }

    private void addNoteToDataBase() {
        try {
            String fecha_final = fecha_seleccionada + " " + hora_seleccionada;
            Tarea tarea = new Tarea(title.getText().toString(),description.getText().toString(),fecha_final);
            new daoTarea(this).insert(tarea);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void actualTime() {
        SimpleDateFormat dfDate_day = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        Calendar c = Calendar.getInstance();
        fechaActual = dfDate_day.format(c.getTime());

    }

    private void showTimePicked() {
        final Calendar getDate = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                getDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                getDate.set(Calendar.MINUTE, minute);
                SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");
                hora_seleccionada = timeformat.format(getDate.getTime());
            }
        }, getDate.get(Calendar.HOUR_OF_DAY), getDate.get(Calendar.MINUTE), false);

        timePickerDialog.show();
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                fecha_seleccionada = day + "/" + (month + 1) + "/" + year;
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    private void initializingComponents() {
        imgCalendar = findViewById(R.id.imgCalendar);
        description = findViewById(R.id.txtTaskDescription);
        imgTime = findViewById(R.id.imgTime);
        btnCancelar = findViewById(R.id.botonCancelarr);
        btnAccept = findViewById(R.id.btn_add_task);
        title = findViewById(R.id.txtTaskTitle);
    }
}