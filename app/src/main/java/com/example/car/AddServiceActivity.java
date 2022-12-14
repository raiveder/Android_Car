package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

@SuppressLint("NonConstantResourceId")
public class AddServiceActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvDetails;
    ListView lvExpendables;
    String[] details;
    int Id_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        initializeComponent();
        uploadDetails();
    }

    private void initializeComponent() {

        Bundle arg = getIntent().getExtras();
        Id_car = arg.getInt("Id");
        details = arg.getStringArray("Details");

        lvDetails = findViewById(R.id.lvDetails);
        lvExpendables = findViewById(R.id.lvExpendables);
        Button btnAddDetails = findViewById(R.id.btnAddDetails);
        Button btnAddExpendables = findViewById(R.id.btnAddExpendables);

        btnAddDetails.setOnClickListener(this);
        btnAddExpendables.setOnClickListener(this);
    }

    private void uploadDetails() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);

        if (details.length != 0) {
            for (int i = 0; i < details.length; i++) {
                if (details[i] != null) {
                    adapter.add(details[i]);
                }
            }
        }

        lvDetails.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAddDetails:
                Intent intent = new Intent(this, AddDetailsActivity.class);
                intent.putExtra("Id_car", Id_car);
                intent.putExtra("Details", details);
                startActivity(intent);
                break;
            case R.id.btnAddExpendables:

                break;
        }
    }
}