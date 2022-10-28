package Models.InterFace;


import com.example.ignite.BuildConfig;

import Models.MainObjectClass;
import Models.MainResponseModelClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OurRetrofitClient {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            BuildConfig.API_KEY
            })

    @POST("/send")
    Call <MainResponseModelClass> getPostValue(@Body MainObjectClass mainObjectClass);

}
