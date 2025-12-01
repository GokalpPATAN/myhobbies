plugins {
    id("com.patan.android.feature")
    id("com.patan.android.sub.hilt")
    id("com.patan.android.firebase")
    id("com.patan.android.library.compose")
}

android {
    namespace = "com.patan.myhobbies.feature.home.presentation"
    hilt.enableAggregatingTask = true
}

dependencies {

    // Projects - Core
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.designsystem)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.play.services.location)
    implementation(libs.play.services.auth)

    // Projects - Domain
    implementation(projects.feature.home.domain)
    implementation(libs.play.services.location)
    implementation(libs.firebase.auth)
}