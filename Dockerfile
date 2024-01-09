FROM public.ecr.aws/docker/library/amazoncorretto:17 as build-stage

WORKDIR /app
COPY . /app/

RUN yum install -y git
RUN ./gradlew build -x test

FROM public.ecr.aws/docker/library/amazoncorretto:17

COPY --from=build-stage /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
