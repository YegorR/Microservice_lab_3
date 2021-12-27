# OpenJDK для запуска JAVA-приложений
FROM openjdk:8

WORKDIR /app

# Копировать всё содерживое проекта в 
COPY . .

# Установка Gradle и компиляция проекта
RUN ["./gradlew", "build"]

# Используется порт 8092
EXPOSE 8092

# Запуск
CMD ["./gradlew", "bootRun"]



