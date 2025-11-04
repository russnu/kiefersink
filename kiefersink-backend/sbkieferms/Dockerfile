FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/sbkieferms-1.0-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]