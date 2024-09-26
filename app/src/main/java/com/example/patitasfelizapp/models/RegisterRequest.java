package com.example.patitasfelizapp.models;

public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public RegisterRequest(String nombre, String email, String password, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
}

