plugins {
    id(if (Deploy.isDebugMode) PluginAssemble.pluginAndroidId else PluginAssemble.pluginLibraryId)
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

        if(Deploy.isDebugMode) {
            multiDexEnabled = true
        }
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

    sourceSets {
        getByName("main") {
            if (Deploy.isDebugMode) {
                manifest.srcFile("src/main/module/AndroidManifest.xml")
            } else {
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
        }
    }
    buildFeatures.viewBinding = true
}

dependencies {
    api(project(":common"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}