package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button requestHolidayButton, viewProfileButton, logoutButton;
    private String employeeDetails = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestHolidayButton = findViewById(R.id.requestHolidayButton);
        viewProfileButton = findViewById(R.id.viewProfileButton);
        logoutButton = findViewById(R.id.logoutButton);

        Intent intent = getIntent();
        employeeDetails = intent.getStringExtra("employeeDetails");

        requestHolidayButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, RequestHolidayActivity.class));
        });

        viewProfileButton.setOnClickListener(v -> {
            if (!employeeDetails.isEmpty()) {
                Intent viewProfileIntent = new Intent(HomeActivity.this, ViewProfileActivity.class);
                viewProfileIntent.putExtra("employeeDetails", employeeDetails);
                startActivity(viewProfileIntent);
            } else {
                Toast.makeText(HomeActivity.this, "Employee details not available", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(v -> {
            Intent logoutIntent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(logoutIntent);
            finish();
        });
    }
}




