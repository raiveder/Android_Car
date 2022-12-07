package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ShowCarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cars);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> startActivity(
                new Intent(ShowCarsActivity.this, AddCarActivity.class)));
    }
}