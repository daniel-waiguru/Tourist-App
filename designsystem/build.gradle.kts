@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.touristnews.designsystem"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.shimmer)
}