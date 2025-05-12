plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.pepinho.pmdm.burgerbuilder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pepinho.pmdm.burgerbuilder"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
//        viewBinding = true
        compose = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
    implementation(platform(libs.androidx.compose.bom))
    // Podríamos usar Material 3 (o una de las restantes)
    implementation(libs.androidx.material3)
    // Podríamos usar material 2:
    // implementation("androidx.compose.material:material")
    // o no emplear Material y hacerlo directamente desde Foundation
    // implementation("androidx.compose.foundation:foundation")
    // o sólo importar las APIs principales para bajo nivel de Compose
    // implementation("androidx.compose.ui:ui")
// Android Studio Preview support
    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3.android)
    debugImplementation(libs.androidx.ui.tooling)

    // UI Tests
    androidTestImplementation(libs.androidx.ui.test.junit4)
//    androidTestImplementation(libs.androidx.ui.test)
    debugImplementation(libs.androidx.ui.test.manifest)

//    // Para Compose Navigation:
//    implementation(libs.androidx.navigation.compose)
//    // Para Compose Navigation Testing:
//    androidTestImplementation(libs.androidx.navigation.compose.test)

    // Opcionales:

//    // Para Compose ViewModel:
//    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    // Para Compose LiveData:
//    implementation(libs.androidx.lifecycle.livedata.compose)
    // Para integración con actividades:
    implementation(libs.androidx.activity.compose)
    // Integración con RxJava, una librería de programación reactiva
//    implementation(libs.androidx.rxjava2)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}