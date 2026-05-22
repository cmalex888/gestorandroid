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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsuario, etClave, etNombre, etApellidos, etCelular;
    Button btnGuardar;
    RequestQueue requestQueue;

    private static final String URL1 = "http://192.168.0.10/gestorandroid/guardar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestQueue = Volley.newRequestQueue(this);
        //UI
        initUI();

        btnGuardar.setOnClickListener(this);
    }



    private void initUI(){
        //Edittext
        etUsuario = findViewById(R.id.editTextText2);
        etClave = findViewById(R.id.editTextText3);
        etNombre = findViewById(R.id.editTextText4);
        etApellidos = findViewById(R.id.editTextText5);
        etCelular = findViewById(R.id.editTextText6);

        //button

        btnGuardar = findViewById(R.id.button2);

    }

public void aProductos (View v)
{
    Intent intento = new Intent(this, actividad2.class);
    startActivity(intento);
}


    /*override*/
    public void onClick(View v){
        int id = v.getId();

        if (id == R.id.button2){
            String usuario = etUsuario.getText().toString().trim();
            String clave = etClave.getText().toString().trim();
            String nombre = etNombre.getText().toString().trim();
            String apellidos = etApellidos.getText().toString().trim();
            String celular = etCelular.getText().toString().trim();

            createUser(usuario, clave, nombre, apellidos, celular);

            etClave.setText("");
            etNombre.setText("");
            etApellidos.setText("");
            etCelular.setText("");
            etUsuario.setText("");
            etUsuario.requestFocus();

        }

    }

    private void createUser(final String usuario, final String clave, final String nombre, final String apellidos, final String celular) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(MainActivity.this, "correcto", Toast.LENGTH_SHORT).show();
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
                params.put("usuario", usuario);
                params.put("clave", clave);
                params.put("nombre", nombre);
                params.put("apellidos", apellidos);
                params.put("celular", celular);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }


}