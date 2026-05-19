FROM openjdk:17
COPY "./target/futbol-app-1.jar" "app.jar"
EXPOSE 8120
ENTRYPOINT [ "java", "-jar", "app.jar" ]