.PHONY: build
build:
	./gradlew quarkusBuild

.PHONY: build_native
build_native:
	./gradlew build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true

.PHONY: test
test:
	./gradlew test jacocoTestReport

.PHONY: lint
lint:
	./gradlew spotlessCheck

.PHONY: format
format:
	./gradlew spotlessApply

.PHONY: check_dependencies
check_dependencies:
	./gradlew dependencyUpdates -Drevision=release

.PHONY: update_dependencies
update_dependencies:
	./gradlew versionCatalogUpdate
