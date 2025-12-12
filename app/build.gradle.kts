plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.gallery.view"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.gallery.view"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

// LiveData (optional)
    implementation(libs.androidx.lifecycle.livedata.ktx)

// Lifecycle runtime
    implementation(libs.androidx.lifecycle.runtime.ktx)

// Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android.v181)

// Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

// Hilt + ViewModel
    implementation(libs.androidx.hilt.navigation.fragment)

    implementation(libs.converter.gson.v2110)

    //Logging interceptor
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.glide)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    implementation (libs.shimmer)

    implementation (libs.core)

}