plugins {
    id("java")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

group = "com.generation"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}