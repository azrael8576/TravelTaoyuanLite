plugins {
    alias(libs.plugins.traveltaoyuanlite.android.library)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.wei.traveltaoyuanlite.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.network)
    api(projects.core.model)

    // KotlinxSerializationJson
    implementation(libs.kotlinx.serialization.json)
}
