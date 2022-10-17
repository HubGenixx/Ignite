package com.example.ignite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Add_Customer_Activity extends AppCompatActivity {

    TextView name;
    TextView phone;
    TextView email;
    TextView bill;
    TextView remark;

    Button Btn_cancel;
    Button Btn_add;


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


    }

    public void btn_add(View view) {
    }
}