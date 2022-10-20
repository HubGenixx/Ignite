package com.example.ignite.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import com.example.ignite.Add_Customer_Activity;
import com.example.ignite.LoadingIndicator;
import com.example.ignite.MainActivity;

import Models.AddUser;
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

public class PostRequest {

    public static void PostData(AddUser User, Context context){

        ToObject toObject;
        RoutingObject routingObject;
        ContentObject contentObject;
        MessageObjectClass messageObjectClass;
        MainObjectClass mainObjectClass;


        LoadingIndicator  loadingIndicator;
        loadingIndicator=new LoadingIndicator((Activity) context);
        loadingIndicator.startLoading();


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.courier.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //to
        toObject=new ToObject(User.getEmail(),"+91"+" "+User.getPhone_number());
        //Content
        contentObject = new ContentObject("Hi"+"\n"+
                "How are you"+"  "+User.getName(),
                "PAY"+User.getBill()+"\n"+
                        User.getRemark());

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
        Call<MainResponseModelClass>res= ourRetrofitClient.getPostValue(mainObjectClass);

        res.enqueue(new Callback<MainResponseModelClass>() {
            @Override
            public void onResponse(Call<MainResponseModelClass> call, Response<MainResponseModelClass> response) {
                if(response.body().getRequestId()==null){
                    System.out.println(response.body());
                    Toast.makeText(context,"Error\n Try Again", Toast.LENGTH_SHORT).show();
                    loadingIndicator.dismissDialog();
                }
                else {
                    if(response.body().getRequestId()!=null) {
                        System.out.println(response.body().getRequestId());
                        Toast.makeText(context,"Request Send Successfully", Toast.LENGTH_SHORT).show();
                        loadingIndicator.dismissDialog();



                    }
                    else {
                        System.out.println(response.body().getType());
                        Toast.makeText(context,"Error"+response.body().getType(), Toast.LENGTH_SHORT).show();
                        loadingIndicator.dismissDialog();

                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponseModelClass> call, Throwable t) {
                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                loadingIndicator.dismissDialog();

            }
        });

    }

}