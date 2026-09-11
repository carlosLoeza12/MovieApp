import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.movieapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties: Properties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        manifestPlaceholders["MOVIE_API_KEY"] = localProperties["MOVIE_API_KEY"] as String

        buildConfigField(
            "String",
            "MOVIE_API_KEY",
            "\"${localProperties["MOVIE_API_KEY"]}\""
        )
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    // Android compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Android Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    // Android Serialization
    implementation(libs.kotlinx.serialization.core)

    // Android core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Dager Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.navigation)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter)

    // Gson
    implementation(libs.gson)
    implementation(libs.converter.gson)

    // OkHttp
    implementation(libs.log.interceptor)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // lottie
    implementation(libs.lottie.compose)

    // Android KSP
    ksp(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)

    // Android test
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Debug test
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}