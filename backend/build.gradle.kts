import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.talosdx"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val kotlinVersion: String = plugins.getPlugin(KotlinPluginWrapper::class.java).pluginVersion
val kotlinLoggingVersion by extra("3.0.4")
val kotlinSerializationJsonVersion by extra("1.4.1")
val testContainersVersion by extra("1.17.6")
val bcprovJdk15onVersion by extra("1.70")

repositories { mavenCentral() }

plugins {
    val kotlinVersion = "1.8.0"
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.convert") version "2.4.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
}
dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testContainersVersion")}")
    }
}

dependencies {
    api(project(":common"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("com.fasterxml.jackson.core")
        exclude("com.fasterxml.jackson.module")
        exclude("com.fasterxml.jackson.datatype")
    }

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")


    //argon2
    implementation("org.bouncycastle:bcprov-jdk15on:$bcprovJdk15onVersion")

    //database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")

    //logging, monitoring, etc
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    //don't need now
    //implementation("org.springframework.boot:spring-boot-starter-actuator")

    //devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    //test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

extra["snippetsDir"] = file("build/generated-snippets")
ext["kotlin.version"] = kotlinVersion

configurations.compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
            jvmTarget = java.sourceCompatibility.majorVersion
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
    val taskTest = test {
        project.property("snippetsDir")!!.let { outputs.dir(it) }
    }

    asciidoctor {
        project.property("snippetsDir")!!.let { inputs.dir(it) }
        dependsOn(taskTest)
    }
}