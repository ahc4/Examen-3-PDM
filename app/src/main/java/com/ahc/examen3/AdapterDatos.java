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

    // Constructor por defecto
    public AdapterDatos(ArrayList<Producto> productos) { this.productos = productos; }

    // Instanciar la vista con el recurso del RecyclerView
    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, null, false);
        return new ViewHolderDatos(view);
    }

    // Prepara el retorno de los elementos
    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(productos.get(position));
    }

    // Devuelve el número de elementos que hay en la lista
    @Override
    public int getItemCount() { return productos.size(); }

    // Clase que permite asignar los valores del objeto a los componentes del recurso
    public static class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        // Variables de los componentes
        TextView id, nombre, precio, foto;

        // Relacionar las variables con los componentes del layout
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nombre = itemView.findViewById(R.id.txt_nombre);
            precio = itemView.findViewById(R.id.txt_costo);
            foto = itemView.findViewById(R.id.txt_foto);
        }

        // Asignación de datos
        public void asignarDatos(Producto p)
        {
            id.setText(p.getId());
            nombre.setText(p.getNombre());
            precio.setText(String.format("$ %s.00", p.getCosto()));
            foto.setText(p.getFoto());
        }
    }
}
