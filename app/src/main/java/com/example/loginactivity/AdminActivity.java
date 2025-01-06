package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private Button searchUserButton, logoutAdminButton;
    private TextView resultText;

    private static final String TAG = "AdminActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        userIdEditText = findViewById(R.id.userIdEditText);
        searchUserButton = findViewById(R.id.searchUserButton);
        logoutAdminButton = findViewById(R.id.logoutAdminButton);
        resultText = findViewById(R.id.resultText);

        searchUserButton.setOnClickListener(v -> {
            String employeeId = userIdEditText.getText().toString().trim();

            if (!employeeId.isEmpty()) {
                String url = "http://10.224.41.11/comp2000/employees/get/" + employeeId;
                fetchData(url);
            } else {
                Toast.makeText(AdminActivity.this, "Please enter a user ID", Toast.LENGTH_SHORT).show();
            }
        });

        logoutAdminButton.setOnClickListener(v -> {
            Intent logoutIntent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(logoutIntent);
            finish();
        });
    }

    private void fetchData(String url) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            String result = fetchDataFromUrl(url);
            runOnUiThread(() -> onPostFetchData(result));
        });
    }

    private String fetchDataFromUrl(String urlString) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urlString).build();
            try (Response response = client.newCall(request).execute()) {
                return response.isSuccessful() ? response.body().string() : null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching data", e);
            return null;
        }
    }

    private void onPostFetchData(String result) {
        if (result != null) {

            Intent intent = new Intent(AdminActivity.this, ViewAdminActivity.class);
            intent.putExtra("employeeDetails", result);
            startActivity(intent);
            finish();
        } else {
            resultText.setText(R.string.failed_to_fetch_data);
        }
    }
}


