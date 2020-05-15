private object LibraryVersion {
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.1.0"
    const val MATERIAL = "1.1.0-rc01"
    const val JUNIT = "4.12"
    const val MOCKITO = "3.0.0"
    const val MOCKITO_KOTLIN = "2.1.0"
    const val ANDROID_X_TEST = "1.1.1"
    const val RETROFIT = "2.7.0"
    const val OK_HTTP_LOGGING_INTERCEPTOR = "4.0.1"
    const val ROOM = "2.2.3"
    const val DAGGER = "2.27"
    const val RX_KOTLIN = "2.4.0"
    const val RX_RELAY = "2.1.1"
    const val RX_ANDROID = "2.1.1"
    const val ADAPTER_DELEGATE = "4.0.0"
    const val RX_BINDING = "3.0.0-alpha2"
    const val RX_BINDING_CORE = "3.0.0-alpha2"
    const val EXO_PLAYER = "2.7.3"
    const val SWIPRE_REVEAL = "1.4.1"
    const val JAVAX_INJECT = "1"
    const val LIFECYCLE_VERSION = "2.1.0"
    const val NAVIGATION = "2.1.0"
    const val TIMBER = "4.7.1"
    const val THREE_TEN_ABP = "1.2.2"
    const val SWIPE_TO_REFRESH = "1.0.0"
    const val GLIDE = "4.11.0"
    const val WORK_MANAGER = "2.2.0"
    const val FIREBASE_ANALYTICS = "17.2.2"
    const val FIREBASE_CRASHLYTICS = "17.0.0-beta01"
    const val RX_PAPARAZZO = "0.6.1-2.x"
    const val FLEX_BOX = "2.0.1"
}

object Deps {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${LibraryVersion.APPCOMPAT}"
    const val LIFECYCLE_EXTENSION =
        "androidx.lifecycle:lifecycle-extensions:${LibraryVersion.LIFECYCLE_VERSION}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${LibraryVersion.RX_KOTLIN}"
    const val RX_RELAY = "com.jakewharton.rxrelay2:rxrelay:${LibraryVersion.RX_RELAY}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${LibraryVersion.RX_ANDROID}"
    const val ADAPTER_DELEGATE = "com.hannesdorfmann:adapterdelegates4:${LibraryVersion.ADAPTER_DELEGATE}"
    const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:${LibraryVersion.RX_BINDING}"
    const val RX_BINDING_CORE = "com.jakewharton.rxbinding3:rxbinding-core:${LibraryVersion.RX_BINDING_CORE}"
    const val EXO_PLAYER = "com.google.android.exoplayer:exoplayer-core:${LibraryVersion.EXO_PLAYER}"
    const val SWIPTE_REVEAL = "com.chauthai.swipereveallayout:swipe-reveal-layout:${LibraryVersion.SWIPRE_REVEAL}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${LibraryVersion.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${LibraryVersion.ROOM}"
    const val ROOM_RX = "androidx.room:room-rxjava2:${LibraryVersion.ROOM}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_RX_ADAPTER =
        "com.squareup.retrofit2:adapter-rxjava2:${LibraryVersion.RETROFIT}"
    const val RETROFIT_MOSHI_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${LibraryVersion.RETROFIT}"
    const val OK_HTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OK_HTTP_LOGGING_INTERCEPTOR}"
    const val DAGGER_RUNTIME = "com.google.dagger:dagger:${LibraryVersion.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${LibraryVersion.DAGGER}"
    const val JAVAX_INJECT = "javax.inject:javax.inject:${LibraryVersion.JAVAX_INJECT}@jar"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${LibraryVersion.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${LibraryVersion.NAVIGATION}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
    const val THREE_TEN_ABP =
        "com.jakewharton.threetenabp:threetenabp:${LibraryVersion.THREE_TEN_ABP}"
    const val SWIPE_TO_REFRESH =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPE_TO_REFRESH}"
    const val GLIDE = "com.github.bumptech.glide:glide:${LibraryVersion.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${LibraryVersion.GLIDE}"
    const val WORK_MANAGER = "androidx.work:work-runtime:${LibraryVersion.WORK_MANAGER}"
    const val RX_WORK = "androidx.work:work-rxjava2:${LibraryVersion.WORK_MANAGER}"
    const val WORK_MANAGER_KTX = "androidx.work:work-runtime-ktx:${LibraryVersion.WORK_MANAGER}"
    const val FIREBASE_ANALYTICS =
        "com.google.firebase:firebase-analytics:${LibraryVersion.FIREBASE_ANALYTICS}"
    const val FIREBASE_CRASHLYTICS =
        "com.google.firebase:firebase-crashlytics:${LibraryVersion.FIREBASE_CRASHLYTICS}"
    const val RX_PAPARAZZO = "com.github.miguelbcr:RxPaparazzo:${LibraryVersion.RX_PAPARAZZO}"
    const val FLEX_BOX = "com.google.android:flexbox:${LibraryVersion.FLEX_BOX}"

    object Testing {
        const val JUNIT = "junit:junit:${LibraryVersion.JUNIT}"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:${LibraryVersion.MOCKITO}"
        const val MOCKITO_ANDROID = "org.mockito:mockito-android:${LibraryVersion.MOCKITO}"
        const val MOCKITO_KOTLIN =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${LibraryVersion.MOCKITO_KOTLIN}"
        const val ANDROID_X_CORE_TESTING =
            "androidx.test.ext:junit:${LibraryVersion.ANDROID_X_TEST}"
        const val ANDROID_X_TEST_RULES = "androidx.test:rules:${LibraryVersion.ANDROID_X_TEST}"
    }
}