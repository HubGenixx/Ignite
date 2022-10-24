package com.example.ignite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import Models.AddUser;

public class Add_Customer_Activity extends AppCompatActivity {

    TextView name;
    TextView phone;
    TextView email;
    TextView bill;
    TextView remark;

    Button Btn_cancel;
    Button Btn_add;


    LoadingIndicator loadingIndicator = new LoadingIndicator(Add_Customer_Activity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        name = findViewById(R.id.edt_uz_name);
        phone = findViewById(R.id.edt_uz_phone);
        email = findViewById(R.id.edt_uz_email);
        bill =  findViewById(R.id.nt_edt_bill);
        remark = findViewById(R.id.nt_edt_remark);

        Btn_add = findViewById(R.id.btn_notify);
        Btn_cancel = findViewById(R.id.btn_cancel);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Add Button
        Btn_add.setOnClickListener(view -> {
            loadingIndicator.startLoading(); // Start Loading
            AddUser(new AddUser(
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    bill.getText().toString(),
                    remark.getText().toString()),this  );

            //Resetting Values
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    bill.setText("");
                    remark.setText("");


        });

        Btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Customer_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // AddUser Add's User to DataBase
    public void AddUser(AddUser model,Context context){
        FirebaseUser currentUser;
        FirebaseDatabase db;
        DatabaseReference databaseReference;
        db=FirebaseDatabase.getInstance();// Root Node
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference=db.getReference("posts/"+currentUser.getUid()+"/Customer/"+model.getPhone_number());

        databaseReference.setValue(model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "User registered", Toast.LENGTH_SHORT).show();
                        loadingIndicator.dismissDialog();
                        Intent intent = new Intent(Add_Customer_Activity.this,MainActivity.class);
                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Server under Maintenance", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void btn_add(View view) {
    }
}