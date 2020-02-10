object GradlePluginId {
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val GOOGLE_SERVICE_PLUGIN = "com.google.gms.google-services"
    const val FIREBASE_CRASHLYTIC_PLUGIN = "com.google.firebase.crashlytics"
    const val TELESCOPE_VERSIONING_PLUGIN = "com.gladed.androidgitversion"
    const val KOTLIN_ANDROID = "android"
    const val KOTLIN_KAPT = "kapt"
    const val KOTLIN_ANDROID_EXTENSIONS = "android.extensions"
    const val KOTLIN_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
}

object GradlePlugins {
    const val ANDROID_GRADLE =
        "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val GOOGLE_SERVICES_GRADLE =
        "com.google.gms:google-services:${GradlePluginVersion.GOOGLE_SERVICES_GRADLE}"
    const val SAFE_ARGS_GRADLE =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAV_VERSION}"
    const val FIREBASE_CRASHLYTIC_GRADLE =
        "com.google.firebase:firebase-crashlytics-gradle:${GradlePluginVersion.FIREBASE_CRASHLYTIC_GRADLE}"
}

object GradlePluginVersion {
    const val ANDROID_GRADLE = "3.5.2"
    const val GOOGLE_SERVICES_GRADLE = "4.3.2"
    const val FIREBASE_CRASHLYTIC_GRADLE = "2.0.0-beta01"
    const val DETEKT = "1.0.1"
    const val GIR_VERSIONING_PLUGIN = "0.4.11"
}
