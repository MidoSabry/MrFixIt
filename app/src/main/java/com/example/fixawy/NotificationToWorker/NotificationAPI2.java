package com.example.fixawy.NotificationToWorker;

import com.example.fixawy.NotificationToClient.MyResponse;
import com.example.fixawy.NotificationToClient.NotificationSender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationAPI2 {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAiOSWHrY:APA91bGo3Yu9xEpdcI-7SX3pD1u7h8_ZYWQ-2MnM8OTDNFcROMWMkJAaR99s57e1uvLCMP0iMwZWakowrZQB58JCsGW8EAJaZGrVs9t-8iZ2JuLg-zdDLxqQGY0lakhnTG3w_RajVRgp" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation2(@Body NotificationSender2 body);

}
