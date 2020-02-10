plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    kotlin(GradlePluginId.KOTLIN_ANDROID)
    kotlin(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(
                    "dagger.gradle.incremental" to "true",
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            buildConfigField("String", Constants.BASE_URL, BuildTypeRelease.baseUrl)
        }

        getByName(BuildType.DEBUG) {
            buildConfigField("String", Constants.BASE_URL, BuildTypeDebug.baseUrl)
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        create(BuildType.STAGING) {
            buildConfigField("String", Constants.BASE_URL, BuildTypeStaging.baseUrl)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as? org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options?.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    //kotlin
    implementation(Deps.KOTLIN)

    //rx
    implementation(Deps.RX_KOTLIN)

    //retrofit
    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_MOSHI_CONVERTER)
    implementation(Deps.RETROFIT_RX_ADAPTER)
    implementation(project(ModuleDeps.LIBRARY_RX_ERROR_ADAPTER))

    implementation(Deps.THREE_TEN_ABP)

    //work manager
    implementation(Deps.WORK_MANAGER)
    implementation(Deps.WORK_MANAGER_KTX)
    implementation(Deps.RX_WORK)

    //http logging
    implementation(Deps.OK_HTTP_LOGGING_INTERCEPTOR)

    //OAuth2Token
    implementation(project(ModuleDeps.LIBRARY_OAUTH2TOKEN))

    implementation(project(ModuleDeps.DOMAIN))

    //dagger
    implementation(Deps.DAGGER_RUNTIME)
    kapt(Deps.DAGGER_COMPILER)

    //room
    implementation(Deps.ROOM_RUNTIME)
    implementation(Deps.ROOM_RX)
    kapt(Deps.ROOM_COMPILER)

    //testing
    implementation(Deps.Testing.JUNIT)
    implementation(Deps.Testing.MOCKITO_KOTLIN)
    implementation(Deps.Testing.MOCKITO_INLINE)
}