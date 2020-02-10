plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.TELESCOPE_VERSIONING_PLUGIN)
    id(GradlePluginId.FIREBASE_CRASHLYTIC_PLUGIN)
    kotlin(GradlePluginId.KOTLIN_ANDROID)
    kotlin(GradlePluginId.KOTLIN_KAPT)
    kotlin(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.KOTLIN_SAFE_ARGS)
}

androidGitVersion {
    baseCode = 100000
    codeFormat = "MNNPPP"
    format = "%tag%%.count%%-branch%"
    hideBranches = listOf("develop")
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        versionCode = androidGitVersion.code()
        versionName = androidGitVersion.name()
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        javaCompileOptions {
            annotationProcessorOptions {
                "dagger.gradle.incremental" to "true"
            }
        }
    }
    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            manifestPlaceholders = mapOf(Constants.USES_CLEAR_TEXT to false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create(BuildType.STAGING) {
            signingConfig = signingConfigs.getByName(BuildType.DEBUG)
            isMinifyEnabled = BuildTypeStaging.isMinifyEnabled
            manifestPlaceholders = mapOf(Constants.USES_CLEAR_TEXT to true)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            manifestPlaceholders = mapOf(Constants.USES_CLEAR_TEXT to true)
        }
    }

    lintOptions {
        // By default lint does not check test sources, but setting this option means that lint will not even parse them
        isIgnoreTestSources = true
        isAbortOnError = false
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
}

androidExtensions { isExperimental = true }

dependencies {
    implementation(Deps.KOTLIN)
    implementation(Deps.RX_KOTLIN)
    implementation(Deps.RX_RELAY)
    implementation(Deps.RX_ANDROID)
    implementation(Deps.RX_PAPARAZZO)

    implementation(Deps.FLEX_BOX)

    implementation(Deps.DAGGER_RUNTIME)
    kapt(Deps.DAGGER_COMPILER)

    implementation(Deps.TIMBER)

    implementation(Deps.FIREBASE_ANALYTICS)
    implementation(Deps.FIREBASE_CRASHLYTICS)

    implementation(Deps.THREE_TEN_ABP)

    implementation(Deps.SWIPE_TO_REFRESH)

    implementation(project(ModuleDeps.DOMAIN))
    implementation(project(ModuleDeps.DATA))

    implementation(Deps.APPCOMPAT)
    implementation(Deps.LIFECYCLE_EXTENSION)
    implementation(Deps.MATERIAL)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation(Deps.CORE_KTX)
    implementation(Deps.RX_PAPARAZZO)

    implementation(Deps.NAVIGATION_FRAGMENT)
    implementation(Deps.NAVIGATION_UI)
    implementation(Deps.GLIDE)
    kapt(Deps.GLIDE_COMPILER)
    addTestDependencies()
}
