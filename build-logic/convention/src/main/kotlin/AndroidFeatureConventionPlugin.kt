import com.android.build.gradle.LibraryExtension
import com.wei.traveltaoyuanlite.configureGradleManagedDevices
import com.wei.traveltaoyuanlite.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(plugin = "traveltaoyuanlite.android.library")
                apply(plugin = "traveltaoyuanlite.android.hilt")
                apply(plugin = "androidx.navigation.safeargs.kotlin")
                apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "com.wei.traveltaoyuanlite.core.testing.traveltaoyuanliteTestRunner"
                }
                configureGradleManagedDevices(this)
            }

            dependencies {
                "implementation"(project(":core:designsystem"))

                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.tracing.ktx").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}
