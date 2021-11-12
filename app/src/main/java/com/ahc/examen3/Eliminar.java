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

    EditText id;
    Button btnDelete;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        id = findViewById(R.id.editTextNumber6);
        btnDelete = findViewById(R.id.button4);

        request = Volley.newRequestQueue(getApplicationContext());

        btnDelete.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=4&id=" + id.getText().toString();
            btnClick(view);
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
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
    }
}