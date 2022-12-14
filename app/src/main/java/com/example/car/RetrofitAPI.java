package com.example.car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("Cars/getParametrs")
    Call<ParametrsCar> getParametrs();

    @GET("Cars/")
    Call<List<CarsValue>> getCars();

    @GET("Cars/")
    Call<Cars> getCarById(@Query("id") int id);

    @POST("Cars/")
    Call<Cars> createCar(@Body Cars car);

    @PUT("Cars/")
    Call<Cars> updateCar(@Query("id") int id, @Body Cars car);

    @DELETE("Cars/")
    Call<Cars> deleteCar(@Query("id") int id);

    @GET("Details/")
    Call<List<Details>> getDetails();
}