package com.example.pancho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        //llamar al fragmento equipos
                        Equipos q = new Equipos();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, q).commit();
                        break;
                    case 1:
                        //llamar al fragmento estadisticas
                        Estadisticas e = new Estadisticas();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, e).commit();
                        break;
                    case 2:
                        //llamar al fragmento encuentro
                        ProximosEncuentros p = new ProximosEncuentros();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, p).commit();
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }




}
