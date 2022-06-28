// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.4.32")
    repositories {
        maven(url = "http://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "https://jitpack.io")
        maven(url = "https://s01.oss.sonatype.org/content/groups/public")
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${VersionAssemble.gradleVersion}")
        classpath(kotlin("gradle-plugin", "1.5.20"))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        maven(url = "http://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "https://jitpack.io")
        maven(url = "https://s01.oss.sonatype.org/content/groups/public")
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}