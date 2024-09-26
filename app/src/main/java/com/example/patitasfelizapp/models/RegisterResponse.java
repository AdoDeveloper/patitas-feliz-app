// src/main/java/com/example/patitasfelizapp/models/UserResponse.java

package com.example.patitasfelizapp.models;

public class RegisterResponse {
    private String message; // Mensaje de respuesta

    // Constructor
    public RegisterResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
