plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish") // Chỉ sử dụng maven-publish, bỏ java-platform
}

android {
    namespace = "com.prox.bom"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenBom") {
            groupId = "com.github.datcorona"
            artifactId = "proxbom"
            version = "1.0.1"

//            afterEvaluate {
//                from(components["release"]) // Thay "release" bằng "default"
//            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    constraints {
        api("androidx.room:room-compiler:2.5.0")
        api("androidx.room:room-ktx:2.5.0")
        api(libs.retrofit.converter)
        api(libs.glide)
        api(libs.hilt.android.v247)
        api(libs.circularSeekBar)
        api(libs.rxjava)
    }
}