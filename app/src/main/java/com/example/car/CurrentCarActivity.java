package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CurrentCarActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvBrand;
    TextView tvModel;
    TextView tvEquipment;
    TextView tvTransmission;
    TextView tvEngine;
    TextView tvFuel;
    TextView tvDrive;
    TextView tvBody;
    TextView tvColor;
    TextView tvWheel;
    TextView tvVIN;
    TextView tvMileage;
    Button btnService;
    Button btnChange;
    Button btnDelete;

    Cars car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_car);

        initializeComponent();

        Bundle arg = getIntent().getExtras();
        car = (Cars) arg.getSerializable("Car");

        setData();
    }

    private void initializeComponent() {

        tvBrand = findViewById(R.id.tvBrandValue);
        tvModel = findViewById(R.id.tvModelValue);
        tvEquipment = findViewById(R.id.tvEquipmentValue);
        tvTransmission = findViewById(R.id.tvTransmissionValue);
        tvEngine = findViewById(R.id.tvEngineValue);
        tvFuel = findViewById(R.id.tvFuelValue);
        tvDrive = findViewById(R.id.tvDriveValue);
        tvBody = findViewById(R.id.tvBodyValue);
        tvColor = findViewById(R.id.tvColorValue);
        tvWheel = findViewById(R.id.tvWheelValue);
        tvVIN = findViewById(R.id.tvVINValue);
        tvMileage = findViewById(R.id.tvMileageValue);

        btnService = findViewById(R.id.btnService);
        btnChange = findViewById(R.id.btnChange);
        btnDelete = findViewById(R.id.btnDelete);

        btnService.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void setData() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnService:

                break;
            case R.id.btnChange:
                Intent intent = new Intent(this, AddCarActivity.class);
                intent.putExtra("Car", car);
                startActivity(intent);
                break;
            case R.id.btnDelete:

                break;

        }
    }
}