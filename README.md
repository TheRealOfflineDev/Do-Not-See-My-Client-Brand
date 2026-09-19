# Do Not See My Client Brand

<p align="center">
  <img width="256" height="256" alt="dnsmcb" src="https://github.com/user-attachments/assets/25209aa8-132a-4645-ba19-e3ccbe3d934c" />

</p>

<p align="center">
  A client-side Fabric mod that lets you customize the Minecraft client brand sent to servers.
</p>

<p align="center">
  <a href="https://modrinth.com/mod/do-not-see-my-client-brand">
    <img src="https://img.shields.io/badge/Modrinth-Do%20Not%20See%20My%20Client%20Brand-1bd96a?logo=modrinth&logoColor=white" alt="Modrinth">
  </a>
  <img src="https://img.shields.io/badge/Minecraft-1.21.11-62b47a?logo=minecraft&logoColor=white" alt="Minecraft 1.21.11">
  <img src="https://img.shields.io/badge/Fabric-1.21.11-dbd0b4?logo=fabric&logoColor=black" alt="Fabric">
</p>

## About

**Do Not See My Client Brand** is a client-side Fabric mod for Minecraft **1.21.11**.

It lets you customize the client brand sent to Minecraft servers through an in-game configuration screen.

The mod is client-side and does not need to be installed on a server.

## Features

* Change the client brand sent to servers
* In-game configuration screen
* Reset the brand to the current client brand
* Client-side
* Fabric based
* Mod Menu integration

## Requirements

### Minecraft

* Minecraft **1.21.11**
* Fabric Loader
* Fabric API

### Dependencies

| Dependency | Required        |
| ---------- | --------------- |
| Fabric API | Yes             |
| Mod Menu   | **17 or newer** |

Fabric API is required to run the mod.

Mod Menu **17+** is required for the mod's Mod Menu integration.

## Installation

1. Install **Minecraft 1.21.11**.
2. Install **Fabric Loader** for Minecraft 1.21.11.
3. Install **Fabric API**.
4. Install **Mod Menu 17 or newer**.
5. Download the latest release of **Do Not See My Client Brand**.
6. Put the mod `.jar` into your Minecraft `mods` folder.
7. Start Minecraft using your Fabric installation.

The mod only needs to be installed on the client.

## Building From Source

### Requirements

You need:

* **Java 21**
* Git
* Internet access for Gradle to download dependencies

You do **not** need to install Gradle manually.

The repository includes the Gradle Wrapper:

```text
gradle/
gradlew
gradlew.bat
```

### Clone the repository

```bash
git clone https://github.com/TheRealOfflineDev/Do-Not-See-My-Client-Brand.git
cd Do-Not-See-My-Client-Brand
```

### Check Java

Run:

```bash
java -version
```

The project requires **Java 21**.

You can also check which Java Gradle is using.

On Windows:

```bat
gradlew.bat --version
```

On Linux/macOS:

```bash
./gradlew --version
```

Make sure the JVM shown by Gradle is Java 21.

### Build the mod

#### Windows

```bat
gradlew.bat build
```

#### Linux/macOS

```bash
./gradlew build
```

Gradle will download the project's required dependencies automatically.

If the build succeeds, the compiled mod will be located in:

```text
build/libs/
```

## Running Minecraft From the Development Environment

You can launch a development Minecraft client directly through Gradle.

### Windows

```bat
gradlew.bat runClient
```

### Linux/macOS

```bash
./gradlew runClient
```

This launches the Minecraft development client with the mod loaded.

## Generating Sources

If your IDE does not show the Minecraft source code correctly, run:

### Windows

```bat
gradlew.bat genSources
```

### Linux/macOS

```bash
./gradlew genSources
```

After it finishes, reload the Gradle project in your IDE.

## Cleaning the Project

If you need to remove generated build files:

### Windows

```bat
gradlew.bat clean
```

### Linux/macOS

```bash
./gradlew clean
```

Then build again:

```bat
gradlew.bat build
```

## Project Structure

```text
Do-Not-See-My-Client-Brand/
├── .github/
├── gradle/
│   └── wrapper/
├── src/
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

Generated files and directories such as these are ignored:

```text
.gradle/
build/
bin/
.vscode/
run/
screendump/
```

The Gradle Wrapper is **not** ignored.

## Modrinth

[Modrinth](https://modrinth.com/mod/do-not-see-my-client-brand)

Project slug:

```text
do-not-see-my-client-brand
```

## Source Code

[GitHub Repository](https://github.com/TheRealOfflineDev/Do-Not-See-My-Client-Brand)

## Minecraft Version

This project currently targets:

```text
Minecraft 1.21.11
```

## License

See [`LICENSE`](LICENSE) for the license used by this project.
