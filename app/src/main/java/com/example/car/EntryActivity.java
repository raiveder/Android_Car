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
import android.widget.Toast;

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
                    Intent intent = new Intent(this, ShowCarsActivity.class);
                    intent.putExtra("Id_user", 1);
                    startActivity(intent);
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
            Toast.makeText(this, "Заполните необходимые данные",
                    Toast.LENGTH_LONG).show();
        }

        return result;
    }
}