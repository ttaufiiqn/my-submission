plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dicoding.mysubmission"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dicoding.mysubmission"
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.glide)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.paging.runtime.ktx)

    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("org.mockito:mockito-inline:4.8.1")
    implementation ("com.google.android.gms:play-services-location:21.3.0")
}