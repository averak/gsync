# gsync

![CI](https://github.com/averak/gsync/workflows/CI/badge.svg)

This is a multi-tenancy game server for MO games.

This component provides only reusable features that can be used across various games, and individual logic cannot be embedded.

## Features

* Player authentication / authorization
* Friend
* Match making
* Realtime messaging
* And more

## Develop

This document only contains minimal setup instructions to launch the application.

For more information, see [Makefile](./Makefile).

### Environments

* Java OpenJDK 17
* Kotlin 1.9
* Spring Boot 3.2
* Cloud Spanner
* Redis

### Running the application in dev mode

You can run your application in dev mode.

```shell
docker compose up -d
./gradlew bootRun
```

### Packaging and running the application

The application can be packaged.

```shell
make build
```

It produces the `gsync.jar` file in the `build/libs/` directory.

The application is now runnable using `java -jar build/libs/gsync.jar`.

### Check Dependency updates

Follow steps [littlerobots/version-catalog-update-plugin](https://github.com/littlerobots/version-catalog-update-plugin?tab=readme-ov-file#interactive-mode) to update outdated dependencies.

```shell
./gradlew versionCatalogUpdate --interactive

# Check the execution plan automatically generated in `gradle/libs.version.updates.toml` and apply if there is no problem.
./gradlew versionCatalogApplyUpdates
```
