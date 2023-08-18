@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("touristnews.android.library")
    id("touristnews.android.room")
    id("touristnews.android.hilt")
}

android {
    namespace = "com.danielwaiguru.touristnews.database"
}
dependencies {
    // Gson
    implementation(libs.gson)

    // Paging
    implementation(libs.androidx.paging.runtime)
}