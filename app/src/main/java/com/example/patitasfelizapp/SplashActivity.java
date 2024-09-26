package com.example.patitasfelizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash); // Asegúrate de que este archivo exista

        // Configura los márgenes de las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al ImageView que muestra la imagen SVG
        ImageView logo = findViewById(R.id.logoImageView);

        // Cargar la animación de escala
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up); // Asegúrate de que el recurso exista
        logo.startAnimation(scaleAnimation);

        // Lanzar la actividad de main después de 3 segundos
        new android.os.Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad Splash para que no vuelva al presionar 'atrás'
        }, 3000); // 3000 milisegundos = 3 segundos
    }
}
