@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.feature")
    id("touristnews.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.touristnews.presentation"
}
dependencies {
    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)
    implementation(libs.androidx.paging.compose)
    implementation(project(":database"))
}