import java.text.SimpleDateFormat
import java.util.*


object VersionAssemble {

    const val kotlinVersion = "1.5.21"
    const val gradleVersion = "4.1.0"

    //SDK版本
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val applicationId = "com.swu.happyshopping"
    const val minSdkVersion = 21
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

    val thirdPartyDependencies = mapOf(
            "Kotlin_Stdlib" to "org.jetbrains.kotlin:kotlin-stdlib:${VersionAssemble.kotlinVersion}",
            "Core_Kts" to "androidx.core:core-ktx:1.3.2",
            "Appcompat" to "androidx.appcompat:appcompat:1.4.2",
            "AndroidX_Legacy" to "androidx.legacy:legacy-support-v4:1.0.0",
            "Constraint_Layout" to "androidx.constraintlayout:constraintlayout:1.1.3",
            "Recyclerview" to "androidx.recyclerview:recyclerview:1.1.0",
            "MaterialDesign" to "com.google.android.material:material:1.3.0",
            "GSon" to "com.google.code.gson:gson:2.8.5",
            "Rxjava" to "io.reactivex.rxjava2:rxjava:2.2.10",
            "RxAndroid" to "io.reactivex.rxjava2:rxandroid:2.1.1",
            "OkHttp" to "com.squareup.okhttp3:okhttp:4.8.1",
            "OkIo" to "com.squareup.okio:okio:2.7.0",
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
            "Coroutine_Android" to "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1",
            "LifeCycle" to "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1",
            "ViewModel" to "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1",
            "Palette" to "androidx.palette:palette:1.0.0",
            "LDialog" to "com.github.liys666666:LDialog:V2.1.0",
            "Banner" to "io.github.youth5201314:banner:2.2.2",
            "LoadingView" to "com.carson_ho:Kawaii_LoadingView:1.0.0",
            "ShimmerLayout" to "io.supercharge:shimmerlayout:2.1.0"
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