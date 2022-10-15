package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class login extends AppCompatActivity {

    TextView signup;
    Button login;
    EditText email_lgn,passwd;

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        currentUser = auth.getCurrentUser();

        signup = findViewById(R.id.change_to_signup);
        login = findViewById(R.id.btn_login_login);
        email_lgn = findViewById(R.id.edt_email_login);
        passwd = findViewById(R.id.edt_password_login);

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

                try{

                    auth.signInWithEmailAndPassword(email_lgn.getText().toString(),passwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent intent = new Intent(login.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });


                }
                catch (Exception e){
                    Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser != null){
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);

        }

    }
}