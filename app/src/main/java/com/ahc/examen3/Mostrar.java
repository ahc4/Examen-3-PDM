package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    //EditText texto;
    RecyclerView lista;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        //texto = findViewById(R.id.texto);
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        request = Volley.newRequestQueue(getApplicationContext());
        String url = "http://serviciosdigitalesplus.com/catalogo.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "" + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "" + response, Toast.LENGTH_SHORT).show();
        JSONArray json = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        String line = "";
        ArrayList<Producto> productoArrayList = new ArrayList<>();

        try
        {
            for (int i=0; i < json.length(); i++)
            {
                jsonObject = json.getJSONObject(i);

                Producto producto = new Producto();
                producto.setId(jsonObject.getInt("id"));
                producto.setNombre(jsonObject.getString("nom"));
                producto.setCosto(jsonObject.getInt("costo"));
                producto.setFoto(jsonObject.getString("foto"));
                /*line += "***************************************** \n";
                line += "ID:" + jsonObject.optString("id") + "\n";
                line += "Nombre:" + jsonObject.optString("nom") + "\n";
                line += "Costo:" + jsonObject.optString("costo") + "\n";
                line += "Foto:" + jsonObject.optString("foto") + "\n";
                line += "***************************************** \n\n";*/
                productoArrayList.add(producto);
            }

            AdapterDatos adapterDatos = new AdapterDatos(getApplicationContext(), productoArrayList);
            lista.setAdapter(adapterDatos);
            //texto.setText(line);
        }
        catch(Exception ex)
        {
            //Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}