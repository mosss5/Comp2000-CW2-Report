package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText idEditText;
    private Button loginButton;
    private TextView resultText;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEditText = findViewById(R.id.idEditText);
        loginButton = findViewById(R.id.loginButton);
        resultText = findViewById(R.id.resultText);

        loginButton.setOnClickListener(v -> {
            String employeeId = idEditText.getText().toString().trim();

            if (employeeId.equalsIgnoreCase("admin")) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            } else {
                String url = "http://10.224.41.11/comp2000/employees/get/" + employeeId;
                fetchData(url);
            }
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
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("employeeDetails", result);
            startActivity(intent);
            finish();
        } else {
            resultText.setText(R.string.failed_to_fetch_data);
        }
    }
}


//http://10.224.41.11/comp2000/employees/get/