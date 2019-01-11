package com.moisesexample1.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
//Esto es una prueba para ver si logro hacer commit, soy tony. Ok listo chulo
   TextView tv_register;
   Button btn_ini;
   EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_register= findViewById(R.id.etiRegister);
        btn_ini= findViewById(R.id.btn_ini);
        et_username= findViewById(R.id.textV_user);
        et_password= findViewById(R.id.textV_password);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister;
                intentRegister = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(intentRegister);
            }
        });

        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username= et_username.getText().toString();
                final String password= et_password.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");

                            if(success){
                                String name= jsonResponse.getString("name");
                                int age= jsonResponse.getInt("age");

                                Intent intent= new Intent(MainActivity.this, User.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);
                                intent.putExtra("password", password);

                                MainActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error de Inicio de sesion").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest= new LoginRequest(username, password, responseListener);
                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);

            }
        });

    }


}
