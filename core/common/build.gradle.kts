plugins {
    alias(libs.plugins.traveltaoyuanlite.android.library)
    alias(libs.plugins.traveltaoyuanlite.android.library.compose)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
}

android {
    namespace = "com.wei.traveltaoyuanlite.core.common"

    defaultConfig {
        testInstrumentationRunner = "com.wei.traveltaoyuanlite.core.testing.TtlTestRunner"
    }
}

dependencies {
    // LifeCycle
    implementation(libs.androidx.lifecycle.runtimeCompose)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(projects.core.testing)
    // For flow test
    testImplementation(libs.turbine)

    androidTestImplementation(projects.core.testing)
}
