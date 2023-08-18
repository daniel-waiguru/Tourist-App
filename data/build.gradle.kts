@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.room")
    id("touristnews.android.hilt")
}

android {
    namespace = "com.danielwaiguru.touristnews.data"
}

dependencies {
    implementation(project(":domain"))
    // Networking
    implementation(libs.bundles.network)
    // Paging
    implementation(libs.androidx.paging.runtime)

    // Chucker
    debugImplementation(libs.chucker.debug)
    implementation(libs.chucker)
}