package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
public class ServiceShowActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private List<Services_ListValue> listServices;
    private ListView listView;
    private AdapterServices adapter;
    private int Id_car;
    private int Id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_show);

        initializeComponent();
        getData();
    }

    private void initializeComponent() {

        Id_car = getIntent().getExtras().getInt("Id_car");
        Id_user = getIntent().getExtras().getInt("Id_user");

        listView = findViewById(R.id.listView);

        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void getData() {

        ProgressBar pbWait = findViewById(R.id.pbWait);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<Services_ListValue>> call = retrofitAPI.getServices(Id_car);

        call.enqueue(new Callback<List<Services_ListValue>>() {

            @Override
            public void onResponse(Call<List<Services_ListValue>> call,
                                   Response<List<Services_ListValue>> response) {

                listServices = response.body();
                adapter = new AdapterServices(ServiceShowActivity.this, listServices);
                pbWait.setVisibility(View.GONE);
                setMileageColon();
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Services_ListValue>> call, Throwable t) {

                Toast.makeText(ServiceShowActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }

    private void setMileageColon() {

        for (int i = 0; i < listServices.size(); i++) {
            listServices.get(i).setMileage(listServices.get(i).getMileage() + " км");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Id_user", Id_user);
                intent.putExtra("Details", new String[]{});
                intent.putExtra("CountDetails", new int[]{});
                intent.putExtra("Expendables", new String[]{});
                intent.putExtra("CountExpendables", new int[]{});
                startActivity(intent);
                break;

            case R.id.imageBack:
                intent = new Intent(this, CurrentCarActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Id_user", Id_user);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, AddServiceActivity.class);
        intent.putExtra("Id_car", Id_car);
        intent.putExtra("Id_user", Id_user);
        intent.putExtra("Details", new String[]{});
        intent.putExtra("CountDetails", new int[]{});
        intent.putExtra("Expendables", new String[]{});
        intent.putExtra("CountExpendables", new int[]{});
        intent.putExtra("FlagShow", true);
        intent.putExtra("IdService", listServices.get(i).getId());
        startActivity(intent);
    }
}