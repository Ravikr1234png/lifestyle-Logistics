FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/favorite-service-*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
