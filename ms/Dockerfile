# OpenJDK для запуска JAVA-приложений
FROM openjdk:8

# Копировать всё содерживое проекта в 
COPY . .

# Установка Gradle и компиляция проекта
RUN ["./gradlew", "build"]

# Используется порт 8091
EXPOSE 8091

# Запуск
CMD ["./gradlew", "bootRun"]



