import java.text.SimpleDateFormat
import java.util.*


object VersionAssemble {

    const val kotlinVersion = "1.4.32"
    const val gradleVersion = "4.1.0"

    //SDK版本
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val applicationId = "com.swu.happyshopping"
    const val minSdkVersion = 16
    const val targetSdkVersion = 31

    //版本
    const val versionCode = 1
    const val versionName = "1.0"

}

object PluginAssemble {
    const val pluginAndroidId = "com.android.application"
    const val pluginLibraryId = "com.android.library"
    const val pluginAndroid = "kotlin-android"
    const val pluginAndroidExtensions = "kotlin-android-extensions"
    const val pluginKapt = "kotlin-kapt"
}

object Dependencies {

    val thirdPartyDependencies = mapOf<String, String>(
            "Kotlin_Stdlib" to "org.jetbrains.kotlin:kotlin-stdlib:${VersionAssemble.kotlinVersion}",
            "Kotlin_Core_Ktx" to "androidx.core:core-ktx:1.3.0",
            "Appcompat" to "androidx.appcompat:appcompat:1.1.0",
            "Constraint_Layout" to "androidx.constraintlayout:constraintlayout:1.1.3",
            "Recyclerview" to "androidx.recyclerview:recyclerview:1.1.0",
            "GSon" to "com.google.code.gson:gson:2.8.5",
            "Converter_Gson" to "com.squareup.retrofit2:converter-gson:2.4.0",
            //导入Rxjava
            // 此处一定要注意使用RxJava2的版本
            "Rxjava" to "io.reactivex.rxjava2:rxjava:2.2.10",
            "RxAndroid" to "io.reactivex.rxjava2:rxandroid:2.1.1",
            // 衔接 Retrofit & RxJava
            // 此处一定要注意使用RxJava2的版本
            "Retrofit2_Rxjava2_Adapter" to "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0",
            "OkHttp" to "com.squareup.okhttp3:okhttp:4.8.1",
            "OkIo" to "com.squareup.okio:okio:2.7.0",
            "Retrofit" to "com.squareup.retrofit2:retrofit:2.4.0",
            // 导入MMKV
            "MMkv" to "com.tencent:mmkv:1.0.10",
            //底部导航栏
            "BottomNavigation" to "com.ashokvarma.android:bottom-navigation-bar:2.2.0",
            //fragment
            "Fragment" to "androidx.legacy:legacy-support-v4:1.0.0",
            //数据库ROOM配置
            "Room" to "android.arch.persistence.room:runtime:1.0.0",
            "BMob" to "io.github.bmob:android-sdk:3.8.13",
            "Glide" to "com.github.bumptech.glide:glide:4.12.0",
            "Flow_Layout" to "com.hyman:flowlayout-lib:1.1.2",
            "Circle_Button" to "com.github.markushi:circlebutton:1.1",
            "Checkbox" to "net.igenius:customcheckbox:1.3",
            "LoadSir" to "com.kingja.loadsir:loadsir:1.3.6",
            "SmartRefresh" to "com.scwang.smartrefresh:SmartRefreshLayout:1.1.0",
            "Lottie" to "com.airbnb.android:lottie:2.8.0",
            "Coroutine_Core" to "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7",
            "Coroutine_Android" to "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
    )

}

object Deploy {

    //当前时间
    fun getSystemTime(): String {
        val mSimpleDateFormat = SimpleDateFormat("YYYY_MM_dd_HH_mm_ss", Locale.CHINA)
        return mSimpleDateFormat.format(System.currentTimeMillis())
    }

    const val isDebugMode = true
}