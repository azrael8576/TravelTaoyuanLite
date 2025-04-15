plugins {
    alias(libs.plugins.traveltaoyuanlite.android.library)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
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
}
