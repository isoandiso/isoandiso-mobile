plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.pedrosiccha.isoandiso_mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pedrosiccha.isoandiso_mobile"
        minSdk = 24
        targetSdk = 34
        versionCode = getVersionCode()
        versionName = getVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // Navigation Compose
    implementation(libs.navigation.compose)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))
    implementation(project(":splash"))
    implementation(project(":login"))
}

// 🔽 Versionamiento dinámico según rama (develop / release/qa)
fun getVersionCode(): Int {
    val branch = System.getenv("GITHUB_REF_NAME") ?: "local"
    return when (branch) {
        "release/qa" -> System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 1
        "main" -> System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 100
        "develop" -> 1000
        else -> 9999
    }
}

fun getVersionName(): String {
    val shortSha = System.getenv("GITHUB_SHA")?.take(7) ?: "dev"
    val branch = System.getenv("GITHUB_REF_NAME") ?: "local"
    if (branch == "local") return "local-dev"

    return when (branch) {
        "release/qa" -> "1.0.0-qa+$shortSha"
        else -> "dev-$shortSha"
    }
}