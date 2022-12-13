package com.example.car;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
public class AddCarActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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

    int Id;
    String ImageString;
    ParametrsCar Parametrs;
    String[][] Colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        initializeComponent();

        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            Id = arg.getInt("Id");
        }

        getParameters();
    }

    private void initializeComponent() {

        spBrand = findViewById(R.id.spBrand);
        spModel = findViewById(R.id.spModel);
        spGeneration = findViewById(R.id.spGeneration);
        spTransmission = findViewById(R.id.spTransmission);
        spEngine = findViewById(R.id.spEngine);
        spFuel = findViewById(R.id.spFuel);
        spDrive = findViewById(R.id.spDrive);
        spBody = findViewById(R.id.spBody);
        spColor = findViewById(R.id.spColor);
        spWheel = findViewById(R.id.spWheel);
        etEquipment = findViewById(R.id.etEquipment);
        etVIN = findViewById(R.id.etVIN);
        etMileage = findViewById(R.id.etMileage);
        imageView = findViewById(R.id.image);
        btnAdd = findViewById(R.id.btnAdd);

        setHandlers();
    }

    private void setHandlers() {

        spBrand.setOnItemSelectedListener(this);
        spModel.setOnItemSelectedListener(this);
        spGeneration.setOnItemSelectedListener(this);
        spTransmission.setOnItemSelectedListener(this);
        spEngine.setOnItemSelectedListener(this);
        spFuel.setOnItemSelectedListener(this);
        spDrive.setOnItemSelectedListener(this);
        spBody.setOnItemSelectedListener(this);
        spColor.setOnItemSelectedListener(this);
        spWheel.setOnItemSelectedListener(this);
        imageView.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        etEquipment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etEquipment.setBackground(AddCarActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });

        etVIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etVIN.setBackground(AddCarActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });

        etMileage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etMileage.setBackground(AddCarActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });
    }

    private void getParameters() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ParametrsCar> call = retrofitAPI.getParametrs();
        call.enqueue(new Callback<ParametrsCar>() {

            @Override
            public void onResponse(Call<ParametrsCar> call, Response<ParametrsCar> response) {

                Parametrs = response.body();
                setParameters();
            }

            @Override
            public void onFailure(Call<ParametrsCar> call, Throwable t) {

                Toast.makeText(AddCarActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setParameters() {

        spBrand.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getBrandsValues()));

        spModel.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getModelsValues()));

        spGeneration.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getGenerationsValues()));

        spTransmission.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getTransmissionsValues()));

        spEngine.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getEnginesValues()));

        spFuel.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getFuel_TypesValues()));

        spDrive.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getDrivesValues()));

        spBody.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getBodysValues()));

        spColor.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getColorsValues()));

        spWheel.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Parametrs.getWheelsValues()));

        if (Id != 0) {
            Colors = new String[Parametrs.getColorsValues().length][2];
            for (int i = 0; i < Colors.length; i++) {
                Colors[i][0] = String.valueOf(i + 1);
                Colors[i][1] = Parametrs.getColorsValues()[i];
            }
            getParametersValue();
        }
    }

    private void getParametersValue() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<Cars> call = retrofitAPI.getCarById(Id);
        call.enqueue(new Callback<Cars>() {

            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {

                Cars car = response.body();
                setParametersValue(car);
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {

                Toast.makeText(AddCarActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setParametersValue(Cars car) {

        int idColor = 0;
        for (int i = 0; i < Colors.length; i++) {
            if (Integer.parseInt(Colors[i][0]) == car.getIdColor()) {
                idColor = i;
                break;
            }
        }

        //spBrand;
        spModel.setSelection(car.getIdModel() - 1);
        spGeneration.setSelection(car.getIdGeneration() - 1);
        etEquipment.setText(car.getEquipment());
        spTransmission.setSelection(car.getIdTransmission() - 1);
        spEngine.setSelection(car.getIdEngine() - 1);
        spFuel.setSelection(car.getIdFuel() - 1);
        spDrive.setSelection(car.getIdDrive() - 1);
        spBody.setSelection(car.getIdBody() - 1);
        spColor.setSelection(idColor);
        spWheel.setSelection(car.getIdWheel() - 1);
        etVIN.setText(car.getVIN());
        etMileage.setText(String.valueOf(car.getMileage()));
        //imageView;
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

        ProgressBar PBWait = findViewById(R.id.pbWait);
        PBWait.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Cars car = new Cars(
                0,
                spModel.getSelectedItemPosition() + 1,
                spGeneration.getSelectedItemPosition() + 1,
                etEquipment.getText().toString(),
                spTransmission.getSelectedItemPosition() + 1,
                spEngine.getSelectedItemPosition() + 1,
                spFuel.getSelectedItemPosition() + 1,
                spDrive.getSelectedItemPosition() + 1,
                spBody.getSelectedItemPosition() + 1,
                spColor.getSelectedItemPosition() + 1, // Подумать
                spWheel.getSelectedItemPosition() + 1,
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

        boolean result = true;

        if (spBrand.getSelectedItemPosition() == 0) {
            spBrand.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spModel.getSelectedItemPosition() == 0) {
            spModel.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spGeneration.getSelectedItemPosition() == 0) {
            spGeneration.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etEquipment.getText().length() == 0) {
            etEquipment.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spTransmission.getSelectedItemPosition() == 0) {
            spTransmission.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spEngine.getSelectedItemPosition() == 0) {
            spEngine.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spFuel.getSelectedItemPosition() == 0) {
            spFuel.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spDrive.getSelectedItemPosition() == 0) {
            spDrive.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spBody.getSelectedItemPosition() == 0) {
            spBody.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spColor.getSelectedItemPosition() == 0) {
            spColor.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (spWheel.getSelectedItemPosition() == 0) {
            spWheel.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etVIN.getText().length() != 17) {
            etVIN.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etMileage.getText().length() == 0 || Integer.parseInt(etMileage.getText().toString()) < 1) {
            etMileage.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }

        if (!result) {
            Toast.makeText(this, "Заполните недостающие данные",
                    Toast.LENGTH_LONG).show();
        }

        return result;
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
                    //postData();
                }

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spBrand:
                spBrand.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spModel:
                spModel.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spGeneration:
                spGeneration.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spTransmission:
                spTransmission.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spEngine:
                spEngine.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spFuel:
                spFuel.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spDrive:
                spDrive.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spBody:
                spBody.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spColor:
                spColor.setBackground(this.getDrawable(R.drawable.spinner_background));
            case R.id.spWheel:
                spWheel.setBackground(this.getDrawable(R.drawable.spinner_background));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}