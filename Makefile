.PHONY: build
build:
	./gradlew build -x test

.PHONY: test
test:
	./gradlew test jacocoTestReport

.PHONY: lint
lint:
	./gradlew spotlessCheck

.PHONY: format
format:
	./gradlew spotlessApply

.PHONY: codegen
codegen:
	./gradlew mbGenerate
	./gradlew spotlessApply

.PHONY: db-migrate
db-migrate:
	./gradlew flywayMigrate

.PHONY: db-clean
db-clean:
	./gradlew flywayClean

.PHONY: check_dependencies
check_dependencies:
	./gradlew dependencyUpdates -Drevision=release

.PHONY: update_dependencies
update_dependencies:
	./gradlew versionCatalogUpdate
