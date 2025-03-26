plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Plugin de KSP
    id("com.google.devtools.ksp")
    // Plugin de Safe Args
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pepinho.freetogame"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pepinho.freetogame"
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

    // Fragment: Kotlin extensions for 'fragment' artifact
    implementation(libs.androidx.fragment.ktx)
    // Kotlin extensions for 'viewmodel' artifact
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // RecyclerView
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)
    // https://mvnrepository.com/artifact/jp.wasabeef/recyclerview-animators
    implementation(libs.recyclerview.animators)

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation(libs.kotlinx.coroutines.core)
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
    implementation(libs.kotlinx.coroutines.android)


    // ROOM
    // Android Room-Runtime
    // https://mvnrepository.com/artifact/androidx.room/room-runtime
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.legacy.support.v4)
    ksp(libs.androidx.room.compiler)//
    implementation(libs.androidx.room.ktx)


    // Navegación
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // https://mvnrepository.com/artifact/io.coil-kt/coil
    implementation(libs.coil)




    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
