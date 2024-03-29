import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    id("org.unbroken-dome.test-sets") version "3.0.1"
    jacoco
}

group = "cabanas.garcia.ismael"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

testSets {
    create("integrationTest") {
        dirName = "integration-test"
    }
    create("acceptanceTest") {
        dirName = "acceptance-test"
    }
}

dependencies {
    // Spring dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("org.postgresql:postgresql:42.3.0")

    //flyway
    implementation("org.flywaydb:flyway-core:6.5.7")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.3")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.3")
    testImplementation("org.mockito:mockito-inline:2.23.0")
    testImplementation("org.mockito:mockito-junit-jupiter:2.27.0")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testImplementation("org.assertj:assertj-db:2.0.2")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

    // Kotlin test dependencies
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    testImplementation("io.mockk:mockk:1.10.5")

    // Integration Test dependencies
    val integrationTestImplementation by configurations
    add("integrationTestImplementation", sourceSets["test"].output)
    integrationTestImplementation("org.testcontainers:junit-jupiter:1.16.2")
    integrationTestImplementation("org.testcontainers:postgresql:1.16.2")

    // Acceptance Test dependencies
    val acceptanceTestImplementation by configurations
    add("acceptanceTestImplementation", sourceSets["test"].output)
    acceptanceTestImplementation("io.rest-assured:spring-mock-mvc:3.1.1")
    acceptanceTestImplementation("org.testcontainers:testcontainers:1.15.3")
    acceptanceTestImplementation("org.awaitility:awaitility-kotlin:4.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks {
    withType(Test::class).configureEach { useJUnitPlatform() }
    "jacocoTestReport"(JacocoReport::class) {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }
    "check" { dependsOn("acceptanceTest", "jacocoTestReport") }
}
