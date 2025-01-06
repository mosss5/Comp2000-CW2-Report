package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAdminActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, departmentEditText, salaryEditText, joiningDateEditText, leavesEditText;
    private ApiService apiService;
    private int employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin);

        // Initialize EditText fields
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        departmentEditText = findViewById(R.id.departmentEditText);
        salaryEditText = findViewById(R.id.salaryEditText);
        joiningDateEditText = findViewById(R.id.joiningDateEditText);
        leavesEditText = findViewById(R.id.leavesEditText);

        // Initialize Retrofit
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Parse employee details from intent
        String employeeDetails = getIntent().getStringExtra("employeeDetails");
        if (employeeDetails != null && !employeeDetails.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(employeeDetails);
                employeeId = jsonObject.optInt("id");
                firstNameEditText.setText(jsonObject.optString("firstname"));
                lastNameEditText.setText(jsonObject.optString("lastname"));
                emailEditText.setText(jsonObject.optString("email"));
                departmentEditText.setText(jsonObject.optString("department"));
                salaryEditText.setText(jsonObject.optString("salary"));
                joiningDateEditText.setText(jsonObject.optString("joiningdate"));
                leavesEditText.setText(jsonObject.optString("leaves"));
            } catch (Exception e) {
                Toast.makeText(this, "Error parsing employee details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        // Button actions
        Button updateButton = findViewById(R.id.updateButton);
        Button backToAdminButton = findViewById(R.id.backToAdminButton);

        updateButton.setOnClickListener(v -> updateEmployeeDetails());
        backToAdminButton.setOnClickListener(v -> navigateToAdminActivity());
    }

    private void updateEmployeeDetails() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String department = departmentEditText.getText().toString();
        String salary = salaryEditText.getText().toString();
        String joiningDate = joiningDateEditText.getText().toString();
        String leaves = leavesEditText.getText().toString();

        Employee updatedEmployee = new Employee(firstName, lastName, email, department, Float.parseFloat(salary), joiningDate, Integer.parseInt(leaves));

        apiService.updateEmployee(employeeId, updatedEmployee).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                String message = response.isSuccessful() ? "Employee details updated successfully" : "Error updating employee details";
                Toast.makeText(ViewAdminActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(ViewAdminActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAdminActivity() {
        Intent intent = new Intent(ViewAdminActivity.this, AdminActivity.class);
        startActivity(intent);
        finish();
    }
}


