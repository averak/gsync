GCP_PROJECT_ID=gsync
SPANNER_INSTANCE_ID=gsync
SPANNER_DATABASE_ID=gsync

.PHONY: build
build:
	./gradlew build -x test

.PHONY: build_native
build_native:
	./gradlew build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true

.PHONY: init_spanner_emulator
init_spanner_emulator:
	gcloud config configurations create emulator || echo "already exists."
	gcloud config set auth/disable_credentials true
	gcloud config set project $(GCP_PROJECT_ID)
	gcloud config set api_endpoint_overrides/spanner http://localhost:9020/
	gcloud spanner instances create $(SPANNER_INSTANCE_ID) --config=emulator-config --description="test instance" --nodes=1  || echo "already exists."
	gcloud spanner databases create $(SPANNER_DATABASE_ID) --instance=$(SPANNER_INSTANCE_ID)  || echo "already exists."

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
