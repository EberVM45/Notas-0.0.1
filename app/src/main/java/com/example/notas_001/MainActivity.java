package com.example.notas_001;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.notas_001.Fragments.MainActivityF;
import com.example.notas_001.Fragments.MainActivityTareas;
import com.example.notas_001.datos.Tarea;
import com.example.notas_001.datos.daoTarea;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //Variables para cargar el fragment notas
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        notification();
        //Establecer evento onclick al navigatorView
        navigationView.setNavigationItemSelectedListener(this);
        //Establecer evetno de onclick en boton
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //Fragment principal
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainActivityF());
        fragmentTransaction.commit();
    }

    private void notification() {
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                checkdateFromDataBase();
                handler.postDelayed(this, 2000);
            }
        }, 2000);

    }

    private void message(String titulo, String texto) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentText(titulo)
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true)
                .setContentTitle(texto);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.notas) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainActivityF());
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.tareas) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainActivityTareas());
            fragmentTransaction.commit();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkdateFromDataBase() {
        Stream<Tarea> lista = new daoTarea(this).getAll().stream();
        lista.forEach(item -> {
            if (item.getFecha().equals(actualTime())) {
                message("Hoy es la fecha de esta tarea", "La tarea es " + item.getTitulo() + "\n" +
                        item.getFecha());
            }
        });
    }

    private String actualTime() {
        SimpleDateFormat dfDate_day = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        Calendar c = Calendar.getInstance();
        return dfDate_day.format(c.getTime());

    }
}