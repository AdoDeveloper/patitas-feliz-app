// src/main/java/com/example/patitasfelizapp/api/ApiService.java

package com.example.patitasfelizapp.api;

import com.example.patitasfelizapp.models.LoginRequest;
import com.example.patitasfelizapp.models.LoginResponse;
import com.example.patitasfelizapp.models.RegisterRequest;
import com.example.patitasfelizapp.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Endpoint para el registro
    @POST("users")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    // Endpoint para el login
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
