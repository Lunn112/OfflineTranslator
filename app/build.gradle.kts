plugins { id("com.android.application"); id("org.jetbrains.kotlin.android"); id("org.jetbrains.kotlin.plugin.compose"); id("org.jetbrains.kotlin.kapt"); id("androidx.room") }
android { namespace = "com.offlinetranslator"; compileSdk = 35
    defaultConfig { applicationId = "com.offlinetranslator"; minSdk = 29; targetSdk = 35; versionCode = providers.exec { commandLine("git","rev-list","--count","HEAD") }.standardOutput.asText.get().trim().toIntOrNull() ?: 1; versionName = providers.environmentVariable("VERSION_NAME").orElse("0.1.0").get(); buildConfigField("String","GIT_COMMIT", "\"${providers.exec { commandLine("git","rev-parse","--short","HEAD") }.standardOutput.asText.get().trim()}\""); buildConfigField("String","BUILD_TIME", "\"${java.time.Instant.now()}\""); buildConfigField("String","GIT_BRANCH", "\"${providers.environmentVariable("GITHUB_REF_NAME").orElse("local").get()}\"") }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true; buildConfig = true }
    externalNativeBuild { cmake { path = file("src/main/cpp/CMakeLists.txt") } }
}
dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui"); implementation("androidx.compose.ui:ui-tooling-preview"); debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3"); implementation("androidx.activity:activity-compose:1.9.3"); implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.room:room-runtime:2.6.1"); implementation("androidx.room:room-ktx:2.6.1"); implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0"); kapt("androidx.room:room-compiler:2.6.1")
    implementation("com.microsoft.onnxruntime:onnxruntime-android:1.19.2")
    testImplementation("junit:junit:4.13.2")
}
