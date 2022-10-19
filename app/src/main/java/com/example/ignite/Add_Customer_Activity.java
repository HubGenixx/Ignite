package com.example.ignite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import Models.AddUser;
import Models.dashboard_bill_model;

public class Add_Customer_Activity extends AppCompatActivity {

    TextView name;
    TextView phone;
    TextView email;
    TextView bill;
    TextView remark;

    Button Btn_cancel;
    Button Btn_add;

    FirebaseDatabase database;
    FirebaseFirestore ffStore;
    dashboard_bill_model dashboard_bill_model;
    DatabaseReference documentReference;
    AddUser addUser;

    LoadingIndicator loadingIndicator = new LoadingIndicator(Add_Customer_Activity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        name = findViewById(R.id.edt_uz_name);
        phone = findViewById(R.id.edt_uz_phone);
        email = findViewById(R.id.edt_uz_email);
        bill =  findViewById(R.id.edt_Uz_bill);
        remark = findViewById(R.id.edt_uz_remark);

        Btn_add = findViewById(R.id.btn_add);
        Btn_cancel = findViewById(R.id.btn_cancel);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Add Button
        Btn_add.setOnClickListener(view -> {
            loadingIndicator.startLoading(); // Start Loading
            AddUser( new AddUser(
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString()),this);
            name.setText("");
            email.setText("");
            phone.setText("");

        });


    }

    // AddUser Add's User to DataBase
    public void AddUser(AddUser model,Context context){
        FirebaseDatabase db;
        DatabaseReference databaseReference;
        db=FirebaseDatabase.getInstance();// Root Node
        databaseReference=db.getReference("Post/Customer/"+model.getFullName());
        databaseReference.child(model.getFullName());

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