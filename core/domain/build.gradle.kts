plugins {
    alias(libs.plugins.traveltaoyuanlite.android.library)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
}

android {
    namespace = "com.wei.traveltaoyuanlite.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    testImplementation(projects.core.testing)
}
