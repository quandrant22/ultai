plugins {
    id("com.android.application") version "8.8.1"
}

android {
    namespace = "com.example.ultai"
    compileSdk = 34

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.ultai"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "ГОРИ ОНА ЁБАННЫМ ОГНЕМ"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // AndroidX
    implementation(libs.appcompat.v170)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata) // Убрали kotlin-ktx
    implementation(libs.lifecycle.viewmodel) // Убрали kotlin-ktx
    implementation(libs.preference)

    // Material Design
    implementation(libs.material.v1120)

    // Jetpack Navigation
    implementation(libs.navigation.fragment.v287) // Убрали kotlin-ktx
    implementation(libs.navigation.ui.v287) // Убрали kotlin-ktx

    // Networking
    implementation(libs.okhttp.v4100)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Database
    implementation(libs.room.runtime)

    // Charts
    implementation(libs.mpandroidchart)
    implementation(libs.legacy.support.v4)
    implementation(libs.constraintlayout.core)
    implementation(libs.constraintlayout.compose.android)
    implementation(libs.animation.core.android)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    implementation(libs.constraintlayout.core.v111)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v121)
    androidTestImplementation(libs.espresso.core.v361)

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation ("com.rometools:rome:1.16.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
