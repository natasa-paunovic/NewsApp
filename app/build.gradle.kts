plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.android.newsapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.android.newsapp"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "NEWS_API_KEY", "\"${project.findProperty("NEWS_API_KEY") ?: "8584737764fa4df2b774b5529fc734d2"}\"")
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            isMinifyEnabled = false
            isShrinkResources = false
        }

        release {
            buildConfigField("String", "NEWS_API_KEY", "\"${project.findProperty("NEWS_API_KEY") ?: "8584737764fa4df2b774b5529fc734d2"}\"")
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
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
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)
    implementation (libs.bundles.koin)


    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}