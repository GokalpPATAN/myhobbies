plugins {
    id("com.patan.android.feature")
    id("com.patan.android.sub.hilt")
    id("com.patan.android.library.compose")
}

android {
    namespace = "com.patan.feature.qible.presentation"
    hilt.enableAggregatingTask = true
}

dependencies {

    // Projects - Core
    implementation(projects.core.common)

    // Projects - Domain
    implementation(projects.feature.qible.domain)
    implementation(libs.play.services.location)

}