// src/main/java/com/example/patitasfelizapp/models/LoginResponse.java

package com.example.patitasfelizapp.models;

public class LoginResponse {
    private String token; // O el campo que se devuelve al iniciar sesión

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
