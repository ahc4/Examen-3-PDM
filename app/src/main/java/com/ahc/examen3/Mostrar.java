package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

// Nuevas clases importadas
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Implementación del Response
public class Mostrar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Variables de los componentes
    //EditText texto;
    RecyclerView lista;

    //Cola de solicitudes
    RequestQueue request;
    // Objeto JSON de solicitud
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        // Relación variable con componente
        //texto = findViewById(R.id.texto);
        lista = findViewById(R.id.lista);
        // Definición del RecyclerView para su vista
        lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Crea la instancia de la cola de solicitudes
        request = Volley.newRequestQueue(getApplicationContext());
        // Url utilizada
        String url = "http://serviciosdigitalesplus.com/catalogo.php";
        // Nueva solicitud
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    // Devuelve mensaje de error de la petición
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    // Acción de la solicitud
    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(this, "" + response, Toast.LENGTH_SHORT).show();
        // Lectura del arreglo de los productos
        JSONArray jsonArray = response.optJSONArray("datos");

        JSONObject jsonObject;
        //String line = "";

        // Nuevo ArrayList
        ArrayList<Producto> productoList = new ArrayList<>();

        try
        {
            // Recorrido del arreglo
            for (int i=0; i < jsonArray.length(); i++)
            {
                // Lectura del objeto del producto
                jsonObject = jsonArray.getJSONObject(i);

                // Nuevo objeto
                Producto producto = new Producto();
                // Pasar los datos del json a los atributos de la clase Producto
                producto.setId(jsonObject.optString("id"));
                producto.setNombre(jsonObject.optString("nom"));
                producto.setCosto(jsonObject.optString("costo"));
                producto.setFoto(jsonObject.optString("foto"));
                /*line += "***************************************** \n";
                line += "ID:" + jsonObject.optString("id") + "\n";
                line += "Nombre:" + jsonObject.optString("nom") + "\n";
                line += "Costo:" + jsonObject.optString("costo") + "\n";
                line += "Foto:" + jsonObject.optString("foto") + "\n";
                line += "***************************************** \n\n";*/
                // Se agrega cada elemento a la lista
                productoList.add(producto);
            }

            // Visualización de los elementos
            AdapterDatos adapterDatos = new AdapterDatos(productoList);
            lista.setAdapter(adapterDatos);
            //texto.setText(line);
        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}