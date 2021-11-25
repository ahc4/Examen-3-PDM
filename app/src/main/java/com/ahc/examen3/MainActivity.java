package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Variables inicializadas
    Button btnAgregar, btnMostrar, btnBuscar, btnModificar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definición de los botones
        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnEliminar);

        //Programación de los botones
        // A Agregar producto
        btnAgregar.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Agregar.class);
            startActivity(i);
        });

        // A Mostrar productos
        btnMostrar.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Mostrar.class);
            startActivity(i);
        });

        // A Buscar producto
        btnBuscar.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Buscar.class);
            startActivity(i);
        });

        // A Modificar producto
        btnModificar.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Modificar.class);
            startActivity(i);
        });

        // A Eliminar producto
        btnEliminar.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Eliminar.class);
            startActivity(i);
        });
    }
}