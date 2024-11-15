# Asteroids

Welcome to **Asteroids**! This project is powered by Gradle, making it easy to build, test, and run. Below, you'll find detailed instructions to help you get started quickly, whether you're using Linux, macOS, or Windows.

## Table of Contents

- [Getting Started](#getting-started)
  - [Building the Project](#building-the-project)
  - [Running the Project](#running-the-project)
  - [Detailed Build Information](#detailed-build-information)
- [Contributing](#contributing)

## Getting Started

Before anything else, clone the Git repository to your local machine and navigate to the project directory:

```sh
git clone https://github.com/comarquet/Asteroids.git
cd Asteroids
```

All commands provided below will use the Gradle Wrapper (`./gradlew` for Linux/macOS and `gradlew.bat` for Windows). This ensures that the correct version of Gradle is used.

### Building the Project

To compile and build the project, run:

- **Linux/macOS**:

  ```sh
  ./gradlew build
  ```

- **Windows**:

  ```sh
  gradlew.bat build
  ```

This command will compile all source code, run static analysis, and create the required JAR files in the `build` directory.

If you want to see more detailed information about what Gradle tasks are being executed, use the `--info` flag with the `build` command:

- **Linux/macOS**:

  ```sh
  ./gradlew build --info
  ```

- **Windows**:

  ```sh
  gradlew.bat build --info
  ```

This flag provides detailed logs about the build process.

### Running the Project

To run the project, execute the following command:

- **Linux/macOS**:

  ```sh
  ./gradlew run
  ```

- **Windows**:

  ```sh
  gradlew.bat run
  ```

This will start the application, which typically runs a main method defined in your project's main class.

