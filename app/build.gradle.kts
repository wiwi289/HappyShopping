plugins {
    id(PluginAssemble.pluginAndroidId)
    id(PluginAssemble.pluginAndroid)
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

        multiDexEnabled = true
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
    buildFeatures.viewBinding = true
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    if (Deploy.isDebugMode) {
        implementation(project(":common"))
    } else {
        implementation(project(":launch"))
    }
}

