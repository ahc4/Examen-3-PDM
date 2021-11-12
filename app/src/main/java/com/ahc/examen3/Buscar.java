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

public class Buscar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText id;
    EditText nombre;
    EditText costo;
    EditText foto;
    EditText search;
    Button buscar;
    String url;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        id = findViewById(R.id.editTextNumber4);
        nombre = findViewById(R.id.editTextTextPersonName);
        costo = findViewById(R.id.editTextNumber5);
        foto = findViewById(R.id.editTextTextPersonName4);
        search = findViewById(R.id.editTextNumber3);
        buscar = findViewById(R.id.button2);
        request = Volley.newRequestQueue(getApplicationContext());

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + buscar.getText().toString();
                btnClick(view);
            }
        });
    }

    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
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
        //Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
    }
}