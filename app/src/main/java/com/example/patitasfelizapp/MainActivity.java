package com.example.patitasfelizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patitasfelizapp.api.ApiService;
import com.example.patitasfelizapp.api.RetrofitClient;
import com.example.patitasfelizapp.models.LoginRequest;
import com.example.patitasfelizapp.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Inicializa Retrofit
        apiService = RetrofitClient.getApiService();

        // Configura el botón de inicio de sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Configura el botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a la ActivityRegister
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Verificar si se proporcionaron las credenciales
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, ingrese las credenciales", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);
        Log.d("MainActivity", "Intentando iniciar sesión con email: " + email);

        apiService.loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d("MainActivity", "Respuesta recibida: " + response);
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    Log.d("MainActivity", "Token recibido: " + token);
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                    // Aquí puedes redirigir a otra actividad
                } else {
                    Log.d("MainActivity", "Error en la respuesta: " + response.code());
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        Toast.makeText(MainActivity.this, "Error: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e("MainActivity", "Error al leer el cuerpo de error", e);
                        Toast.makeText(MainActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "Error en la conexión: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
