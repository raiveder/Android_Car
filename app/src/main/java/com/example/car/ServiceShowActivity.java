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

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        Bundle arg = getIntent().getExtras();
        Id_car = arg.getInt("Id");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddServiceActivity.class);
                intent.putExtra("Id", Id_car);
                intent.putExtra("Details", new String[]{});
                intent.putExtra("Added", new boolean[]{});
                startActivity(intent);
                break;
        }
    }
}