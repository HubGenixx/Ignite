package com.example.ignite;

import static com.example.ignite.Data.PostRequest.PostData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Models.AddUser;

public class NotificationActivity extends AppCompatActivity {

    EditText billAmt,remark;
    TextView name,email,phone_number;
    Button onNotify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        name = findViewById(R.id.nt_name);
        email = findViewById(R.id.nt_email);
        phone_number = findViewById(R.id.nt_phone_number);
        billAmt = findViewById(R.id.nt_edt_bill);
        remark=findViewById(R.id.nt_edt_remark);
        onNotify= findViewById(R.id.btn_notify);


        // Setting Values
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        phone_number.setText(getIntent().getStringExtra("phone_number"));
        billAmt.setText(getIntent().getStringExtra("bill"));

        onNotify.setOnClickListener(view -> {
            PostData(new AddUser(
                    name.getText().toString(),
                    email.getText().toString(),
                    phone_number.getText().toString(),
                    billAmt.getText().toString()==null?"0":billAmt.getText().toString(),
                    remark.getText().toString()==null?"No Remark":remark.getText().toString()),this);
        });





    }
}