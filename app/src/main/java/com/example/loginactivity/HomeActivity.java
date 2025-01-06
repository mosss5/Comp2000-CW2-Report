package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button requestHolidayButton, viewProfileButton;
    private String employeeDetails = ""; // Store employee details from Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestHolidayButton = findViewById(R.id.requestHolidayButton);
        viewProfileButton = findViewById(R.id.viewProfileButton);

        // Retrieve employee details from Intent
        Intent intent = getIntent();
        employeeDetails = intent.getStringExtra("employeeDetails");

        requestHolidayButton.setOnClickListener(v -> {
            // Navigate to RequestHolidayActivity
            startActivity(new Intent(HomeActivity.this, RequestHolidayActivity.class));
        });

        viewProfileButton.setOnClickListener(v -> {
            // Pass employee details to ViewProfileActivity if available
            if (!employeeDetails.isEmpty()) {
                Intent viewProfileIntent = new Intent(HomeActivity.this, ViewProfileActivity.class);
                viewProfileIntent.putExtra("employeeDetails", employeeDetails);
                startActivity(viewProfileIntent);
            } else {
                Toast.makeText(HomeActivity.this, "Employee details not available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



