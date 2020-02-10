plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Deps.KOTLIN)
    implementation(Deps.RX_KOTLIN)
    implementation(Deps.JAVAX_INJECT)
    implementation(Deps.THREE_TEN_ABP)
    implementation(Deps.Testing.JUNIT)
    implementation(Deps.Testing.MOCKITO_KOTLIN)
    implementation(Deps.Testing.MOCKITO_INLINE)

}