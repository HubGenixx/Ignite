package com.example.ignite.Data;

import android.content.Context;
import android.widget.Toast;


import androidx.annotation.NonNull;

import Models.ContentObject;
import Models.InterFace.OurRetrofitClient;
import Models.MainObjectClass;
import Models.MainResponseModelClass;
import Models.MessageObjectClass;
import Models.RoutingObject;
import Models.ToObject;
import Models.dashboard_bill_model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRequest {
    

    public static void PostData(String email, String phone_number, String FullName ,String body,
            String bill, Context context){
        
        ToObject toObject;
        RoutingObject routingObject;
        ContentObject contentObject;
        MessageObjectClass messageObjectClass;
        MainObjectClass mainObjectClass;

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.courier.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Giving Values
        //to
        toObject=new ToObject(email,"+91"+" "+phone_number);
        //Content
        contentObject = new ContentObject("Hi"+"How are you"+FullName+body,"Amount"+bill);
        //ChannelModes
        String []channelModes = new String[] {"sms","email"};
        //Routing
        routingObject = new RoutingObject("all",channelModes);
        //Message
        messageObjectClass = new MessageObjectClass(routingObject,contentObject,toObject);
        //MainObject
        mainObjectClass = new MainObjectClass(messageObjectClass);

        // Creating the Interface
        OurRetrofitClient ourRetrofitClient = retrofit.create(OurRetrofitClient.class);
        Call<MainResponseModelClass> res= ourRetrofitClient.getPostValue(mainObjectClass);

        res.enqueue(new Callback<MainResponseModelClass>() {
            @Override
            public void onResponse(Call<MainResponseModelClass> call, Response<MainResponseModelClass> response) {
                if(response.body()==null){
                    System.out.println(response.body().getMessage());
                    System.out.println(response.body().getType());
                    Toast.makeText(context, "Server under Maintenance", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(response.body().getRequestId()!=null) {
                        System.out.println(response.body().getRequestId());
                        Toast.makeText(context, "Request Send Successfully", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        System.out.println(response.body().getType());
                        Toast.makeText(context, "Error try again later", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponseModelClass> call, Throwable t) {
                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
