plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("ru.practicum.android.diploma.plugins.developproperties")
    id("kotlin-kapt")
}

android {
    namespace = "ru.practicum.android.diploma"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ru.practicum.android.diploma"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(type = "String", name = "HH_ACCESS_TOKEN", value = "\"${developProperties.hhAccessToken}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidX.core)
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.legacySupport)

    // UI layer libraries
    implementation(libs.ui.material)
    implementation(libs.ui.constraintLayout)
    implementation(libs.ui.fragments)

    // Navigation
    implementation(libs.nav.navigationFragment)
    implementation(libs.nav.navigationUI)

    // ViewModel
    implementation(libs.viewModel.lifecycleViewModel)

    // Livedata
    implementation(libs.liveData.lifecycleLiveData)

    // Glide
    implementation(libs.glide.glide)

    // Network
    implementation(libs.network.retrofit)
    implementation(libs.network.retrofitConverterGson)
    implementation(libs.network.gson)

    // Dependency Injections
    implementation(libs.di.koinAndroid)
    implementation(libs.di.koinCore)

    // Coroutines
    implementation(libs.coroutines.coroutines)

    // Database
    implementation(libs.db.roomRuntime)
    kapt(libs.db.roomCompiler)
    implementation(libs.db.roomKtx)

    // region Unit tests
    testImplementation(libs.unitTests.junit)
    // endregion

    // region UI tests
    androidTestImplementation(libs.uiTests.junitExt)
    androidTestImplementation(libs.uiTests.espressoCore)
    // endregion
}
