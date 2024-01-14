.PHONY: build
build:
	./gradlew bootJar

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
	rm -r app/adapter/build/protoGen
	./gradlew generateProto
	./gradlew mbGenerate
	./gradlew spotlessApply

.PHONY: db-apply
db-apply:
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
