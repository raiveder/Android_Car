package com.example.car;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("Cars/")
    Call<Cars> createCar(@Body Cars car);
}
