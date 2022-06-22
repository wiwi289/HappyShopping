plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(VersionAssemble.compileSdkVersion)
    buildToolsVersion(VersionAssemble.buildToolsVersion)

    defaultConfig {
        applicationId(VersionAssemble.applicationId)
        minSdkVersion(VersionAssemble.minSdkVersion)
        targetSdkVersion(VersionAssemble.targetSdkVersion)
        versionCode = VersionAssemble.versionCode
        versionName = VersionAssemble.versionName

    }

    buildTypes {
        getByName("release") {
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
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
}