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
version = "0.0.3"

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

repositories {
    google()
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://maven.pkg.github.com/JustBurrow/packages")

        credentials {
            username = properties["github.actor"] as String?
                ?: System.getenv("GITHUB_ACTOR")
            password = properties["github.token"] as String?
                ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    withSourcesJar()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

dependencies {
    api(libs.kr.lul.semantic.version)
    api(libs.spring.boot.starter.data.jpa)

    testImplementation(libs.com.mysql.connector)
    testImplementation(libs.kotest.extensions.spring)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotlin.logging)
    testImplementation(libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
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

            groupId = "${project.group}"
            artifactId = rootProject.name
            version = "${project.version}"

            pom {
                scm {
                    url = "https://github.com/JustBurrow/support-spring-data-jpa"
                }
            }
        }
    }
}
