package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("NonConstantResourceId")
public class ShowCarsActivity extends AppCompatActivity implements View.OnClickListener {

    private List<CarsValue> listCar;
    private ListView listView;
    private AdapterCars adapter;
    private int Id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cars);

        Id_user = getIntent().getExtras().getInt("Id_user");

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        listCar = new ArrayList<>();
        adapter = new AdapterCars(this, listCar);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent intent = new Intent(this, CurrentCarActivity.class);
            intent.putExtra("Id_car", listCar.get(position).getId());
            intent.putExtra("Id_user", Id_user);
            startActivity(intent);
        });

        getData();
    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarsValue>> call = retrofitAPI.getCars(Id_user);

        call.enqueue(new Callback<List<CarsValue>>() {

            @Override
            public void onResponse(Call<List<CarsValue>> call, Response<List<CarsValue>> response) {

                listCar = response.body();
                adapter = new AdapterCars(ShowCarsActivity.this, listCar);
                ProgressBar pbWait = findViewById(R.id.pbWait);
                pbWait.setVisibility(View.GONE);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CarsValue>> call, Throwable t) {

                Toast.makeText(ShowCarsActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddCarActivity.class);
                intent.putExtra("Id_user", Id_user);
                startActivity(intent);
                break;
        }
    }
}