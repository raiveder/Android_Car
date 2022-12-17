package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
public class AddServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etDate;
    private EditText etCost;
    private EditText etWork;
    private EditText etMileage;
    private Button btnAddDetails;
    private Button btnAddExpendables;
    private ListView lvDetails;
    private ListView lvExpendables;
    private int Id_car;
    private String[] details;
    private int[] countDetails;
    private String[] expendables;
    private int[] countExpendables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        initializeComponent();
        uploadDetails();
        uploadExpendables();
    }

    private void initializeComponent() {

        Bundle arg = getIntent().getExtras();
        Id_car = arg.getInt("Id");
        details = arg.getStringArray("Details");
        countDetails = arg.getIntArray("CountDetails");
        expendables = arg.getStringArray("Expendables");
        countExpendables = arg.getIntArray("CountExpendables");

        etDate = findViewById(R.id.etDate);
        etCost = findViewById(R.id.etCost);
        etWork = findViewById(R.id.etWork);
        etMileage = findViewById(R.id.etMileage);
        lvDetails = findViewById(R.id.lvDetails);
        lvExpendables = findViewById(R.id.lvExpendables);
        btnAddDetails = findViewById(R.id.btnAddDetails);
        btnAddExpendables = findViewById(R.id.btnAddExpendables);

        setListeners();
    }

    private void setListeners() {

        findViewById(R.id.btnAdd).setOnClickListener(this);
        btnAddDetails.setOnClickListener(this);
        btnAddExpendables.setOnClickListener(this);

        etCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etCost.setBackground(AddServiceActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });

        etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etDate.setBackground(AddServiceActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });
    }

    private void uploadDetails() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);

        if (details.length != 0) {
            btnAddDetails.setText("Изменить детали");

            for (int i = 0; i < details.length; i++) {
                if (details[i] != null) {
                    adapter.add(details[i] + " x" + countDetails[i]);
                }
            }
        }

        lvDetails.setAdapter(adapter);
    }

    private void uploadExpendables() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);

        if (expendables.length != 0) {
            btnAddExpendables.setText("Изменить расходники");

            for (int i = 0; i < expendables.length; i++) {
                if (expendables[i] != null) {
                    adapter.add(expendables[i] + " x" + countExpendables[i]);
                }
            }
        }

        lvExpendables.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAddDetails:
                Intent intent = new Intent(this, AddDetailsActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Details", details);
                intent.putExtra("CountDetails", countDetails);
                intent.putExtra("Expendables", expendables);
                intent.putExtra("CountExpendables", countExpendables);
                startActivity(intent);
                break;
            case R.id.btnAddExpendables:
                intent = new Intent(this, AddExpendablesActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Details", details);
                intent.putExtra("CountDetails", countDetails);
                intent.putExtra("Expendables", expendables);
                intent.putExtra("CountExpendables", countExpendables);
                startActivity(intent);
                break;
            case R.id.btnAdd:
                if (checkData()) {
                    postData();
                }
                break;
        }
    }

    private boolean checkData() {

        boolean result = true;

        if (countDetails.length == 0 && countExpendables.length == 0) {
            Toast.makeText(this, "Выберите детали или расходные материалы",
                    Toast.LENGTH_LONG).show();
            result = false;
        }
        if (etDate.getText().length() == 0 || etDate.getText().length() < 8) {
            etDate.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etCost.getText().length() == 0 || Integer.parseInt(etCost.getText().toString()) < 1) {
            etCost.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etWork.getText().length() > 20) {
            etWork.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (!result && countDetails.length != 0) {
            Toast.makeText(this, "Заполните недостающие данные",
                    Toast.LENGTH_LONG).show();
        }

        return result;
    }

    private void postData() {

        ProgressBar pbWait = findViewById(R.id.pbWait);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Services_List service = new Services_List(
                0,
                Id_car,
                etWork.getText().toString(),
                Double.parseDouble(etCost.getText().toString()),
                etDate.getText().toString(),
                Integer.parseInt(etMileage.getText().toString()));

        Call<Services_List> call = retrofitAPI.createService(service);
        call.enqueue(new Callback<Services_List>() {

            @Override
            public void onResponse(Call<Services_List> call, Response<Services_List> response) {

                Toast.makeText(AddServiceActivity.this, "ТО успешно добавлено",
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
                new Handler().postDelayed(() -> startActivity(new Intent(
                        AddServiceActivity.this, ServiceShowActivity.class)),
                        500);
            }

            @Override
            public void onFailure(Call<Services_List> call, Throwable t) {

                Toast.makeText(AddServiceActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }
}