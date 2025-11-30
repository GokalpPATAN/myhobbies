plugins {
    id("com.patan.android.feature")
    id("com.patan.android.sub.hilt")
    id("com.patan.android.library.compose")
}

android {
    namespace = "com.patan.myhobbies.feature.auth"
    hilt.enableAggregatingTask = true
}

dependencies{

    // Projects - Core
    implementation(projects.core.common)

}