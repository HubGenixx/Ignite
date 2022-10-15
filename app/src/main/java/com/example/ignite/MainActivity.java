package com.example.ignite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Models.ContentObject;
import Models.InterFace.OurRetrofitClient;
import Models.MainObjectClass;
import Models.MainResponseModelClass;
import Models.MessageObjectClass;
import Models.RoutingObject;
import Models.ToObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ImageView discover;
    ImageView chat;

    ToObject toObject;
    RoutingObject routingObject;
    ContentObject contentObject;
    MessageObjectClass messageObjectClass;
    MainObjectClass mainObjectClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        discover =findViewById(R.id.uz_discover_logo);
        chat = findViewById(R.id.uz_chat_logo);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostData();
//                Intent intent = new Intent(MainActivity.this,DiscoverActivity.class);
//                startActivity(intent);
            }
        });
    }

    public void PostData(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.courier.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Giving Values
        //to
        toObject=new ToObject("riteshsonawane1372@gmail.com","+91 9130759227");
        //Content
        contentObject = new ContentObject("Testing Api","TestTing Api");
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
}