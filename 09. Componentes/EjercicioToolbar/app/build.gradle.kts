plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pepinho.pmdm.ejerciciotoolbar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pepinho.pmdm.ejerciciotoolbar"
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

    // Recycler View
    implementation(libs.androidx.recyclerview)
    // Para control sobre la selección de items de selección mediante pulsación o ratón
    implementation(libs.androidx.recyclerview.selection)

    // Navegación
    // https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx
    implementation(libs.androidx.navigation.fragment.ktx)
// https://mvnrepository.com/artifact/androidx.navigation/navigation-ui-ktx
    implementation(libs.androidx.navigation.ui.ktx)







    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}