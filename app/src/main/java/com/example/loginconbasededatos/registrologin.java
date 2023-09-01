package com.example.loginconbasededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class registrologin extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    EditText cedula,nombres,contraseña,correo;
    TextView col;
    Button registrar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrologin);

        cedula= (EditText) findViewById(R.id.registro_cedula);
        nombres= (EditText) findViewById(R.id.registro_nombres);
        correo= (EditText) findViewById(R.id.registro_correo);

        contraseña= (EditText) findViewById(R.id.registro_contraseña);
        registrar= (Button) findViewById(R.id.registrar);
        request= Volley.newRequestQueue(this);




        //bloquiar tecla de salto
        cedula.setSingleLine(false);
        cedula.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        nombres.setSingleLine(false);
        nombres.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        correo.setSingleLine(false);
        correo.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
         /* pro.setProgress(10);


                            pro.setProgressTintList(ColorStateList.valueOf(Color.RED));
                            col.setText("Debil :(");*/




        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarwebservice();
            }
        });
    }


    public void cargarwebservice(){

        try {
            if(nombres.getText().toString().equals("")||correo.getText().toString().equals("")||cedula.getText().toString().equals("")||contraseña.getText().toString().equals("")){
                Toast.makeText(this,"Al menos un campo vacio, todos los campos son obligatorio, Por favor Completelo",Toast.LENGTH_LONG).show();
            }else {
                progreso = new ProgressDialog(this);
                progreso.setMessage("Cargando...");
                progreso.show();
                //https://clinicaprotoipo.000webhostapp.com/registro.php
                String url = "http://hospitalmanabideldia.com/registro_pacientes.php?Nombres=" + nombres.getText().toString()
                        + "&Correo=" + correo.getText().toString()+ "&username=" + cedula.getText().toString()+ "&password=" + contraseña.getText().toString();
                url = url.replace(" ", "%20");
                jsonObjectRequest = new JsonObjectRequest(VoiceInteractor.Request.Method.GET, url, null, this, this);
                request.add(jsonObjectRequest);
            }

        }catch (Exception exe){
            Toast.makeText(this,exe.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResponse(JSONObject response) {


        Toast.makeText(this,"Se registrado correctamente", Toast.LENGTH_SHORT).show();
        progreso.hide();
        nombres.setText("");
        correo.setText("");
        nombres.setText("");
        contraseña.setText("");
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(),"No se pudo registrar , Hubo un error al conectar por favor verifica la conexión a internet o intente nuevamente , Error : "+ error.toString(), Toast.LENGTH_LONG).show();

        Log.i("ERROR", error.toString());

    }

}
