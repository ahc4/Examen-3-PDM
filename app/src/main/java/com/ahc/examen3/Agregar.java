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

public class Agregar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText id, nombre, costo, foto;
    Button btnAdd;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        id = findViewById(R.id.txtID);
        nombre = findViewById(R.id.txtNombre);
        costo = findViewById(R.id.txtCosto);
        foto = findViewById(R.id.txtFoto);
        btnAdd = findViewById(R.id.btnAdd);
        request = Volley.newRequestQueue(getApplicationContext());

        btnAdd.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=3&id=" + id.getText().toString() +
                    "&nom=" + nombre.getText().toString() + "&costo=" + costo.getText().toString() + "&foto=" + foto.getText().toString();
            btnClick(view);
        });
    }

    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Producto agregado exitosamente.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }
}