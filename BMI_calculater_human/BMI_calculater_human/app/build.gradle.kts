plugins {
    alias(libs.plugins.android.application)
}


android {
    namespace = "com.example.bmi_calculater_human"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bmi_calculater_human"
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

//    buildscript {
//        repositories {
//            google()
//            mavenCentral()
//        }
//        dependencies {
//            classpath ("com.android.tools.build:gradle:7.0.2");
//            // NOTE: Do not place your application dependencies here; they belong
//            // in the individual module build.gradle files
//        }
//    }
//
//    allprojects {
//        repositories {
//            google()
//            mavenCentral()
//        }
//    }


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
    implementation(libs.play.services.ads)
//    implementation("com.google.android.gms:play-services-ads:23.2.0")
//    implementation("com.google.android.gms:play-services-ads:20.1.0")






}