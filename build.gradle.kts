import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.diffplug.spotless") version "6.20.0"
}
true // Needed to make the Suppress annotation work for the plugins block

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        android.set(true)
        ignoreFailures.set(false)
        // disabledRules.set(listOf("no-wildcard-imports", "final-newline", "import-ordering", "package-name"))
        reporters {
            ReporterType.CHECKSTYLE
        }
        filter {
            exclude { element -> element.file.path.contains("generated/") }
        }
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("${project.rootDir}/build-logic/**/*.kt")
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
    }
}