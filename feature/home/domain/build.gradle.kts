plugins {
    id("com.patan.android.feature")
}

android {
    namespace = "com.patan.myhobbies.feature.home.domain"
    hilt.enableAggregatingTask = true
}

dependencies {

    // Projects - Core
    implementation(projects.core.common)
    implementation(libs.javax.inject)

}