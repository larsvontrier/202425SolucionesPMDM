plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pepinho.nba"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pepinho.nba"
        minSdk = 24
        targetSdk = 35
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // Añadimos al proyecto: fragmento y viewModel
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation(libs.okhttp)
    //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Corrutinas
    implementation(libs.kotlinx.coroutines.android)
    // Gson
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(libs.gson)
    // Recycler View
    implementation(libs.androidx.recyclerview)
    // Para control sobre la selección de items de selección mediante pulsación o ratón
    implementation(libs.androidx.recyclerview.selection)

    // Animaciones personalizadas:
    implementation(libs.recyclerview.animators)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx
    implementation(libs.androidx.navigation.fragment.ktx.v287)
// https://mvnrepository.com/artifact/androidx.navigation/navigation-ui-ktx
    implementation(libs.androidx.navigation.ui.ktx.v287)
    implementation(libs.androidx.legacy.support.v4)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}