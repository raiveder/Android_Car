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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("NonConstantResourceId")
public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvDetails;
    List<Details> listDetails;
    AdapterDetails adapter;
    String[] details;
    boolean[] IsAdded;
    int Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        initializeComponent();

        getData();
    }

    private void initializeComponent() {

        Index = 0;

        lvDetails = findViewById(R.id.lvDetails);
        lvDetails.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            details[Index++] = listDetails.get(position).getDetail();
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<Details>> call = retrofitAPI.getDetails();

        call.enqueue(new Callback<List<Details>>() {

            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {

                listDetails = response.body();
                adapter = new AdapterDetails(AddDetailsActivity.this, listDetails);
                ProgressBar pbWait = findViewById(R.id.pbWait);
                pbWait.setVisibility(View.GONE);
                lvDetails.setAdapter(adapter);
                getExtrasIntent();
            }

            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {

                Toast.makeText(AddDetailsActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getExtrasIntent() {

        Bundle arg = getIntent().getExtras();
        details = arg.getStringArray("Details");
        IsAdded = arg.getBooleanArray("Added");

        if (details.length == 0) {
            details = new String[listDetails.size()];
            IsAdded = new boolean[listDetails.size()];
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:

                break;
        }
    }
}