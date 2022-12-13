package com.example.car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("Cars/")
    Call<Cars> getCarById(@Query("id") int id);

    @GET("Cars/")
    Call<List<CarsValue>> getCars();

    @POST("Cars/")
    Call<Cars> createCar(@Body Cars car);

    @GET("Cars/getParametrs")
    Call<ParametrsCar> getParametrs();
}