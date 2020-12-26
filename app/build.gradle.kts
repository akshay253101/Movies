import com.beetlestance.movies.buildsrc.Libs
import com.beetlestance.movies.buildsrc.Movies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdkVersion(Movies.compileSdkVersion)

    defaultConfig {
        applicationId = Movies.applicationId
        minSdkVersion(Movies.minSdkVersion)
        targetSdkVersion(Movies.targetSdkVersion)
        versionCode = Movies.versionCode
        versionName = Movies.versionName

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    kapt {
        arguments {
            arg("dagger.experimentalDaggerErrorMessages", "enabled")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-routines
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    // Navigation
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.fragmentKtx)

    // Material Design
    implementation(Libs.Google.material)

    // Gson
    implementation(Libs.Google.gson)

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    // Dagger
    implementation(Libs.Dagger.dagger)
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.processor)
    implementation(Libs.Dagger.support)

    // Coil
    implementation(Libs.Coil.coil)
}