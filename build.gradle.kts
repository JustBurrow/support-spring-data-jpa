plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "kr.lul.support"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)

    testImplementation(rootProject.libs.kotest.extensions.spring)
    testImplementation(rootProject.libs.kotest.runner.junit5)
    testImplementation(rootProject.libs.kotlin.logging)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
