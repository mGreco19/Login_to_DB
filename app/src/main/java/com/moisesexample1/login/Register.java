package com.moisesexample1.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends Activity implements View.OnClickListener {

    EditText etName, etUser, etPassword, etAge;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName= findViewById(R.id.ediT_name);
        etUser= findViewById(R.id.ediT_user);
        etPassword= findViewById(R.id.ediT_password);
        etAge= findViewById(R.id.ediT_age);

        btnRegister= findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        final String name= etName.getText().toString();
        final String username= etUser.getText().toString();
        final String password= etPassword.getText().toString();
        final int age= Integer.parseInt(etAge.getText().toString());
//Se lleva a cabo el request en la Base de datos
        Response.Listener<String> responseListener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse= new JSONObject(response);
                    boolean success= jsonResponse.getBoolean("succes");

                    if(success){
                        Intent intent= new Intent(Register.this, MainActivity.class);
                        Register.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder= new AlertDialog.Builder(Register.this);
                        builder.setMessage("Error en el registro").setNegativeButton("Retry", null).create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest=new RegisterRequest(name, username, age, password, responseListener);
        RequestQueue queue= Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);
    }
}
