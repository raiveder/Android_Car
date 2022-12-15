package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@SuppressLint("NonConstantResourceId")
public class ServiceShowActivity extends AppCompatActivity implements View.OnClickListener {

    int Id_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_show);

        initializeComponent();
    }

    private void initializeComponent() {

        Id_car = getIntent().getExtras().getInt("Id");

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id", Id_car);
                intent.putExtra("Details", new String[]{});
                intent.putExtra("CountDetails", new int[]{});
                startActivity(intent);
                break;
        }
    }
}