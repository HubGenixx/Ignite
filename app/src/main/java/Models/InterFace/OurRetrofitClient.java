package Models.InterFace;

import Models.MainObjectClass;
import Models.MainResponseModelClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OurRetrofitClient {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
            //api key here
            })

    @POST("/send")
    Call <MainResponseModelClass> getPostValue(@Body MainObjectClass mainObjectClass);



}
