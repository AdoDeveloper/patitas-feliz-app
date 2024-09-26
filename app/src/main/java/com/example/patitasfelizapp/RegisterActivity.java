package com.example.patitasfelizapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patitasfelizapp.api.ApiService;
import com.example.patitasfelizapp.api.RetrofitClient;
import com.example.patitasfelizapp.models.RegisterRequest;
import com.example.patitasfelizapp.models.RegisterResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private Spinner roleSpinner;
    private String selectedRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        roleSpinner = findViewById(R.id.roleSpinner);

        // Configurar el Spinner para seleccionar el rol
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        // Manejar selección de rol
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedRole = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedRole = "usuario"; // Valor por defecto si no se selecciona nada
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fullName = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest registerRequest = new RegisterRequest(fullName, email, password, selectedRole);

        ApiService apiService = RetrofitClient.getApiService();
        Call<RegisterResponse> call = apiService.registerUser(registerRequest);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    // Aquí puedes navegar a otra actividad si lo deseas
                } else {
                    // Manejar el error del servidor
                    try {
                        String errorMessage = response.errorBody().string();
                        Toast.makeText(RegisterActivity.this, "Error en el registro: " + errorMessage, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Fallo en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
