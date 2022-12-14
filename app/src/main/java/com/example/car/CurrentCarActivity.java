package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"NonConstantResourceId", "SetTextI18n"})
public class CurrentCarActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNameCar;
    TextView tvBrand;
    TextView tvModel;
    TextView tvEquipment;
    TextView tvTransmission;
    TextView tvEngine;
    TextView tvFuel;
    TextView tvDrive;
    TextView tvBody;
    TextView tvColor;
    TextView tvWheel;
    TextView tvVIN;
    TextView tvMileage;
    Button btnService;
    Button btnChange;
    Button btnDelete;
    ProgressBar pbWait;

    CarsValue car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_car);

        initializeComponent();

        Bundle arg = getIntent().getExtras();
        int id_car = arg.getInt("Id");

        getData(id_car);
    }

    private void initializeComponent() {

        tvNameCar = findViewById(R.id.tvNameCar);
        tvBrand = findViewById(R.id.tvBrandValue);
        tvModel = findViewById(R.id.tvModelValue);
        tvEquipment = findViewById(R.id.tvEquipmentValue);
        tvTransmission = findViewById(R.id.tvTransmissionValue);
        tvEngine = findViewById(R.id.tvEngineValue);
        tvFuel = findViewById(R.id.tvFuelValue);
        tvDrive = findViewById(R.id.tvDriveValue);
        tvBody = findViewById(R.id.tvBodyValue);
        tvColor = findViewById(R.id.tvColorValue);
        tvWheel = findViewById(R.id.tvWheelValue);
        tvVIN = findViewById(R.id.tvVINValue);
        tvMileage = findViewById(R.id.tvMileageValue);
        btnService = findViewById(R.id.btnService);
        btnChange = findViewById(R.id.btnChange);
        btnDelete = findViewById(R.id.btnDelete);
        pbWait = findViewById(R.id.pbWait);

        btnService.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void setData() {

        tvNameCar.setText(car.getBrand() + " " + car.getModel() + " " + car.getGeneration());
        tvBrand.setText(car.getBrand());
        tvModel.setText(car.getModel());
        tvEquipment.setText(car.getEquipment());
        tvTransmission.setText(car.getTransmission());
        tvEngine.setText(car.getEngine() + " кл. " + car.getHorsepower() + " л.с.");
        tvFuel.setText(car.getFuel());
        tvDrive.setText(car.getDrive());
        tvBody.setText(car.getBody());
        tvColor.setText(car.getColors());
        tvWheel.setText(car.getWheel());
        tvVIN.setText(car.getVIN());
        tvMileage.setText(car.getMileage() + " км");
    }

    private void getData(int id) {

        pbWait.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarsValue>> call = retrofitAPI.getCars();

        call.enqueue(new Callback<List<CarsValue>>() {

            @Override
            public void onResponse(Call<List<CarsValue>> call, Response<List<CarsValue>> response) {

                List<CarsValue> tempList = response.body();

                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getId() == id) {
                        car = tempList.get(i);
                        break;
                    }
                }
                pbWait.setVisibility(View.GONE);
                setData();
            }

            @Override
            public void onFailure(Call<List<CarsValue>> call, Throwable t) {

                Toast.makeText(CurrentCarActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnService:
                Intent intent = new Intent(this, ServiceShowActivity.class);
                intent.putExtra("Id", car.getId());
                startActivity(intent);
                break;
            case R.id.btnChange:
                intent = new Intent(this, AddCarActivity.class);
                intent.putExtra("Id", car.getId());
                intent.putExtra("Brand", car.getBrand());
                startActivity(intent);
                break;
            case R.id.btnDelete:
                deleteData();
                break;

        }
    }

    private void deleteData() {

        pbWait.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Cars> call = retrofitAPI.deleteCar(car.getId());

        call.enqueue(new Callback<Cars>() {

            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {

                Toast.makeText(CurrentCarActivity.this, "Автомобиль успешно удалён",
                        Toast.LENGTH_SHORT).show();
                pbWait.setVisibility(View.GONE);
                new Handler().postDelayed(() -> startActivity(
                        new Intent(CurrentCarActivity.this,
                                ShowCarsActivity.class)), 500);
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {

                Toast.makeText(CurrentCarActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }
}