package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Nuevas librerías importadas
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

// Implementación del Response
public class Agregar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Variables para los componentes
    EditText id, nombre, costo, foto;
    Button btnAdd;

    // Cola de peticiones
    RequestQueue request;
    // Objeto JSON de la petición
    JsonObjectRequest jsonObjectRequest;
    // Cadena del url
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        // Relación de variables con componentes
        id = findViewById(R.id.txtID);
        nombre = findViewById(R.id.txtNombre);
        costo = findViewById(R.id.txtCosto);
        foto = findViewById(R.id.txtFoto);
        btnAdd = findViewById(R.id.btnAdd);
        // Define la cola de solicitudes
        request = Volley.newRequestQueue(getApplicationContext());

        // Programación del botón Agregar
        btnAdd.setOnClickListener(view -> {
            // Su URL con los valores de entrada
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=3&id=" + id.getText().toString() +
                    "&nom=" + nombre.getText().toString() + "&costo=" + costo.getText().toString() + "&foto=" + foto.getText().toString();
            btnClick(view);
        });
    }

    // Crea una solicitud y la guarda en la cola
    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    // Cuando se crea el producto nuevo, el texto de los campos se borra y muestra un mensaje emergente indicando que se agregó.
    @Override
    public void onResponse(JSONObject response) {
        id.setText("");
        nombre.setText("");
        costo.setText("");
        foto.setText("");
        Toast.makeText(this, "Producto agregado exitosamente.", Toast.LENGTH_SHORT).show();
    }

    // Devuelve el mensaje de error de la petición
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }
}