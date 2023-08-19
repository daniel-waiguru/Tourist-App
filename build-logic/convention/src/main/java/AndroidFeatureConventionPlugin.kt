
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("touristnews.android.library")
                apply("touristnews.android.hilt")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {

                add("implementation", project(":designsystem"))
                add("implementation", project(":data"))
                add("implementation", project(":domain"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())


                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}