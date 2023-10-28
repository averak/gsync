.PHONY: build
build:
	./gradlew build -x test

.PHONY: build_native
build_native:
	./gradlew build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true

.PHONY: run_application
run_application:
	./gradlew quarkusDev

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
