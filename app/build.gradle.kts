plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.patitasfelizapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.patitasfelizapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Dependencias que ya tienes
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Lottie para animaciones
    implementation(libs.lottie)

    // Retrofit para la comunicación con la API
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Logging Interceptor para depuración (opcional pero recomendado)
    implementation(libs.logging.interceptor)

    // Dependencias de pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
