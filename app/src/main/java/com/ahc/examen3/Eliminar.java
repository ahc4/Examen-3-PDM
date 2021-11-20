package com.ahc.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Eliminar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText id, nombre, costo, foto;
    Button buscar, btnDelete;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String url = "";
    String url2 = "";
    int tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        id = findViewById(R.id.editTextNumber6);
        nombre = findViewById(R.id.editTextTextPersonName5);
        costo = findViewById(R.id.editTextNumber8);
        foto = findViewById(R.id.editTextTextPersonName6);
        buscar = findViewById(R.id.button4);
        btnDelete = findViewById(R.id.button5);

        request = Volley.newRequestQueue(getApplicationContext());

        buscar.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + id.getText().toString();
            btnClick(view);
        });

        btnDelete.setOnClickListener(view -> {
            url2 = "http://serviciosdigitalesplus.com/catalogo.php?tipo=4&id=" + id.getText().toString();
            btnClick2(view);
        });
    }

    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        //Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        tipo = 1;
    }

    public void btnClick2(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, this, this);
        request.add(jsonObjectRequest);
        //Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        tipo = 4;
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        if(tipo == 1)
        {
            JSONObject jsonObject2 = response.optJSONObject("dato");

            try {
                id.setText(jsonObject2.optString("id"));
                nombre.setText(jsonObject2.optString("nom"));
                costo.setText(jsonObject2.optString("costo"));
                foto.setText(jsonObject2.optString("foto"));
            }
            catch (Exception e)
            {

            }
        }
        else if (tipo == 4)
        {
            Toast.makeText(this, "Producto eliminado.", Toast.LENGTH_SHORT).show();
        }
    }
}