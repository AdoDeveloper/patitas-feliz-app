// src/main/java/com/example/patitasfelizapp/api/RetrofitClient.java

package com.example.patitasfelizapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://patitas-feliz-api.onrender.com/api/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Configurar el interceptor de logging (opcional pero útil para depuración)
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getClient().create(ApiService.class);
    }
}
