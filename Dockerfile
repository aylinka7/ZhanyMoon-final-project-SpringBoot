# Этап 1: Сборка JAR с Gradle (JDK 17)
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Этап 2: Запуск JAR в лёгком Alpine-based JDK 17 (стабильный, без slim-проблем)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]