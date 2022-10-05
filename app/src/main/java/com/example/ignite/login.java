package com.example.ignite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class login extends AppCompatActivity {

    TextView signup;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.change_to_signup);
        login = findViewById(R.id.btn_login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
}