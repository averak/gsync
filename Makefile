GOOGLE_CLOUD_PROJECT_ID="gsync"

.PHONY: init_spanner_emulator
init_spanner_emulator:
	docker-compose up -d
	gcloud config configurations create emulator || true
	gcloud config set auth/disable_credentials true
	gcloud config set project ${GOOGLE_CLOUD_PROJECT_ID}
	gcloud config set api_endpoint_overrides/spanner http://localhost:9020/
	gcloud spanner instances create gsync --config=emulator-config --description="gsync" --nodes=1
	gcloud spanner databases create gsync --instance=gsync

.PHONY: test
test:
	./gradlew test

.PHONY: lint
lint:
	./gradlew spotlessCheck

.PHONY: format
format:
	./gradlew spotlessApply

.PHONY: check_dependencies
check_dependencies:
	./gradlew dependencyUpdates -Drevision=release
