plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.dagger)
    ksp(libs.google.dagger.compiler)
}
