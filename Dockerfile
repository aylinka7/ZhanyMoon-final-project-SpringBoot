# Этап 1: Сборка JAR с Gradle (JDK 17)
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Копируй Gradle wrapper и конфиги сначала (для кэша)
COPY gradle ./gradle
COPY gradlew .
COPY gradle.properties .
COPY settings.gradle .
COPY build.gradle .

# Копируй исходники
COPY src ./src

# Собирай JAR (без тестов, чтобы быстрее)
RUN ./gradlew clean build -x test --no-daemon

# Этап 2: Запуск JAR в лёгком JDK 17 (Alpine — маленький и быстрый)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]