package com.example.car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
public class AddExpendablesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lvExpendables;
    private List<Expendables> listExpendables;
    private AdapterExpendables adapter;
    private String[] details;
    private int[] countDetails;
    private String[] expendables;
    private int[] countExpendables;
    private int Id_car;
    private int Id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expendables);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initializeComponent();

        getData();
    }

    private void initializeComponent() {

        lvExpendables = findViewById(R.id.lvExpendables);

        lvExpendables.setOnItemClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    private void getData() {

        ProgressBar pbWait = findViewById(R.id.pbWait);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<List<Expendables>> call = retrofitAPI.getExpendables();

        call.enqueue(new Callback<List<Expendables>>() {

            @Override
            public void onResponse(Call<List<Expendables>> call, Response<List<Expendables>> response) {

                listExpendables = response.body();
                pbWait.setVisibility(View.GONE);
                getExtrasIntent();
            }

            @Override
            public void onFailure(Call<List<Expendables>> call, Throwable t) {

                Toast.makeText(AddExpendablesActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }

    private void getExtrasIntent() {

        Bundle arg = getIntent().getExtras();
        Id_car = arg.getInt("Id_car");
        Id_user = arg.getInt("Id_user");
        details = arg.getStringArray("Details");
        countDetails = arg.getIntArray("CountDetails");
        expendables = arg.getStringArray("Expendables");
        countExpendables = arg.getIntArray("CountExpendables");

        if (expendables.length == 0) {
            expendables = new String[listExpendables.size()];
            countExpendables = new int[listExpendables.size()];
        }

        adapter = new AdapterExpendables(AddExpendablesActivity.this,
                listExpendables, countExpendables);
        lvExpendables.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (expendables[i] == null) {

            getAlertDialog(view, i).show();
        } else {
            expendables[i] = null;
            countExpendables[i] = 0;
            TextView tv = view.findViewById(R.id.tvCount);
            tv.setText(null);
            view.setBackground(this.getDrawable(R.drawable.default_item));
        }
    }

    private AlertDialog.Builder getAlertDialog(View view, int i) {

        View v = View.inflate(this, R.layout.choice_count, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(v);

        alertDialogBuilder.setPositiveButton("Добавить", (dialog, id) -> {

            expendables[i] = listExpendables.get(i).getExpendable();
            EditText et = v.findViewById(R.id.etCount);
            if (et.getText().length() == 0) {
                countExpendables[i] = 1;
            } else {
                countExpendables[i] = Integer.parseInt(et.getText().toString());
            }
            TextView tv = view.findViewById(R.id.tvCount);
            tv.setText(String.valueOf(countExpendables[i]));
            view.setBackground(AddExpendablesActivity.this.getDrawable(R.drawable.selected_item));
        });

        alertDialogBuilder.setNegativeButton("Отмена", (dialog, id) -> {
        });

        return alertDialogBuilder;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
            case R.id.imageBack:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Id_user", Id_user);
                intent.putExtra("Details", details);
                intent.putExtra("CountDetails", countDetails);
                intent.putExtra("Expendables", expendables);
                intent.putExtra("CountExpendables", countExpendables);
                startActivity(intent);
                break;
        }
    }
}