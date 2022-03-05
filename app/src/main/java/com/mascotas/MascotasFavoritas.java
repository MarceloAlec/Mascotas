package com.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {

    private ArrayList<Mascota> mascotasFav;
    private RecyclerView rvMascotasFav;
    private Toolbar mToolbar;
    private MascotaAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Mascotas Favoritas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMascotasFav = findViewById(R.id.rvMascotasFavoritas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotasFav.setLayoutManager(llm);

        mascotasFav = (ArrayList<Mascota> ) getIntent().getSerializableExtra("mascotasFav");

        adaptador = new MascotaAdapter(mascotasFav, MascotasFavoritas.this);
        rvMascotasFav.setAdapter(adaptador);

    }
}