import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
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

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("com.h2database:h2")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    testImplementation("org.mockito:mockito-inline:2.23.0")
    testImplementation("org.mockito:mockito-junit-jupiter:2.27.0")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

    // Kotlin test dependencies
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    testImplementation("io.mockk:mockk:1.10.5")

    // Integration Test dependencies
    val integrationTestImplementation by configurations
    add("integrationTestImplementation", sourceSets["test"].output)

    // Acceptance Test dependencies
    val acceptanceTestImplementation by configurations
    add("acceptanceTestImplementation", sourceSets["test"].output)
    acceptanceTestImplementation("io.rest-assured:spring-mock-mvc:3.1.1")
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
