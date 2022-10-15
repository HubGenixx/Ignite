package com.example.ignite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Models.User;

public class sign_up extends AppCompatActivity {

    private TextView signin;
    EditText email,password, con_password,name,phn_no;
    Button signup;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = findViewById(R.id.btn_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        email =findViewById(R.id.edt_email);
        password =findViewById(R.id.edt_password);
        con_password =findViewById(R.id.edt_confirm_password);
        name = findViewById(R.id.edt_name);
        phn_no = findViewById(R.id.edt_phno);



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Navigation to Login Screen
        signin = findViewById(R.id.change_to_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_up.this,login.class);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_str = email.getText().toString();
                String password_str = password.getText().toString();


                    try{
                        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    User user = new User(name.getText().toString(),
                                            email.getText().toString(),
                                            phn_no.getText().toString(),
                                            password.getText().toString()
                                            ) ;
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);

                                    Toast.makeText(sign_up.this, "Success", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(sign_up.this, "error", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                    }
                    catch (Exception e){
                        Toast.makeText(sign_up.this, "some field might be empty.", Toast.LENGTH_SHORT).show();
                    }

            }
        });









    }
}