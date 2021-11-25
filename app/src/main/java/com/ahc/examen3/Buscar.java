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
public class Buscar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Variables de los componentes
    EditText id, nombre, costo, foto;
    Button buscar;
    String url;
    // Cola de solicitudes
    RequestQueue request;
    // Objeto JSON de la solicitud
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        // Definición de variables con componentes
        id = findViewById(R.id.editTextNumber4);
        nombre = findViewById(R.id.editTextTextPersonName);
        costo = findViewById(R.id.editTextNumber5);
        foto = findViewById(R.id.editTextTextPersonName4);
        buscar = findViewById(R.id.button2);
        // Nueva cola
        request = Volley.newRequestQueue(getApplicationContext());

        // Programación del botón
        buscar.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + id.getText().toString();
            btnClick(view);
        });
    }

    // Nueva petición y ser agregada a la cola
    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    // Mensaje de error en la solicitud
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    // Acciones  de la solicitud
    @Override
    public void onResponse(JSONObject response) {
        // Lee el objeto JSON
        JSONObject jsonObject2 = response.optJSONObject("dato");

        try {
            // Muestra los datos del producto encontrado
            id.setText(jsonObject2.optString("id"));
            nombre.setText(jsonObject2.optString("nom"));
            costo.setText(jsonObject2.optString("costo"));
            foto.setText(jsonObject2.optString("foto"));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}