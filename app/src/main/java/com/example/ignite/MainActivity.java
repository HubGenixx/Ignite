package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import Models.dashboard_bill_model;
import adapters.dashboard_list_adapter;
public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button logout,billPostreq;

    RecyclerView dashboardview;
    ArrayList<dashboard_bill_model> dashboardList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dashboardview = findViewById(R.id.idRVItems);
        dashboardList = new ArrayList<>();
        dashboardList.add(new dashboard_bill_model(
                "Ritesh Sonavne",
                "2334455226",
                "rits@gmail.com",
                "Stonks only go up burr...",
                "750"));


    dashboard_list_adapter adapter = new dashboard_list_adapter(dashboardList,MainActivity.this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
    dashboardview.setLayoutManager(layoutManager);
    dashboardview.setNestedScrollingEnabled(false);
    dashboardview.setAdapter(adapter);

    dashboardview.setOnClickListener(view -> {

    });




        // FireBase
        auth = FirebaseAuth.getInstance();

        //Id's
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(view -> {

            auth.signOut();
            Intent intent = new Intent(MainActivity.this,login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }



    public void btn_add_customer(View view) {
        Intent intent = new Intent(MainActivity.this,Add_Customer_Activity.class);
        startActivity(intent);

    }
}