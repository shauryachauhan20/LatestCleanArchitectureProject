import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.mycleanarchitecture"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mycleanarchitecture"
        minSdk = 24
        targetSdk = 35
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

    flavorDimensions("version")

    productFlavors {
        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            manifestPlaceholders.putAll(mapOf("variant" to "dev"))
            getProps("./config/development.props").forEach { (key, value) ->
                buildConfigField("String", key, value)
            }
        }
        create("stage") {
            dimension = "version"
            applicationIdSuffix = ".stage"
            versionNameSuffix = "-stage"
            manifestPlaceholders.putAll(mapOf("variant" to "stage"))
            getProps("./config/testing.props").forEach { (key, value) ->
                buildConfigField("String", key, value)
            }
        }
        create("prod") {
            dimension = "version"
            versionNameSuffix = "-prod"
            manifestPlaceholders.putAll(mapOf("variant" to "prod"))
            getProps("./config/production.props").forEach { (key, value) ->
                buildConfigField("String", key, value)
            }
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
        dataBinding = true
    }
}

fun getProps(path: String): Map<String, String> {
    val props = Properties()
    val file = file(path)
    if (!file.exists()) {
        throw IllegalArgumentException("Properties file not found: $path")
    }
    FileInputStream(file).use { props.load(it) }
    return props.entries.associate { it.key.toString() to it.value.toString() }
}
dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0") //to get string response from Retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava:2.9.0") //to create call adapter

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Lifecycle ViewModel Components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    //For activity Ktx extension
    implementation("androidx.activity:activity-ktx:1.9.3")

    //Dagger Hilt core
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

// Kotlin dependencies (match Hilt compatibility)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")

    //test library
    testImplementation ("io.mockk:mockk:1.13.5")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation ("org.jetbrains.kotlin:kotlin-test:1.9.10")

}