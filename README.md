# Do Not See My Client Brand

<p align="center">
  <img src="icon.png" width="128" alt="Do Not See My Client Brand">
</p>

<p align="center">
  A client-side Fabric mod that lets you customize the Minecraft client brand sent to servers.
</p>

---

## About

**Do Not See My Client Brand** is a client-side Minecraft Fabric mod for **Minecraft 1.21.11**.

The mod lets you change the client brand that Minecraft reports to servers through a simple in-game interface.

It does not require anything to be installed on the server.

## Requirements

### Minecraft

* Minecraft **1.21.11**
* Fabric Loader
* Fabric API

### Mod Dependencies

* **Fabric API**
* **Mod Menu 17 or newer**

Fabric API is required by the mod.

Mod Menu is used to access the mod's configuration screen.

## Development Requirements

To build the project from source, you need:

* **JDK 21**
* Git
* An internet connection for Gradle to download dependencies
* An IDE such as IntelliJ IDEA or Visual Studio Code

Minecraft 1.21.11 development requires JDK 21.

You do **not** need to install Gradle manually because this repository includes the Gradle Wrapper.

## Building From Source

### 1. Clone the repository

```bash
git clone YOUR_REPOSITORY_URL
cd Do-Not-See-My-Client-Brand
```

### 2. Check Java

Make sure Java 21 is installed:

```bash
java -version
```

You should see Java 21.

If Gradle is using the wrong Java installation, check:

```bash
gradlew.bat --version
```

On Linux/macOS:

```bash
./gradlew --version
```

Check that the JVM shown is Java 21.

### 3. Build the mod

#### Windows

```bat
gradlew.bat build
```

#### Linux / macOS

```bash
./gradlew build
```

Gradle will download the required Minecraft, Fabric, mappings, and other development dependencies automatically.

### 4. Find the built JAR

After a successful build, the mod JAR will be in:

```text
build/libs/
```

The generated JAR can then be placed in the `mods` folder of a Minecraft 1.21.11 Fabric installation.

## Running the Development Client

You can launch a development Minecraft client directly from Gradle.

### Windows

```bat
gradlew.bat runClient
```

### Linux / macOS

```bash
./gradlew runClient
```

This starts a development instance with the mod loaded.

## Generating Minecraft Sources

If your IDE does not have the Minecraft sources available, run:

### Windows

```bat
gradlew.bat genSources
```

### Linux / macOS

```bash
./gradlew genSources
```

After the task finishes, refresh/reload the Gradle project in your IDE.

Fabric's development documentation recommends `genSources` for generating the Minecraft sources used while developing.

## Cleaning the Build

If you run into Gradle or generated-file issues, clean the project:

### Windows

```bat
gradlew.bat clean
```

### Linux / macOS

```bash
./gradlew clean
```

Then build again:

```bat
gradlew.bat build
```

## Gradle Wrapper

This repository includes:

```text
gradlew
gradlew.bat
gradle/
```

These files are intentionally included.

They allow the project to use its configured Gradle version without requiring contributors to install Gradle separately.

## Project Structure

```text
.
├── gradle/
├── src/
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── LICENSE
├── README.md
└── .gitignore
```

Generated directories such as `build/` and `.gradle/` should not be committed to the repository.

## Modrinth

**Project:** Do Not See My Client Brand

**Slug:**

```text
do-not-see-my-client-brand
```

The Modrinth project is intended for the released Minecraft 1.21.11 builds.

## Version Support

| Minecraft      | Support                       |
| -------------- | ----------------------------- |
| 1.21.11        | Supported                     |
| Other versions | Not supported by this release |

This project currently targets **Minecraft 1.21.11** specifically.

Minecraft 26.1+ is a separate version line and should not be treated as compatible with a 1.21.11 Fabric mod. Fabric's porting documentation notes that mods from 1.21.11 and older do not work on 26.1.

## License

See [`LICENSE`](LICENSE) for the license of this project.
