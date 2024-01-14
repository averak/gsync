PROTOC_GEN_JAVA_VERSION=1.61.0
PROTOC_GEN_JAVA_PATH=tmp/bin/protoc-gen-grpc-java.exe

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

.PHONY: install-protoc-gen-plugin
install-protoc-gen-plugin:
	mkdir -p tmp/bin
	wget https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/${PROTOC_GEN_JAVA_VERSION}/protoc-gen-grpc-java-${PROTOC_GEN_JAVA_VERSION}-osx-x86_64.exe -O ${PROTOC_GEN_JAVA_PATH}
	chmod +x ${PROTOC_GEN_JAVA_PATH}

.PHONY: codegen
codegen:
	rm -rf protobuf
	mkdir -p protobuf/src/main/java
	find ./schema/protobuf -name "*.proto" | xargs -I {} protoc \
		-I=schema/protobuf \
		--plugin=protoc-gen-grpc-java=${PROTOC_GEN_JAVA_PATH} \
		--java_out=protobuf/src/main/java \
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
