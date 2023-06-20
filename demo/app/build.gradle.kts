plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val STUDY_GUIDE_SERVICE_URL: String by project

android {
    namespace = "dev.goobar.advancedandroiddemo"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.goobar.advancedandroiddemo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "STUDY_GUIDE_SERVICE_URL", "\"$STUDY_GUIDE_SERVICE_URL\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ksp {
    arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.30.1")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.28.0")

    implementation("androidx.room:room-runtime:2.4.2")
    ksp("androidx.room:room-compiler:2.4.2")
    implementation("androidx.room:room-ktx:2.4.2")
    implementation("net.zetetic:android-database-sqlcipher:4.5.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.44")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {

    override fun asArguments(): Iterable<String> {
        return listOf("room.schemaLocation=${schemaDir.path}")
    }
}