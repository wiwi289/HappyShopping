plugins {
    id(PluginAssemble.pluginLibraryId)
    id(PluginAssemble.pluginAndroid)
}

android {
    compileSdkVersion(VersionAssemble.compileSdkVersion)
    buildToolsVersion(VersionAssemble.buildToolsVersion)

    defaultConfig {
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
    api(project(":base"))
}