import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

tasks
    .withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>()
    .configureEach {
        compilerOptions {
            jvmTarget.set(JVM_21)
            languageVersion.set(KotlinVersion.KOTLIN_2_0)
            apiVersion.set(KotlinVersion.KOTLIN_2_0)
            freeCompilerArgs.add("-Xcontext-receivers")
        }
    }

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(projects.database)
    implementation(projects.komokServerCommon)
    implementation(projects.komokAuthCommon)
    implementation(projects.komokTechLogging)
    implementation(projects.komokTechConfig)
    implementation(projects.komokTechDotenv)
    ksp(projects.komokTechDi)
    implementation(projects.komokTechDiLib)

    implementation(libs.kotlinx.coroutines.jdk8)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.hikari)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter)
    runtimeOnly(libs.junit.platform.launcher)
}
