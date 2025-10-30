plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.shop_online"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.shop_online"
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
    // Kích hoạt/tắt các tính năng build trong Android
    buildFeatures{
        // Cho phép tự động tạo class binding để thao tác với View dễ dàng hơn
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.fragment)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Thư viện tải và hiển thị hình ảnh cho androi
    implementation("com.github.bumptech.glide:glide:5.0.5")
    // Thư viện của google để chuyển JSON -> object java
    implementation("com.google.code.gson:gson:2.13.2")
    // Thư viện tạo thanh điều hướng dưới
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
}