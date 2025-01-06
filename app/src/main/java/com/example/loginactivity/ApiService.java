package com.example.loginactivity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @PUT("employees/edit/{id}")
    Call<Void> updateEmployee(@Path("id") int id, @Body Employee employee);
}
