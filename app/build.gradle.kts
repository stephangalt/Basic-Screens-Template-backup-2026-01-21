import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.oss.licenses)
    alias(libs.plugins.dependency.license.report)
}

// Read version properties
val versionProps = Properties()
versionProps.load(project.rootProject.file("gradle.properties").inputStream())

android {
    namespace = "org.smithbros.basicscreenstemplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.smithbros.basicscreenstemplate"
        minSdk = 24
        targetSdk = 36
        versionCode = (versionProps["VERSION_CODE"] as String).toInt()
        versionName = versionProps["VERSION_NAME"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
        buildConfig = true // <-- Enable BuildConfig
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets", "src/main/assets")
            }
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

dependencies {

    // --- KEEP: Core Android & Compose UI ---
    // These are essential for any modern Android app using Jetpack Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.material:material-icons-extended")

    // --- KEEP: Core Navigation ---
    // Essential for navigating between the template screens.
    implementation(libs.androidx.navigation.compose)

    // --- KEEP: Material3 Android ---
    // Provides additional Material Design components.
    implementation(libs.androidx.material3.android)

    // KEEP: This is a good 'kick-starter' addition.
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.kotlinx.coroutines.android)


    // --- KEEP: Testing Dependencies ---
    // A good template should come with a solid testing setup.
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

tasks.register("copyLicenseReport", Copy::class) {
    dependsOn(tasks.named("generateLicenseReport"))
    from("$buildDir/reports/dependency-license")
    into("src/main/assets")
    include("index.html")
    rename { "open_source_licenses.html" }
}

tasks.named("preBuild") {
    dependsOn(tasks.named("copyLicenseReport"))
}
