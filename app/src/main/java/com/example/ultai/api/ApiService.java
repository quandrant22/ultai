package com.example.ultai.api;

import com.example.ultai.models.ApiResponse;
import com.example.ultai.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/register")
    Call<ApiResponse> registerUser(@Body User user);
}
