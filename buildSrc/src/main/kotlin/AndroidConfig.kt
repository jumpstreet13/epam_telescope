object AndroidConfig {
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val BUILD_TOOLS_VERSION = "29.0.2"

    const val VERSION_CODE = 2
    const val VERSION_NAME = "0.1.1"

    const val ID = "com.abocha.epam_telescope"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Constants {
    const val BASE_URL = "BASE_URL"
    const val USES_CLEAR_TEXT = "usesCleartextTraffic"
}

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
        const val STAGING = "staging"
    }

    val isMinifyEnabled: Boolean
    val baseUrl: String
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val baseUrl: String = "\"https://api.deezer.com/\""
}

object BuildTypeStaging : BuildType {
    override val isMinifyEnabled = false
    override val baseUrl: String = "\"https://api.deezer.com/\""
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val baseUrl: String = "\"https://api.deezer.com/\""
}
