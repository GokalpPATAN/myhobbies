plugins {
    id("com.patan.android.feature")
}

android {
    namespace = "com.patan.myhobbies.feature.auth.domain"
    hilt.enableAggregatingTask = true
}

dependencies {

    // Projects - Core
    implementation(projects.core.common)
    implementation(libs.javax.inject)

}