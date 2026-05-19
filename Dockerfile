FROM openjdk:17-jdk-slim
COPY "./target/futbol-app-1.jar" "app.jar"
EXPOSE 8120