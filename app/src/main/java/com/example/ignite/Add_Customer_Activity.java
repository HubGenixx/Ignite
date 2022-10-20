package com.example.ignite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Models.Post;

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
        bill =  findViewById(R.id.edt_Uz_bill);
        remark = findViewById(R.id.edt_uz_remark);

        Btn_add = findViewById(R.id.btn_add);
        Btn_cancel = findViewById(R.id.btn_cancel);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Customer_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //Add Button
        Btn_add.setOnClickListener(view -> {
            loadingIndicator.startLoading(); // Start Loading

            Post post = new Post();
            post.setPostedBy(FirebaseAuth.getInstance().getUid());
            post.setName(name.getText().toString());
            post.setEmail(email.getText().toString());
            post.setBill(bill.getText().toString());
            post.setRemark(remark.getText().toString());
            post.setPhone_number(phone.getText().toString());

            FirebaseDatabase.getInstance().getReference().child("posts")
                    .push().setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loadingIndicator.dismissDialog();
                            Toast.makeText(Add_Customer_Activity.this, "Posted", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            remark.setText("");
                            bill.setText("");
                            phone.setText("");

                        }
                    });


        });


    }

}