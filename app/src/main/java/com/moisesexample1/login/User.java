package com.moisesexample1.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.app.Activity;

public class User extends Activity {

    TextView tvName, tvUser, tvAge, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvName= findViewById(R.id.textV_name);
        tvUser= findViewById(R.id.textV_user);
        tvPassword= findViewById(R.id.textV_password);
        tvAge= findViewById(R.id.textV_age);

        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String username= intent.getStringExtra("username");
        String password= intent.getStringExtra("password");
        int age= intent.getIntExtra("age",-1);

        tvName.setText(name);
        tvUser.setText(username);
        tvPassword.setText(password);
        tvAge.setText(age+"");

    }
}
