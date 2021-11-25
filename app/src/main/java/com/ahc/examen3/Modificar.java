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

public class Modificar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    Button search, btnUpdate;
    EditText id, nombre, costo, foto;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String url = "";
    String url2 = "";
    int tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        search = findViewById(R.id.button);
        id = findViewById(R.id.editTextNumber7);
        nombre = findViewById(R.id.editTextTextPersonName3);
        costo = findViewById(R.id.editTextNumber2);
        foto = findViewById(R.id.editTextTextPersonName2);
        btnUpdate = findViewById(R.id.button3);
        request = Volley.newRequestQueue(getApplicationContext());

        search.setOnClickListener(view -> {
            url = "http://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=" + id.getText().toString();
            btnClick(view);
        });

        btnUpdate.setOnClickListener(view -> {
            url2 = "http://serviciosdigitalesplus.com/catalogo.php?tipo=5&id=" + id.getText().toString() +
                    "&nom=" + nombre.getText().toString() + "&costo=" + costo.getText().toString() + "&foto=" + foto.getText().toString();
            btnClick2(view);
        });
    }

    public void btnClick(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 1;
    }

    public void btnClick2(View v)
    {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, this, this);
        request.add(jsonObjectRequest);
        tipo = 2;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
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
            catch (Exception ex)
            {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else if (tipo == 2)
        {
            Toast.makeText(this, "Producto modificado.", Toast.LENGTH_SHORT).show();
        }

    }
}