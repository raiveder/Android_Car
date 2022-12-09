package com.example.car;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spBrand;
    Spinner spModel;
    Spinner spGeneration;
    EditText etEquipment;
    Spinner spTransmission;
    Spinner spEngine;
    Spinner spFuel;
    Spinner spDrive;
    Spinner spBody;
    Spinner spColor;
    Spinner spWheel;
    EditText etVIN;
    EditText etMileage;
    ImageView imageView;
    Button btnAdd;

    int[] IdBrands;
    int[] IdModels;
    int[] IdGenerations;
    int[] IdTransmissions;
    int[] IdEngines;
    int[] IdFuels;
    int[] IdDrives;
    int[] IdBodys;
    int[] IdColors;
    int[] IdWheels;
    String ImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        initializeComponent();

        String[] test = new String[]{"test", "test", "test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, test);
        spBrand.setAdapter(adapter);
        spModel.setAdapter(adapter);
    }

    private void initializeComponent() {

        spBrand = findViewById(R.id.spBrand);
        spModel = findViewById(R.id.spModel);
        spGeneration = findViewById(R.id.spGeneration);
        etEquipment = findViewById(R.id.etEquipment);
        spTransmission = findViewById(R.id.spTransmission);
        spEngine = findViewById(R.id.spEngine);
        spFuel = findViewById(R.id.spFuel);
        spDrive = findViewById(R.id.spDrive);
        spBody = findViewById(R.id.spBody);
        spColor = findViewById(R.id.spColor);
        spWheel = findViewById(R.id.spWheel);
        etVIN = findViewById(R.id.etVIN);
        etMileage = findViewById(R.id.etMileage);
        imageView = findViewById(R.id.image);
        btnAdd = findViewById(R.id.btnAdd);

        imageView.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    private final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            InputStream is = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            imageView.setImageBitmap(bitmap);
                            ImageString = Images.encodeImage(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    private void postData() {

        ProgressBar PBWait = findViewById(R.id.pbWait); // Добавить ProgressBar
        PBWait.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Cars car = new Cars(
                0,
                IdModels[spModel.getSelectedItemPosition()],
                IdGenerations[spGeneration.getSelectedItemPosition()],
                etEquipment.getText().toString(),
                IdTransmissions[spTransmission.getSelectedItemPosition()],
                IdEngines[spEngine.getSelectedItemPosition()],
                IdFuels[spFuel.getSelectedItemPosition()],
                IdDrives[spDrive.getSelectedItemPosition()],
                IdBodys[spBody.getSelectedItemPosition()],
                IdColors[spColor.getSelectedItemPosition()],
                IdWheels[spWheel.getSelectedItemPosition()],
                etVIN.getText().toString(),
                Integer.parseInt(etMileage.getText().toString()),
                ImageString);

        Call<Cars> call = retrofitAPI.createCar(car);

        call.enqueue(new Callback<Cars>() {

            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {
                Toast.makeText(AddCarActivity.this, "Автомобиль успешно добавлен",
                        Toast.LENGTH_LONG).show();

                PBWait.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {
                Toast.makeText(AddCarActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();

                PBWait.setVisibility(View.GONE);
            }
        });
    }

    private boolean checkData() {

        if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите марку", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите модель", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите комплектацию", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите трансмиссию", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите двигатель", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите вид топлива", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите тип привода", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите тип кузова", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите цвет", Toast.LENGTH_LONG).show();
            return false;
        } else if (spBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите расположения руля",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (etVIN.getText().length() != 17) {
            Toast.makeText(this, "VIN номер должен содержать 17 знаков",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (Integer.parseInt(etMileage.getText().toString()) < 1) {
            Toast.makeText(this, "Пробег не может быть меньше 0 км",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImg.launch(intent);
                break;

            case R.id.btnAdd:

                if (checkData()) {
                    postData();
                }

                break;
        }
    }
}