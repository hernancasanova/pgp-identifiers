FROM eclipse-temurin:11-jdk

WORKDIR /app

COPY target/pgp-identifiers-1.0.jar app.jar

EXPOSE 8007

ENTRYPOINT ["java", "-jar", "app.jar"]