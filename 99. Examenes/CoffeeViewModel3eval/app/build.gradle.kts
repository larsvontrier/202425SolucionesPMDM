plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    // Plugin de Safe Args
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pepinho.pmdm.exames"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pepinho.pmdm.exames"
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
// https://medium.com/@nicosnicolaou/migration-gradle-dependencies-to-version-catalogs-libs-versions-toms-part-3-ac3469155109

    // Arquitectura y diseño
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.kotlinx.coroutines.android)

    // https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview
    implementation(libs.androidx.recyclerview)
// Para control sobre la selección de items de selección mediante pulsación o ratón
    implementation(libs.androidx.recyclerview.selection)

    // ViewModel
    implementation(libs.androidx.lifecycle.runtime.ktx) //
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
// https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime.ktx.v290)
    // Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    // Fragmentos
    // Kotlin
    implementation(libs.androidx.fragment.ktx)
    // Compose
    implementation(libs.androidx.fragment.compose)
    // Testing
    debugImplementation(libs.androidx.fragment.testing)

    // Navegación
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // Navegación compose
    implementation(libs.androidx.navigation.compose)
    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)
    // JSON serialization library, works with the Kotlin serialization plugin
    implementation(libs.kotlinx.serialization.json)

    // Room
    implementation(libs.androidx.room.runtime) // Biblioteca principal de Room
    ksp(libs.androidx.room.compiler)//
    implementation(libs.androidx.room.ktx) //

//    // Retrofit
//    implementation(libs.retrofit)
//    implementation(libs.retrofit.gson)
//    // OkHttp
//    implementation(libs.okHttp)
//    // Gson
//    implementation(libs.gson)
//    // Coil
//    implementation(libs.coil)
//    // Coroutines
//    implementation(libs.coroutine.core)
//    implementation(libs.coroutine.android)
//    //Hilt
//    implementation(libs.dagger.android)
//    ksp(libs.dagger.compiler)
//    ksp(libs.hilt.compiler)
//    //Palette
//    implementation(libs.androidx.palette.ktx)
//    //My Library - https://github.com/NicosNicolaou16/ImagePickerAndroid
//    implementation(libs.image.picker.android)
//    //My Library - https://github.com/NicosNicolaou16/PercentagesWithAnimationCompose
//    implementation(libs.percentages.with.animation.compose)

    // Pruebas unitarias
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}