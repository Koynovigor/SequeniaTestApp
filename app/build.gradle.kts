plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.l3on1kl.sequeniatestapp"
    compileSdk = 35

    buildFeatures {
        compose = true
    }

    defaultConfig {
        applicationId = "com.l3on1kl.sequeniatestapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.koin.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.androidx.navigation.safe.args.generator)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}