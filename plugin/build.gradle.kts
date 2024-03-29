plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.plugin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.plugin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
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

tasks.register("packageApkTransform") {
    dependsOn(":plugin:assembleDebug")
    doLast {
        copy {
            from("${project.buildDir}/outputs/apk/debug/plugin-debug.apk")
            into("${project.rootProject.projectDir}/app/src/main/assets")
            rename("plugin-debug.apk", "plugin1.apk")
            print("💥💥💥")
        }
    }
}






dependencies {

    implementation(project(":plugin_scheme"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    //fixme 经过测试约束布局在插件中使用会发生崩溃
    //java.lang.NullPointerException: Attempt to invoke virtual method 'int androidx.constraintlayout.core.widgets.ConstraintAnchor.getFinalValue()' on a null object reference
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}