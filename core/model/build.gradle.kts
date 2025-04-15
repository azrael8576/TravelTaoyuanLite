plugins {
    alias(libs.plugins.traveltaoyuanlite.android.library)
    alias(libs.plugins.traveltaoyuanlite.android.library.compose)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.wei.traveltaoyuanlite.core.model"
}

dependencies {
    // For androidx.compose.runtime.Stable
    implementation(libs.androidx.compose.runtime)
}