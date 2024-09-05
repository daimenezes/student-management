configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    val junitVersion = "5.10.0"
    val slf4jVersion = "2.0.7"
    val lombokVersion = "1.18.34"

    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    implementation("jakarta.inject:jakarta.inject-api:2.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:5.13.0")
}