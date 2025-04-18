plugins {
    alias(libs.plugins.traveltaoyuanlite.android.feature)
    alias(libs.plugins.traveltaoyuanlite.android.library.compose)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
}

android {
    namespace = "com.wei.traveltaoyuanlite.feature.home"
}

dependencies {
    implementation(projects.core.data)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}