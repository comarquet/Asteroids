# Asteroids

Welcome to **Asteroids**! This project is powered by Gradle, making it easy to build, test, and run. Below, you'll find detailed instructions to help you get started quickly, whether you're using Linux, macOS, or Windows.

## Table of Contents

- [Getting Started](#getting-started)
- [Building the Project](#building-the-project)
- [Running the Project](#running-the-project)
- [Testing the Project](#testing-the-project)
- [Detailed Build Information](#detailed-build-information)

## Getting Started

Before anything else, clone the Git repository to your local machine and navigate to the project directory:

```sh
git clone https://github.com/comarquet/Asteroids.git
cd Asteroids
```

All commands provided below will use the Gradle Wrapper (`./gradlew` for Linux/macOS and `gradlew.bat` for Windows). This ensures that the correct version of Gradle is used.

### Building the Project

To compile and build the project, run:

```sh
./gradlew build
```

This command will compile all source code, run static analysis, and create the required JAR files in the `build` directory.

### Running the Project

To run the project, execute the following command:

```sh
./gradlew run
```

This will start the application, which typically runs a main method defined in your project's main class.

### Testing the Project

To run all tests in the project, use:

```sh
./gradlew test
```

This command will:
- Compile the test source files.
- Run all tests located in `src/test/java` (or equivalent test directories).
- Generate a test report in `build/reports/tests/test/index.html`.

### Detailed Build Information

If you want to see more detailed information about what Gradle tasks are being executed, use the `--info` flag with the `build` command:

```sh
./gradlew build --info
```

This flag provides detailed logs about the build process.
