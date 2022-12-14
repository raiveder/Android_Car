package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

@SuppressLint("NonConstantResourceId")
public class AddServiceActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvDetails;
    ListView lvExpendables;
    String[] details;
    boolean[] IsAdded;
    int Id_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        initializeComponent();

        Bundle arg = getIntent().getExtras();
        Id_car = arg.getInt("Id");
        details = arg.getStringArray("Details");
        IsAdded = arg.getBooleanArray("Added");
    }

    private void initializeComponent() {

        lvDetails = findViewById(R.id.lvDetails);
        lvExpendables = findViewById(R.id.lvExpendables);
        Button btnAddDetails = findViewById(R.id.btnAddDetails);
        Button btnAddExpendables = findViewById(R.id.btnAddExpendables);

        btnAddDetails.setOnClickListener(this);
        btnAddExpendables.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAddDetails:
                Intent intent = new Intent(this, AddDetailsActivity.class);
                intent.putExtra("Details", details);
                intent.putExtra("Added", IsAdded);
                startActivity(intent);
                break;
            case R.id.btnAddExpendables:

                break;
        }
    }
}