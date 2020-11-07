package com.example.notas_001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.notas_001.Fragments.MainActivityF;
import com.example.notas_001.Fragments.MainActivityTareas;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;
        ActionBarDrawerToggle actionBarDrawerToggle;
        Toolbar toolbar;
        NavigationView navigationView;
        //Variables para cargar el fragment notas
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);

        //Establecer evento onclick al navigatorView
        navigationView.setNavigationItemSelectedListener(this);
        //Establecer evetno de onclick en boton
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //Fragment principal
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new MainActivityF());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId()==R.id.notas){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new MainActivityF());
            fragmentTransaction.commit();
        }
        if (item.getItemId()==R.id.tareas){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new MainActivityTareas());
            fragmentTransaction.commit();
        }
        return false;
    }
}