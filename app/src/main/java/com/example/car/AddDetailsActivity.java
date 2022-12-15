package com.example.car;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.M)
@SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnScrollChangeListener {

    ListView lvDetails;
    List<Details> listDetails;
    AdapterDetails adapter;
    int Id_car;
    String[] details;
    int[] countDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initializeComponent();

        getData();
    }

    private void initializeComponent() {

        lvDetails = findViewById(R.id.lvDetails);
        lvDetails.setOnItemClickListener(this);
        lvDetails.setOnScrollChangeListener(this);

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
        Id_car = arg.getInt("Id_car");
        details = arg.getStringArray("Details");
        countDetails = arg.getIntArray("CountDetails");

        if (details.length == 0) {
            details = new String[listDetails.size()];
            countDetails = new int[listDetails.size()];
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (details[i] == null) {
            View v = View.inflate(this, R.layout.choice_count, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(v);
            alertDialogBuilder.setPositiveButton("Добавить", (dialog, id) -> {

                details[i] = listDetails.get(i).getDetail();
                EditText et = v.findViewById(R.id.etCount);
                if (et.getText().length() == 0) {
                    countDetails[i] = 1;
                } else {
                    countDetails[i] = Integer.parseInt(et.getText().toString());
                }
                TextView tv = view.findViewById(R.id.tvCount);
                tv.setText(String.valueOf(countDetails[i]));
                view.setBackground(AddDetailsActivity.this.getDrawable(R.drawable.selected_item));

            });
            alertDialogBuilder.setNegativeButton("Отмена", (dialog, id) -> {
            });
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.show();
        } else {
            details[i] = null;
            countDetails[i] = 0;
            adapterView.getChildAt(i).setBackground(this.getDrawable(R.drawable.default_item));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id", Id_car);
                intent.putExtra("Details", details);
                intent.putExtra("CountDetails", countDetails);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {

        if (countDetails != null) {
            for (int index = 0; index < countDetails.length; index++) {
                if (countDetails[i] != 0) {

                    TextView tv = view.findViewById(R.id.tvCount);
                    tv.setText(String.valueOf(countDetails[index]));
                    view.setBackground(AddDetailsActivity.this.getDrawable(R.drawable.selected_item));
                }
            }
        }
    }
}