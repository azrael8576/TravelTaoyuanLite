import com.wei.traveltaoyuanlite.TtlBuildType

plugins {
    alias(libs.plugins.traveltaoyuanlite.android.application)
    alias(libs.plugins.traveltaoyuanlite.android.application.compose)
    alias(libs.plugins.traveltaoyuanlite.android.application.flavors)
    alias(libs.plugins.traveltaoyuanlite.android.hilt)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.wei.traveltaoyuanlite"

    defaultConfig {
        applicationId = "com.wei.traveltaoyuanlite"
        /**
         * Version Code: AABCXYZ
         *
         * AA: API Level (35)
         *
         * BC: Supported screen sizes for this APK.
         * 12: Small to Normal screens
         * 34: Large to X-Large screens
         *
         * XYZ: App version (050 for 0.5.0)
         */
        versionCode = 3514000
        /**
         * SemVer major.minor.patch
         */
        versionName = "0.0.0"

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "com.wei.traveltaoyuanlite.core.testing.TtlTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = TtlBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = TtlBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)

    implementation(projects.feature.home)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    // Splashscreen
    implementation(libs.androidx.core.splashscreen)
    // Write trace events to the system trace buffer.
    implementation(libs.androidx.tracing.ktx)
    // LifeCycle
    implementation(libs.androidx.lifecycle.runtimeCompose)
    // WindowSizeClass
    implementation(libs.androidx.compose.material3.windowSizeClass)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    // Timber
    implementation(libs.timber)

    debugImplementation(projects.uiTestHiltManifest)
    // LeakCanary
    debugImplementation(libs.leakcanary)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    kspTest(libs.hilt.compiler)

    testImplementation(projects.core.testing)
    testImplementation(libs.accompanist.testharness)
    testImplementation(libs.hilt.android.testing)

    testDemoImplementation(libs.robolectric)
    testDemoImplementation(libs.roborazzi)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
}