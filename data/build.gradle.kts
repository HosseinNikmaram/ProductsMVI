plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.nikmaram.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Room Components
    implementation(libs.room.runtime)
    kapt(libs.daggerCompiler)
    // Room KTX
    implementation(libs.room.ktx)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.daggerCompiler)

    // Serialization
    implementation(libs.serializationJson)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.coroutines)
    testImplementation(libs.junit)

    // Logging
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}