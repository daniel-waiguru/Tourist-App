plugins {
    `kotlin-dsl`
}
group = "com.danielwaiguru.touristnews.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "touristnews.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidRoom") {
            id = "touristnews.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidFeature") {
            id = "touristnews.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "touristnews.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("jvmLibrary") {
            id = "touristnews.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
