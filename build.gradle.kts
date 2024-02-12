import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    java
    `maven-publish`

    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "kr.lul.support"
version = "0.0.1"

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/JustBurrow/packages")
            credentials {
                username = properties["github.actor"] as String?
                    ?: System.getenv("GITHUB_ACTOR")
                password = properties["github.token"] as String?
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])

            groupId = "kr.lul.support"
            artifactId = rootProject.name
            version = "0.0.1"

            pom {
                scm {
                    url = "https://github.com/JustBurrow/support-spring-data-jpa"
                }
            }
        }
    }
}
