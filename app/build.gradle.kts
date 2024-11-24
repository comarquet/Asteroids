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
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("org.openjfx:javafx-controls:21:win")
    implementation("org.openjfx:javafx-controls:21:linux")
    implementation("org.openjfx:javafx-controls:21:mac")
    implementation("org.openjfx:javafx-fxml:21:win")
    implementation("org.openjfx:javafx-fxml:21:linux")
    implementation("org.openjfx:javafx-fxml:21:mac")
    testImplementation("org.testfx:testfx-core:4.0.18")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testImplementation("org.testfx:testfx-junit5:4.0.18")
    testImplementation("org.assertj:assertj-core:3.26.3")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly ("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.36")
    implementation("org.apache.logging.log4j:log4j-bom:2.24.2")
    implementation("org.apache.logging.log4j:log4j-api:2.24.2")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.24.2")
    runtimeOnly("org.apache.logging.log4j:log4j-layout-template-json:2.24.2")
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
    mainClass.set("com.project.AsteroidsApplication")
}

// It is necessary to create a JAR file instead of using modules structure
// because others old dependencies (Apache Commons Math) don't support modules
tasks.named<Test>("test") {
    useJUnitPlatform()
}
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

/*
 * When the main application extends Application and also contains a main method,
 * LauncherHelper checks for the 'javafx.graphics' module, and if it is not present,
 * as a named module, the launch is aborted. As a result, having the JavaFX libraries
 * as JARs on the classpath is not sufficient and will cause the application launch to fail.
 * A workaround to make it work is to add a new Main class (Launcher.java) that will be the
 * main class of the project, and that class will be the one that calls the JavaFX Application class.
 */
    manifest {
        attributes["Main-Class"] = "com.project.Launcher"
    }
    from({
        configurations.runtimeClasspath.get().flatMap {
            if (it.isDirectory) listOf(it) else zipTree(it).files
        }
    })
}

