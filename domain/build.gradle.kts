
plugins {
    id("touristnews.jvm.library")
}
dependencies {
    implementation(libs.coroutines.core)
    // Paging 3
    implementation(libs.androidx.paging.common.ktx)
}