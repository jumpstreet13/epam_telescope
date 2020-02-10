plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Deps.KOTLIN)
    implementation(Deps.RX_KOTLIN)
    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_RX_ADAPTER)
    testImplementation(Deps.Testing.JUNIT)
}
