package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.AddUser;
import adapters.dashboard_list_adapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView dashboardview;
    ArrayList<AddUser> dashboardList;
    TextView pretext;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    // FireBase
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        pretext = findViewById(R.id.pre_text);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.license:
//                        Log.i("MENU_DRAWER_TAG","Home item is selected");
                        Intent i = new Intent(MainActivity.this,webview.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_about:
                        Log.i("MENU_DRAWER_TAG","About item is selected");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_profile:
                        Log.i("MENU_DRAWER_TAG","Profile item is selected");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_setting:
                        Log.i("MENU_DRAWER_TAG","setting item is selected");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.btn_logout:
                        auth.signOut();
                        Intent intent = new Intent(MainActivity.this,login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }


                return true;
            }
        });


        dashboardview = findViewById(R.id.idRVItems);
        dashboardList = new ArrayList<>();



    dashboard_list_adapter adapter = new dashboard_list_adapter(dashboardList,MainActivity.this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
    dashboardview.setLayoutManager(layoutManager);
    dashboardview.setNestedScrollingEnabled(false);
    dashboardview.setAdapter(adapter);

    database.getReference().child("posts/"+currentUser.getUid()+"/Customer/").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            dashboardList.clear();
            for(DataSnapshot datasnapshot : snapshot.getChildren()){
                AddUser getPostData = datasnapshot.getValue(AddUser.class);
                dashboardList.add(getPostData);
                pretext.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
        }
    });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_add_customer(View view) {
        Intent intent = new Intent(MainActivity.this,Add_Customer_Activity.class);
        startActivity(intent);

    }

}