package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Nuevas clases importadas
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

// Implementación del Response
public class Eliminar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Variables inicializadas
    EditText id, nombre, costo, foto;
    Button buscar, btnDelete;
    // Cola de solicitudes
    RequestQueue request;
    // Objeto JSON de la solicitud
    JsonObjectRequest jsonObjectRequest;
    // URL de Buscar
    String url = "";
    // URL de Eliminar
    String url2 = "";
    // Tipo de método
    int tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        // Relación variables con componentes
        id = findViewById(R.id.editTextNumber6);
        nombre = findViewById(R.id.editTextTextPersonName5);
        costo = findViewById(R.id.editTextNumber8);
        foto = findViewById(R.id.editTextTextPersonName6);
        buscar = findViewById(R.id.button4);
        btnDelete = findViewById(R.id.button5);

        // Nueva cola de solicitudes
        request = Volley.newRequestQueue(getApplicationContext());

        // Programación del botón Buscar
        buscar.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + id.getText().toString();
            btnClick(view);
        });

        // Programación del botón Eliminar
        btnDelete.setOnClickListener(view -> {
            url2 = "http://serviciosdigitalesplus.com/catalogo.php?tipo=4&id=" + id.getText().toString();
            btnClick2(view);
        });
    }

    // Función del botón Buscar
    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 1;
    }

    // Función del botón Eliminar
    public void btnClick2(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 4;
    }

    // Devuelve el mensaje de error de la solicitud
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    // Acciones durante las solicitudes
    @Override
    public void onResponse(JSONObject response) {
        // Si se realizó la búsqueda
        if(tipo == 1)
        {
            // Lee el objeto
            JSONObject jsonObject2 = response.optJSONObject("dato");

            try {
                // Los datos son mostrados en los campos de texto
                id.setText(jsonObject2.optString("id"));
                nombre.setText(jsonObject2.optString("nom"));
                costo.setText(jsonObject2.optString("costo"));
                foto.setText(jsonObject2.optString("foto"));
            }
            catch (Exception ex)
            {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        // Si se realizó la eliminación del producto.
        else if (tipo == 4)
        {
            id.setText("");
            nombre.setText("");
            costo.setText("");
            foto.setText("");
            Toast.makeText(this, "Producto eliminado.", Toast.LENGTH_SHORT).show();
        }
    }
}