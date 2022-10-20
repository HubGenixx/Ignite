package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import Models.ContentObject;
import Models.InterFace.OurRetrofitClient;
import Models.MainObjectClass;
import Models.MainResponseModelClass;
import Models.MessageObjectClass;
import Models.Post;
import Models.RoutingObject;
import Models.ToObject;
import adapters.dashboard_list_adapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    Button logout;
    RecyclerView dashboardview;
    ArrayList<Post> dashboardList;
    TextView pretext;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    FirebaseDatabase database;


    ToObject toObject;
    RoutingObject routingObject;
    ContentObject contentObject;
    MessageObjectClass messageObjectClass;
    MainObjectClass mainObjectClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pretext = findViewById(R.id.pre_text);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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

    database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            dashboardList.clear();
            for(DataSnapshot datasnapshot : snapshot.getChildren()){
                Post post = datasnapshot.getValue(Post.class);
                dashboardList.add(post);
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

    public void PostData(String email,String phone_number,String body,String title,String fullName){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.courier.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Giving Values
        //to
        toObject=new ToObject(email,"+91"+" "+phone_number);
        //Content
        contentObject = new ContentObject("Hi"+body+"How are you"+fullName,title);
        //ChannelModes
        String []channelModes = new String[] {"sms","email"};
        //Routing
        routingObject = new RoutingObject("all",channelModes);
        //Message
        messageObjectClass = new MessageObjectClass(routingObject,contentObject,toObject);
        //MainObject
        mainObjectClass = new MainObjectClass(messageObjectClass);

        // Creatig the Interface
        OurRetrofitClient ourRetrofitClient = retrofit.create(OurRetrofitClient.class);
        Call<MainResponseModelClass>res= ourRetrofitClient.getPostValue(mainObjectClass);

       res.enqueue(new Callback<MainResponseModelClass>() {
           @Override
           public void onResponse(Call<MainResponseModelClass> call, Response<MainResponseModelClass> response) {
               if(response.body().getRequestId()==null){
                   System.out.println(response.body().getMessage());
                   System.out.println(response.body().getType());
                   Toast.makeText(MainActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                   Toast.makeText(MainActivity.this, response.body().getType().toString(), Toast.LENGTH_SHORT).show();
                   return;
               }
               else {
                   if(response.body().getRequestId()!=null) {
                       System.out.println(response.body().getRequestId());
                       Toast.makeText(MainActivity.this, response.body().getRequestId().toString(), Toast.LENGTH_SHORT).show();
                       return;
                   }
                   else {
                       System.out.println(response.body().getType());
                       Toast.makeText(MainActivity.this, response.body().getType().toString(), Toast.LENGTH_SHORT).show();

                       return;
                   }
               }
           }

           @Override
           public void onFailure(Call<MainResponseModelClass> call, Throwable t) {
               Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

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