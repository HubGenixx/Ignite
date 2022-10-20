package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import Models.dashboard_bill_model;
import adapters.dashboard_list_adapter;
public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button logout,billPostreq;

    RecyclerView recyclerView;
    ArrayList<dashboard_bill_model> dashboardList;
    DatabaseReference databaseReference;
    dashboard_list_adapter adapter;
//    LoadingIndicator loadingIndicator = new LoadingIndicator(MainActivity.this);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.idRVItems);
        dashboardList = getData();
//        dashboardList.add(new dashboard_bill_model(
//                "Ritesh Sonavne",
//                "2334455226",
//                "rits@gmail.com",
//                "Stonks only go up burr...",
//                "750"));


        adapter = new dashboard_list_adapter(dashboardList,MainActivity.this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setNestedScrollingEnabled(false);
    recyclerView.setAdapter(adapter);

    recyclerView.setOnClickListener(view -> {

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

    public ArrayList <dashboard_bill_model> getData(){
        ArrayList<dashboard_bill_model> arr=new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference("Post/Customer");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    dashboard_bill_model UserData = dataSnapshot.getValue(dashboard_bill_model.class);
                    arr.add(UserData);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        loadingIndicator.dismissDialog();
        return dashboardList;
    }
}