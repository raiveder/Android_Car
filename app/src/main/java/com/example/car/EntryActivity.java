package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
public class EntryActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etLogin;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);

        setListeners();
    }

    private void setListeners() {

        findViewById(R.id.btnEntry).setOnClickListener(this);
        findViewById(R.id.btnReg).setOnClickListener(this);

        etLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etLogin.setBackground(EntryActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etPassword.setBackground(EntryActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnEntry:
                if (checkData()) {
                    checkAccount();
                }
                break;

            case R.id.btnReg:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }

    private boolean checkData() {

        boolean result = true;

        if (etLogin.getText().length() == 0) {
            etLogin.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }
        if (etPassword.getText().length() == 0) {
            etPassword.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            result = false;
        }

        if (!result) {
            Toast.makeText(this, "Заполните необходимые поля корректно",
                    Toast.LENGTH_LONG).show();
        }

        return result;
    }

    private void checkAccount() {

        ProgressBar pbWait = findViewById(R.id.pbWait);
        pbWait.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Accounts> call = retrofitAPI.getAccount(etLogin.getText().toString());

        call.enqueue(new Callback<Accounts>() {

            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {

                pbWait.setVisibility(View.GONE);

                if (response.body() == null) {

                    Toast.makeText(EntryActivity.this, "Пользователя не существует",
                            Toast.LENGTH_LONG).show();
                } else {
                    String password = response.body().getPassword().substring(0, 1);
                    password += response.body().getPassword().substring(3);
                    password = password.substring(0, password.length()-1);

                    if (!password.equals(etPassword.getText().toString())) {

                        Toast.makeText(EntryActivity.this, "Неверный пароль",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(EntryActivity.this,
                                ShowCarsActivity.class);
                        intent.putExtra("Id_user", response.body().getId());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

                Toast.makeText(EntryActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }
}