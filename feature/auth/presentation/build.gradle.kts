plugins {
    id("com.patan.android.feature")
    id("com.patan.android.sub.hilt")
    id("com.patan.android.library.compose")
}

android {
    namespace = "com.patan.myhobbies.feature.auth.presentation"
    hilt.enableAggregatingTask = true
}

dependencies {
    implementation(projects.feature.auth.data) // !!!!!! MİMARİ AÇIDAN SIKINTILI KALDIRILMASI GEREK KISA SÜRELİĞİNE EKLENDŞ
    implementation(projects.feature.auth.domain)
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
}