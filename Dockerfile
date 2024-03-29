FROM public.ecr.aws/docker/library/amazoncorretto:21 AS build-stage

WORKDIR /app
COPY . /app/

RUN yum install -y git
RUN ./gradlew bootJar

FROM public.ecr.aws/docker/library/amazoncorretto:21

COPY --from=build-stage /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
