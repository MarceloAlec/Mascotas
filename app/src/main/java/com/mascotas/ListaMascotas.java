package com.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> mascotasFav;
    private MascotaAdapter adaptador;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMascotas = findViewById(R.id.rvMascotas);
        mToolbar = findViewById(R.id.toolbar);


        setSupportActionBar(mToolbar);
        setTitle("Mascotas");

        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.corgi, "Peluche",0));
        mascotas.add(new Mascota(R.drawable.fat_cat, "Pelusa",0));
        mascotas.add(new Mascota(R.drawable.h_mster, "Emilio",0));
        mascotas.add(new Mascota(R.drawable.hachiko, "Tommy",0));
        mascotas.add(new Mascota(R.drawable.perro_gu, "Ben",0));
        mascotas.add(new Mascota(R.drawable.lindo_h_mster, "Tefy",0));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);

        adaptador = new MascotaAdapter(mascotas, ListaMascotas.this);
        rvMascotas.setAdapter(adaptador);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_mascotas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favoritos:
                abrirActividad();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirActividad() {

        mascotasFav=adaptador.obtenerMascotasFav();

        if(mascotasFav.size()>0){
            Intent intent = new Intent(ListaMascotas.this, MascotasFavoritas.class);
            intent.putExtra("mascotasFav", mascotasFav);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "No tiene mascotas favoritas", Toast.LENGTH_SHORT).show();
        }
    }

}