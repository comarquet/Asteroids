group = "com.example"
version = "1.0.0"

plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("org.openjfx:javafx-controls:21:win")
    implementation("org.openjfx:javafx-controls:21:linux")
    implementation("org.openjfx:javafx-controls:21:mac")
    implementation("org.openjfx:javafx-fxml:21:win")
    implementation("org.openjfx:javafx-fxml:21:linux")
    implementation("org.openjfx:javafx-fxml:21:mac")
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.example.AsteroidsApplication")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "com.example.Launcher"
    }
    from({
        configurations.runtimeClasspath.get().flatMap {
            if (it.isDirectory) listOf(it) else zipTree(it).files
        }
    })
}

