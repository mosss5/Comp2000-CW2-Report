package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RequestHolidayActivity extends AppCompatActivity {

    private TextView holidayDaysTextView;
    private CalendarView startCalendarView, endCalendarView;
    private Button submitRequestButton, returnHomeButton;
    private int totalHolidayDays = 25; // Total holiday days
    private String startDate = null, endDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_holiday);

        holidayDaysTextView = findViewById(R.id.holidayDaysTextView);
        startCalendarView = findViewById(R.id.startCalendarView);
        endCalendarView = findViewById(R.id.endCalendarView);
        submitRequestButton = findViewById(R.id.submitRequestButton);
        returnHomeButton = findViewById(R.id.returnHomeButton);

        holidayDaysTextView.setText("Total Holiday Days: " + totalHolidayDays);

        // Capture start date
        startCalendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                startDate = dayOfMonth + "/" + (month + 1) + "/" + year);

        // Capture end date
        endCalendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                endDate = dayOfMonth + "/" + (month + 1) + "/" + year);

        // Submit request
        submitRequestButton.setOnClickListener(v -> {
            if (startDate == null || endDate == null) {
                Toast.makeText(this, "Please select both start and end dates.", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Holiday requested from: " + startDate + " to " + endDate, Toast.LENGTH_LONG).show();
        });

        // Return to home activity
        returnHomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(RequestHolidayActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
