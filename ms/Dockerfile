# OpenJDK для запуска JAVA-приложений
FROM openjdk:8

COPY . .

# Установка Gradle и компиляция проекта
RUN ["./gradlew", "build"]

EXPOSE 8091

# Запуск
CMD ["./gradlew", "bootRun"]



