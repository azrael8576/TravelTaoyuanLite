plugins {
    alias(libs.plugins.traveltaoyuanlite.android.feature)
    alias(libs.plugins.traveltaoyuanlite.android.library.compose)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
}

android {
    namespace = "com.wei.traveltaoyuanlite.feature.attractions"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.appcompat)
    // WindowSizeClass
    implementation(libs.androidx.compose.material3.windowSizeClass)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}