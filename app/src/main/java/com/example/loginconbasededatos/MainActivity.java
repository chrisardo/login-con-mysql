package com.example.loginconbasededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    TextView regist;
    ProgressDialog progreso;
    StringRequest stringRequest;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);


        UsernameEt.setSingleLine(false);
        UsernameEt.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        log= (Button) findViewById(R.id.btnLogin);
        regist= (TextView) findViewById(R.id.registrar);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplication(), registrologin.class);
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();

            }
        });
    }

    public  void fi(View view){
        System.exit(0);
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        finish();
    }

    private void log() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Iniciando Sesión...");
        progreso.show();
        String url="http://hospitalmanabideldia.com/login.php?usu="+ UsernameEt.getText().toString()+
                "&pas=" + PasswordEt.getText().toString();

        stringRequest=new StringRequest(DownloadManager.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                if(response.trim().equals("exito")){
                    progreso.hide();
                    UsernameEt.setText("");
                    PasswordEt.setText("");
                    Intent i = new Intent(getApplication(), inicio.class);
                    startActivity(i);
                    finish();
                    Bienvenido();

                }else{

                    progreso.hide();
                    Toast.makeText(getApplication(),"Datos Incorrectos",Toast.LENGTH_SHORT).show();



                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),"No se ha podido conectar" + error, Toast.LENGTH_SHORT).show();

            }
        });
        //request.add(stringRequest);
        VolleySingleton.getIntanciaVolley(getApplication()).addToRequestQueue(stringRequest);

    }

    public void Bienvenido(){
        LayoutInflater inflater= (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View customToast=inflater.inflate(R.layout.toas_personalizado,null);
        TextView txt= (TextView)customToast.findViewById(R.id.txttoas);
        txt.setText("Bienvenido, Gracias por preferirnos ");
        Toast toast =new Toast(this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(customToast);
        toast.show();
    }
}
