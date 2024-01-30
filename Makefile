PROTOC_GEN_JAVA_VERSION=1.61.0
PROTOC_GEN_JAVA_PATH=tmp/bin/protoc-gen-grpc-java-${PROTOC_GEN_JAVA_VERSION}.exe

.PHONY: build
build:
	./gradlew build -x test

.PHONY: test
test:
	./gradlew test
	./gradlew jacocoTestReport

.PHONY: lint
lint:
	./gradlew spotlessCheck

.PHONY: format
format:
	./gradlew spotlessApply

.PHONY: install-protoc-gen-plugin
install-protoc-gen-plugin:
	./gradlew :protoc-gen-java-gsync-server:build
	chmod +x ./plugin/protoc-gen-java-gsync-server/build/scripts/protoc-gen-java-gsync-server
	mkdir -p tmp/bin
	if [ ! -f ${PROTOC_GEN_JAVA_PATH} ]; then \
		wget https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/${PROTOC_GEN_JAVA_VERSION}/protoc-gen-grpc-java-${PROTOC_GEN_JAVA_VERSION}-osx-x86_64.exe -O ${PROTOC_GEN_JAVA_PATH}; \
		chmod +x ${PROTOC_GEN_JAVA_PATH}; \
	fi

.PHONY: codegen
codegen:
	rm -rf protobuf
	mkdir -p protobuf/src/main/java
	find ./schema/protobuf -name "*.proto" | xargs -I {} protoc \
		-I=schema/protobuf \
		--plugin=protoc-gen-grpc-java=${PROTOC_GEN_JAVA_PATH} \
		--plugin=protoc-gen-java-gsync-server=./plugin/protoc-gen-java-gsync-server/protoc-gen-java-gsync-server \
		--java_out=protobuf/src/main/java \
		--java-gsync-server_out=protobuf/src/main/java \
		--grpc-java_out=protobuf/src/main/java {}
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

.PHONY: run-battle-server
run-battle-server:
	kubectl create -f ./infra/k8s/battle-server.yaml
