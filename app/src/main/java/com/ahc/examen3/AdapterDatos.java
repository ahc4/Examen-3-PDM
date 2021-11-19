package com.ahc.examen3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>
{
    ArrayList<Producto> productos;

    public AdapterDatos(ArrayList<Producto> productos) { this.productos = productos; }

    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(productos.get(position));
    }

    @Override
    public int getItemCount() { return productos.size(); }

    public static class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        TextView id, nombre, precio;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nombre = itemView.findViewById(R.id.txt_nombre);
            precio = itemView.findViewById(R.id.txt_costo);
        }

        public void asignarDatos(Producto p)
        {
            id.setText(p.id);
            nombre.setText(p.nombre);
            precio.setText("$ " + p.costo);
        }
    }

}
