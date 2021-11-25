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

// Implementación de Response
public class Modificar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Inicializar variables
    Button search, btnUpdate;
    EditText id, nombre, costo, foto;

    // Cola de peticiones
    RequestQueue request;
    // Objeto JSON de la petición
    JsonObjectRequest jsonObjectRequest;
    // URL para buscar producto
    String url = "";
    // URL para modificar producto
    String url2 = "";
    // Define el tipo de método en el URL
    int tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        // Relación variables con componentes
        search = findViewById(R.id.button);
        id = findViewById(R.id.editTextNumber7);
        nombre = findViewById(R.id.editTextTextPersonName3);
        costo = findViewById(R.id.editTextNumber2);
        foto = findViewById(R.id.editTextTextPersonName2);
        btnUpdate = findViewById(R.id.button3);
        request = Volley.newRequestQueue(getApplicationContext());

        // Programación del botón Buscar
        search.setOnClickListener(view -> {
            // URL1 definida
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + id.getText().toString();
            btnClick(view);
        });

        // Programación del botón modificar
        btnUpdate.setOnClickListener(view -> {
            // URL2 definida
            url2 = "http://serviciosdigitalesplus.com/catalogo.php?tipo=5&id=" + id.getText().toString() +
                    "&nom=" + nombre.getText().toString() + "&costo=" + costo.getText().toString() + "&foto=" + foto.getText().toString();
            btnClick2(view);
        });
    }

    // Nueva solicitud de buscar
    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 1;
    }

    // Nueva solicitud de modificar
    public void btnClick2(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 2;
    }

    // Mensaje de error en la solicitud
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }

    // Acciones durante las peticiones
    @Override
    public void onResponse(JSONObject response) {
        // Cuando hay que buscar el producto
        if(tipo == 1)
        {
            // Lee el objeto JSON
            JSONObject jsonObject2 = response.optJSONObject("dato");

            try {
                // Sus datos pasan a los campos de texto para editar
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
        // Después de que se realizó la modificación
        else if (tipo == 2)
        {
            Toast.makeText(this, "Producto modificado.", Toast.LENGTH_SHORT).show();
        }

    }
}