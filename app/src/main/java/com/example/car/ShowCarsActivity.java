package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowCarsActivity extends AppCompatActivity implements View.OnClickListener {

    Cars car;
    List<CarsValue> list;
    ListView listView;
    AdapterCars adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cars);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        list = new ArrayList<>();
        adapter = new AdapterCars(this, list);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent intent = new Intent(this, CurrentCarActivity.class);
            intent.putExtra("Car", list.get((int) arg3));
            startActivity(intent);
        });

        //new GetCars().execute();
        getData();
    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<CarsValue>> call = retrofitAPI.getCars();

        call.enqueue(new Callback<List<CarsValue>>() {

            @Override
            public void onResponse(Call<List<CarsValue>> call, Response<List<CarsValue>> response) {

                List<CarsValue> test = response.body();
                adapter = new AdapterCars(ShowCarsActivity.this, test);
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
                Intent intent = new Intent(this, CurrentCarActivity.class);
                intent.putExtra("Car", car);
                startActivity(intent);
                break;
        }
    }

    private class GetCars extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/Cars?Id_car=\"1\"");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            try {
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0; i < tempArray.length(); i++) {

                    JSONObject CarsJson = tempArray.getJSONObject(i);
                    CarsValue tempCar = new CarsValue(
                            CarsJson.getInt("Id_car"),
                            CarsJson.getString("Model"),
                            CarsJson.getString("Generation"),
                            CarsJson.getString("Equipment"),
                            CarsJson.getString("Transmission"),
                            CarsJson.getString("Horsepower"),
                            CarsJson.getString("Fuel"),
                            CarsJson.getString("Drive"),
                            CarsJson.getString("Body"),
                            CarsJson.getString("Color"),
                            CarsJson.getString("Wheel"),
                            CarsJson.getString("VIN"),
                            CarsJson.getInt("Mileage"),
                            CarsJson.getString("Image")
                    );
                    list.add(tempCar);
                    adapter.notifyDataSetInvalidated();
                }
                listView.setAdapter(adapter);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}