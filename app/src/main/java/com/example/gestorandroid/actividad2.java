package com.example.gestorandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class actividad2 extends AppCompatActivity implements View.OnClickListener {

    EditText etReferencia, etDescripcion, etUnidadmedida, etValoruni;
    Button btnguardar1;
    RequestQueue requestQueue;

    private static final String URL2 = "http://192.168.0.10/gestorandroid/producto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestQueue = Volley.newRequestQueue(this);
        //UI
        initUI();

        btnguardar1.setOnClickListener(this);

    }

    public void aRetornar(View v){
        finish();

    }

    private void initUI() {
        //Edittext
        etReferencia = findViewById(R.id.editTextText2);
        etDescripcion = findViewById(R.id.editTextText3);
        etUnidadmedida = findViewById(R.id.editTextText4);
        etValoruni = findViewById(R.id.editTextText5);
        //button

        btnguardar1 = findViewById(R.id.button2);

    }

    /*override*/
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button2) {
            String referencia = etReferencia.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String unidadmedida = etUnidadmedida.getText().toString().trim();
            String valoruni = etValoruni.getText().toString().trim();

            createUser(referencia, descripcion, unidadmedida, valoruni);

            etReferencia.setText("");
            etDescripcion.setText("");
            etUnidadmedida.setText("");
            etValoruni.setText("");
            etReferencia.requestFocus();

        }


    }

    private void createUser(final String referencia, final String descripcion, final String unidadmedida, final String valoruni) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(actividad2.this, "producto registrado", Toast.LENGTH_LONG  ).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        ){
            //Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("referencia", referencia);
                params.put("descripcion", descripcion);
                params.put("unidadmedida", unidadmedida);
                params.put("valoruni", valoruni);
                return params;
            }
        };

        requestQueue.add(stringRequest);





    }

}

