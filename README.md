# gsync

![CI](https://github.com/averak/gsync/workflows/CI/badge.svg)
![version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue.svg)

This is a multi-tenancy game server for MO games.

This component provides only reusable features that can be used across various games, and individual logic cannot be embedded.

## Features

* Player authentication / authorization
* Friend
* Match making
* Realtime messaging
* And more

## Develop

### Environments

* Java OpenJDK 17
* Kotlin 1.9
* Quarkus 3.4
* TiDB 7.1

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
docker compose up -d
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```
