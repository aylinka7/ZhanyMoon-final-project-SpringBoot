# Этап 1: Сборка JAR
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Копируем всё сразу — проще и надёжнее
COPY . .

# Собираем JAR
RUN ./gradlew clean build -x test --no-daemon

# Этап 2: Запуск
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]