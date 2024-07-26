plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.jsonparsinginandroidusingretrofitlibrary_2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jsonparsinginandroidusingretrofitlibrary_2"
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // below dependency for using retrofit.

    implementation (libs.retrofit);

    implementation (libs.converter.gson);

// below dependency for using picasso image loading library

    implementation (libs.picasso);
}