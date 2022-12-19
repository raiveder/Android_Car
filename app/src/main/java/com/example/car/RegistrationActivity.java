package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("UseCompatLoadingForDrawables")
public class RegistrationActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etPassword;
    EditText etRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);

        setListeners();
    }

    private void setListeners() {

        findViewById(R.id.btnReg).setOnClickListener((v) -> {

            if (checkData()) {
                checkAccount();
            }
        });

        etLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etLogin.setBackground(RegistrationActivity.this
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
                etPassword.setBackground(RegistrationActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });

        etRepeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etRepeatPassword.setBackground(RegistrationActivity.this
                        .getDrawable(R.drawable.spinner_background));
            }
        });
    }

    private boolean checkData() {

        if (etLogin.getText().length() == 0) {

            Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show();
            etLogin.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            return false;
        } else if (etPassword.getText().length() == 0) {

            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
            etPassword.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            return false;
        } else if (!etRepeatPassword.getText().toString().equals(etPassword.getText().toString())) {

            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            etRepeatPassword.setBackground(this.getDrawable(R.drawable.spinner_background_invalid));
            return false;
        }

        return true;
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

                if (response.body() != null) {

                    Toast.makeText(RegistrationActivity.this,
                            "Логин занят", Toast.LENGTH_SHORT).show();
                } else {

                    postData(pbWait);
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

                Toast.makeText(RegistrationActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }

    private void postData(ProgressBar pbWait) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/СергеевДЕ/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Accounts account = new Accounts(
                0,
                etLogin.getText().toString(),
                etPassword.getText().toString());

        Call<Accounts> call = retrofitAPI.createAccount(account);
        call.enqueue(new Callback<Accounts>() {

            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {

                Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно",
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);

                new Handler().postDelayed(() -> startActivity(new Intent(
                                RegistrationActivity.this, EntryActivity.class)),
                        500);
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

                Toast.makeText(RegistrationActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                pbWait.setVisibility(View.GONE);
            }
        });
    }
}