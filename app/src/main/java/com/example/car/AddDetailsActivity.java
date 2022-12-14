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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView lvDetails;
    List<Details> listDetails;
    AdapterDetails adapter;
    int Id_car;
    String[] details;
    boolean[] IsAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        initializeComponent();

        getData();
    }

    private void initializeComponent() {

        lvDetails = findViewById(R.id.lvDetails);
        lvDetails.setOnItemClickListener(this);

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
        Id_car = arg.getInt("Id_car");

        if (details.length == 0) {
            details = new String[listDetails.size()];
            IsAdded = new boolean[listDetails.size()];
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (IsAdded[i]) {
            IsAdded[i] = false;
            details[i] = null;
            adapterView.getChildAt(i).setBackground(this.getDrawable(R.drawable.default_item));
        } else {
            IsAdded[i] = true;
            details[i] = listDetails.get(i).getDetail();
            adapterView.getChildAt(i).setBackground(this.getDrawable(R.drawable.selected_item));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id", Id_car);
                intent.putExtra("Details", details);
                startActivity(intent);
                break;
        }
    }
}