buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.1.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        // Hilt DI
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
    }
}
