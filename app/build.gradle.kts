plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.foivos.mycryptoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.foivos.mycryptoapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.foivos.mycryptoapp.HiltTestRunner"
        //testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder") // Use JUnit 5 for local unit tests
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    buildTypes {

        debug {
            // Development url (in this case it is same with the Production)
            buildConfigField("String", "BASE_URL", "\"https://api.coinpaprika.com\"")
        }

        release {
            // Production url
            buildConfigField("String", "BASE_URL", "\"https://api.coinpaprika.com\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)
    implementation(libs.timber)
    implementation(libs.okHttp3.logging.interceptor)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Local unit tests
    testImplementation(libs.junit)
    testImplementation(libs.io.mockK)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp3.mockwebserver)
    testImplementation(libs.junit.jupiter.api)
    kaptTest(libs.dagger.hilt.android.compiler)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // Instrumented tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.app.cash.turbine)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
    kaptAndroidTest(libs.dagger.hilt.android.compiler)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}