@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.hilt")
    id("touristnews.android.room")
}

android {
    namespace = "com.danielwaiguru.touristnews.data"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":database"))
    // Networking
    implementation(libs.bundles.network)
    // Paging
    implementation(libs.androidx.paging.runtime)

    // Chucker
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker)

    testImplementation(project(":testing"))
}