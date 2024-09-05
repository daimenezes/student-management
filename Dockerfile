FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . /app

RUN ./gradlew build

FROM gcr.io/distroless/java17-debian11

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]