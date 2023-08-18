@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.hilt")
    id("touristnews.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.touristnews.presentation"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}