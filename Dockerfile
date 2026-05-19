FROM eclipse-temurin:17-jdk-alpine
COPY "./target/futbol-app-1.jar" "app.jar"
EXPOSE 8120