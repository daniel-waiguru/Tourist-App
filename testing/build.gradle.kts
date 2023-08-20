@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.hilt")
    id("touristnews.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.touristnews.testing"
}

dependencies {
    api(libs.junit4)
    api(libs.junit4.ext)
    api(libs.turbine)
    api(libs.mock.android)
    api(libs.mock.agent)
    api(libs.google.truth)
    api(libs.coroutines.test)
    api(libs.espresso.core)
    api(libs.androidx.arch.core.testing)
    api(libs.robolectric)
    implementation(project(":domain"))
    implementation(libs.androidx.paging.common.ktx)
}