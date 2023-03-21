import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.talosdx"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val kotlinVersion: String = plugins.getPlugin(org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper::class.java).pluginVersion
val kotlinLoggingVersion by extra("3.0.4")
val testContainersVersion by extra("1.17.6")

val bcprovJdk15onVersion by extra("1.70")
val telegramBotApiVersion by extra("2.5.1")

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
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
    implementation("org.bouncycastle:bcprov-jdk15on:$bcprovJdk15onVersion")
    implementation("eu.vendeli:telegram-bot:$telegramBotApiVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    //database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")

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