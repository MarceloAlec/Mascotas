package com.mascotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> mascotasFav = new ArrayList<>();
    private Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {

        Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgMascota.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());

        if (mascota.getRaiting() != 0){
            mascotaViewHolder.tvRaiting.setText(String.valueOf(mascota.getRaiting()));
        }

        mascotaViewHolder.btnRaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nuevoRate;

                if(mascotaViewHolder.tvRaiting.getText().equals("")){
                    nuevoRate = "1";
                }
                else{
                    nuevoRate = String.valueOf(Integer.parseInt(mascotaViewHolder.tvRaiting.getText().toString())+1);
                }

                mascotaViewHolder.tvRaiting.setText(nuevoRate);
                mascota.setRaiting(Integer.valueOf(nuevoRate));

                if(mascotasFav.size()<5){
                    if(!mascotasFav.contains(mascota)){
                        mascotasFav.add(mascota);
                    }
                }
                else{
                    mascotasFav.remove(0);
                    mascotasFav.add(mascota);
                }
            }
        });
    }

    public ArrayList<Mascota> obtenerMascotasFav(){
        return mascotasFav;
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMascota;
        private ImageButton btnRaiting;
        private TextView tvNombre;
        private TextView tvRaiting;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMascota = itemView.findViewById(R.id.imgMascota);
            btnRaiting = itemView.findViewById(R.id.btnHueso);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRaiting = itemView.findViewById(R.id.tvRaiting);
        }
    }
}
